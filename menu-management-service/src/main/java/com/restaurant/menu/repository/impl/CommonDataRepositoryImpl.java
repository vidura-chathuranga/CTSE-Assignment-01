package com.restaurant.menu.repository.impl;

import com.restaurant.common.models.Audit;
import com.restaurant.common.models.embeddable.Status;
import com.restaurant.menu.exception.UpdateOperationFailedException;
import com.restaurant.menu.repository.CommonDataRepository;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class CommonDataRepositoryImpl<T> implements CommonDataRepository<T> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CommonDataRepositoryImpl(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<T> find(Query query, Class<T> clazz) {
        return mongoTemplate.find(query, clazz);
    }

    @Override
    public Audit updateStatus(final ObjectId id, final long version, final Status status,
                              final Class<T> clazz) {
        Query query = new Query()
                .addCriteria(Criteria.where("id").is(id.toHexString()))
                .addCriteria(Criteria.where("version").is(version));
        Update update = new Update().set("status", status.name());

        log.info("updateStatus query details. query: {}, update:{}", query, update);
        return update(query, update, clazz);
    }

    @Override
    public Audit update(final Query query, final Update update, final Class<T> clazz) {
        @SuppressWarnings("unchecked") final Audit result = (Audit) mongoTemplate
                .findAndModify(query, update, FindAndModifyOptions.options().returnNew(true), clazz);

        if (result == null) {
            final String message =
                    "Update failed. Either a document does not exist for the given criteria, or trying to update a stale object";
            log.warn(message);
            throw new UpdateOperationFailedException(message);
        }

        return result;
    }

    @Override
    public Page<T> find(Query query, Class<T> clazz, Pageable pageable) {
        if (pageable == null) {
            pageable = PageRequest.of(0, 20);
        }
        query.with(pageable);
        return PageableExecutionUtils.getPage(find(query, clazz), pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), clazz));
    }

    @Override
    public Page<T> convertToPage(List<T> result, Pageable pageable, Query query, Class<T> clazz) {
        if (pageable == null) {
            pageable = PageRequest.of(0, 20);
        }
        query.with(pageable);
        return PageableExecutionUtils.getPage(result, pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), clazz));
    }

}