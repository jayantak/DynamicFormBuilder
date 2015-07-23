package com.dfb;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class H2Operations {

    String URI;
    String userName;
    String password;
    String tableName;

    public H2Operations(String URI, String userName, String password, String tableName) {
        this.URI = URI;
        this.userName = userName;
        this.password = password;
        this.tableName = tableName;
    }

    public Statement getStatement() {

        Connection connection;
        Statement statement = null;

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(URI, userName, password);
            statement = connection.createStatement();

            statement.executeUpdate("CREATE TABLE "+tableName+";");

            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return statement;
    }

    public JSONObject readFields() throws IOException {

        JSONObject pageFields = new JSONObject();
        JSONObject formFields = new JSONObject();
        JSONArray options = new JSONArray();
        JSONObject field = new JSONObject();

        try{

            ResultSet resultSet = getStatement().executeQuery("select * from INFORMATION_SCHEMA.COLUMNS WHERE " +
                    "table_name='" + tableName + "';");

            while(resultSet.next()){

                field = new JSONObject();

                String type = resultSet.getString("COLUMN_COMMENT");
                String name = resultSet.getString("COLUMN_NAME");

                if(type.equals("timestamp")){
                    continue;
                }

                if(type.equals("radio")){
                    ResultSet resultSet1 = getStatement().executeQuery("SELECT DISTINCT SUBSTRING_INDEX(" +
                            "SUBSTRING_INDEX(SUBSTRING(COLUMN_TYPE, 7, LENGTH(COLUMN_TYPE) - 8), \"','\", " +
                            "1 + units.i + tens.i * 10) , \"','\", -1) AS abc\n" +
                            "FROM INFORMATION_SCHEMA.COLUMNS\n" +
                            "CROSS JOIN (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT" +
                            " 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) units\n" +
                            "CROSS JOIN (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT" +
                            " 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) tens\n" +
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
            ResultSet fieldResultSet = getStatement().executeQuery("select * from INFORMATION_SCHEMA.COLUMNS WHERE" +
                    " table_name='" + tableName + "'");

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

    public void writeForm(JSONObject Values) throws IOException {

        JSONObject Form = (JSONObject) Values.get("form");
        String tableName = Values.get("formName").toString();
        this.tableName = tableName;
        Set set = Form.keySet();
        Iterator iterator = set.iterator();
        String key, comment;
        JSONObject attributes;

        String query = "CREATE TABLE " + tableName + "(%s);";
        String fields = "";

        while(iterator.hasNext()) {

            key = iterator.next().toString();
            attributes = (JSONObject) Form.get(key);
            comment = attributes.get("type").toString();

            fields = fields.concat(key + " varchar(30) COMMENT '" + comment + "'");
            if (iterator.hasNext()){
                fields = fields.concat(", ");
            }
        }

        query = String.format(query, fields);
        try{
            getStatement().executeUpdate(query);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void setTableName(String form){

        this.tableName = form;
    }

    public JSONArray readForms() throws IOException{

        JSONArray pageFields = new JSONArray();

        try{
            ResultSet formResultSet = getStatement().executeQuery("SHOW tables;");

            while(formResultSet.next()) {

                String field = formResultSet.getString(1);
                pageFields.add(field);
            }
            return pageFields;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return pageFields;
    }

    public JSONArray getResponses() throws IOException{

        JSONArray data = new JSONArray();

        try{
            ResultSet number = getStatement().executeQuery("SELECT COUNT(*)\n" +
                    "FROM INFORMATION_SCHEMA.COLUMNS\n" +
                    "   WHERE table_name = '"+tableName+"';");
            number.next();
            int columns = number.getInt(1);
            ResultSet resultSet = getStatement().executeQuery("SELECT * FROM "+tableName + ";");

            while(resultSet.next()){
                JSONArray response = new JSONArray();
                for (int i = 1; i <= columns; i++) {
                    String value = resultSet.getString(i);
                    response.add(i-1, value);
                }
                data.add(response);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return data;
    }

    public JSONArray getFieldNames() throws IOException{

        JSONArray data = new JSONArray();

        try{
            ResultSet fieldResultSet = getStatement().executeQuery("select * from INFORMATION_SCHEMA.COLUMNS WHERE" +
                    " table_name='" + tableName + "';");

            while(fieldResultSet.next()){
                data.add(fieldResultSet.getString("COLUMN_NAME"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
