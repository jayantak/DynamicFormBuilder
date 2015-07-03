package com.springapp.mvc;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/")
public class FormController {

    @Value("${mysql.uri}") private String uri;
    @Value("${mysql.database}") private String databaseName;
    @Value("${mysql.userName}") private String userName;
    @Value("${mysql.password}") private String password;
    @Value("${mysql.tableName}") private String tableName;
    @Value("${data.source}") private String source;
    @Value("${json.in}") private String jsonFields;
    @Value("${json.out}") private String jsonOut;

    DatabaseOperations db;
    JSONOperations jsonOperations = new JSONOperations();



    @RequestMapping(method = RequestMethod.GET)
	public String frontPage() throws IOException {

        db = new DatabaseOperations(uri, databaseName, userName, password, tableName);
        return "HomePage";
	}

    @RequestMapping(method = RequestMethod.GET, value = "/newForm")
    public String newForm() throws Exception {

        return "HomePage";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/createForm")
    public String createForm() throws Exception {
        return "createForm";
    }

	@RequestMapping(method = RequestMethod.GET, value = "/formData")
	public ResponseEntity fetchFormData() throws IOException{

        JSONObject input = null;
        if(source.equals("json")) {
            input = jsonOperations.JSONRead(jsonFields);
        }
        else if(source.equals("mysql")) {
            input = db.readFields();
        }
		return new ResponseEntity(input.toString(), HttpStatus.OK);
	}

    @RequestMapping(method = RequestMethod.GET, value = "/dataOut")
    public ResponseEntity fetchFormOutput() throws IOException{

        JSONObject input = null;
        if(source.equals("json")) {
            input = jsonOperations.JSONRead(jsonOut);
        }
        else if(source.equals("mysql")) {
            input = db.readValues();
        }
        return new ResponseEntity(input.toString(), HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, value = "/sendForm", consumes = "application/json", produces = "application/json")
	public ResponseEntity giveData(@RequestParam Map userData) throws IOException {

        if(source.equals("json")) {
            JSONObject JSONoutput = new JSONObject(userData);
            System.out.println(JSONoutput.toJSONString());
            jsonOperations.JSONWrite(JSONoutput, jsonOut);
        }
        else if(source.equals("mysql")) {
            db.writeValues(userData);
        }
        return new ResponseEntity(HttpStatus.CREATED);
	}

    @RequestMapping(method = RequestMethod.POST, value = "/sendFields"/*, consumes = "application/json", produces = "application/json"*/)
    public ResponseEntity createForm(@RequestParam /*Map<String, Map<String, String>>*/ String param1) throws IOException {
        System.out.println(param1);
        JSONObject JSONoutput = new JSONObject();
        Object output;
        JSONOperations jops1 = new JSONOperations();
        JSONParser parser = new JSONParser();

        if(source.equals("json")) {
//            JSONObject JSONoutput = new JSONObject(userData);
//            System.out.println("this is JSON " + JSONoutput.toJSONString());
//            jsonOperations.JSONWrite(JSONoutput, jsonFields);
            try{
                output =  parser.parse(param1);
                JSONoutput=(JSONObject) output;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            jops1.JSONWrite(JSONoutput, "dataFields.json");

        }
        else if(source.equals("mysql")) {
            //db.writeValues(userData);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value="/formsubmitted")
    public String formSubmitted() throws IOException {

        return "formsubmitted";
    }
}