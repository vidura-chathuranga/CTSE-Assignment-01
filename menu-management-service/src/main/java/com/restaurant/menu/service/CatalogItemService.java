package com.restaurant.menu.service;
import com.restaurant.menu.models.CatalogItem;
import com.restaurant.menu.repository.CatalogItemRepository;
import org.bson.types.ObjectId;
import java.util.List;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Log4j2

public class CatalogItemService{

    private final CatalogItemRepository catalogRepository;

    // Creates a new catalog object and saves it to the database using the injected catalogRepository which calls MongoRepository's save method..
    public CatalogItem createCatalogItem(CatalogItem catalog){
        return catalogRepository.save(catalog);
    }

    // Retrieves all catalog objects from the database using the injected catalogRepository which calls MongoRepository's findAll method.
    public List<CatalogItem> getAllCatalogItems() {
        return catalogRepository.findAll();
    }

    //  Retrieves a Catalog object by its unique identifier (ObjectId) using the injected catalogRepository which calls MongoRepository's findById method and passes the catalog id as a parameter.
    public Optional<CatalogItem> getCatalogItemById(ObjectId id) {
        return catalogRepository.findById(id);
    }

    // Updates an existing catalog object in the database using the injected catalogRepository.
    // First finds the existing catalog by its id, then updates its fields based on the provided catalog object,
    // and finally saves the updated catalog.
    public CatalogItem updateCatalogItem(CatalogItem catalog, ObjectId id) {
        // Find the existing catalog by id
        Optional<CatalogItem> existingCatalog = catalogRepository.findById(id);
        if (existingCatalog.isPresent()) {
            CatalogItem catalogToUpdate = existingCatalog.get();
            // Update existing catalog fields (excluding id) based on the provided catalog object
            catalogToUpdate.setName(catalog.getName());
            catalogToUpdate.setDescription(catalog.getDescription());
            catalogToUpdate.setType(catalog.getType());
            catalogToUpdate.setPrice(catalog.getPrice());
            catalogToUpdate.setStockQuantity(catalog.getStockQuantity());
            catalogToUpdate.setStatus(catalog.getStatus());
            return catalogRepository.save(catalogToUpdate);
        } else {
            return null;
        }
    }

    // Deletes a catalog from the database based on its identifier (ObjectId) using the injected catalogRepository.
    // Logs a message before deletion.
    public void deleteCatalogItemById(ObjectId id){
        log.info("Deleting catalog with id: {}", id);
        catalogRepository.deleteById(id);
    }




}
