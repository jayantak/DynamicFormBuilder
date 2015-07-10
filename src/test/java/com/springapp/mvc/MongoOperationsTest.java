package com.springapp.mvc;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MongoOperationsTest {

    private MongoOperations db;

    @Before
    public void setUp() throws  Exception{
        db = new MongoOperations("localhost", "27017", "TestDB", "TestColl");
        db.setCollName("TestColl");
    }

    @Test
    public void testGetMongoDB() throws Exception {
        DB db1 = db.getMongoDB();
        assertNotNull(db1);
    }

    @Test
    public void testGetMongoCollection() throws Exception {
        DBCollection collection = db.getMongoCollection();
        assertNotNull(collection);
    }

    @Test
    public void testGetFields() throws Exception {

    }

    @Test
    public void testReadValues() throws Exception {

    }

    @Test
    public void testWriteValues() throws Exception {

    }

    @Test
    public void testWriteForm() throws Exception {

    }

    @Test
    public void testReadForms() throws Exception {

    }

    @Test
    public void testGetResponses() throws Exception {

    }

    @Test
    public void testGetFieldNames() throws Exception {

    }
}