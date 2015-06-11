package com.springapp.mvc;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.parser.JSONParser;

public class JOps {

    public void JSONWrite(JSONObject obj, String fileName) throws IOException
    {
        ClassLoader classLoader = getClass().getClassLoader();
        File tfile1 = new File(classLoader.getResource(fileName).getFile());
        FileWriter file = new FileWriter(tfile1);
        try {
            file.write(obj.toJSONString());

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            file.flush();
            file.close();
        }
    }

    public JSONObject JSONRead(String fileName) throws IOException
    {
        ClassLoader classLoader = getClass().getClassLoader();
        File tfile1 = new File(classLoader.getResource(fileName).getFile());
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();
        Object obj1 = new Object();
        try {
            obj1 = parser.parse(new FileReader(tfile1));
            obj = (JSONObject) obj1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return obj;
    }

}
