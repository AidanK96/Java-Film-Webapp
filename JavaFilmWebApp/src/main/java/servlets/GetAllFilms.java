package servlets;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Film;
import Models.FilmDAO;


//Creates a websevlet with the address /getallfilms

@WebServlet(
    name = "GetAllFilmsServlet",
    urlPatterns = {"/getallfilms"}
)
//@WebServlet(name = "GetAllFilmsServlet", urlPatterns = {"/getallfilms"})

public class GetAllFilms extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

//doGet for fetching the data from the database
public void doGet(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
	//creates the object to send to the jsp
	response.getWriter().print("Get all films!\r\n");
	FilmDAO dao = new FilmDAO();
	ArrayList<Film> films = dao.getAllFilms();
	request.setAttribute("films",films);
	//takes the parameter format from the user, depending on the format entered the user is served a different JSP page
	String format = request.getParameter("format");
	//Prints out JSON for each record found within the database
	if ("json".equals(format)) {
		response.setContentType("text/javascript");
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("WEB-INF/AllFilms/AllFilmsJSON.jsp");
	    dispatcher.forward(request, response);
	    }
		
	if ("xml".equals(format)) {
		response.setContentType("text/xml");
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AllFilms/AllFilmsXML.jsp");
	    dispatcher.forward(request, response);
	}
	if ("string".equals(format)) {
		response.setContentType("text/plain");
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AllFilms/AllFilmsString.jsp");
	    dispatcher.forward(request, response);
	}
	if ("table".equals(format)) {
		response.setContentType("text/javascript");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/AllFilms/AllFilmsTable.jsp");
		dispatcher.forward(request, response);
	}
  }
}

 
