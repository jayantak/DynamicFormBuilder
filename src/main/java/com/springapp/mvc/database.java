package com.springapp.mvc;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class database {

    Statement statement = null;
    Connection connection = null;
    ResultSet resultSet = null;

    String databaseName = "formFlight";
    String userName = "root";
    String password = "";
    String tableName = "people";
    public JSONObject pageFields = new JSONObject();
   public JSONObject formFields = new JSONObject();
    public JSONObject field = new JSONObject();


    public JSONObject readFields() throws IOException{

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName,userName,password);
            statement = connection.createStatement();

            resultSet = statement.executeQuery("select * from INFORMATION_SCHEMA.COLUMNS WHERE table_name='" + tableName+ "'");

            while(resultSet.next()){

                String type = resultSet.getString("COLUMN_COMMENT");
                String name = resultSet.getString("COLUMN_NAME");

                if(name.equals("Time_Of_Entry")){
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

    public void writeValues1(Map input) throws IOException{
        String query = "INSERT into people (%s) VALUES (%a)";

        Set keys= input.keySet();



        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName,userName,password);
            statement = connection.createStatement();

            String columns = keys.stream().reduce((key1, key2) -> key1 + "," + key2).get().toString();
            String values = keys.stream().reduce((key1, key2) -> input.get(key1.toString()) + "," + input.get(key2.toString())).get().toString();

            String.format(query, columns, values);

            Iterator iter3 = keys.iterator();
            while(iter3.hasNext())
            {
                String str= (iter3.next().toString());
                query=query+str;
                if(iter3.hasNext())
                {
                    query=query+",";
                }

            }

            query= query + ") VALUES (";
            Iterator iter4 = keys.iterator();
            while(iter4.hasNext())
            {
                String str= (iter4.next().toString());
                query=query+ "'" + input.get(str) + "'";
                if(iter4.hasNext())
                {
                    query=query+",";
                }
            }
            query= query + ");";
            System.out.println(query);

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

    public JSONObject readEntry() throws IOException{

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName,userName,password);
            statement = connection.createStatement();

            resultSet = statement.executeQuery("select * from INFORMATION_SCHEMA.COLUMNS WHERE table_name='" + tableName+ "'");

            while(resultSet.next()){

                String type = resultSet.getString("COLUMN_COMMENT");
                String name = resultSet.getString("COLUMN_NAME");

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

}
