<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="mathtest.User" %>
<%@ page import="mathtest.StoreData" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
</head>
<body>

<%
	Cookie cookie = null;
    String username = "";
	Cookie[] cookies = request.getCookies();
	
	if( cookies != null ) {
	   
	   for (int i = 0; i < cookies.length; i++) {
	      cookie = cookies[i];
	      if (cookie.getName( ).equals("username")) {
	    	  username = cookie.getValue( );
	      }
	   }
	}

    StoreData store = new StoreData();
    User u = store.getUser(username); // get cookie
%>

<form action='register' method='post'>
<input type='hidden' name='csrf' value='<%=u.getId()%>'>
Name     <input                 name='name'>
Password <input type='password' name='pss'>
<input type='submit' value='Remember me'>
</form>

</body>
</html>