package com.springapp.mvc;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Key;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/")
public class FormController {
	@RequestMapping(method = RequestMethod.GET)
	public String frontPage(ModelMap model) throws IOException {

        return "HomePage";
	}


	@RequestMapping(method = RequestMethod.GET, value = "/formData")
	public ResponseEntity fetchFormData() throws IOException{

//		JSONOperations jops1 = new JSONOperations();
//		JSONObject input1 = jops1.JSONRead("dataFields.json");

        database db = new database();
        JSONObject input2 = db.readFields();

		return new ResponseEntity(input2.toString(), HttpStatus.OK);
	}

    @RequestMapping(method = RequestMethod.GET, value = "/dataOut")
    public ResponseEntity fetchFormOutput() throws IOException{

        JSONOperations jops1 = new JSONOperations();
        JSONObject input = jops1.JSONRead("dataOut.json");

        return new ResponseEntity(input.toString(), HttpStatus.OK);
    }


	@RequestMapping(method = RequestMethod.POST, value = "/sendForm")
	public ResponseEntity giveData(@RequestParam Map<String, String> param1) throws IOException {

        JSONOperations jops1 = new JSONOperations();
        JSONParser parser = new JSONParser();

        JSONObject JSONoutput = new JSONObject();
        Object output;
        System.out.println("param1 is " + param1);

        Set set=param1.keySet();


        Iterator iter = set.iterator();
        while(iter.hasNext())
        {
            String str= (iter.next().toString());
            System.out.println("dsfgag   " + str);
            System.out.println(param1.get(str));
        }

//        try {
//            output =  parser.parse(param1);
//            JSONoutput=(JSONObject) output;
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }

//        jops1.JSONWrite(JSONoutput, "dataOut.json");
//
        database db =  new database();
        db.writeValues1(param1);

        return new ResponseEntity( param1, HttpStatus.CREATED);//Check here

	}

    @RequestMapping(method = RequestMethod.GET, value="/formsubmitted")
    public String formSubmitted(ModelMap model) throws IOException {

        return "formsubmitted";
    }


}