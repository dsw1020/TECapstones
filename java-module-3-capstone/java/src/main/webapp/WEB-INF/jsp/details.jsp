<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:import url="/WEB-INF/jsp/common/header.jsp" />

<body>
	<div class="details-page-content">
		<div class="detailImageContainer">
			<img class="detailParkImage" href
				src="img/parks/${aPark.code.toLowerCase()}.jpg" />
			<p id="quote">"${aPark.quote}" - ${aPark.quoteSource}</p>
		</div>
		<c:set var="parkFee" value="${aPark.entryFee}" />
		<div class="detailSummary">
			<div id="firstLine"></div>
			<div id="name">${aPark.name} (${aPark.code})</div>
			<div id="founded">${aPark.state} (founded in ${aPark.yearFounded})</div>
			<p></p>
			<div id="climate">Climate: ${aPark.climate}</div>
			<div id="fee">Fee: <fmt:formatNumber value="${aPark.entryFee}" type="currency" /></div>
			<p class="description">${aPark.description}</p>

			<h3>Cool Facts!</h3>
			<div id="elevation">Elevation: ${aPark.elevation} feet</div>
			<div id="campsites">Number of Campsites: ${aPark.numberOfCampsites}</div>
			<div id="miles">Miles of Trails: ${aPark.milesOfTrail}</div>
			<div id="animals">Number of Animal Species: ${aPark.numberOfAnimals}</div>
			<div id="acreage">${aPark.name} protects ${aPark.acreage} acres!</div>
		</div>
	</div>
<br>
	<c:url var="formAction" value="details" />
	<form method="POST" action="${formAction}">
		<select name="fahOrCel">
			<option value="Fahrenheit">Fahrenheit</option>
			<option value="Celcius">Celcius</option>
		</select>
		<input name="selectedPark" type="hidden" value="${aPark.code}"/>
		<input type="submit" value="Select"/>
	</form>
	<!-- We're going to iterate through the entire 5 day forecast -->
	
	<c:forEach var="dailyWeather" items ="${fiveDay}">
		<!-- When the day = 1, that means it's today! Set the div as such -->	
		<!-- Otherwise set the dav for the rest of the week -->
		<c:choose>
			<c:when test="${dailyWeather.day==1}">
				<div id="todaysWeather"> 
				<div id="todayWord">Today</div>
			</c:when>
			<c:otherwise>
				<div id="otherWeather">
			</c:otherwise>
		</c:choose>	
	<!--  Partly cloudy is 2 words in the database but the image file is 1 word. This checks for that -->	
		<c:choose>
			<c:when test="${dailyWeather.forecast=='partly cloudy'}">
				<img src ="img/weather/partlyCloudy.png"/>			
			</c:when>
			<c:otherwise>
				<img src ="img/weather/${dailyWeather.forecast}.png"/>		
			</c:otherwise>
		</c:choose>
	<!-- The rest of this logic is for putting out the forecast and the messages -->	
		<div id="temp"><div id="todayHigh">High ${dailyWeather.tempHigh}°F</div> <div id="todayLow">Low ${dailyWeather.tempLow}°F</div></div>
		<div id="psa">
		<c:choose>
			<c:when test="${dailyWeather.forecast == 'snow'}">
				Pack snowshoes!
			</c:when>
			<c:when test="${dailyWeather.forecast == 'rain'}">
				Pack rain gear and wear waterproof shoes!
			</c:when>
			<c:when test="${dailyWeather.forecast == 'thunderstorms'}">
				Seek shelter and avoid hiking on exposed ridges!
			</c:when>
			<c:when test="${dailyWeather.forecast == 'sun'}">
				Pack Sunblock!
			</c:when>
		</c:choose>
		
		<c:choose>
			<c:when test="${dailyWeather.tempHigh > 75}">
				Bring an extra gallon of water!
			</c:when>
			<c:when test="${dailyWeather.tempHigh - dailyWeather.tempLow > 20}">
				Wear breathable layers!
			</c:when>
			<c:when test="${dailyWeather.tempLow < 20}">
				Please be warned: exposure to frigid temperatures is bad, mmkay?
			</c:when>
		</c:choose>
	</div>	
	</c:forEach>

<!--  	<div id="todaysWeather"> 
		<div id="todayWord">Today</div>
		<img src ="img/weather/${fiveDay[0].forecast}.png"/>
		<c:choose>
			<c:when test="${param.tempChange == 'Celcius' }">
				<div id="temp"><div id="todayHigh">High ${Math.round((today.tempHigh - 32) * (5/9))}C</div> <div id="todayLow">Low ${Math.round((today.tempLow - 32) * (5/9))}C</div></div>	
			</c:when>
			<c:otherwise>
				<div id="temp"><div id="todayHigh">High ${fiveDay[0].tempHigh}F</div> <div id="todayLow">Low ${fiveDay[0].tempLow}F</div></div>
			</c:otherwise>
		</c:choose>
		<hr>
		<div id="psa">
	<c:choose>
		
		<c:when test="${today.forecast == 'snow'}">
			Pack snowshoes!
		</c:when>
		
		<c:when test="${today.forecast == 'rain'}">
			Pack rain gear and wear waterproof shoes!
		</c:when>
		
		<c:when test="${today.forecast == 'thunderstorms'}">
			Seek shelter and avoid hiking on exposed ridges!
		</c:when>
		
		<c:when test="${today.forecast == 'sun'}">
			Pack Sunblock!
		</c:when>
		
	</c:choose>
	<c:choose>
		
		<c:when test="${today.high > 75}">
			Bring an extra gallon of water!
		</c:when>
		
		<c:when test="${today.high - today.low > 20}">
			Wear breathable layers!
		</c:when>
		
		<c:when test="${today.low < 20}">
			Please be warned: exposure to frigid temperatures is bad.
		</c:when>
		
		
	</c:choose>
	</div>
	</div>
<c:choose>
	<c:when test="${param.tempChange == 'Celcius' }">
		
		
		<div id="otherWeather"> 
			<img src ="img/weather/${day2.forecast}.png"/>
			<div id="otherTemp"><div id="high">High ${Math.round((day2.high - 32) * (5/9))}C</div> <hr> <div id="low">Low ${Math.round((day2.low - 32) * (5/9))}C</div></div>
		</div>
		<div id="otherWeather"> 
			<img src ="img/weather/${day3.forecast}.png"/>
			<div id="otherTemp"><div id="high">High ${Math.round((day3.high - 32) * (5/9))}C</div> <hr> <div id="low">Low ${Math.round((day3.low - 32) * (5/9))}C</div></div>
		</div>
		<div id="otherWeather"> 
			<img src ="img/weather/${day4.forecast}.png"/>
			<div id="otherTemp"><div id="high">High ${Math.round((day4.high - 32) * (5/9))}C</div> <hr> <div id="low">Low ${Math.round((day4.low - 32) * (5/9))}C</div></div>
		</div>
		<div id="otherWeather"> 
			<img src ="img/weather/${day5.forecast}.png"/>
			<div id="otherTemp"><div id="high">High ${Math.round((day5.high - 32) * (5/9))}C</div> <hr> <div id="low">Low ${Math.round((day5.low - 32) * (5/9))}C</div></div>
		</div>
	</c:when>
	<c:otherwise>
		
		<div id="otherWeather"> 
			<img src ="img/weather/${day2.forecast}.png"/>
			<div id="otherTemp"><div id="high">High ${day2.high}F</div> <hr> <div id="low">Low ${day2.low}F</div></div>
		</div>
		<div id="otherWeather"> 
			<img src ="img/weather/${day3.forecast}.png"/>
			<div id="otherTemp"><div id="high">High ${day3.high}F</div> <hr> <div id="low">Low ${day3.low}F</div></div>
		</div>
		<div id="otherWeather"> 
			<img src ="img/weather/${day4.forecast}.png"/>
			<div id="otherTemp"><div id="high">High ${day4.high}F</div> <hr> <div id="low">Low ${day4.low}F</div></div>
		</div>
		<div id="otherWeather"> 
			<img src ="img/weather/${day5.forecast}.png"/>
			<div id="otherTemp"><div id="high">High ${day5.high}F</div> <hr> <div id="low">Low ${day5.low}F</div></div>
		</div>
	</c:otherwise>
</c:choose> -->
</div>
</body>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />