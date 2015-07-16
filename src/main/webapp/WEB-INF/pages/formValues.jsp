<html>
    <head>
        <title>Form</title>
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
        <script data-main="../js/mainFormValues.js" src="../../libraries/require.js"></script>

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
                <div>
                     <legend id = "FormName"></legend>
                     <legend>Data</legend>
                </div>
                <div class="l-box-lrg pure-u-2-5">
                <style scoped>
                        .button-large {
                            font-size: 110%;
                        }
                </style>
                    <button id = "enableFilter" class="pure-button pure-button-primary">Filter &#x25B6</button>
                    <pre class = "code" id = "filterOption">
                     <style scoped>
                         .button-orange {
                             background: rgb(223, 117, 20);
                         }
                         .small {
                             font-size: 75%;
                         }
                     </style>
                     <form id = "filterForm" class="pure-form small">
                     </form>
                     <button class="pure-button button-orange small" id="SubmitFilter" > Submit </button>
                    </pre>
                </div>
                <div>
                    <table id = "DataTable" class = "pure-table pure-table-striped">
                        <thead>
                            <tr id = "fields"></tr>
                        </thead>
                        <tbody id = "data"></tbody>
                    </table>
                </div>
        </div>

    </body>
</html>