<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
   	<meta charset="UTF-8">
	<title>National Park Weather Information System</title>
			<c:url value="/css/site.css" var="cssURL" />
   			<link rel="stylesheet" type="text/css" href="${cssURL}">
</head>

<body>
	<header>
		<c:url value="/" var="homePageHref" />
		<c:url value="img/logo.png" var="logoImg"/>
		<a href="${homePageHref}">
			<img src="${logoImg}" id="logo" />
		</a>
		<h1>National Park Weather Information System</h1>
    </header>
    <nav>
        <ul>
            <li><a href="${homePageHref}">Home</a></li>
            <c:url value="/survey" var="survey" />       
            <li><a href="${survey}">Survey</a></li>
      		<c:url value="/favorites" var="favorites" />      
            <li><a href="${favorites}">Favorite Parks</a></li>           
        </ul>
    </nav>
