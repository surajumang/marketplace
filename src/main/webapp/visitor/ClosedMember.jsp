<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title></title>

        <style type="text/css">
            form {
                text-align: center;
            }
            input {
                width: 100px;
            }
            .right{
                float:right;
            }
        </style>
    </head>
    <body>
        <h2>Your account is INACTIVE, Do you want to activate it <h2>
             <form action="${pageContext.request.contextPath}/visitor/ClosedUserLogin.do" method="post">
                    <input type="submit" name="response" value="Yes">
             </form>
             <form action="${pageContext.request.contextPath}/visitor/index.jsp" method="post">
                    <input type="submit" name="response" value="No">
             </form>
     </body>
</html>


