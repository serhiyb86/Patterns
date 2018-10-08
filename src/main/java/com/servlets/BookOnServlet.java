/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.servlets;

import com.cloud.APIClient;
import com.google.gson.JsonObject;
import com.models.representation.ApiResponse;
import com.models.representation.BookOnParameters;
import org.restlet.engine.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.translation.CadCloudTranslator;
import com.utils.CadCloudUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.constants.InterfaceConstants.BOOK_ON_REQUEST_TYPE;
import static com.constants.InterfaceConstants.REQUEST_TYPE;

@WebServlet(urlPatterns = "/bookOn")
public class BookOnServlet extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookOnServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getHeader("accessToken");

		if (!StringUtils.isNullOrEmpty(token)) {
			JsonObject json = CadCloudUtils.extractPayloadFromHttpRequest(request);
			if (json.get(REQUEST_TYPE) != null && BOOK_ON_REQUEST_TYPE.equals(json.get(REQUEST_TYPE).getAsString())) {
				APIClient client = new APIClient();
				client.getConfig().getSecurityConfig().configureAuthApi_key(token);

				BookOnParameters bookOnParameters = CadCloudTranslator.translateBookOn(json);
				//TODO: write response to the on-premise adapter
				//ApiResponse apiResponse = client.userSession().bookOn(bookOnParameters);
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
		}
	}
}
