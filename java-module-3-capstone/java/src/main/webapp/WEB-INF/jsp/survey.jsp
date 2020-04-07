<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div id="survey-content">
	 <h2>Favorite Parks Survey</h2>
	<p></p>
	<c:url value="/survey" var="survey" />
	<form:form action="${survey}" method="POST" modelAttribute="Survey">
	 <div>
		<label for="parkCode">What is your preferred National Park</label>
		<form:select path="parkCode">
			<option value ="">Select Park</option>			
			<c:forEach var="aPark" items="${allParks}">
				<option value="${aPark.code}">${aPark.name}</option>
			</c:forEach>
		</form:select>
		<form:errors path="parkCode" cssClass="error" />
</div>   
<p></p>

		<div>
		<label for="email">Email</label>
			<form:input path="emailAddress" />
			<form:errors path="emailAddress" cssClass="error"/>
			
		</div>
		

		<div>
	<label for="state">
	<div id=radioLabel>What state do you reside in?</div>
	</label>
	<form:select path="state">
		<option value="">Select State</option>
		<option value="AL">Alabama</option>
		<option value="AK">Alaska</option>
		<option value="AZ">Arizona</option>
		<option value="AR">Arkansas</option>
		<option value="CA">California</option>
		<option value="CO">Colorado</option>
		<option value="CT">Connecticut</option>
		<option value="DE">Delaware</option>
		<option value="DC">District Of Columbia</option>
		<option value="FL">Florida</option>
		<option value="GA">Georgia</option>
		<option value="HI">Hawaii</option>
		<option value="ID">Idaho</option>
		<option value="IL">Illinois</option>
		<option value="IN">Indiana</option>
		<option value="IA">Iowa</option>
		<option value="KS">Kansas</option>
		<option value="KY">Kentucky</option>
		<option value="LA">Louisiana</option>
		<option value="ME">Maine</option>
		<option value="MD">Maryland</option>
		<option value="MA">Massachusetts</option>
		<option value="MI">Michigan</option>
		<option value="MN">Minnesota</option>
		<option value="MS">Mississippi</option>
		<option value="MO">Missouri</option>
		<option value="MT">Montana</option>
		<option value="NE">Nebraska</option>
		<option value="NV">Nevada</option>
		<option value="NH">New Hampshire</option>
		<option value="NJ">New Jersey</option>
		<option value="NM">New Mexico</option>
		<option value="NY">New York</option>
		<option value="NC">North Carolina</option>
		<option value="ND">North Dakota</option>
		<option value="OH">Ohio</option>
		<option value="OK">Oklahoma</option>
		<option value="OR">Oregon</option>
		<option value="PA">Pennsylvania</option>
		<option value="RI">Rhode Island</option>
		<option value="SC">South Carolina</option>
		<option value="SD">South Dakota</option>
		<option value="TN">Tennessee</option>
		<option value="TX">Texas</option>
		<option value="UT">Utah</option>
		<option value="VT">Vermont</option>
		<option value="VA">Virginia</option>
		<option value="WA">Washington</option>
		<option value="WV">West Virginia</option>
		<option value="WI">Wisconsin</option>
		<option value="WY">Wyoming</option>
	</form:select>
	<form:errors path="state" cssClass="error" />
</div>
<p></p>
<fieldset>
	<form:label path="activity">
	<div id=radioLabel>How active are from a lifestyle perspective?</div>
	</form:label>
	<div id="radioList">
		<form:radiobutton path="activity" value="None" />
		What's activity?<br>
		<form:radiobutton path="activity" value="Inactive" />
		Pretty Inactive<br>
		<form:radiobutton path="activity" value="Moderate" checked="true" />
		Moderately Active<br>
		<form:radiobutton path="activity" value="Active" />
		Regular Activity<br>
		<form:radiobutton path="activity" value="ExtremelyActive" />
		"Let's run a marathon for fun this weekend!"
		<form:errors path="activity" cssClass="error" />
	</div>
</fieldset>
<p></p>
<div>
	<input type="submit" value="SUBMIT" />
</div>
</form:form>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />