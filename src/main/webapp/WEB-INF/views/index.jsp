<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <form name="form" method="post" action="${pageContext.request.contextPath}/">
            <input type="text" name="jsonRequestUserDto" placeholder="input json object">
            <input type="submit" name="Submit">
        </form>
    </div>
</body>
</html>
