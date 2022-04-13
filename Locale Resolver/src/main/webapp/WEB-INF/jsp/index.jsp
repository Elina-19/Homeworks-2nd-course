<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
  <body>
    <a href='<c:url value="/test/change"/>'>Рус</a> <a href='<c:url value="/test/change"/>}'>Eng</a>
    <h3><s:message code="test"/></h3>
    <h3><s:message code="param.message" arguments="3.14 2.71" argumentSeparator=" "/></h3>
    <h3>${message0}</h3>
    <h3>${message1}</h3>
  </body>
</html>
