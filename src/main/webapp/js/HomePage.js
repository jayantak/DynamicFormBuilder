define(['jquery', 'HomePageFunctions', 'FormAdd'], function ($, Page, Form) {

    $(document).ready(function() {$(function() {

        $.getJSON('formData', Form.createForm);

       //$('input[type="submit"]').attr('disabled','disabled');


        $("#SubmitButton").click(Page.submitForm);

    })
    });
});

