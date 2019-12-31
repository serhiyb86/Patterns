/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.api;

import com.motorola.api.utils.ApiClient;
import com.motorola.api.utils.ApiException;
import com.motorola.api.utils.Configuration;
import com.motorola.api.utils.Pair;
import com.motorola.models.representation.ModelApiResponse;
import com.motorola.models.representation.ResponseData;
import com.motorola.models.representation.ResponseNotification;
import com.motorola.models.representation.UserSession;

import javax.ws.rs.core.GenericType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseApi {

	private ApiClient apiClient;

	public ResponseApi() {
		this(Configuration.getDefaultApiClient());
	}

	public ResponseApi(ApiClient apiClient) {
		this.apiClient = apiClient;
	}

	public ApiClient getApiClient() {
		return apiClient;
	}

	public void setApiClient(ApiClient apiClient) {
		this.apiClient = apiClient;
	}

	/**
	 * On Prem CAD Services to post a book on response to cloud CAD2Mobile Cloud On Prem API service
	 * On Prem CAD Services to post a book on response to cloud CAD2Mobile Cloud On Prem API
	 * @param body BookOn Response data - session object. (required)
	 * @param authorization security token for authorization (required)
	 * @param correlationId Correlation ID for the book on response. Provided by the book on request SB message (required)
	 * @return ModelApiResponse
	 * @throws ApiException if fails to make API call
	 */
	public ModelApiResponse bookOnResponse(UserSession body, String authorization, String correlationId) throws ApiException {
		Object localVarPostBody = body;
		// verify the required parameter 'body' is set
		if (body == null) {
			throw new ApiException(400, "Missing the required parameter 'body' when calling bookOnResponse");
		}
		// verify the required parameter 'authorization' is set
		if (authorization == null) {
			throw new ApiException(400, "Missing the required parameter 'authorization' when calling bookOnResponse");
		}
		// verify the required parameter 'correlationId' is set
		if (correlationId == null) {
			throw new ApiException(400, "Missing the required parameter 'correlationId' when calling bookOnResponse");
		}
		// create path and map variables
		String localVarPath = "/response/userSession/{correlationId}"
			.replaceAll("\\{" + "correlationId" + "\\}", apiClient.escapeString(correlationId.toString()));

		// query params
		List<Pair> localVarQueryParams = new ArrayList<Pair>();
		Map<String, String> localVarHeaderParams = new HashMap<String, String>();
		Map<String, Object> localVarFormParams = new HashMap<String, Object>();

		if (authorization != null)
			localVarHeaderParams.put("Authorization", apiClient.parameterToString(authorization));

		final String[] localVarAccepts = {
			"application/json"
		};
		final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

		final String[] localVarContentTypes = {
			"application/json"
		};
		final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

		String[] localVarAuthNames = new String[] { "clientCredentialApi_auth" };

		GenericType<ModelApiResponse> localVarReturnType = new GenericType<ModelApiResponse>() {

		};
		return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
	}

	/**
	 * On Prem CAD Services to post response data with notification information to CAD Cloud Ingest API service.
	 * On Prem CAD Services to post a generic response notification with data to CAD Cloud Ingest API service. This API call will generate a service bus notification using provided notification information (without data). The responseData will be stored in database.
	 * @param body Generic Response Data in JSON format with notification information (required)
	 * @param authorization security token for authorization (required)
	 * @return ModelApiResponse
	 * @throws ApiException if fails to make API call
	 */
	public ModelApiResponse responseData(ResponseData body, String authorization) throws ApiException {
		Object localVarPostBody = body;
		// verify the required parameter 'body' is set
		if (body == null) {
			throw new ApiException(400, "Missing the required parameter 'body' when calling responseData");
		}
		// verify the required parameter 'authorization' is set
		if (authorization == null) {
			throw new ApiException(400, "Missing the required parameter 'authorization' when calling responseData");
		}
		// create path and map variables
		String localVarPath = "/response/data";

		// query params
		List<Pair> localVarQueryParams = new ArrayList<Pair>();
		Map<String, String> localVarHeaderParams = new HashMap<String, String>();
		Map<String, Object> localVarFormParams = new HashMap<String, Object>();


		if (authorization != null)
			localVarHeaderParams.put("Authorization", apiClient.parameterToString(authorization));


		final String[] localVarAccepts = {
			"application/json"
		};
		final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

		final String[] localVarContentTypes = {
			"application/json"
		};
		final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

		String[] localVarAuthNames = new String[] { "clientCredentialApi_auth" };

		GenericType<ModelApiResponse> localVarReturnType = new GenericType<ModelApiResponse>() {};
		return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
	}

	/**
	 * On Prem CAD Services to post a generic response notification to cloud CAD2Mobile Cloud On Prem API service. This notification only goes to Service Bus
	 * On Prem CAD Services to post a generic response notification to cloud CAD2Mobile Cloud On Prem API service. This notification only goes to Service Bus
	 * @param body Generic Response Notification Data. (required)
	 * @param authorization security token for authorization (required)
	 * @return ModelApiResponse
	 * @throws ApiException if fails to make API call
	 */
	public ModelApiResponse responseNotification(ResponseNotification body, String authorization) throws ApiException {
		Object localVarPostBody = body;
		// verify the required parameter 'body' is set
		if (body == null) {
			throw new ApiException(400, "Missing the required parameter 'body' when calling responseNotification");
		}
		// verify the required parameter 'authorization' is set
		if (authorization == null) {
			throw new ApiException(400, "Missing the required parameter 'authorization' when calling responseNotification");
		}
		// create path and map variables
		String localVarPath = "/response/notification";

		// query params
		List<Pair> localVarQueryParams = new ArrayList<Pair>();
		Map<String, String> localVarHeaderParams = new HashMap<String, String>();
		Map<String, Object> localVarFormParams = new HashMap<String, Object>();

		if (authorization != null)
			localVarHeaderParams.put("Authorization", apiClient.parameterToString(authorization));

		final String[] localVarAccepts = {
			"application/json"
		};
		final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

		final String[] localVarContentTypes = {
			"application/json"
		};
		final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

		String[] localVarAuthNames = new String[] { "clientCredentialApi_auth" };

		GenericType<ModelApiResponse> localVarReturnType = new GenericType<ModelApiResponse>() {

		};
		return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
	}

	/**
	 * Service to invalidate a session in the cloud
	 * Service to invalidate a session in the cloud
	 * @param authorization security token for authorization (required)
	 * @param sessionId Session ID being invalidated (required)
	 * @return ModelApiResponse
	 * @throws ApiException if fails to make API call
	 */
	public ModelApiResponse sessionInvalidate(String authorization, String sessionId) throws ApiException {
		Object localVarPostBody = null;
		// verify the required parameter 'authorization' is set
		if (authorization == null) {
			throw new ApiException(400, "Missing the required parameter 'authorization' when calling sessionInvalidate");
		}
		// verify the required parameter 'sessionId' is set
		if (sessionId == null) {
			throw new ApiException(400, "Missing the required parameter 'sessionId' when calling sessionInvalidate");
		}
		// create path and map variables
		String localVarPath = "/push/userSession/{sessionId}"
			.replaceAll("\\{" + "sessionId" + "\\}", apiClient.escapeString(sessionId.toString()));

		// query params
		List<Pair> localVarQueryParams = new ArrayList<Pair>();
		Map<String, String> localVarHeaderParams = new HashMap<String, String>();
		Map<String, Object> localVarFormParams = new HashMap<String, Object>();

		if (authorization != null)
			localVarHeaderParams.put("Authorization", apiClient.parameterToString(authorization));

		final String[] localVarAccepts = {
			"application/json"
		};
		final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

		final String[] localVarContentTypes = {

		};
		final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

		String[] localVarAuthNames = new String[] { "clientCredentialApi_auth" };

		GenericType<ModelApiResponse> localVarReturnType = new GenericType<ModelApiResponse>() {

		};
		return apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
	}
}
