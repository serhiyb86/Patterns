/*
 * Copyright 2021 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.models.representation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Class represents HistoryTransaction
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class HistoryTransaction implements Serializable {

    private String userId;

    private String deviceId;

    private String insertedDateTime;

    private String transactionType;

    private List<String> userRoles;

    private HistoryTransactionDetail detail;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getInsertedDateTime() {
        return insertedDateTime;
    }

    public void setInsertedDateTime(String insertedDateTime) {
        this.insertedDateTime = insertedDateTime;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }

    public HistoryTransactionDetail getDetail() {
        return detail;
    }

    public void setDetail(HistoryTransactionDetail detail) {
        this.detail = detail;
    }

}
