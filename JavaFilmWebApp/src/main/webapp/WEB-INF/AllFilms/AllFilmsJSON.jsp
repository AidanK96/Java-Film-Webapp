<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach items="${films}" var="films">
     filmid: "${films.id}",
 	 title: "${films.title}",
 	 year: "${films.year}",
 	 director: "${films.director}",
  	 stars: "${films.stars}",
  	 review: "${films.review}"
</c:forEach>
