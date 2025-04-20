package com.restaurant.menu.service;

import com.restaurant.menu.models.Menu;
import com.restaurant.common.models.Audit;
import com.restaurant.common.models.embeddable.Status;
import com.restaurant.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.bson.types.ObjectId;
import java.util.List;

// This class provides services for managing the Menus.
@Service
@RequiredArgsConstructor
@Log4j2
public class MenuService {
    // Injected dependency for interacting with Menu data in the database.
    private final MenuRepository menuRepository;

    // Creates a new Menu object and saves it to the database using the injected MenuRepository which calls MongoRepository's save method..
    public Menu createMenu(Menu menu){
        return menuRepository.save(menu);
    }

    // Retrieves all Menu objects from the database using the injected MenuRepository which calls MongoRepository's findAll method.
    public List<Menu> getAllMenus() { return menuRepository.findAll();
    }

    // Retrieves a Menu object by its unique identifier (ObjectId) using the injected MenuRepository which calls MongoRepository's findById method and passes the menu id as a parameter.
    public Optional<Menu> getMenuById(ObjectId id) {
        return menuRepository.findById(id);
    }

    // Updates an existing Menu object in the database using the injected MenuRepository.
    // First finds the existing Menu by its id, then updates its fields based on the provided Menu object,
    // and finally saves the updated Menu.
    public Menu updateMenu(Menu menu, ObjectId id) {
        // Find the existing menu by id
        Optional<Menu> existingMenu = menuRepository.findById(id);
        if (existingMenu.isPresent()) {
            Menu menuToUpdate = existingMenu.get();
            // Update existing menu fields (excluding id) based on the provided menu object
            menuToUpdate.setName(menu.getName());
            menuToUpdate.setDescription(menu.getDescription());
            menuToUpdate.setCategories(menu.getCategories());
            menuToUpdate.setTags(menu.getTags());
            menuToUpdate.setStatus(menu.getStatus());
            return menuRepository.save(menuToUpdate);
        } else {
            return null;
        }
    }

    // Deletes a Menu from the database based on its identifier (ObjectId) using the injected MenuRepository.
    // Logs a message before deletion.
    public void deleteMenuById(ObjectId id){
        log.info("Deleting menu with id: {}", id);
        menuRepository.deleteById(id);
    }
}
