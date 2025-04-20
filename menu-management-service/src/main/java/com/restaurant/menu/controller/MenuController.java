package com.restaurant.menu.controller;

import com.restaurant.menu.models.Menu;
import com.restaurant.common.models.embeddable.Status;
import com.restaurant.common.models.Audit;
import com.restaurant.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Optional;

@RestController //class is a REST controller for handling API requests
@RequestMapping(path = "/menus") //sets the base path for all the controller's endpoints "/menus"
@RequiredArgsConstructor // This Lombok annotation reduces boilerplate code for constructor injection.
@Log4j2 // This Lombok annotation provides a logger for logging messages.
public class MenuController {
    private final MenuService menuService;

    // POST request to create a menu by calling the MenuService createMenu method.
    @PostMapping
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu){
        // show message in terminal when creating menu
        log.info("Creating menu: {}", menu);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuService.createMenu(menu));
    }

    // PUT request to update a menu using the menu ID by calling the MenuService updateMenu method.
    @PutMapping(path = "/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable ObjectId id, @RequestBody Menu menu){
        // show message in terminal when updating menu
        log.info("Updating menu with id: {}", id);

        // Call the MenuService to update the menu and store the returned updated menu (if successful) in updatedMenu.
        Menu updatedMenu = menuService.updateMenu(menu, id);
        if (updatedMenu != null) {
            return ResponseEntity.ok(updatedMenu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET request to retrieve all menus created.
    @GetMapping
    public ResponseEntity<List<Menu>> getAllMenus() {
        // show message in terminal when retrieving all menus
        log.info("Retrieving all menus");
        // Create a List to retrieve and store all the menu objects
        // calls the getAllMenus() method in the MenuService class.
        List<Menu> menus = menuService.getAllMenus();
        return ResponseEntity.ok(menus);
    }

    // Handles GET requests to retrieve a Menu by its ID.
    // Receives the ID from the path variable "{id}".
    @GetMapping(path = "/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable ObjectId id) {
        // Log a message indicating retrieval of a menu by its id.
        log.info("Retrieving menu with id: {}", id);

        // Call the MenuService to retrieve a Menu by its id and store the result in an Optional object (might be empty).
        Optional<Menu> menu = menuService.getMenuById(id);

        // Check if the Optional object has a value (meaning the menu was found)
        if (menu.isPresent()) {
            // If the menu was found, extract the Menu object from the Optional and return it in a ResponseEntity with OK status
            return ResponseEntity.ok(menu.get());
        } else {
            // If the menu wasn't found, return a ResponseEntity with NOT_FOUND status indicating the menu wasn't found
            return ResponseEntity.notFound().build();
        }
    }

    // Handles DELETE requests to delete a Menu by its ID.
    // Receives the ID from the path variable "{id}".
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteMenuById(@PathVariable ObjectId id) {
        // Log a message indicating deletion of a menu by its id.
        log.info("Deleting menu with id: {}", id);

        // Call the MenuService method to delete the Menu by its id.
        menuService.deleteMenuById(id);
        return ResponseEntity.noContent().build(); // 204 No Content response
    }

    // activate menu by changing the status in the Audit class which is inherited
    // by the Menu class to "ACTIVE"
    // Handles PATCH requests to activate a Menu by its ID.
    // Receives the ID from the path variable "{id}".
    @PatchMapping(path = "/{id}/activate")
    public ResponseEntity<Menu> activateMenu(@PathVariable ObjectId id) {
        // Log a message indicating activation of a menu by its id
        log.info("Activating menu with id: {}", id);

        // Call the MenuService to retrieve a Menu by its id and store the result in an Optional object (might be empty)
        Optional<Menu> menu = menuService.getMenuById(id);

        // Check if the Optional object has a value (meaning the menu was found)
        if (menu.isPresent()) {
            // If the menu was found, extract the Menu object and set its status to ACTIVE
            Menu updatedMenu = menu.get();
            updatedMenu.setStatus(Status.ACTIVE);
            // Call the MenuService to update the menu with the activated status
            menuService.updateMenu(updatedMenu, id);
            return ResponseEntity.ok(updatedMenu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // deactivate menu by changing the status in the Audit class which is inherited
    // by the Menu class to "INACTIVE"
    // Handles PATCH requests to deactivate a Menu by its ID.
    // Receives the ID from the path variable "{id}".
    @PatchMapping(path = "/{id}/deactivate")
    public ResponseEntity<Menu> deactivateMenu(@PathVariable ObjectId id) {
        // Log a message indicating deactivation of a menu by its id.
        log.info("Deactivating menu with id: {}", id);

        // Call the MenuService to retrieve a Menu by its id and store the result in an Optional object (might be empty)
        Optional<Menu> menu = menuService.getMenuById(id);

        // Check if the Optional object has a value (meaning the menu was found)
        if (menu.isPresent()) {
            Menu updatedMenu = menu.get();
            updatedMenu.setStatus(Status.INACTIVE);
            // Call the MenuService to update the menu with the deactivated status
            menuService.updateMenu(updatedMenu, id);
            return ResponseEntity.ok(updatedMenu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}