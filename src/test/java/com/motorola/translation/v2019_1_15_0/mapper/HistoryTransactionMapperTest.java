/*
 * Copyright 2021 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.motorola.models.representation.HistoryTransaction;
import com.motorola.models.representation.HistoryTransactionDetail;
import com.motorola.translation.v2019_1_15_0.mappers.incident.HistoryTransactionMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Contains unit tests related to the HistoryTransaction mapper.
 */
public class HistoryTransactionMapperTest {

    public static final String INC_CREATE_INCIDENT_CREATED = "[{\n" +
            "\"userId\": \"sds\",\n" +
            "\"deviceId\": \"DEVICE\",\n" +
            "\"insertedDateTime\": \"2021-02-09T14:30:38+02:00\",\n" +
            "\"transactionType\": \"INC CREATE\",\n" +
            "\"detail\": {\n" +
            "\"transactionSubType\": \"Incident Created\",\n" +
            "\"fields\": [{\n" +
            "\"key\": \"Table being accessed\",\n" +
            "\"value\": \"sycad\"\n" +
            "}, {\n" +
            "\"key\": \"Mode used\",\n" +
            "\"value\": \"A\"\n" +
            "}, {\n" +
            "\"key\": \"Universal Unique Identifier\",\n" +
            "\"value\": \"9A346CD4-75E2-4779-A220-A4B4118C86F9\"\n" +
            "}, {\n" +
            "\"key\": \"Time of access\",\n" +
            "\"value\": \"2021-02-09 14:30:38.0\"\n" +
            "}, {\n" +
            "\"key\": \"Miscellaneous data\",\n" +
            "\"value\": \"PRIMARY KEY FIELDS AND VALUES\\nsycad.callid    :     C856\\nsycad.type      :l\"\n" +
            "}\n" +
            "]\n" +
            "}\n" +
            "}\n" +
            "]";

    private final HistoryTransactionMapper historyTransactionMapper = new HistoryTransactionMapper();

    private static final Gson GSON = new Gson();

    @Test
    public void createAndMapToAddress_GeoVerifiedData_Test() {
        JsonArray testData = GSON.fromJson(INC_CREATE_INCIDENT_CREATED, JsonArray.class);
        List<HistoryTransaction> historyTransactions = historyTransactionMapper.createAndMapToCadCloudHistoryTransactions(testData);
        HistoryTransaction historyTransaction = historyTransactions.get(0);
        Assert.assertEquals("sds", historyTransaction.getUserId());
        Assert.assertEquals("DEVICE", historyTransaction.getDeviceId());
        Assert.assertEquals("2021-02-09T14:30:38+02:00", historyTransaction.getInsertedDateTime());
        Assert.assertEquals("INC CREATE", historyTransaction.getTransactionType());
        HistoryTransactionDetail historyTransactionDetail = historyTransaction.getDetail();
        Assert.assertEquals("Incident Created", historyTransactionDetail.getTransactionSubType());
        Assert.assertEquals(5, historyTransactionDetail.getFields().size());
    }
}
