<html>
<head>

</head>
<body id ="b">
<div id="div1">
    <h1>Dynamic Form Builder</h1>
    <p id="para">LOLOLOL</p>
</div>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>
    var info={"Field1":"ValueX"};

    $.getJSON('/Users/kjayanta/Documents/file2.json', function(data) {
        info=data;
    });
    $(document).ready(function(){
        $("p").click(function(){
            $(this).hide();
        });
    });
    console.log(info);
    var para1 = document.createElement("p");
    var node = document.createTextNode(info.Field1);
    para1.appendChild(node);
    var element = document.getElementById("div1");
    element.appendChild(para1);
</script>
</body>
</html>