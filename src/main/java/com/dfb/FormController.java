package com.dfb;

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

	//region application.properties data
	@Value("${data.source}") private String source;
	@Value("${mysql.uri}") private String mySQLuri;
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
	@Value("${h2.uri}") private String h2uri;
	@Value("${h2.userName}") private String h2userName;
	@Value("${h2.password}") private String h2password;
	@Value("${h2.tableName}") private String h2table;
	//endregion

	MySQLOperations mySQLOperations;
	JSONOperations jsonOperations;
	MongoOperations mongoOperations;
	H2Operations h2Operations;
	Validation validation = new Validation();

	@RequestMapping(method = RequestMethod.GET)
	public String frontPage() throws IOException {

		mySQLOperations = new MySQLOperations(mySQLuri, mysqlDBName, userName, password, tableName);
		mongoOperations = new MongoOperations(mongoHost, mongoPort, mongoDBName, mongoCollName);
		h2Operations = new H2Operations(h2uri, h2userName, h2password, h2table);
		jsonOperations = new JSONOperations(jsonFields, jsonOut);
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
	public ResponseEntity fetchFormData() throws IOException {

		JSONObject input = new JSONObject();
		switch(source){
			case "mongo":
				input = mongoOperations.getFields();
				break;
			case "mysql":
				input = mySQLOperations.readFields();
				break;
			case "json":
				input = jsonOperations.JSONRead();
				break;
			case "h2":
				input = h2Operations.readFields();
				break;
			default:
				throw unknownSource();
		}
		return new ResponseEntity(input.toString(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/dataOut")
	public ResponseEntity fetchFormOutput() throws IOException {

		JSONObject input = new JSONObject();
		switch(source){
			case "mongo":
				input = mongoOperations.readValues();
				break;
			case "mysql":
				input = mySQLOperations.readValues();
				break;
			case "json":
				input = jsonOperations.JSONRead();
				break;
			default:
				throw unknownSource();
		}
		return new ResponseEntity(input.toString(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendForm", consumes = "application/json", produces = "application/json")
	public ResponseEntity giveData(@RequestParam Map userData) throws IOException {

		int b= validation.check(mongoOperations,userData);
		System.out.println(b);

		switch(source){

			case "mongo":
				mongoOperations.writeValues(userData);
				break;
			case "mysql":
				mySQLOperations.writeValues(userData);
				break;
			case "json":
				JSONObject JSONoutput = new JSONObject(userData);
				jsonOperations.JSONWrite(JSONoutput);
				break;
			case "h2":
				h2Operations.writeValues(userData);
				break;
			default:
				throw unknownSource();
		}
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendFields")
	public ResponseEntity createFormFields(@RequestParam String fieldData) throws IOException {

		JSONObject JSONoutput = new JSONObject();
		Object output;
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
				jsonOperations.JSONWrite(JSONoutput);
				break;
			case "h2":
				h2Operations.writeForm(JSONoutput);
				break;
			default:
				throw unknownSource();
		}
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value="/formsubmitted")
	public String formSubmitted() throws IOException {

		return "formsubmitted";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/forms")
	public ResponseEntity fetchFormList() throws IOException {

		JSONArray input= new JSONArray();

		switch(source){
			case "mongo":
				input = mongoOperations.readForms();
				break;
			case "mysql":
				input = mySQLOperations.readForms();
				break;
			case "json":
//              input = jsonOperations.JSONRead();
				break;
			case "h2":
				input = h2Operations.readForms();
				break;
			default:
				throw unknownSource();
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
//                input = jsonOperations.JSONRead();
				break;
			case "h2":
				input = h2Operations.getResponses();
				break;
			default:
				throw unknownSource();
		}
		return new ResponseEntity(input.toString(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/formFieldNames")
	public ResponseEntity fetchFormFields() throws IOException {

		int a;
		JSONArray input = new JSONArray();
		switch(source){
			case "mongo":
				input = mongoOperations.getFieldNames();
				break;
			case "mysql":
				input = mySQLOperations.getFieldNames();
				break;
			case "json":
//                input = jsonOperations.JSONRead();
				break;
			case "h2":
				input = h2Operations.getFieldNames();
				break;
			default:
				throw unknownSource();
		}
		return new ResponseEntity(input.toString(), HttpStatus.OK);
	}

	public IllegalArgumentException unknownSource(){
		return new IllegalArgumentException("Invalid Source Data type "+ source);
	}
}