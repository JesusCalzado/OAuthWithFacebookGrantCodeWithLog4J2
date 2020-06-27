package com.calzado.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.calzado.util.ApplicationProperties;


import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


@WebServlet(name="OAuthCallbackListener", urlPatterns={"/callback"})
public class OAuthCallbackListener extends HttpServlet {
private static final long serialVersionUID = 1L;
	String TOKEN_ENDPOINT = ApplicationProperties.getValue("TOKEN_ENDPOINT").replaceAll("\"", "");
	String GRANT_TYPE = ApplicationProperties.getValue("GRANT_TYPE").replaceAll("\"", "");
	String REDIRECT_URI = ApplicationProperties.getValue("REDIRECT_URI").replaceAll("\"", "");
	String CLIENT_ID = ApplicationProperties.getValue("CLIENT_ID").replaceAll("\"", "");
	String CLIENT_SECRET = ApplicationProperties.getValue("CLIENT_SECRET").replaceAll("\"", "");


 protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
	
	String authorizationCode = request.getParameter("code");
	if (authorizationCode != null && authorizationCode.length() > 0) {
		System.out.println("authorizationCode: " + authorizationCode);
		sendRequestToken(authorizationCode);
	} else {
	// Handle failure
	}
 }
 private void sendRequestToken(String authorizationCode) throws UnsupportedEncodingException,IOException{
	HttpPost httpPost = new HttpPost(TOKEN_ENDPOINT + "?grant_type=" + URLEncoder.encode(GRANT_TYPE,StandardCharsets.UTF_8.name()) + "&code=" + URLEncoder.encode(authorizationCode,StandardCharsets.UTF_8.name()) +  "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI,StandardCharsets.UTF_8.name()) + "&client_id=" + URLEncoder.encode(CLIENT_ID,StandardCharsets.UTF_8.name()));
	String clientCredentials = CLIENT_ID + ":" + CLIENT_SECRET;
	System.out.println("clientCredentials: " + clientCredentials);
	String encodedClientCredentials =  new String(Base64.encodeBase64(clientCredentials.getBytes()));
	System.out.println("encodedClientCredentials: " + encodedClientCredentials);
	httpPost.setHeader("Authorization", "Basic " + encodedClientCredentials);
	CloseableHttpClient httpClient = HttpClients.createDefault();
	HttpResponse httpResponse = httpClient.execute(httpPost);
	Reader reader = new InputStreamReader(httpResponse.getEntity().getContent());
	BufferedReader bufferedReader = new BufferedReader(reader);
	String line = bufferedReader.readLine();
	String accessToken = null;
	String[] responseProperties = line.split("&");
	for (String responseProperty : responseProperties) {
		System.out.println("responseProperty: " + responseProperty);
		if (responseProperty.contains("access_token:")) {
			System.out.println("Yes!!");
			accessToken = responseProperty.split(",")[1];
			break;
		}
	}	
	System.out.println("Access token: " + accessToken);
	httpClient.close();
 }
}