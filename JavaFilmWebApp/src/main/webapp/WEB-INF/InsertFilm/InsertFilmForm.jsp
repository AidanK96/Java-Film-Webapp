<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert Film Form</title>
</head>
<body>

	<form method="GET" action="./insertfilm">
		FilmID: <input type="text" name="filmid">
		Film Title: <input type="text" name="title">
		Film Year: <input type="text" name="year">
		Director: <input type="text" name="director">
		Stars: <input type="text" name="stars">
		Review: <input type="text" name="review">
		<input type="submit" value="Submit new film">
	</form>
	
</body>
</html>