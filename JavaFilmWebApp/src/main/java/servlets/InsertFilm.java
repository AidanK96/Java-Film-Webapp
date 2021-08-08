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

//Creates a websevlet with the address /insertfilm
@WebServlet(name = "InsertFilmServlet", urlPatterns = "/insertfilm")

public class InsertFilm extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

//doGet for fetching the data from the database
public void doGet(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
	
	  //takes the parameter from the user, if the user enters form they get a form layout instead of having to inert the film via http
	  String format = request.getParameter("format");
	  if ("form".equals(format)) {
			response.setContentType("application/javascript");
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/JSP/InsertFilm/InsertFilmForm.jsp");
		    dispatcher.forward(request, response);
		}
	  
	  //Creates a new film dao, enters the parameters that the user has specified into ths specific fields
	  FilmDAO dao = new FilmDAO();
	  PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream()));
	  int id = Integer.valueOf(request.getParameter("filmid"));
      String title = (String) request.getParameter("title");
      int year = Integer.valueOf(request.getParameter("year"));
      String director = (String) request.getParameter("director");
      String stars = (String) request.getParameter("stars");
      String review = (String) request.getParameter("review");
      Film film = new Gson().fromJson(request.getReader(), Film.class);
      //creates a new film object with these parameters
      film  = new Film (id, title, year, director, stars,
      		review);

      boolean insert = false;
         try {
        	 //uses the FilmDAO command insert film to insert the new film into the DB
             dao.insertFilm(film);
             insert = true;
         } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
        
         if(insert) {
        	 //if a new film is added display a success message, if not display an error
             out.write("New film added");
             
         } else {
             out.write("error");
         }
         out.close();
         }
}

