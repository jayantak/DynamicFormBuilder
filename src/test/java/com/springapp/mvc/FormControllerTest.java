package com.springapp.mvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class FormControllerTest {

    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testFrontPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFrontPage1() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testNewForm() throws Exception {
        mockMvc.perform(get("/newForm"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateForm() throws Exception {
        mockMvc.perform(get("/createForm"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFetchFormData() throws Exception {
        mockMvc.perform(get("/formData"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFetchFormOutput() throws Exception {
        mockMvc.perform(get("/dataOut"))
                .andExpect(status().isOk());

    }

    @Test
    public void testFormSubmitted() throws Exception {
        mockMvc.perform(get("/formsubmitted"))
                .andExpect(status().isOk());

    }

    @Test
    public void testFetchFormList() throws Exception {
        mockMvc.perform(get("/forms"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFormList() throws Exception {
        mockMvc.perform(get("/formpage"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFormValues() throws Exception {
        mockMvc.perform(get("/formValues"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAllFormValues() throws Exception {
        mockMvc.perform(get("/allFormResponses"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFetchFormFields() throws Exception {
        mockMvc.perform(get("/formFieldNames"))
                .andExpect(status().isOk());

    }
}