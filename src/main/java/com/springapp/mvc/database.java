package com.springapp.mvc;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
    String password = "lego";
    String tableName = "people";
    public static String [] arr = new String[10];
    public static int ctr;
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

                field.put("type", type);

                formFields.put(name,field);
                System.out.println(formFields);
                arr[ctr++]=name;

            }
            int i;
            ctr--;
            for(i=0;i<ctr;i++)

            pageFields.put("form", formFields);
//            System.out.println("Here123");
//            System.out.println(pageFields);
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

    public void writeValues(String ValuesString) throws IOException{



        try{
            int i,j;
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName,userName,password);
            statement = connection.createStatement();

            JSONParser parser = new JSONParser();
            Object ReadObject = new Object();
            ReadObject = parser.parse(ValuesString);

            JSONObject Values = (JSONObject) ReadObject;

            Set set = Values.keySet();
            Iterator iter = set.iterator();
            String query = "insert into " + tableName + " (";
            while(iter.hasNext())
            {
                String key = (iter.next()).toString();
                System.out.print(key);

                query=query + key;
                if(iter.hasNext())
                {
                    query = query + ",";
                }
            }
            query = query + ") VALUES (";
            Iterator iter1 = set.iterator();
            while(iter1.hasNext())
            {
                String key = (iter1.next()).toString();
                query = query+"'" + Values.get(key) + "'";
                if(iter1.hasNext())
                {
                    query = query + ",";
                }

            }

//            for(i=0;i<ctr;i++)
//            {
//                System.out.print(arr[i]);
//            }
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
