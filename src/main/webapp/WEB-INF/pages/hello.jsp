<html>
<head>

</head>
<body id ="b">
<div id="div1">
    <h1>Dynamic Form Builder</h1>
    <p id="para">Example</p>
</div>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="../../js/main.js"></script>
<script>
    var info={"Field1":"ValueX"};

    console.log(info);
    var para1 = document.createElement("p");
    var node = document.createTextNode(info.Field1);
    para1.appendChild(node);
    var element = document.getElementById("div1");
    element.appendChild(para1);
</script>
<a href="#" id="clickme">Get JSON Data</a>
</body>
</html>

<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<script>--%>
        <%--function loadXMLDoc()--%>
        <%--{--%>
            <%--var xmlhttp;--%>
            <%--if (window.XMLHttpRequest)--%>
            <%--{// code for IE7+, Firefox, Chrome, Opera, Safari--%>
                <%--xmlhttp=new XMLHttpRequest();--%>
            <%--}--%>
            <%--else--%>
            <%--{// code for IE6, IE5--%>
                <%--xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");--%>
            <%--}--%>
            <%--xmlhttp.onreadystatechange=function()--%>
            <%--{--%>
                <%--if (xmlhttp.readyState==4 && xmlhttp.status==200)--%>
                <%--{--%>
                    <%--document.getElementById("myDiv").innerHTML=xmlhttp.responseText;--%>
                <%--}--%>
            <%--}--%>
            <%--xmlhttp.open("GET","ajax_info.txt",true);--%>
            <%--xmlhttp.send();--%>
        <%--}--%>
    <%--</script>--%>
<%--</head>--%>
<%--<body>--%>

<%--<div id="myDiv"><h2>Let AJAX change this text</h2></div>--%>
<%--<button type="button" onclick="loadXMLDoc()">Change Content</button>--%>

<%--</body>--%>
<%--</html>--%>
