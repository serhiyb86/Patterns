package com.motorola.api;

import com.motorola.api.utils.ApiException;
import com.motorola.api.utils.ApiClient;
import com.motorola.api.utils.Configuration;
import com.motorola.api.utils.Pair;

import javax.ws.rs.core.GenericType;

import com.motorola.models.representation.HistoricalIncidentApiResponse;
import com.motorola.models.representation.ModelApiResponse;
import com.motorola.models.representation.RefreshIncidentData;
import com.motorola.models.representation.RefreshUnitData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RefreshApi {

	private static final int BAD_REQUEST = 400;
	private static final String CLIENT_CREDENTIALS = "clientCredentialApi_auth";
	private static final String APPLICATION_JSON = "application/json";
	private static final String AUTHORIZATION = "Authorization";

	private ApiClient apiClient;

	public RefreshApi() {
		this(Configuration.getDefaultApiClient());
	}

	public RefreshApi(ApiClient apiClient) {
		this.apiClient = apiClient;
	}

	public ApiClient getApiClient() {
		return apiClient;
	}

	public void setApiClient(ApiClient apiClient) {
		this.apiClient = apiClient;
	}

	/**
	 * Receives new list of incidents from back end CAD systems and updates it in cloud database
	 * Receives new list of incident
	 * @param body On Prem CAD system incident lists message (required)
	 * @param authorization security token for authorization (required)
	 * @return ModelApiResponse
	 * @throws ApiException if fails to make API call
	 */
	public ModelApiResponse refreshIncidents(RefreshIncidentData body, String authorization) throws ApiException {
		Object localVarPostBody = body;
		// verify the required parameter 'body' is set
		if (body == null) {
			throw new ApiException(BAD_REQUEST, "Missing the required parameter 'body' when calling refreshIncidents");
		}
		// verify the required parameter 'authorization' is set
		if (authorization == null) {
			throw new ApiException(BAD_REQUEST, "Missing the required parameter 'authorization' when calling refreshIncidents");
		}
		// create path and map variables
		String localVarPath = "/refresh/incident";

		// query params
		List<Pair> localVarQueryParams = new ArrayList<>();
		Map<String, String> localVarHeaderParams = new HashMap<>();
		Map<String, Object> localVarFormParams = new HashMap<>();

		localVarHeaderParams.put(AUTHORIZATION, apiClient.parameterToString(authorization));

		final String[] localVarAccepts = {
			APPLICATION_JSON
		};
		final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

		final String[] localVarContentTypes = {
			APPLICATION_JSON
		};
		final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

		String[] localVarAuthNames = new String[] { CLIENT_CREDENTIALS };

		GenericType<ModelApiResponse> localVarReturnType = new GenericType<ModelApiResponse>() {

		};
		return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
	}

	/**
	 * Receives new list of on-duty units from back end CAD systems and updates it cloud database
	 * Receives new list of on-duty units
	 * @param body On Prem CAD system unit lists message (required)
	 * @param authorization security token for authorization (required)
	 * @return ModelApiResponse
	 * @throws ApiException if fails to make API call
	 */
	public ModelApiResponse refreshUnits(RefreshUnitData body, String authorization) throws ApiException {
		Object localVarPostBody = body;
		// verify the required parameter 'body' is set
		if (body == null) {
			throw new ApiException(BAD_REQUEST, "Missing the required parameter 'body' when calling refreshUnits");
		}
		// verify the required parameter 'authorization' is set
		if (authorization == null) {
			throw new ApiException(BAD_REQUEST, "Missing the required parameter 'authorization' when calling refreshUnits");
		}
		// create path and map variables
		String localVarPath = "/refresh/unit";

		// query params
		List<Pair> localVarQueryParams = new ArrayList<>();
		Map<String, String> localVarHeaderParams = new HashMap<>();
		Map<String, Object> localVarFormParams = new HashMap<>();

		localVarHeaderParams.put(AUTHORIZATION, apiClient.parameterToString(authorization));

		final String[] localVarAccepts = {
			APPLICATION_JSON
		};
		final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

		final String[] localVarContentTypes = {
			APPLICATION_JSON
		};
		final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

		String[] localVarAuthNames = new String[] { CLIENT_CREDENTIALS };

		GenericType<ModelApiResponse> localVarReturnType = new GenericType<ModelApiResponse>() {

		};
		return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
	}

	/**
	 * Receives new list of historical incidents from back end CAD systems and updates it in cloud database
	 * Receives new list of incident
	 * @param body On Prem CAD system incident lists message (required)
	 * @param authorization security token for authorization (required)
	 * @return ModelApiResponse
	 * @throws ApiException if fails to make API call
	 */
	public List<HistoricalIncidentApiResponse> refreshHistoricalIncidents(RefreshIncidentData body, String authorization) throws ApiException {
		Object localVarPostBody = body;
		// verify the required parameter 'body' is set
		if (body == null) {
			throw new ApiException(BAD_REQUEST, "Missing the required parameter 'body' when calling refreshHistoricalIncidents");
		}
		// verify the required parameter 'authorization' is set
		if (authorization == null) {
			throw new ApiException(BAD_REQUEST, "Missing the required parameter 'authorization' when calling refreshHistoricalIncidents");
		}
		// create path and map variables
		String localVarPath = "/incidents";

		// query params
		List<Pair> localVarQueryParams = new ArrayList<>();
		Map<String, String> localVarHeaderParams = new HashMap<>();
		Map<String, Object> localVarFormParams = new HashMap<>();

		localVarHeaderParams.put(AUTHORIZATION, apiClient.parameterToString(authorization));

		final String[] localVarAccepts = {
				APPLICATION_JSON
		};
		final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

		final String[] localVarContentTypes = {
				APPLICATION_JSON
		};
		final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

		String[] localVarAuthNames = new String[] { CLIENT_CREDENTIALS };

		GenericType<List<HistoricalIncidentApiResponse>> localVarReturnType = new GenericType<List<HistoricalIncidentApiResponse>>() {

		};
		return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
	}
}
