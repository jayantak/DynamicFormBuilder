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

    @RequestMapping(method = RequestMethod.GET, value = "/validation")
    public String jsonPage() throws IOException {


        return "jsonPage";
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

    //Check something in the following function

    @RequestMapping(method = RequestMethod.GET, value = "/formData")
    public ResponseEntity fetchFormData() throws IOException {

        JSONObject input = null;
        JSONObject input1 = null;
        switch(source){
            case "mongo":
                input = mongoOperations.getFields();
                break;
            case "mysql":
                input = mySQLOperations.readFields();
                break;
            case "json":
                JSONOperations jops1 = new JSONOperations();
                input1 = jops1.JSONRead("dataFields.json");
                break;

            default:
                throw new IllegalArgumentException("Invalid Source Data type"+ source);
        }


        return new ResponseEntity(input1.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dataOut")
    public ResponseEntity fetchFormOutput() throws IOException{

        JSONObject input = null;
        switch(source){
            case "mongo":
                input = mongoOperations.readValues();
                break;
            case "mysql":
                input = mySQLOperations.readValues();
                break;
            case "json":
                input = jsonOperations.JSONRead(jsonOut);
                break;
            default:
                throw new IllegalArgumentException("Invalid Source Data type"+ source);
        }
        return new ResponseEntity(input.toString(), HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, value = "/sendForm", consumes = "application/json", produces = "application/json")
	public ResponseEntity giveData(@RequestParam Map userData) throws IOException {

        switch(source){
            case "mongo":
                mongoOperations.writeValues(userData);
                break;
            case "mysql":
                mySQLOperations.writeValues(userData);
                break;
            case "json":
                JSONObject JSONoutput = new JSONObject(userData);
                jsonOperations.JSONWrite(JSONoutput, jsonOut);
                break;
            default:
                throw new IllegalArgumentException("Invalid Source Data type"+ source);
        }
        return new ResponseEntity(HttpStatus.OK);
	}

    @RequestMapping(method = RequestMethod.POST, value = "/sendFields")
    public ResponseEntity createFormFields(@RequestParam String fieldData) throws IOException {

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

        switch(source){
            case "mongo":
                mongoOperations.writeForm(result);
                break;
            case "mysql":
                mySQLOperations.writeForm(JSONoutput);
                break;
            case "json":
                jops1.JSONWrite(JSONoutput, "dataFields.json");
                break;
            default:
                throw new IllegalArgumentException("Invalid Source Data type"+ source);
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

        switch(source){
            case "mongo":
                input = mongoOperations.readForms();
                break;
            case "mysql":
                input = mySQLOperations.readForms();
                break;
            case "json":
//              input = jsonOperations.JSONRead(jsonOut);
                break;
            default:
                throw new IllegalArgumentException("Invalid Source Data type"+ source);
        }
        return new ResponseEntity(input.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value="/formpage")
    public String formList() throws IOException {

        return "currentForms";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/formValues")
    public String formValues(@RequestParam(value = "form", required = false, defaultValue = "people") String form) throws Exception {

        mongoOperations.setCollName(form);
        mySQLOperations.setTableName(form);
        return "formValues";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/allFormResponses")
    public ResponseEntity allFormValues() throws IOException {

        JSONArray input = new JSONArray();
        switch(source){
            case "mongo":
                input = mongoOperations.getResponses();
                break;
            case "mysql":
                input = mySQLOperations.getResponses();
                break;
            case "json":
//                input = jsonOperations.JSONRead(jsonFields);
                break;
            default:
                throw new IllegalArgumentException("Invalid Source Data type"+ source);
        }
        return new ResponseEntity(input.toString(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/formFieldNames")
    public ResponseEntity fetchFormFields() throws IOException{

        JSONArray input = new JSONArray();
        switch(source){
            case "mongo":
                input = mongoOperations.getFieldNames();
                break;
            case "mysql":
                input = mySQLOperations.getFieldNames();
                break;
            case "json":
//                input = jsonOperations.JSONRead(jsonFields);
                break;
            default:
                throw new IllegalArgumentException("Invalid Source Data type"+ source);
        }
        return new ResponseEntity(input.toString(), HttpStatus.OK);
    }
}