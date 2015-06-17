define(function (require) {

    console.log("reached 1");

    var Page = require('./HomePageFunctions');

    $(document).ready(function() {$(function() {

        console.log("reached 2");

        $.getJSON('formData', Page.createForm);

        $("#SubmitButton").click(Page.submitForm);

    })
    });
});