package com.springapp.mvc;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.parser.JSONParser;

public class JOps {

    public static void JSONWrite(JSONObject obj, File fileName) throws IOException
    {
        FileWriter file = new FileWriter(fileName);
        try {
            file.write(obj.toJSONString());

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            file.flush();
            file.close();
        }
    }

    public static JSONObject JSONRead(String fileName) throws IOException
    {
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();
        Object obj1 = new Object();
        try {
            obj1 = parser.parse(new FileReader(fileName));
            obj = (JSONObject) obj1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return obj;
    }

}
