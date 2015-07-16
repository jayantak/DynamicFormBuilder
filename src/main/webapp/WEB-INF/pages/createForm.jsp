<html>
  <head>
    <title>Create New Form</title>
    <%--<link href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,300,600,700' rel='stylesheet' type='text/css'>--%>
    <%--<link href="css/bootstrapTheme.css" rel="stylesheet">--%>
    <%--<link href="css/custom.css" rel="stylesheet">--%>
    <%--<link href="css/owl.carousel.css" rel="stylesheet">--%>
    <%--<link href="css/owl.theme.css" rel="stylesheet">--%>
    <%--<link href="css/owl.carousel.css" rel="stylesheet">--%>
    <%--<link href="css/owl.transitions.css" rel="stylesheet">--%>
    <%--<link href="css/prettify.css" rel="stylesheet">--%>
    <%--<link href="css/responsive.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="css/font-awesome.css">
    <link rel="stylesheet" href="css/marketing.css">
    <link rel="stylesheet" href="css/pure-min.css">
    <script type="text/javascript" src="libraries/jquery.min.js"></script>
    <script data-main="../../js/mainCreate.js" src="../../libraries/require.js"></script>
  </head>
  <body id ="b">
  <div class="header">
    <div class="home-menu pure-menu pure-menu-horizontal pure-menu-fixed">
      <a class="pure-menu-heading" href="">Dynamic Form Builder</a>
      <ul class="pure-menu-list">
        <li class="pure-menu-item"><a href="/" class="pure-menu-link">Back To Home</a>
        </li>
      </ul>
    </div>
  </div>
  <br><br>
  <div class="content">
    <div class = "pure-g">

      <div class="">
        <div class="l-box-lrg pure-u-1 pure-u-md-2-5">
        <form id = "fullForm" class="pure-form">
          <legend id ="title">Create New Form</legend>
          <label for = "formName">Form Name: </label><input id = "formName" placeholder="Form Name">
            <fieldset id = "newForm" >
            </fieldset>
        </form>
        <button id="PlusButton" class="button-secondary pure-button">+</button><br><br><br>
        <button id="CreateButton" class="pure-button pure-button-primary">CREATE FORM</button>
      </div>
        </div>
    </div>
  </div>

  </body>
</html>
