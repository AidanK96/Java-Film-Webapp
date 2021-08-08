package servlets;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.FilmDAO;
@WebServlet(name = "DeleteFilmByIDServlet", urlPatterns = "/deletefilmbyid")

public class DeleteFilm extends HttpServlet{

	private static final long serialVersionUID = 1L;
	   protected void doGet(HttpServletRequest request,
			   HttpServletResponse response) throws ServletException, IOException {
		   
				int filmid= Integer.parseInt(request.getParameter("filmid"));
				request.setAttribute("filmid",filmid);
				{
				 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/JSP/DeleteFilm/DeleteFilm.jsp");
				    dispatcher.forward(request, response);
				}
		
	    }
	}
//}
