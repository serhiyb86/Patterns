/*
 * Copyright 2021 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.HistoryTransactionDetail;
import com.motorola.models.representation.TransactionField;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper for converting Json Object with appropriate data to the {@link HistoryTransactionDetail} object for EmergencyIncident model.
 */
public class HistoryTransactionDetailMapper {

    private static final Map<String, Setter<HistoryTransactionDetail>> setters = new HashMap<>();

    static {
        setters.put(InterfaceConstants.EmergencyIncident.Dispatches.CadCloudHistoryTransactions.TRANSACTION_SUB_TYPE, new StringSetter<>(HistoryTransactionDetail::setTransactionSubType));
        setters.put(InterfaceConstants.EmergencyIncident.Dispatches.CadCloudHistoryTransactions.FIELDS, (model, value) -> {
            TransactionFieldMapper transactionFieldMapper = new TransactionFieldMapper();
            List<TransactionField> transactionFields = transactionFieldMapper.createAndMapToTransactionField(((JsonElement) value).getAsJsonArray());
            model.setFields(transactionFields);
        });
    }

    /**
     * Creates {@link HistoryTransactionDetail} object and sets mapped data from json to it.
     *
     * @param data json data.
     * @return {@link HistoryTransactionDetail} object with mapped data.
     */
    public HistoryTransactionDetail createAndMapToHistoryTransactionDetail(JsonObject data) {
        HistoryTransactionDetail historyTransactionDetail = new HistoryTransactionDetail();
        if (data != null) {
            mapToHistoryTransactionDetail(data, historyTransactionDetail);
        }
        return historyTransactionDetail;

    }

    /**
     * Sets mapped data from json to the {@link HistoryTransactionDetail} object.
     *
     * @param data json data.
     * @param historyTransactionDetail target object.
     */
    private void mapToHistoryTransactionDetail(JsonObject data, HistoryTransactionDetail historyTransactionDetail) {
        setters.forEach((key, value) -> {
            if (data.get(key) != null) {
                value.accept(historyTransactionDetail, data.get(key));
            }
        });
    }
}
