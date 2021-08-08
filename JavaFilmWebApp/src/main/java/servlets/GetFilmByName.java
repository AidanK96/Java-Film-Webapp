package servlets;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Models.Film;
import Models.FilmDAO;


//Creates a websevlet with the address /getfilmbytitle
@WebServlet(name = "GetFilmByNameServlet", urlPatterns = "/getfilmbytitle")

public class GetFilmByName extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	
public void doGet(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
	
	//takes the parameter format & film title from the user, depending on the format entered the user is served a different JSP page
	//Creates new dao object for the jsp pages to use
	String format = request.getParameter("format");
	String title = request.getParameter("title");
	Gson gson = new Gson();
	FilmDAO dao = new FilmDAO();
	ArrayList<Film> films = dao.getFilmByName(title);
	request.setAttribute("films",films);
	String json = gson.toJson(films);
	
	//the jsp pages, choice depends on user input
	if ("json".equals(format)) {
			response.setContentType("text/javascript");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("src/main/webapp/WEB-INF/JSP/FilmByTitle/FilmByTitleJSON.jsp");
			dispatcher.forward(request, response);
	}
	 if ("xml".equals(format)) {
		 response.setContentType("text/xml");
		 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/JSP/FilmByTitle/FilmByTitleXML.jsp");
		 dispatcher.forward(request, response);
	}
	
	
	 if ("string".equals(format)) {
		 response.setContentType("text/plain");
		 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/JSP/FilmByTitle/FilmByTitleString.jsp");
		 dispatcher.forward(request, response);
	}
	 if ("table".equals(format)) {
		 response.setContentType("text/javascript");
		 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/JSP/FilmByTitle/FilmByTitleTable.jsp");
		 dispatcher.forward(request, response);
	 }
	 	 
}  
}

 
