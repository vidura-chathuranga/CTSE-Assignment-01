package com.restaurant.menu.repository;

import com.restaurant.menu.models.MenuItem;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MenuItemRepository extends MongoRepository<MenuItem, ObjectId> {
    @Query("{ 'status': { $ne: 'DELETED' } }")
    List<MenuItem> findAllNotDeleted();
}