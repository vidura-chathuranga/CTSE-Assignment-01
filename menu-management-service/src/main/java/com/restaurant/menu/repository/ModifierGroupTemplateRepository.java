package com.restaurant.menu.repository;

import com.restaurant.menu.models.ModifierGroupTemplate;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModifierGroupTemplateRepository extends MongoRepository<ModifierGroupTemplate, ObjectId> {
}