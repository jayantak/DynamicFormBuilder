package com.springapp.mvc;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.sql.*;

/**
 * Created by kjayanta on 23/06/15.
 */
public class database {

    Statement statement = null;
    Connection connection = null;
    ResultSet resultSet = null;

    public JSONObject readFields() throws IOException{

        JSONObject pageFields = new JSONObject();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/formSchool","root","");
            statement = connection.createStatement();

            resultSet = statement.executeQuery("select * from INFORMATION_SCHEMA.COLUMNS WHERE table_name='records'");

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

        JSONObject pageFields = new JSONObject();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/formSchool","root","");
            statement = connection.createStatement();

            JSONParser parser = new JSONParser();
            Object ReadObject = new Object();
            ReadObject = parser.parse(ValuesString);

            JSONObject Values = (JSONObject) ReadObject;


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
