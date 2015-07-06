package com.springapp.mvc;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
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
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class FormController {

    @Value("${data.source}") private String source;
    @Value("${mysql.uri}") private String uri;
    @Value("${mysql.database}") private String mysqlDBName;
    @Value("${mysql.userName}") private String userName;
    @Value("${mysql.password}") private String password;
    @Value("${mysql.tableName}") private String tableName;
    @Value("${json.in}") private String jsonFields;
    @Value("${json.out}") private String jsonOut;
    @Value("${mongo.host}") private String mongoHost;
    @Value("${mongo.port}") private String mongoPort;
    @Value("${mongo.databaseName}") private String mongoDBName;
    @Value("${mongo.collName}") private String mongoCollName;

    MySQLOperations mySQLOperations;
    JSONOperations jsonOperations = new JSONOperations();
    MongoOperations mongoOperations;

    @RequestMapping(method = RequestMethod.GET)
	public String frontPage() throws IOException {

        mySQLOperations = new MySQLOperations(uri, mysqlDBName, userName, password, tableName);
        mongoOperations = new MongoOperations(mongoHost, mongoPort, mongoDBName, mongoCollName);
        return "currentForms";
	}

    @RequestMapping(method = RequestMethod.GET, value = "/newForm")
    public String newForm(@RequestParam(value = "form", required = false, defaultValue = "people") String form) throws Exception {

        mongoOperations.setCollName(form);
        mySQLOperations.setTableName(form);
        return "NewForm";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/createForm")
    public String createForm() throws Exception {
        return "createForm";
    }

	@RequestMapping(method = RequestMethod.GET, value = "/formData")
	public ResponseEntity fetchFormData() throws IOException{

        JSONObject input = null;
        if(source.equals("mongo")) {
            input = mongoOperations.getFields();
        }
        else if(source.equals("mysql")) {
            input = mySQLOperations.readFields();
        }
        else if(source.equals("json")){
            input = jsonOperations.JSONRead(jsonFields);
        }
		return new ResponseEntity(input.toString(), HttpStatus.OK);
	}

    @RequestMapping(method = RequestMethod.GET, value = "/dataOut")
    public ResponseEntity fetchFormOutput() throws IOException{

        JSONObject input = null;
        if(source.equals("mongo")) {
            input = mongoOperations.readValues();
        }
        else if(source.equals("mysql")) {
            input = mySQLOperations.readValues();
        }
        else if(source.equals("json")) {
            input = jsonOperations.JSONRead(jsonOut);
        }
        return new ResponseEntity(input.toString(), HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, value = "/sendForm", consumes = "application/json", produces = "application/json")
	public ResponseEntity giveData(@RequestParam Map userData) throws IOException {

        if(source.equals("mongo")) {
            mongoOperations.writeValues(userData);
        }
        else if(source.equals("mysql")) {
            mySQLOperations.writeValues(userData);
        }
        else if(source.equals("json")) {
            JSONObject JSONoutput = new JSONObject(userData);
            jsonOperations.JSONWrite(JSONoutput, jsonOut);
        }
        return new ResponseEntity(HttpStatus.CREATED);
	}

    @RequestMapping(method = RequestMethod.POST, value = "/sendFields")
    public ResponseEntity createForm(@RequestParam String fieldData) throws IOException {

        JSONObject JSONoutput = new JSONObject();
        Object output;
        JSONOperations jops1 = new JSONOperations();
        JSONParser parser = new JSONParser();
        try{
            output =  parser.parse(fieldData);
            JSONoutput=(JSONObject) output;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        Map<String,Object> result = new ObjectMapper().readValue(fieldData, Map.class);

        if(source.equals("json")) {
            jops1.JSONWrite(JSONoutput, "dataFields.json");
        }
        else if(source.equals("mysql")) {
            mySQLOperations.writeForm(JSONoutput);
        }
        else if(source.equals("mongo")) {
            mongoOperations.writeForm(result);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value="/formsubmitted")
    public String formSubmitted() throws IOException {

        return "formsubmitted";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/forms")
    public ResponseEntity fetchFormList() throws IOException{

        JSONArray input= new JSONArray();
        if(source.equals("json")) {
           // input = jsonOperations.JSONRead(jsonOut);
        }
        else if(source.equals("mysql")) {
            input = mySQLOperations.readForms();
        }
        else if(source.equals("mongo")){
            input = mongoOperations.readForms();
        }
        return new ResponseEntity(input.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value="/formpage")
    public String formList() throws IOException {

        return "currentForms";
    }

    @RequestMapping(method = RequestMethod.GET, value="/test")
    public ResponseEntity test(@RequestParam Map map) throws IOException {

        mongoOperations.writeValues(map);
        return new ResponseEntity("TEST", HttpStatus.OK);
    }
}