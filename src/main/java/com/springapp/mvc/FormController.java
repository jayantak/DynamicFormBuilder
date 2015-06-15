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

	@RequestMapping(method = RequestMethod.POST, value = "/sendForm")
	public ResponseEntity giveData(@RequestParam String param1) throws IOException {

        JSONOperations jops1 = new JSONOperations();
        JSONParser parser = new JSONParser();

        JSONObject JSONoutput = new JSONObject();
        Object output;

        try {
            output =  parser.parse(param1);
            JSONoutput=(JSONObject) output;
                System.out.println(JSONoutput);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        jops1.JSONWrite(JSONoutput, "dataOut.json");

        System.out.println(JSONoutput);
        System.out.println("Hello World");
        return new ResponseEntity(param1, HttpStatus.OK);
	}
}