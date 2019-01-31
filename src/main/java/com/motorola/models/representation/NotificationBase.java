package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.motorola.models.Config;

import java.io.Serializable;
import java.util.Date;

/**
 * Header for on prem notification SB messages
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class NotificationBase implements Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private String serviceId;

    private String customerId;

    private String notificationType;

    private Date whenSubmitted;

    /**
     * Returns the value of property "serviceId".
     * Define the service (application) subscribe to the Service Bus (For filtering)
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * Updates the value of property "serviceId".
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * Returns the value of property "customerId".
     * Customer ID == Tenant ID
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Updates the value of property "customerId".
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * Returns the value of property "notificationType".
     * Enum to represent the types of SB notification message.
     */
    public String getNotificationType() {
        return notificationType;
    }

    /**
     * Updates the value of property "notificationType".
     */
    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    /**
     * Returns the value of property "whenSubmitted".
     * The time stamp when the book on response notification is submitted
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Config.DATETIME_FORMAT)
    public Date getWhenSubmitted() {
        return whenSubmitted;
    }

    /**
     * Updates the value of property "whenSubmitted".
     */
    public void setWhenSubmitted(Date whenSubmitted) {
        this.whenSubmitted = whenSubmitted;
    }

}
