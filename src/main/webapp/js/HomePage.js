define(function (require) {

    var Page = require('./HomePageFunctions');

    $(document).ready(function() {$(function() {

        $.getJSON('formData', Page.createForm);

        $("#SubmitButton").click(Page.submitForm);

    })
    });
});