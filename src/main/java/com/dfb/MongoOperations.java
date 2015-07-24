package com.dfb;

import com.mongodb.*;
import org.bson.BSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MongoOperations {

    String host;
    String port;
    String databaseName;
    String collName;

    public MongoOperations(String host, String port, String databaseName, String collName){
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.collName = collName;
    }

    public void setCollName(String collName){
        this.collName = collName;
    }

    public DB getMongoDB(){
        try{

            MongoClient mongoClient = new MongoClient(host, Integer.parseInt(port));
            DB db = mongoClient.getDB(databaseName);
            return db;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public DBCollection getMongoCollection(){
        return getMongoDB().getCollection(collName);
    }

    public JSONObject getFields(){

        BasicDBObject query = new BasicDBObject("formName", new BasicDBObject("$exists", true));
        BSONObject bsonObject = getMongoCollection().find(query).next();
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(bsonObject.toMap());
        jsonObject.remove("_id");
        return jsonObject;
    }

    public JSONObject readValues(){

        BasicDBObject query = new BasicDBObject("formName", new BasicDBObject("$exists", false));

        DBCursor cursor = getMongoCollection().find(query);
        int entries = cursor.count();
        for (int i = 1; i < entries; i++) {
            cursor.next();
        }
        BSONObject bsonObject = cursor.next();
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(bsonObject.toMap());
        jsonObject.remove("_id");
        return jsonObject;
    }

    public void writeValues(Map map){

        BasicDBObject basicDBObject = new BasicDBObject(map);
        getMongoCollection().insert(basicDBObject);
    }

    public void writeForm(Map map){
        BasicDBObject basicDBObject = new BasicDBObject(map);
        this.collName = basicDBObject.get("formName").toString();
        getMongoCollection().insert(basicDBObject);
    }

    public JSONArray readForms() {

        JSONArray jsonArray = new JSONArray();
        Set set = getMongoDB().getCollectionNames();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){

            jsonArray.add(iterator.next());
        }
        jsonArray.remove("system.indexes");
        return jsonArray;
    }

    public JSONArray getResponses() {

        BasicDBObject query = new BasicDBObject("formName", new BasicDBObject("$exists", false));

        DBCursor cursor = getMongoCollection().find(query);
        int entries = cursor.count();
        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArrayResponse = new JSONArray();
        for (int i = 1; i <= entries; i++) {
            jsonArrayResponse = new JSONArray();

            BSONObject bsonObject = cursor.next();
            Set set = bsonObject.keySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext()){
                String key = iterator.next().toString();
                if(!key.equals("_id")){
                    String value = (bsonObject.get(key)).toString();
                    jsonArrayResponse.add(value);
                }
            }
            jsonArray.add(jsonArrayResponse);
        }

        return jsonArray;
    }

    public JSONArray getFieldNames() {

        JSONArray jsonArray = new JSONArray();

        BasicDBObject query = new BasicDBObject("formName", new BasicDBObject("$exists", true));
        BSONObject bsonObject = getMongoCollection().find(query).next();
        Object object = bsonObject.get("form");
        bsonObject = (BSONObject) object;
        Set set = bsonObject.keySet();

        Iterator iterator = set.iterator();

        while(iterator.hasNext()){
            jsonArray.add(iterator.next().toString());
        }
        return jsonArray;
    }

    public JSONObject formStructure(){
        BasicDBObject query = new BasicDBObject("formName", new BasicDBObject("$exists", true));
        DBCursor cursor = getMongoCollection().find(query);
        BSONObject bsonObject = cursor.next();
        JSONObject jsonObject = new JSONObject();
        System.out.println("bson object is " + bsonObject);
        jsonObject.putAll(bsonObject.toMap());
        jsonObject.remove("_id");
        System.out.println("form structure1 is " + jsonObject);
        return jsonObject;
    }
}