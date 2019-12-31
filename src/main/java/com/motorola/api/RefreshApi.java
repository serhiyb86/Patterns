package com.motorola.api;

import com.motorola.api.utils.ApiException;
import com.motorola.api.utils.ApiClient;
import com.motorola.api.utils.Configuration;
import com.motorola.api.utils.Pair;

import javax.ws.rs.core.GenericType;

import com.motorola.models.representation.ModelApiResponse;
import com.motorola.models.representation.RefreshIncidentData;
import com.motorola.models.representation.RefreshUnitData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RefreshApi {

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
			throw new ApiException(400, "Missing the required parameter 'body' when calling refreshIncidents");
		}
		// verify the required parameter 'authorization' is set
		if (authorization == null) {
			throw new ApiException(400, "Missing the required parameter 'authorization' when calling refreshIncidents");
		}
		// create path and map variables
		String localVarPath = "/refresh/incident";

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
			throw new ApiException(400, "Missing the required parameter 'body' when calling refreshUnits");
		}
		// verify the required parameter 'authorization' is set
		if (authorization == null) {
			throw new ApiException(400, "Missing the required parameter 'authorization' when calling refreshUnits");
		}
		// create path and map variables
		String localVarPath = "/refresh/unit";

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
}
