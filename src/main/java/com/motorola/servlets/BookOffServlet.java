/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.servlets;

import com.motorola.cloud.APIClient;
import com.google.gson.JsonObject;
import org.restlet.engine.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.motorola.utils.CadCloudUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.motorola.constants.InterfaceConstants.ACCESS_TOKEN;
import static com.motorola.constants.InterfaceConstants.BOOK_OFF_REQUEST_TYPE;
import static com.motorola.constants.InterfaceConstants.REQUEST_TYPE;

@WebServlet(urlPatterns = "/bookOff")
public class BookOffServlet extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookOffServlet.class);
	private final APIClient client = new APIClient();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter(ACCESS_TOKEN);
		if (!StringUtils.isNullOrEmpty(token)) {
			JsonObject json = CadCloudUtils.extractPayloadFromHttpRequest(request);
			if (json.get(REQUEST_TYPE) != null && BOOK_OFF_REQUEST_TYPE.equals(json.get(REQUEST_TYPE).getAsString())) {
				client.getConfig().getSecurityConfig().configureAuthApi_key(token);
				//TODO: write response to the on-premise adapter
				//ApiResponse apiResponse = client.userSessionSessionId(json.get(SESSION_ID).getAsString()).bookOff();
				//response.getOutputStream().write(apiResponse.toString().getBytes());
				response.getOutputStream().write("OK".getBytes());
			}
			else {
				LOGGER.error("Wrong request type.");
				response.getOutputStream().write("Wrong request type.".getBytes());
			}
		}
		else {
			LOGGER.error("Token is required.");
			response.getOutputStream().write("Token is required.".getBytes());
			return;
		}
	}
}
