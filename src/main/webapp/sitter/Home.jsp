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
        <script type="text/javascript">
                                function confirmDeletion(form) {
                                    if(window.confirm("Are you sure you want to Delete this Job")){
                                        form.submit();
                                    }
                                }
        </script>
    </head>
    <body>
        <%--
            The HomePage for the sitter will display all the available options
            and a Welcome Message.
      --%>
      <jsp:include page="/header.jsp"/>

      <form class="" action="${pageContext.request.contextPath}/sitter/ShowAllJobs.do" method="post">
            <input type="submit" name="PostJob" value="Show Jobs"/>
      </form>
      <form class="" action="${pageContext.request.contextPath}/sitter/ShowMyApplications.do" method="post">
            <input type="submit" name="ViewApplications" value="My Applications"/>
      </form>
      
      <form class="" action="${pageContext.request.contextPath}/sitter/EditProfile.jsp" method="post">
            <input type="submit" name="EditProfile" value="Edit Profile"/>
      </form>

      <div class="right">
          <form class="" action="${pageContext.request.contextPath}/sitter/CloseAccount.do" method="post">
               <input type="button" name="closeAccount" value="Close Account" onclick="confirmDeletion(this.form, 'close your account')">
          </form>
     </div>


      <jsp:include page="/footer.jsp"/>
    </body>
</html>
