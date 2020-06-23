<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
 var AUTH_ENDPOINT = "https://www.facebook.com/dialog/oauth";
 var RESPONSE_TYPE = "code";
 var CLIENT_ID = "537451827134465";
 var REDIRECT_URI = "https://calzado.com" + "<%=request.getContextPath()%>"+ "/callback";
 var SCOPE = "public_profile";
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