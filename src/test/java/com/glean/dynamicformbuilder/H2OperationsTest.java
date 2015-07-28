package com.glean.dynamicformbuilder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class H2OperationsTest {

    private H2Operations db;
    @Before
    public void setUp() throws  Exception{
        db = new H2Operations("jdbc:h2:mem:cust;MODE=MYSQL", "root","","TESTTABLE");
        db.getStatement().executeUpdate("DROP TABLE IF EXISTS TESTTABLE;");
        db.getStatement().executeUpdate("CREATE TABLE IF NOT EXISTS TESTTABLE(Test1 varchar(20) COMMENT 'text', Test2 varchar(30) COMMENT 'text');");
    }

    @Test
    public void testStatementMustNotBeNull() throws Exception {
        Statement statement = db.getStatement();
        assertNotNull(statement);
    }

    @Test
    public void readFieldsMustRetrieveFieldsSuccessfully() throws Exception{


        JSONObject testJSON = new JSONObject();
        JSONObject testSet = new JSONObject();
        JSONObject testEntry = new JSONObject();

        testEntry.put("type", "text");
        testSet.put("Test1", testEntry);
        testSet.put("Test2", testEntry);
        testJSON.put("form", testSet);

        JSONObject fieldsJSON = db.readFields();

        assertEquals(testJSON, fieldsJSON);
    }

    @Test
    public void queryMustBeSuccessfullyGenerated() throws Exception{
        Map<String,String> map = new HashMap<String,String>() {{
            put("Test1", "y");
            put("Test2", "b");
        }};

        String generatedQuery = db.generateWriteQuery(map);

        assertEquals("INSERT into TESTTABLE (test1,test2) VALUES ('y','b');", generatedQuery);
    }

    @Test
    public void valuesShouldBeWrittenToDb() throws Exception{

        Map<String,String> map = new HashMap<String,String>() {{
            put("Test1", "x");
            put("Test2", "a");
        }};
        Map<String,String> map1 = new HashMap<String,String>() {{
            put("Test1", "y");
            put("Test2", "b");
        }};

        db.writeValues(map);
        db.writeValues(map1);

        ResultSet resultSet = db.getStatement().executeQuery("SELECT * FROM TESTTABLE;");

        resultSet.next();
        String val1= resultSet.getString("Test1");
        String val2 =resultSet.getString("Test2");
        assertEquals("x", val1);
        assertEquals("a", val2);

        resultSet.next();
        val1= resultSet.getString("Test1");
        val2 =resultSet.getString("Test2");
        assertEquals("y", val1);
        assertEquals("b", val2);
    }

    @Test
    public void readValuesTest() throws Exception {

        db.getStatement().executeUpdate("INSERT INTO TESTTABLE(Test1, Test2) VALUES('a','b');");
        db.getStatement().executeUpdate("INSERT INTO TESTTABLE(Test1, Test2) VALUES('s','t');");

        JSONObject newEntry = db.readValues();
        JSONObject testJSON = new JSONObject();
        testJSON.put("Test1","s");
        testJSON.put("Test2", "t");

        assertEquals(testJSON,newEntry);
    }

    @Test
    public void testWriteForm() throws Exception {
        JSONObject jsonObject = new JSONObject();
        JSONObject form = new JSONObject();
        JSONObject field = new JSONObject();
        field.put("type", "text");
        form.put("Field1", field);
        jsonObject.put("form", form);
        jsonObject.put("formName", "Form1");

        db.writeForm(jsonObject);

        ResultSet resultSet = db.getStatement().executeQuery("SHOW TABLES;");
        resultSet.next();
        assertEquals("form1", resultSet.getString(1));
        resultSet = db.getStatement().executeQuery("select * from INFORMATION_SCHEMA.COLUMNS WHERE table_name='Form1';");
        resultSet.last();
        assertEquals("Field1", resultSet.getString("COLUMN_NAME"));
    }

    @Test
    public void testReadForms() throws Exception {
        db.getStatement().executeUpdate("CREATE TABLE IF NOT EXISTS AAAA(Field1 varchar(30));");

        JSONArray jsonArray = db.readForms();

        assertEquals("aaaa", jsonArray.get(0));
    }
}
