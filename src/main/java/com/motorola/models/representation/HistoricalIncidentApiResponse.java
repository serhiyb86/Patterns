/*
 * Copyright 2021 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.models.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class HistoricalIncidentApiResponse {

    @JsonProperty("correlationId")
    private String correlationId;

    @JsonProperty("incidentKey")
    private String incidentKey;

    public HistoricalIncidentApiResponse correlationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    public HistoricalIncidentApiResponse incidentKey(String incidentKey) {
        this.incidentKey = incidentKey;
        return this;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getIncidentKey() {
        return incidentKey;
    }

    public void setIncidentKey(String incidentKey) {
        this.incidentKey = incidentKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HistoricalIncidentApiResponse refreshIncidentData = (HistoricalIncidentApiResponse) o;
        return Objects.equals(this.correlationId, refreshIncidentData.correlationId) &&
                Objects.equals(this.incidentKey, refreshIncidentData.incidentKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(correlationId, incidentKey);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RefreshIncidentData {\n");

        sb.append("    correlationId: ").append(toIndentedString(correlationId)).append("\n");
        sb.append("    incidentKey: ").append(toIndentedString(incidentKey)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private static String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
