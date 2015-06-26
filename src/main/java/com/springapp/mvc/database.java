package com.springapp.mvc;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class database {

    Statement statement = null;
    Statement statement1 = null;
    Connection connection = null;
    ResultSet resultSet = null;
    ResultSet resultSet1 = null;

    String databaseName = "formFlight";
    String userName = "root";
    String password = "";
    String tableName = "people";

    public JSONObject readFields() throws IOException{

        JSONObject pageFields = new JSONObject();
        JSONObject formFields = new JSONObject();
        JSONObject field = new JSONObject();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName,userName,password);
            statement = connection.createStatement();

            resultSet = statement.executeQuery("select * from INFORMATION_SCHEMA.COLUMNS WHERE table_name='" + tableName+ "'");

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
        finally{
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(statement != null){
                    connection.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        return pageFields;
    }

    public void writeValues(Map input) throws IOException{

        String query = "INSERT into people (%s) VALUES ('%s');";

        Collection keys= input.keySet();
        Collection values = input.values();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName,userName,password);
            statement = connection.createStatement();

            String columnsQuery = keys.stream().reduce((key1, key2) -> key1 + "," + key2).get().toString();

            String valuesQuery = values.stream().reduce((value1, value2) -> value1 + "','" + value2).get().toString();

            query = String.format(query, columnsQuery, valuesQuery);

            statement.executeUpdate(query);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(statement != null){
                    connection.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }

    }

    public JSONObject readValues() throws IOException{

        JSONObject pageFields = new JSONObject();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName,userName,password);
            statement = connection.createStatement();
            statement1 = connection.createStatement();

            resultSet1 = statement1.executeQuery("SELECT * FROM " + tableName+ " ORDER BY Time_Of_Entry DESC LIMIT 1;");
            resultSet = statement.executeQuery("select * from INFORMATION_SCHEMA.COLUMNS WHERE table_name='" + tableName+ "'");

            resultSet1.next();
            while(resultSet.next()){

                String field = resultSet.getString("COLUMN_NAME");
                String type = resultSet.getString("COLUMN_COMMENT");
                String value = resultSet1.getString(field);

                if(type.equals("timestamp")){
                    continue;
                }

                pageFields.put(field, value);


            }

            return pageFields;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(statement != null){
                    connection.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }

        return pageFields;
    }
}
