 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div id="favorites-content">
<c:url value="details" var="detailsPage" />

<c:forEach var="faves" items="${favParks}" >
<div class = "content">
	
		<div class="imageContainer">
			<img class="parkFaveImage" href src="img/parks/${faves.code.toLowerCase()}.jpg"/>
		</div>
	
	<div class="summary">
		<h3>${faves.name}</h3>
		<p class="description">${faves.count} vote(s)!</p>
	</div>
</div>
</c:forEach>
</div>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />


