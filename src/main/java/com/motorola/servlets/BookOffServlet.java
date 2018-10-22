/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.servlets;

import com.motorola.cloud.APIClient;
import com.google.gson.JsonObject;
import com.motorola.models.representation.ApiResponse;
import com.motorola.models.representation.ResponseNotification;
import com.motorola.translation.BaseTranslator;
import com.motorola.translation.TranslatorsFactory;
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

import static com.motorola.constants.InterfaceConstants.*;

@WebServlet(urlPatterns = "/bookOff")
public class BookOffServlet extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookOffServlet.class);
	private final APIClient client = new APIClient();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getHeader(ACCESS_TOKEN);
		String spillmanVersion = request.getHeader(SPILLMAN_VERSION);
		if (!StringUtils.isNullOrEmpty(token)) {
			JsonObject json = CadCloudUtils.extractPayloadFromHttpRequest(request);
			if (json.get(REQUEST_TYPE) != null && BOOK_OFF_REQUEST_TYPE.equals(json.get(REQUEST_TYPE).getAsString())) {
				client.getConfig().getSecurityConfig().configureAuthApi_key(token);
				BaseTranslator translator = TranslatorsFactory.getTranslator(spillmanVersion);
				if (translator != null){
					ResponseNotification responseNotification = translator.translateBookOff(json);
					if (responseNotification.getCorrelationId() != null ){
						//TODO: write response to the on-premise adapter
						//ApiResponse apiResponse = client.responseNotification().responseNotification(responseNotification);
						//response.getOutputStream().write(apiResponse.toString().getBytes());
						response.getOutputStream().write("OK".getBytes());
					}
					else {
						response.getOutputStream().write(String.format("Spillman version: %s is missing or unknown.", spillmanVersion).getBytes());
					}
				}
				else {
					LOGGER.error("Failed to translate payload to the ResponseNotification model.");
					response.getOutputStream().write("Failed to translate payload to the ResponseNotification model.".getBytes());
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
			return;
		}
	}
}
