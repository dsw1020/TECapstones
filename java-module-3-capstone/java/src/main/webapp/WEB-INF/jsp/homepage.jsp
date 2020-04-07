<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />


 <div class = "main">
	<!-- <form action="{submitAction}" method="GET"> -->
		
			<c:forEach var="aPark" items ="${allParks}">
				<div class="content">
				<input type="hidden" name="parkCode" value="${aPark.code}"></input>	
				<c:url value="img/parks/${aPark.code.toLowerCase()}.jpg" var="parkImage">
				</c:url>
				<c:url var="pickPark" value="details">
						<c:param name="selectedPark" value="${aPark.code}"></c:param>
				</c:url>
				<a class="park-image" href="${pickPark}">
				<img src="${parkImage}"/>				
				</a>
				<div class="summary">
					<h3>${aPark.name}</h3>
					<p class="state">State: ${aPark.state}</p>
					<p class="description">${aPark.description}</p>
				</div>
				</div>
			</c:forEach>
		

<!-- </form> -->
</div> 

<c:import url="/WEB-INF/jsp/common/footer.jsp" />


