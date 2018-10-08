/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.servlets;

import com.cloud.APIClient;
import com.google.gson.JsonObject;
import com.models.representation.ApiResponse;
import org.restlet.engine.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.utils.CadCloudUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.constants.InterfaceConstants.ACCESS_TOKEN;
import static com.constants.InterfaceConstants.BOOK_OFF_REQUEST_TYPE;
import static com.constants.InterfaceConstants.REQUEST_TYPE;
import static com.constants.InterfaceConstants.SESSION_ID;

@WebServlet(urlPatterns = "/bookOff")
public class BookOffServlet extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookOffServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter(ACCESS_TOKEN);

		if (!StringUtils.isNullOrEmpty(token)) {
			JsonObject json = CadCloudUtils.extractPayloadFromHttpRequest(request);
			if (json.get(REQUEST_TYPE) != null && BOOK_OFF_REQUEST_TYPE.equals(json.get(REQUEST_TYPE).getAsString())) {
				APIClient client = new APIClient();
				client.getConfig().getSecurityConfig().getClientFactory().builder().withOAuth2(token);


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
