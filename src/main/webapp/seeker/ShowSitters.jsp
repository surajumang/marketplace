<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn"
       uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title></title>
    </head>
    <body>
    <jsp:include page="/header.jsp"/>
        <p>Show all the sitters based on some criteria</p>

        Shows a List of sitters. A row represents one Sitter.
        <%--
            ShowSitterServlet will pass the request to this page
            and handover the list of Sitters.
            Transfer content from jsp to another jsp in this case.
      --%>
      Do not use it now.
      <form  action="ShowSitters.do" method="post">
          <input type="text" name="emailPattern" value="" placeholder="Enter Email">
          <input type="text" name="zipCode" value="" placeholder="Zip Code">
          <input type="submit" name="" value="Apply Filters">
      </form>

        <table>
            <c:forEach var ="sitter" items="${sitters}">
            <tr>
                <td>${sitter.firstName}</td>
                <td>${sitter.lastName}</td>
                <td>${sitter.email}</td>
                <td>${sitter.experience}</td>
                <td>${sitter.expectedPay}</td>
                <td>${sitter.phone}</td>
            </tr>
            </c:forEach>
        </table>
    </body>
    <jsp:include page="/footer.jsp"/>
</html>