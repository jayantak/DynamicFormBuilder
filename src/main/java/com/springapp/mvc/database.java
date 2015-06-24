package com.springapp.mvc;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.sql.*;
import java.util.Iterator;
import java.util.Set;

public class database {

    Statement statement = null;
    Connection connection = null;
    ResultSet resultSet = null;

    String databaseName = "formFlight";
    String userName = "root";
    String password = "";
    String tableName = "people";

    public JSONObject readFields() throws IOException{

        JSONObject pageFields = new JSONObject();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName,userName,password);
            statement = connection.createStatement();

            resultSet = statement.executeQuery("select * from INFORMATION_SCHEMA.COLUMNS WHERE table_name='" + tableName+ "'");

            JSONObject formFields = new JSONObject();

            while(resultSet.next()){

                JSONObject field = new JSONObject();
                String type = resultSet.getString("COLUMN_COMMENT");
                String name = resultSet.getString("COLUMN_NAME");

                field.put("type", type);

                formFields.put(name,field);

            }

            pageFields.put("form", formFields);
            System.out.println(pageFields.toJSONString());
            return pageFields;
        }
        catch(Exception e){
            e.printStackTrace();
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

    public void writeValues(String ValuesString) throws IOException{

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName,userName,password);
            statement = connection.createStatement();

            JSONParser parser = new JSONParser();
            Object ReadObject = new Object();
            ReadObject = parser.parse(ValuesString);

            JSONObject Values = (JSONObject) ReadObject;

            Set set = Values.keySet();
            Iterator iter = set.iterator();
            String query = "insert into " + tableName + " values(";
            while(iter.hasNext())
            {
                String key = (iter.next()).toString();
                query = query+"'" + Values.get(key) + "'";
                if(iter.hasNext())
                {
                    query = query + ",";
                }
            }
            query = query+ ");";
            System.out.println(query);

            statement.executeUpdate(query);

        }
        catch(Exception e){
            e.printStackTrace();
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

}
