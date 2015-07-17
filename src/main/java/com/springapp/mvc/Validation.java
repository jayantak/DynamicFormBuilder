package com.springapp.mvc;


import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class Validation {

    JSONOperations jsonOperations = new JSONOperations();
    MongoOperations mongoOperations ;

    public int check(Map userData) throws IOException {
        int i;
        JSONOperations jops1 = new JSONOperations();
        JSONObject input = new JSONObject();

        System.out.println("input is" + input);
        JSONObject formStructure = mongoOperations.formStructure();
        System.out.println(formStructure);



        System.out.println(userData);

        Collection keys= userData.keySet();
        Collection values =userData.values();

        for(i=0;i<keys.size();i++){



        }

        System.out.println(keys);
        System.out.println(values);
        return 1;
    }

}
