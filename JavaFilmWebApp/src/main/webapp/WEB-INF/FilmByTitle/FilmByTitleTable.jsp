<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import = "Models.Film"%>
<%@page import = "Models.FilmDAO"%>
<%@page import = "java.io.*" %>
<%@page import = "java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet"
      href="/css/style.css"
      type="text/css"/>
<%
	FilmDAO dao = new FilmDAO();
	String title= request.getParameter("title");
	ArrayList<Film> films = dao.getFilmByName(title);
	request.setAttribute("films",films); 
%>
</head>
<body>
<div align="center">
<table border="1">
  <tr><th class="title">Film By Title</th></tr>
</table>
<table>
<c:forEach items="${films}" var="films">
       <tr>
        <td>Film ID: ${films.id}</td>
        <td>Film Title:  ${films.title}</td>
        <td>Film Year: ${films.year}</td>
        <td>Film Stars: ${films.stars}</td>
        <td>Film Review: ${films.review}</td> 
   	  </tr>
</c:forEach>
</table>
</div>
</body>
</html>
