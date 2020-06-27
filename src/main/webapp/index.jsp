<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.calzado.util.ApplicationProperties" %>
<%
pageContext.setAttribute("auth_endpoint", ApplicationProperties.getValue("AUTH_ENDPOINT"));
pageContext.setAttribute("response_type", ApplicationProperties.getValue("RESPONSE_TYPE"));
pageContext.setAttribute("client_id", ApplicationProperties.getValue("CLIENT_ID"));
pageContext.setAttribute("redirect_uri", ApplicationProperties.getValue("REDIRECT_URI"));
pageContext.setAttribute("scope", ApplicationProperties.getValue("SCOPE"));
%>

<html>
 <head>
 <title>The World's Most Interesting Infographic</title>
 <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
 <script>
 $(document).ready(function() {
    $("#goButton").click(makeRequest);
 });
 function makeRequest() {
    // Define properties
    var AUTH_ENDPOINT = ${auth_endpoint};
    var RESPONSE_TYPE = ${response_type};
    var CLIENT_ID = ${client_id};
    var REDIRECT_URI = ${redirect_uri};
    var SCOPE = ${scope};
    // Build authorization request endpoint
    var requestEndpoint = AUTH_ENDPOINT + "?" +
    "response_type=" + encodeURIComponent(RESPONSE_TYPE) + "&" +
    "client_id=" + encodeURIComponent(CLIENT_ID) + "&" +
    "redirect_uri=" + encodeURIComponent(REDIRECT_URI) + "&" +
    "scope=" + encodeURIComponent(SCOPE);
    // Send to authorization request endpoint
    window.location.href = requestEndpoint;
}
 </script>
 </head>
 <body>
 <button id="goButton" type="button">Go!</button>
 <div id="results"></div>
 </body>
</html>