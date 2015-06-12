package com.springapp.mvc;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class FormController {
	@RequestMapping(method = RequestMethod.GET)
	public String frontPage(ModelMap model) throws IOException {

        return "hello";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/data")
	public ResponseEntity fetchData() throws IOException{

		JSONOperations jops1 = new JSONOperations();
		JSONObject input = jops1.JSONRead("data19.json");

		return new ResponseEntity(input.toString(), HttpStatus.OK);
	}

	@RequestMapping("/handle")
	public ResponseEntity<String> giveData() {

		return new ResponseEntity<String>("Hello World", HttpStatus.CREATED);
	}



}