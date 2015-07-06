package com.springapp.mvc;


import com.mongodb.*;
import org.bson.BSONObject;
import org.json.simple.JSONObject;

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

    public JSONObject getFields(){

        DBCollection dbCollection = getMongoDB().getCollection(collName);
        BasicDBObject query = new BasicDBObject("formName", new BasicDBObject("$exists", true));
        BSONObject bsonObject = dbCollection.find(query).next();
        JSONObject abc = new JSONObject();
        abc.putAll(bsonObject.toMap());
        abc.remove("_id");
        return abc;
    }

    public JSONObject readValues(){

        DBCollection dbCollection = getMongoDB().getCollection(collName);
        BasicDBObject query = new BasicDBObject("formName", new BasicDBObject("$exists", true));
        BSONObject bsonObject = dbCollection.find(query).next();
        JSONObject abc = new JSONObject();
        abc.putAll(bsonObject.toMap());
        abc.remove("_id");
        System.out.println(abc);
        return abc;
    }

}
