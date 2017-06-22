package com.petercoulton.bluecowmoon.core.entities.events;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document
public abstract class BaseEvent implements Serializable {

    @Id
    private ObjectId id;

    private ObjectId correlationId;

    @CreatedDate
    private Date createdDate;

    public BaseEvent() {
        super();
    }

    public BaseEvent(final ObjectId correlationId) {
        super();
        this.correlationId = correlationId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(final ObjectId id) {
        this.id = id;
    }

    public ObjectId getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(final ObjectId correlationId) {
        this.correlationId = correlationId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "BaseEvent{" +
                "id=" + id +
                ", correlationId=" + correlationId +
                ", createdDate=" + createdDate +
                '}';
    }
}
