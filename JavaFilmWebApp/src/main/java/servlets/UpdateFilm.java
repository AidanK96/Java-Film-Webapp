package servlets;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import com.google.gson.Gson;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Film;
import Models.FilmDAO;

//Creates a websevlet with the address /updatefilm
@WebServlet(name = "UpdateFilmServlet", urlPatterns = "/updatefilm")

public class UpdateFilm extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

//doGet for fetching the data from the database
public void doGet(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
	
		String format = request.getParameter("format");
		if ("form".equals(format)) {
			response.setContentType("application/javascript");
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/JSP/UpdateFilm/UpdateFilmForm.jsp");
		    dispatcher.forward(request, response);
		}
	
	  //Creates a DAO for the film, takes parameters entered and adds them to the object
	  FilmDAO dao = new FilmDAO();
	  PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream()));
	  int id = Integer.valueOf(request.getParameter("filmid"));
      String title = (String) request.getParameter("title");
      int year = Integer.valueOf(request.getParameter("year"));
      String director = (String) request.getParameter("director");
      String stars = (String) request.getParameter("stars");
      String review = (String) request.getParameter("review");
      Film film = new Gson().fromJson(request.getReader(), Film.class);
      
      //Creates a  film using the inputed parameters
      film  = new Film (id, title, year, director, stars,
      		review);
      
      boolean insert = false;
         try {
        	 //invokes the update film command from the FilmDAO class
             dao.updateFilm(film);
             insert = true;
         } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
        
         if(insert) {
        	 //If the film is updated displays film updated
             out.write("Film Updated");
         } else {
        	 //If the film is not updated displays error
             out.write("error");
         }
         out.close();
         }
}

