package com.restaurant.menu.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.restaurant.common.models.Audit;
import com.restaurant.common.models.embeddable.Status;


@Service
public interface CommonDataRepository<T> {

    List<T> find(Query query, Class<T> clazz);

    Page<T> find(Query query, Class<T> clazz, Pageable pageable);

    Page<T> convertToPage(List<T> result, Pageable pageable, Query query, Class<T> clazz);

    Audit updateStatus(ObjectId id, long version, Status status, Class<T> clazz);

    Audit update(Query query, Update update, Class<T> clazz);
}