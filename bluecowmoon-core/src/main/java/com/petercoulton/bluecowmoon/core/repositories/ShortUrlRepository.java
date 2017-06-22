package com.petercoulton.bluecowmoon.core.repositories;

import com.petercoulton.bluecowmoon.core.entities.ShortUrlEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShortUrlRepository extends MongoRepository<ShortUrlEntity, ObjectId> {
    ShortUrlEntity findOneByShortUrlName(final String shortUrlName);
}
