<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 07.09.2019
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<form class="form" action="hello"
      method="get" enctype="multipart/form-data">
    <label class="btn btn-secondary">
        <input type="radio" name="parser" value="dom" checked/>DOM
    </label>
    <label class="btn btn-secondary">
        <input type="radio" name="parser" value="sax"/>SAX
    </label>
    <label class="btn btn-secondary">
        <input type="radio" name="parser" value="stax"/>STAX
    </label>
    <input type="text" name="file" class="form-control"/>

    <input class="btn btn-primary mt-1" type="submit" value="Upload"/>
</form>
</body>
</html>
