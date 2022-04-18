<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <form method="post" name="form" action="${pageContext.request.contextPath}/chat">
            <input name="encodedJsonMessage" type="text">
            <input name="Submit" type="submit">
        </form>
    </div>

</body>
</html>
