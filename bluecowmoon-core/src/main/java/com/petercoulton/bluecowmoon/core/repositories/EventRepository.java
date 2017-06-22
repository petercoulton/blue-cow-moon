package com.petercoulton.bluecowmoon.core.repositories;

import com.petercoulton.bluecowmoon.core.entities.events.BaseEvent;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface EventRepository extends MongoRepository<BaseEvent, ObjectId> {
    List<BaseEvent> findAllByCreatedDateAfter(Date date);

    @Query("{ $and: [ { '_class': { $eq: ?0 } }, { 'createdDate': { $gt: ?1 } } ] }")
    List<BaseEvent> findByTypeAndCreatedDateAfter(final String type, final Date date);
}
