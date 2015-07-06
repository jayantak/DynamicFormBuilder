<html>
<head>
  <title>Form Submitted</title>
  <link href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,300,600,700' rel='stylesheet' type='text/css'>
  <link href="css/bootstrapTheme.css" rel="stylesheet">
  <link href="css/custom.css" rel="stylesheet">
  <link href="css/owl.carousel.css" rel="stylesheet">
  <link href="css/owl.theme.css" rel="stylesheet">
  <link href="css/owl.carousel.css" rel="stylesheet">
  <link href="css/owl.transitions.css" rel="stylesheet">
  <link href="css/prettify.css" rel="stylesheet">
  <link href="css/responsive.css" rel="stylesheet">
  <link rel="stylesheet" href="css/font-awesome.css">
  <link rel="stylesheet" href="css/marketing.css">
  <link rel="stylesheet" href="css/pure-min.css">
  <script type="text/javascript" src="libraries/jquery.min.js"></script>
  <script src="../../libraries/bootstrap-collapse.js"></script>
  <script src="../../libraries/bootstrap-transition.js"></script>
  <script src="../../libraries/bootstrap-tab.js"></script>
  <script src="../../libraries/prettify.js"></script>
  <script src="../../libraries/run_prettify.js"></script>
  <script src="../../libraries/owl.carousel.js"></script>
  <script src="../../libraries/owl.carousel.min.js"></script>
  <script data-main="../../js/mainCurrentForm.js" src="../../libraries/require.js"></script>
</head>
<body>
<div class="header">
  <div class="home-menu pure-menu pure-menu-horizontal pure-menu-fixed">
    <a class="pure-menu-heading">Dynamic Form Builder</a>

      <ul class="pure-menu-list">
          <li class="pure-menu-item"><a href="/" class="pure-menu-link">Back To Home</a>
          </li>
      </ul>
  </div>
</div>
<br><br><br>
<div class="content">
    <div class="l-box-lrg pure-u-1 pure-u-md-2-5">
    <div>
        <legend>List of existing forms</legend>
    </div>
  <div>
    <table id = "FormTable" class = "pure-table pure-table-striped">
      <thead>
        <tr>
          <th>Current Forms</th>
        </tr>
      </thead>
      <tbody id="formList">

      </tbody>
    </table>
  </div>
    <div>
        <br>
        <br>
        <button id="CreateForm" class="pure-button pure-button-primary">Create New Form</button>
    </div>

</div>
</div>
</body>
</html>