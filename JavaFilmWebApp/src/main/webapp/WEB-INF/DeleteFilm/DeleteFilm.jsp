<%@page import = "Models.Film"%>
<%@page import = "Models.FilmDAO"%>
<%@page import = "java.io.*" %>
<%@page import = "java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%	
	int filmid= Integer.parseInt(request.getParameter("filmid"));
	FilmDAO dao = new FilmDAO();
	boolean deleted = false;
	deleted = dao.delFilm(filmid);
	System.out.println(filmid);
	 if(deleted == true) {
         out.write("Film Deleted");
     }else {
         //displays negative message if not done
         out.write("Film ID not found");
     }
%>
