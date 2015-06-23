package com.springapp.mvc;
import java.io.IOException;
import java.sql.*;

/**
 * Created by kjayanta on 23/06/15.
 */
public class dbTest {

    Statement statement = null;
    Connection connection = null;
    ResultSet resultSet = null;

    public void readDatabase() throws IOException{

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","","");
            statement = connection.createStatement();

            resultSet = statement.executeQuery("select * from simple");

            while(resultSet.next()){
                String name = resultSet.getString("Name");
                System.out.println(name);
            }
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
