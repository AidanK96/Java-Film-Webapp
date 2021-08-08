package servlets;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Film;
import Models.FilmDAO;


@WebServlet(name = "APIServlet", urlPatterns = "/api")
public class APIServlet extends HttpServlet {
	FilmDAO dao = new FilmDAO();
	private static final long serialVersionUID = 1L;
	
	//function for retrieving films from the db
	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {

        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream()));
            ArrayList<Film> films = dao.getAllFilms();
            response.setContentType("text/html");
            Gson gson = new Gson();
        	String json = gson.toJson(films);
            out.write(json);
            out.close();

            }catch(Exception e) {
                e.printStackTrace();
                }
            }
	
		
	//api function for inserting new films into the db
	protected void doPost(
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
			PrintWriter out = response.getWriter();
	       
	        response.setContentType("text/html");
	        //read the parameters into the film class
	        Film film = new Gson().fromJson(request.getReader(), Film.class);
	        
	        System.out.println(film);
	        //variables are taken from the film class
	        int id = Integer.valueOf(film.getId());
	        String title = (String) film.getTitle();
	        int year = Integer.valueOf(film.getYear());
	        String director = (String) film.getDirector();
	        String stars = (String) film.getStars();
	        String review = (String) film.getReview();
	  
	       
	        //creating a record from the parameters
	        film  = new Film (id, title, year, director, stars,
	        		review);
	       
	 
	         boolean insert = false;
	            try {
	                dao.insertFilm(film);
	                insert = true;
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	           
	            if(insert) {
	                out.write("New film added");
	            } else {
	                out.write("error");
	            }
	            out.close();
	            }
		
	//api function for updating film records within the db
	protected void doPut(
			HttpServletRequest request,
			HttpServletResponse response) 
					throws IOException, ServletException {
			PrintWriter out = response.getWriter();
			Film film = new Gson().fromJson(request.getReader(), Film.class);
			System.out.println(film);
		    int id = Integer.valueOf(film.getId());
	        String title = (String) film.getTitle();
	        int year = Integer.valueOf(film.getYear());
	        String director = (String) film.getDirector();
	        String stars = (String) film.getStars();
	        String review = (String) film.getReview();

	        film  = new Film (id, title, year, director, stars,
	        		review);
	  
	         boolean inserted = false;
	            try {
	            	//Executes the filmdao update film function 
	                dao.updateFilm(film);
	                inserted = true;
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	                if(inserted) {
		                out.write("film Updated");
		            } else {
		                out.write("error");
		            }
		            out.close();
		            }
	            } 
		
	//api function for deleting films via doDelete 
	protected void doDelete(
			HttpServletRequest request,
			HttpServletResponse response) 
					throws IOException, ServletException {

			String filmid = request.getParameter("filmid");
			Writer out = response.getWriter();
			boolean deleted = false;
			System.out.println(filmid);
			try {
            
				//passes into film id to the delete dao
				deleted = dao.delFilm(Integer.parseInt(filmid));
				deleted=true;
            
			}catch(Exception h) {
				//catches exceptions
				h.printStackTrace();
			}
			//displays  message if done
			if(deleted) {
				out.write("film deleted");
			}else {
				//displays negative message if not done
				out.write("film ID not found");
        }   	
	}	
}