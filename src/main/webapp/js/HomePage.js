define(function (require) {

    var Page = require('./HomePageFunctions');
    var Form = require('./FormAdd');

    $(document).ready(function() {$(function() {

        $.getJSON('formData', Form.createForm);

        $("#SubmitButton").click(Page.submitForm);

    })
    });
});

