package com.restaurant.menu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import com.restaurant.menu.service.CatalogItemService;
import org.springframework.http.ResponseEntity;
import com.restaurant.menu.models.CatalogItem;
import org.springframework.http.HttpStatus;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Optional;
import com.restaurant.common.models.embeddable.Status;





@RestController //class is a REST controller for handling API requests
@RequestMapping(path = "/catalogItem") //sets the base path for all the controller's endpoints "/catalogItem"
@RequiredArgsConstructor // This Lombok annotation reduces boilerplate code for constructor injection.
@Log4j2 // This Lombok annotation provides a logger for logging messages.
public class CatalogItemController {


    private final CatalogItemService catalogItemService;

    // POST request to create a catalog by calling the CatalogItemService createCatalogItem method.
    @PostMapping
    public ResponseEntity<CatalogItem> createCatalogItem(@RequestBody CatalogItem catalogItem){
        // show message in terminal when creating catalogItem
        log.info("Creating Catalog: {}", catalogItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(catalogItemService.createCatalogItem(catalogItem));
    }

    // PUT request to update a catalog using the catalog ID by calling the CatalogItemService updateCatalog method.
    @PutMapping(path = "/{id}")
    public ResponseEntity<CatalogItem> updateCatalogItem(@PathVariable ObjectId id, @RequestBody CatalogItem catalogItem){
        // show message in terminal when updating catalog
        log.info("Updating catalog with id: {}", id);

        // Call the catalogService to update the catalog and store the returned updated catalog (if successful) in updatedcatalog.
        CatalogItem updatedCatalogItem = catalogItemService.updateCatalogItem(catalogItem, id);
        if (updatedCatalogItem != null) {
            return ResponseEntity.ok(updatedCatalogItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET request to retrieve all catalogs created.
    @GetMapping
    public ResponseEntity<List<CatalogItem>> getAllCatalogItems() {
        // show message in terminal when retrieving all catalogs
        log.info("Retrieving all catalogs");
        // Create a List to retrieve and store all the menu objects
        // calls the getAllCatalogs() method in the CatalogItemService class.
        List<CatalogItem> catalogItem = catalogItemService.getAllCatalogItems();
        return ResponseEntity.ok(catalogItem);
    }

    // Handles GET requests to retrieve a Menu by its ID.
    // Receives the ID from the path variable "{id}".
    @GetMapping(path = "/{id}")
    public ResponseEntity<CatalogItem> getCatalogItemById(@PathVariable ObjectId id) {
        // Log a message indicating retrieval of a menu by its id.
        log.info("Retrieving catalog with id: {}", id);

        // Call the MenuService to retrieve a Menu by its id and store the result in an Optional object (might be empty).
        Optional<CatalogItem> catalogItem = catalogItemService.getCatalogItemById(id);

        // Check if the Optional object has a value (meaning the menu was found)
        if (catalogItem.isPresent()) {
            // If the catalogItem was found, extract the catalogItem object from the Optional and return it in a ResponseEntity with OK status
            return ResponseEntity.ok(catalogItem.get());
        } else {
            // If the catalogItem wasn't found, return a ResponseEntity with NOT_FOUND status indicating the menu wasn't found
            return ResponseEntity.notFound().build();
        }
    }

    // Handles DELETE requests to delete a CatalogItem by its ID.
    // Receives the ID from the path variable "{id}".
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCatalogItemById(@PathVariable ObjectId id) {
        // Log a message indicating deletion of a catalog by its id.
        log.info("Deleting catalog with id: {}", id);

        // Call the CatalogItemService method to delete the Catalog by its id.
        catalogItemService.deleteCatalogItemById(id);
        return ResponseEntity.noContent().build(); // 204 No Content response
    }

    // activate catalog by changing the status in the Audit class which is inherited
    // by the catalog class to "ACTIVE"
    // Handles PATCH requests to activate a catalog by its ID.
    // Receives the ID from the path variable "{id}".
    @PatchMapping(path = "/{id}/activate")
    public ResponseEntity<CatalogItem> activateCatalogItem(@PathVariable ObjectId id) {
        // Log a message indicating activation of a catalog by its id
        log.info("Activating catalog with id: {}", id);

        // Call the catalogItemService to retrieve a catalog by its id and store the result in an Optional object (might be empty)
        Optional<CatalogItem> catalogItem = catalogItemService.getCatalogItemById(id);

        // Check if the Optional object has a value (meaning the catalog was found)
        if (catalogItem.isPresent()) {
            // If the catalog was found, extract the catalog object and set its status to ACTIVE
            CatalogItem updateCatalogItem = catalogItem.get();
            updateCatalogItem.setStatus(Status.ACTIVE);
            // Call the catalogItemService to update the catalogItem with the activated status
            catalogItemService.updateCatalogItem(updateCatalogItem, id);
            return ResponseEntity.ok(updateCatalogItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // deactivate catalogItem by changing the status in the Audit class which is inherited
    // by the catalogItem class to "INACTIVE"
    // Handles PATCH requests to deactivate a catalogItem by its ID.
    // Receives the ID from the path variable "{id}".
    @PatchMapping(path = "/{id}/deactivate")
    public ResponseEntity<CatalogItem> deactivateMenu(@PathVariable ObjectId id) {
        // Log a message indicating deactivation of a catalogItem by its id.
        log.info("Deactivating catalogItem with id: {}", id);

        // Call the MenuService to retrieve a catalogItem by its id and store the result in an Optional object (might be empty)
        Optional<CatalogItem> catalogItem = catalogItemService.getCatalogItemById(id);

        // Check if the Optional object has a value (meaning the catalogItem was found)
        if (catalogItem.isPresent()) {
            CatalogItem updatedCatalogItem = catalogItem.get();
            updatedCatalogItem.setStatus(Status.INACTIVE);
            // Call the CatalogItemService to update the catalogItem with the deactivated status
            catalogItemService.updateCatalogItem(updatedCatalogItem, id);
            return ResponseEntity.ok(updatedCatalogItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
