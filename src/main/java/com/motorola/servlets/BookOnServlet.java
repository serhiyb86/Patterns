/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.servlets;

import com.motorola.api.utils.ApiException;
import com.motorola.api.utils.ApiExceptionModel;
import com.motorola.api.utils.ExceptionModel;
import com.motorola.constants.InterfaceConstants;
import com.motorola.manager.BookOnOffRequestManager;
import com.motorola.models.representation.ModelApiResponse;
import com.motorola.models.representation.UserSessionWrapper;
import com.motorola.utils.CadCloudUtils;
import com.motorola.validation.ValidationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/bookOn")
public class BookOnServlet extends BaseHttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookOnServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookOnOffRequestManager requestManager = new BookOnOffRequestManager();
		List<ValidationResult> validationResult = requestManager.validateRequest(request, InterfaceConstants.BookOnProperties.BOOK_ON_REQUEST_TYPE);
		if (validationResult.isEmpty()) {
			UserSessionWrapper wrapper = requestManager.getTranslator().translateBookOn(requestManager.getPayload());
			if (requestManager.getTranslator().getValidationResults().isEmpty()) {
				String outgoingModel = CadCloudUtils.convertObjectToJsonString(wrapper);
				ServletOutputStream outputStream = null;
				try {
					ModelApiResponse modelApiResponse = requestManager.bookOn(wrapper.getModel(), wrapper.getCorrelationId());
					outputStream = response.getOutputStream();
					outputStream.write(CadCloudUtils.convertObjectToJsonString(modelApiResponse).getBytes());
				}
				catch (ApiException e) {
					respondWithTranslatedModel(response, outgoingModel, CadCloudUtils.convertObjectToJsonString(new ApiExceptionModel(e)));
				}
				catch (Exception e) {
					LOGGER.error("Failed to send BookOn data.", e);
					respondWithTranslatedModel(response, outgoingModel, CadCloudUtils.convertObjectToJsonString(new ExceptionModel(e.getMessage())));
				}
				finally {
					if (outputStream != null) {
						try {
							outputStream.close();
						}
						catch (IOException e) {
							LOGGER.error("IOException: ", e);
						}
					}
				}
			}
			else {
				respondFailure(response, requestManager.getTranslator().getValidationResults());
			}
		}
		else {
			respondFailure(response, validationResult);
		}
	}

}

