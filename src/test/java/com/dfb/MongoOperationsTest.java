package com.dfb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.bson.BSONObject;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class MongoOperationsTest {

    private MongoOperations db;

    @Before
    public void setUp() throws  Exception{
        db = new MongoOperations("localhost", "27017", "TestDB", "TestColl");
        DBCollection collection = db.getMongoCollection();
        collection.drop();
        BasicDBObject field = new BasicDBObject();
        field.put("type", "text");
        BasicDBObject form = new BasicDBObject();
        form.put("Test1", field);
        form.put("Test2", field);
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("form", form);
        basicDBObject.put("formName", "TestCollection");
        collection.insert(basicDBObject);
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
        BasicDBObject field = new BasicDBObject();
        JSONObject jsonObject = new JSONObject();
        field.put("type", "text");
        BasicDBObject form = new BasicDBObject();
        form.put("Test1", field);
        form.put("Test2", field);
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("form", form);
        basicDBObject.put("formName", "TestCollection");
        jsonObject.putAll(basicDBObject.toMap());
        jsonObject.remove("_id");

        assertEquals(jsonObject, db.getFields());
    }

    @Test
    public void testReadValues() throws Exception {
        BasicDBObject entry = new BasicDBObject();
        entry.put("Test1", "a");
        entry.put("Test2", "b");
        db.getMongoCollection().insert(entry);

        JSONObject entry1 = new JSONObject();
        entry1.putAll(entry.toMap());
        entry1.remove("_id");

        JSONObject jsonObject = db.readValues();

        assertEquals(entry1, jsonObject);
    }

    @Test
    public void testWriteValues() throws Exception {
        BasicDBObject entry = new BasicDBObject();

        entry.put("Test1", "a");
        entry.put("Test2", "b");

        Map map = entry.toMap();
        JSONObject expected = new JSONObject();
        expected.putAll(map);
        map.remove("_id");
        db.writeValues(map);

        BasicDBObject query = new BasicDBObject("formName", new BasicDBObject("$exists", false));
        BSONObject bsonObjectActual = db.getMongoCollection().find(query).next();

        JSONObject actual = new JSONObject();
        actual.putAll(bsonObjectActual.toMap());
        actual.remove("_id");

        assertEquals(expected, actual);
    }

}