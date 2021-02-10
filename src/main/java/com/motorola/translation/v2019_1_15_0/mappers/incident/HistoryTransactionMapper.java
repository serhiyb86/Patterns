/*
 * Copyright 2021 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.HistoryTransaction;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Mapper for converting Json Object with appropriate data to the {@link HistoryTransaction} object for EmergencyIncident model.
 */
public class HistoryTransactionMapper {

    private static final Map<String, Setter<HistoryTransaction>> setters = new HashMap<>();

    static {
        setters.put(InterfaceConstants.EmergencyIncident.Dispatches.CadCloudHistoryTransactions.USER_ID, new StringSetter<>(HistoryTransaction::setUserId));
        setters.put(InterfaceConstants.EmergencyIncident.Dispatches.CadCloudHistoryTransactions.DEVICE_ID, new StringSetter<>(HistoryTransaction::setDeviceId));
        setters.put(InterfaceConstants.EmergencyIncident.Dispatches.CadCloudHistoryTransactions.INSERTED_DATE_TIME, new StringSetter<>(HistoryTransaction::setInsertedDateTime));
        setters.put(InterfaceConstants.EmergencyIncident.Dispatches.CadCloudHistoryTransactions.TRANSACTION_TYPE, new StringSetter<>(HistoryTransaction::setTransactionType));
        setters.put(InterfaceConstants.EmergencyIncident.Dispatches.CadCloudHistoryTransactions.DETAIL, (model, value) -> model.setDetail(new HistoryTransactionDetailMapper().createAndMapToHistoryTransactionDetail((JsonObject) value)));
    }

    /**
     * Creates {@link List <{@link HistoryTransaction }>} instance from the incoming {@link JsonArray} object.
     *
     * @param array {@link JsonArray} instance
     * @return {@link List<HistoryTransaction>} instance
     */
    public List<HistoryTransaction> createAndMapToCadCloudHistoryTransactions(JsonArray array) {
        List<HistoryTransaction> historyTransactions = new ArrayList<>();
        array.forEach(jsonElement -> {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            historyTransactions.add(mapToHistoryTransaction(jsonObject.entrySet()));
        });
        return historyTransactions;

    }

    /**
     * Maps Json data into single {@link HistoryTransaction} object.
     *
     * @param data json data
     * @return {@link HistoryTransaction} object
     */
    private HistoryTransaction mapToHistoryTransaction(Set<Map.Entry<String, JsonElement>> data) {
        HistoryTransaction reportNumber = new HistoryTransaction();

        data.forEach(entry -> {
            Setter<HistoryTransaction> consumer = setters.get(entry.getKey());
            if (consumer != null) {
                consumer.accept(reportNumber, entry.getValue());
            }
        });
        return reportNumber;
    }
}
