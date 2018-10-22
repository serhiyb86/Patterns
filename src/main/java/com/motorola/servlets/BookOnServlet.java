/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.servlets;

import com.google.gson.Gson;
import com.motorola.cloud.APIClient;
import com.google.gson.JsonObject;
import com.motorola.models.representation.ApiResponse;
import com.motorola.models.representation.UserSession;
import com.motorola.translation.BaseTranslator;
import com.motorola.translation.TranslatorsFactory;
import com.motorola.utils.CadCloudUtils;
import org.restlet.engine.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.motorola.constants.InterfaceConstants.ACCESS_TOKEN;
import static com.motorola.constants.InterfaceConstants.BOOK_ON_REQUEST_TYPE;
import static com.motorola.constants.InterfaceConstants.REQUEST_TYPE;
import static com.motorola.constants.InterfaceConstants.SPILLMAN_VERSION;

@WebServlet(urlPatterns = "/bookOn")
public class BookOnServlet extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookOnServlet.class);
	private final APIClient client = new APIClient();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getHeader(ACCESS_TOKEN);
		String spillmanVersion = request.getHeader(SPILLMAN_VERSION);
		if (!StringUtils.isNullOrEmpty(token)) {
			JsonObject json = CadCloudUtils.extractPayloadFromHttpRequest(request);
			if (json.get(REQUEST_TYPE) != null && BOOK_ON_REQUEST_TYPE.equals(json.get(REQUEST_TYPE).getAsString())) {
				client.getConfig().getSecurityConfig().configureAuthApi_key(token);
				BaseTranslator translator = TranslatorsFactory.getTranslator(spillmanVersion);
				if (translator != null) {
					UserSession sessionBean = translator.translateBookOn(json);
					if (sessionBean != null) {
						//ApiResponse apiResponse = client.responseUserSessionCorrelationId(sessionBean.getUserId()).bookOnResponse(sessionBean);
						//response.getOutputStream().write(apiResponse.toString().getBytes());
						// send also the model for reviewing on the interface side
						Gson gson = new Gson();
						String outgoingModel = gson.toJson(sessionBean);
						response.getOutputStream().write(outgoingModel.getBytes());
					}
					else {
						LOGGER.error("Failed to translate payload to the UserSession model.");
						response.getOutputStream().write("Failed to translate payload to the UserSession model.".getBytes());
					}
				} else {
					response.getOutputStream().write(String.format("Spillman version: %s is missing or unknown.", spillmanVersion).getBytes());
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
