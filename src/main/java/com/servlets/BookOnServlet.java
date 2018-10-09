/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.servlets;

import com.cloud.APIClient;
import com.google.gson.JsonObject;
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

import static com.constants.InterfaceConstants.ACCESS_TOKEN;
import static com.constants.InterfaceConstants.BOOK_ON_REQUEST_TYPE;
import static com.constants.InterfaceConstants.REQUEST_TYPE;

@WebServlet(urlPatterns = "/bookOn")
public class BookOnServlet extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookOnServlet.class);
	private final APIClient client = new APIClient();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getHeader(ACCESS_TOKEN);
		if (!StringUtils.isNullOrEmpty(token)) {
			JsonObject json = CadCloudUtils.extractPayloadFromHttpRequest(request);
			if (json.get(REQUEST_TYPE) != null && BOOK_ON_REQUEST_TYPE.equals(json.get(REQUEST_TYPE).getAsString())) {
				client.getConfig().getSecurityConfig().configureAuthApi_key(token);
				BookOnParameters bookOnParameters = CadCloudTranslator.translateBookOn(json);
				if (bookOnParameters != null) {
					//TODO: write response to the on-premise adapter
					//ApiResponse apiResponse = client.userSession().bookOn(bookOnParameters);
					//response.getOutputStream().write(apiResponse.toString().getBytes());
					response.getOutputStream().write("OK".getBytes());
				}
				else {
					LOGGER.error("Failed to translate payload to the BookOnParameters model.");
					response.getOutputStream().write("Failed to translate payload to the BookOnParameters model.".getBytes());
				}
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
