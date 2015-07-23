package com.dfb;


import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class Validation {

    JSONOperations jsonOperations = new JSONOperations("dataFields.json", "dataOut.json");
    MongoOperations mongoOperations ;

    public int check(Map userData) throws IOException {
        int i;
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
