package servlets;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Film;
import Models.FilmDAO;

//Creates a websevlet with the address /getfilmbyid
@WebServlet (name = "GetFilmByIDServlet", urlPatterns = "/getfilmbyid")

public class GetFilmByID extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	
public void doGet(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
	//creates the object to send to the jsp
	FilmDAO dao = new FilmDAO();
	Integer filmid =null;
	filmid = Integer.parseInt(request.getParameter("filmid"));
	Film films = dao.getFilmByID(filmid);
	request.setAttribute("films",films);
	String format = request.getParameter("format");

	//If table is the format display the FilmByIDTable.jsp page
	if ("table".equals(format)) {
			response.setContentType("application/javascript");
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/JSP/FilmByID/FilmByIDTable.jsp");
		    dispatcher.forward(request, response);
		}
	
	//if format = json write json object to browser 
	if ("json".equals(format)) {
			response.setContentType("application/javascript");
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/JSP/FilmByID/FilmByIDJSON.jsp");
		    dispatcher.forward(request, response);
	}
	
	//if format = xml write write xml to browser
	if ("xml".equals(format)) {
		response.setContentType("text/xml");
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/JSP/FilmByID/FilmByIDXML.jsp");
	    dispatcher.forward(request, response);
	}
	//if format=string display plain string output
	if ("string".equals(format)) {
		response.setContentType("text/plain");
		 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/JSP/FilmByID/FilmByIDString.jsp");
		 dispatcher.forward(request, response);
	}
  }	
}

	
    


 
