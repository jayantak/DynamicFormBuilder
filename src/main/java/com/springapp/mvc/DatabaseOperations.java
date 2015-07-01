package com.springapp.mvc;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.sql.*;
import java.util.Collection;
import java.util.Map;

@PropertySource("classpath:application.properties")
public class DatabaseOperations {

    String URI;
    String databaseName;
    String userName ;
    String password ;
    String tableName ;

    public DatabaseOperations(String URI, String databaseName, String userName, String password, String tableName) {
        this.URI = URI;
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
            connection = DriverManager.getConnection(URI + databaseName, userName, password);
            statement = connection.createStatement();

            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName);

            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return statement;
    }

    public JSONObject readFields() throws IOException{

        JSONObject pageFields = new JSONObject();
        JSONObject formFields = new JSONObject();
        JSONArray options = new JSONArray();
        JSONObject field = new JSONObject();

        try{

            ResultSet resultSet = getStatement().executeQuery("select * from INFORMATION_SCHEMA.COLUMNS WHERE table_name='" + tableName + "'");

            while(resultSet.next()){

                field = new JSONObject();

                String type = resultSet.getString("COLUMN_COMMENT");
                String name = resultSet.getString("COLUMN_NAME");

                if(type.equals("timestamp")){
                    continue;
                }

                if(type.equals("radio")){
                    ResultSet resultSet1 = getStatement().executeQuery("SELECT DISTINCT SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING(COLUMN_TYPE, 7, LENGTH(COLUMN_TYPE) - 8), \"','\", 1 + units.i + tens.i * 10) , \"','\", -1) AS abc\n" +
                            "FROM INFORMATION_SCHEMA.COLUMNS\n" +
                            "CROSS JOIN (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) units\n" +
                            "CROSS JOIN (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) tens\n" +
                            "WHERE TABLE_NAME = '"+tableName+"' AND COLUMN_NAME = '"+ name + "';");
                    while(resultSet1.next()){
                        String value = resultSet1.getString(1);
                        options.add(value);
                    }
                    field.put("options", options);
                }

                field.put("type", type);

                formFields.put(name, field);
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
