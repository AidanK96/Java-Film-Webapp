<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach items="${films}" var="films">
   ${films.id}#${films.title}#${films.year}#${films.director}#${films.stars}#${films.review}
</c:forEach>
