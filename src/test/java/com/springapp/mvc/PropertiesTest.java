package com.springapp.mvc;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kjayanta on 01/07/15.
 */
public class PropertiesTest {


    @Test
    public void propertyTest() throws Exception{

        Properties properties = new Properties();
        System.out.println(properties.databaseValue());
    }

}