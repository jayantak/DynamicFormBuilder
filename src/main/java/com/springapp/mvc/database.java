package com.springapp.mvc;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.sql.*;
import java.util.Collection;
import java.util.Map;

public class database {

    String databaseName;
    String userName ;
    String password ;
    String tableName ;

    public database(String databaseName, String userName, String password, String tableName) {
        this.databaseName = databaseName;
        this.userName = userName;
        this.password = password;
        this.tableName = tableName;
    }

    public Statement getStatement() {

        Connection connection;
        Statement statement = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, userName, password);
            statement = connection.createStatement();

            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return statement;
    }

    public JSONObject readFields() throws IOException{

        JSONObject pageFields = new JSONObject();
        JSONObject formFields = new JSONObject();
        JSONObject field = new JSONObject();

        try{

            ResultSet resultSet = getStatement().executeQuery("select * from INFORMATION_SCHEMA.COLUMNS WHERE table_name='" + tableName + "'");

            while(resultSet.next()){

                String type = resultSet.getString("COLUMN_COMMENT");
                String name = resultSet.getString("COLUMN_NAME");

                if(type.equals("timestamp")){
                    continue;
                }

                field.put("type", type);

                formFields.put(name,field);
            }

            pageFields.put("form", formFields);
            return pageFields;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return pageFields;
    }

    public void writeValues(Map input) throws IOException{
        try{
            getStatement().executeUpdate(generateWriteQuery(input));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String generateWriteQuery(Map input) {

        String query = "INSERT into "+ tableName+ " (%s) VALUES ('%s');";

        Collection keys= input.keySet();
        Collection values = input.values();

        String columnsQuery = keys.stream().reduce((key1, key2) -> key1 + "," + key2).get().toString();
        String valuesQuery = values.stream().reduce((value1, value2) -> value1 + "','" + value2).get().toString();

        query = String.format(query, columnsQuery, valuesQuery);
        return query;
    }

    public JSONObject readValues() throws IOException{

        JSONObject pageFields = new JSONObject();

        try{
            ResultSet valueResultSet = getStatement().executeQuery("SELECT * FROM " + tableName);
            ResultSet fieldResultSet = getStatement().executeQuery("select * from INFORMATION_SCHEMA.COLUMNS WHERE table_name='" + tableName + "'");

            valueResultSet.last();
            while(fieldResultSet.next()) {
                String field = fieldResultSet.getString("COLUMN_NAME");
                String value = valueResultSet.getString(field);

                pageFields.put(field, value);
            }

            return pageFields;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return pageFields;
    }
}
