package com.springapp.mvc;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/")
public class FormController {


    private final database db = new database("tests", "jayanta", "lego", "people");

    @RequestMapping(method = RequestMethod.GET)
	public String frontPage() throws IOException {

        return "HomePage";
	}


	@RequestMapping(method = RequestMethod.GET, value = "/formData")
	public ResponseEntity fetchFormData() throws IOException{
        JSONObject input = db.readFields();

		return new ResponseEntity(input.toString(), HttpStatus.OK);
	}

    @RequestMapping(method = RequestMethod.GET, value = "/dataOut")
    public ResponseEntity fetchFormOutput() throws IOException{

        JSONObject input = db.readValues();
        return new ResponseEntity(input.toString(), HttpStatus.OK);
    }


	@RequestMapping(method = RequestMethod.POST, value = "/sendForm", consumes = "application/json", produces = "application/json")
	public ResponseEntity giveData(@RequestParam Map<String, String> param1) throws IOException {
        db.writeValues(param1);

        return new ResponseEntity(HttpStatus.CREATED);
	}

    @RequestMapping(method = RequestMethod.GET, value="/formsubmitted")
    public String formSubmitted(ModelMap model) throws IOException {

        return "formsubmitted";
    }


}