package com.springapp.mvc;

import org.json.simple.JSONObject;
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

    @Value("${mysql.uri}")
    private String uri;
    @Value("${mysql.database}")
    private String databaseName;
    @Value("${mysql.userName}")
    private String userName;
    @Value("${mysql.password}")
    private String password;
    @Value("${mysql.tableName}")
    private String tableName;

    @Value("${data.source}")
    private String source;

    @Value("${json.in}")
    private String jsonFields;
    @Value("${json.out}")
    private String jsonOut;

    @RequestMapping(method = RequestMethod.GET)
	public String frontPage() throws IOException {

        return "HomePage";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/formData")
	public ResponseEntity fetchFormData() throws IOException{

        JSONObject input = null;
        if(source.equals("json")) {
            JSONOperations jops1 = new JSONOperations();
            input = jops1.JSONRead(jsonFields);
        }
        else if(source.equals("mysql")) {
            DatabaseOperations db = new DatabaseOperations(uri, databaseName, userName, password, tableName);
            input = db.readFields();
        }
		return new ResponseEntity(input.toString(), HttpStatus.OK);
	}

    @RequestMapping(method = RequestMethod.GET, value = "/dataTest")
    public ResponseEntity dataTest(@RequestParam(value = "input", defaultValue = "mysql") String input) throws IOException{

        return new ResponseEntity(input, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dataOut")
    public ResponseEntity fetchFormOutput() throws IOException{

        JSONObject input = null;
        if(source.equals("json")) {
            JSONOperations jops1 = new JSONOperations();
            input = jops1.JSONRead(jsonOut);
        }
        else if(source.equals("mysql")) {
            DatabaseOperations db = new DatabaseOperations(uri, databaseName, userName, password, tableName);
            input = db.readValues();
        }
        return new ResponseEntity(input.toString(), HttpStatus.OK);
    }


	@RequestMapping(method = RequestMethod.POST, value = "/sendForm", consumes = "application/json", produces = "application/json")
	public ResponseEntity giveData(@RequestParam Map userData) throws IOException {

        if(source.equals("json")) {
            JSONOperations jops1 = new JSONOperations();

            JSONObject JSONoutput = new JSONObject(userData);

            jops1.JSONWrite(JSONoutput, jsonOut);
        }
        else if(source.equals("mysql")) {
            DatabaseOperations db = new DatabaseOperations(uri, databaseName, userName, password, tableName);
            db.writeValues(userData);
        }
        return new ResponseEntity(HttpStatus.CREATED);
	}

    @RequestMapping(method = RequestMethod.GET, value="/formsubmitted")
    public String formSubmitted() throws IOException {

        return "formsubmitted";
    }


}