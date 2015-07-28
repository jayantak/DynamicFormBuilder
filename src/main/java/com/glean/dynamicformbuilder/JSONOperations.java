package com.glean.dynamicformbuilder;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.parser.JSONParser;

public class JSONOperations {

    String in;
    String out;

    public JSONOperations(String in, String out){
        this.in = in;
        this.out = out;
    }

    public void JSONWrite(JSONObject obj) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File JSONFile = new File(classLoader.getResource(out).getFile());
        FileWriter JSONFileWriter = new FileWriter(JSONFile);
        try {
            JSONFileWriter.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();

        }
    finally {
            JSONFileWriter.flush();
           JSONFileWriter.close();
        }
    }

    public JSONObject JSONRead() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File JSONFile = new File(classLoader.getResource(in).getFile());
        JSONParser parser = new JSONParser();
        JSONObject JSONObj = new JSONObject();
        Object ReadObject = new Object();
        try {
            ReadObject = parser.parse(new FileReader(JSONFile));
            JSONObj = (JSONObject) ReadObject;
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return JSONObj;
    }

}
