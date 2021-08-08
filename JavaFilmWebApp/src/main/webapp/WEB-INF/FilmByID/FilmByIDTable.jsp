<%@page import = "Models.Film"%>
<%@page import = "Models.FilmDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>FilmID Table</title>
<%
	FilmDAO dao = new FilmDAO();
	Integer filmid =null;
	filmid = Integer.parseInt(request.getParameter("filmid"));
    Film films = dao.getFilmByID(filmid);
    request.setAttribute("films",films); 
%>
</head>
<body>
<div align="center">
<table border="1">
  <tr><th class="title">FilmID</th></tr>
</table>
<p/>
<table>
  <tr>
        <td>Film ID:${films.id} |</td>
        <td>Film Title:${films.title} |</td>
        <td>Film Year:${films.year} |</td>
        <td>Film Stars:${films.stars} |</td>
        <td>Film Review:${films.review} |</td>  
  </tr>
</table>
</div></body></html>