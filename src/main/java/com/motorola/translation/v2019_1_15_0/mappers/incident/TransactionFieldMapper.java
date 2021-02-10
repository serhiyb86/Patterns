/*
 * Copyright 2021 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.TransactionField;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Mapper for converting Json Object with appropriate data to the {@link TransactionField} object for EmergencyIncident model.
 */
public class TransactionFieldMapper {

    private static final Map<String, Setter<TransactionField>> setters = new HashMap<>();

    static {
        setters.put(InterfaceConstants.EmergencyIncident.Dispatches.CadCloudHistoryTransactions.KEY, new StringSetter<>(TransactionField::setKey));
        setters.put(InterfaceConstants.EmergencyIncident.Dispatches.CadCloudHistoryTransactions.VALUE, new StringSetter<>(TransactionField::setValue));
    }

    /**
     * Creates {@link List <{@link TransactionField }>} instance from the incoming {@link JsonArray} object.
     *
     * @param array {@link JsonArray} instance
     * @return {@link List<TransactionField>} instance
     */
    public List<TransactionField> createAndMapToTransactionField(JsonArray array) {
        List<TransactionField> transactionFields = new ArrayList<>();
        array.forEach(jsonElement -> {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            transactionFields.add(mapToTransactionField(jsonObject.entrySet()));
        });
        return transactionFields;

    }

    /**
     * Maps Json data into single {@link TransactionField} object.
     *
     * @param data json data
     * @return {@link TransactionField} object
     */
    private TransactionField mapToTransactionField(Set<Map.Entry<String, JsonElement>> data) {
        TransactionField transactionField = new TransactionField();

        data.forEach(entry -> {
            Setter<TransactionField> consumer = setters.get(entry.getKey());
            if (consumer != null) {
                consumer.accept(transactionField, entry.getValue());
            }
        });
        return transactionField;
    }
}
