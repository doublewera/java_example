<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.concurrent.ThreadLocalRandom" %>
<%@ page import="mathtest.Question" %>
<%@ page import="mathtest.DBsrc" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/css/style.css"%></style>
<title>Insert title here</title>
</head>
<body>

<%
    Question q = new Question();
    //StoreData store = new StoreData();
    DBsrc store = new DBsrc();
    store.saveToTbl(q);
    
   
    Cookie cookie = null;
    Cookie[] cookies = null;
    
    // Get an array of Cookies associated with the this domain
    cookies = request.getCookies();
    
    if( cookies != null ) {
       out.println("<h2> Found Cookies </h2>");
       
       for (int i = 0; i < cookies.length; i++) {
          cookie = cookies[i];
          out.print("Name : " + cookie.getName( ) + ",  ");
          out.print("Value: " + cookie.getValue( )+" <br/>");
       }
    } else {
       out.println("<h2>No cookies founds</h2>");
    }

%>

<form action='check' method='post'>
<input type='hidden' name='questionId' value='<%=q.getId()%>'>
How many is <%=q.getContent()%> ? <input type='number' name='answer'>?
<input type='submit' value='check'>
</form>
</body>
</html>