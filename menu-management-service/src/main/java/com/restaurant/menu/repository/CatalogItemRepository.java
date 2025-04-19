package com.restaurant.menu.repository;

import com.restaurant.menu.models.CatalogItem;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CatalogItemRepository extends MongoRepository<CatalogItem, ObjectId> {
}
