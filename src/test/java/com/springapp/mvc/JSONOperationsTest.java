package com.springapp.mvc;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.*;

public class JSONOperationsTest {

    @Test
    public void testJSONWrite() throws Exception {

        JSONOperations jops1 = new JSONOperations();

        JSONParser parser = new JSONParser();


        JSONObject json1 = new JSONObject();
        json1.put("Field 1", "Value1");
        json1.put("Field 2", "Value2");
        ClassLoader classLoader = getClass().getClassLoader();
        File tfile1 = new File(classLoader.getResource("tfile1.txt").getFile());

        jops1.JSONWrite(json1,"tfile1.txt");

        JSONObject obj = new JSONObject();
        Object obj1;
        try {
            obj1 = parser.parse(new FileReader(tfile1));
            obj = (JSONObject) obj1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(json1, obj);
    }

    @Test
    public void testJSONRead() throws Exception {

        JSONOperations jops1 = new JSONOperations();

        JSONParser parser = new JSONParser();


        JSONObject json1 = new JSONObject();
        json1.put("Field 3", "Value3");
        json1.put("Field 3", "Value4");
        ClassLoader classLoader = getClass().getClassLoader();
        File tfile1 = new File(classLoader.getResource("tfile2.txt").getFile());

        FileWriter file = new FileWriter(tfile1);
        try {
            file.write(json1.toJSONString());

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            file.flush();
            file.close();
        }

        JSONObject obj = new JSONObject();
        Object obj1;
        try {
            obj1 = parser.parse(new FileReader(tfile1));
            obj = (JSONObject) obj1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        JSONObject objj = new JSONObject();
        Object obj11;
        try {
            obj11 = parser.parse(new FileReader(tfile1));
            objj = (JSONObject) obj11;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        assertEquals(objj,obj);
    }
}