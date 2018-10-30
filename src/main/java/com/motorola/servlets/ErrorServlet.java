/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */

package com.motorola.servlets;

import com.motorola.models.representation.ApiResponse;
import com.motorola.models.representation.ResponseNotification;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The implementation of endpoint servlet that called when error appears onPrem side
 */
@WebServlet(urlPatterns = "/error")
public class ErrorServlet extends BaseHttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponseNotification errorNotification = new ResponseNotification();
		// parse incoming errorNotification and create error notification object
		ApiResponse apiResponse = client.responseNotification().responseNotification(errorNotification);
		response.getOutputStream().write(apiResponse.toString().getBytes());
	}
}
