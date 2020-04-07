<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

 <div class = "main-content">
 
 
 <form:form action="${registerUrl}" method="POST" modelAttribute="User">
    <div class="form-group">
        <label for="username">Username</label>
        <form:input class="form-control" path="username"/>
        <form:errors path="username" cssClass="bg-danger"/>
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <form:password class="form-control" path="password"/>
        <form:errors path="password" cssClass="bg-danger"/>
    </div>
    <div class="form-group">
        <label for="confirmPassword">Confirm Password</label>
        <form:password class="form-control" path="confirmPassword"/>
        <form:errors path="passwordMatching" cssClass="bg-danger"/>
    </div> 
    <div class="form-group">
        <label for="passwordHint">Password Hint</label>
        <form:input class="form-control" path="passwordHint"/>
        <form:errors path="passwordHint" cssClass="bg-danger"/>
    </div>
	<div class="form-group">
        <label for="email">Email Address</label>
        <form:input class="form-control" path="email"/>
        <form:errors path="email" cssClass="bg-danger"/>
    </div>    
	<div class="form-group">
        <label for="confirmEmail">Confirm Email Address</label>
        <form:input class="form-control" path="confirmEmail"/>
        <form:errors path="emailMatching" cssClass="bg-danger"/>
    </div>         
    <button type="submit" class="btn btn-default">Save User</button>
</form:form>
 
 <div id="login-here">
 <c:url var="loginUrl" value="/login"/>
 <p> Already have an account?</p>
 <a href="${loginUrl}">Log in here!</a>
 </div>
 
 
 </div> 

<c:import url="/WEB-INF/jsp/common/footer.jsp" />