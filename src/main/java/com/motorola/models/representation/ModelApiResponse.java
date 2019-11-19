/*
 * CAD Cloud Ingest API
 * CAD Ingest API in Azure cloud
 *
 * OpenAPI spec version: 1.0.0
 * Contact: onemobileapiarchitects@motorolasolutions.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.motorola.models.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * ModelApiResponse
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2019-11-14T12:18:59.350+02:00[Europe/Helsinki]")
public class ModelApiResponse {

	@JsonProperty("correlationId")
	private String correlationId = null;

	public ModelApiResponse correlationId(String correlationId) {
		this.correlationId = correlationId;
		return this;
	}

	/**
	 * Get correlationId
	 * @return correlationId
	 **/
	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ModelApiResponse _apiResponse = (ModelApiResponse) o;
		return Objects.equals(this.correlationId, _apiResponse.correlationId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(correlationId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ModelApiResponse {\n");

		sb.append("    correlationId: ").append(toIndentedString(correlationId)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

}
