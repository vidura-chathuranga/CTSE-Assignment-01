package com.restaurant.menu.repository;

import com.restaurant.menu.models.Menu;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// This interface extends Spring Data MongoDB's MongoRepository to provide methods
// for interacting with Menu data stored in a MongoDB database.
@Repository
public interface MenuRepository extends MongoRepository<Menu, ObjectId> {
}
