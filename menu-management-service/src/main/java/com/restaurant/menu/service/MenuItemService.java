package com.restaurant.menu.service;
import com.restaurant.menu.models.MenuItem;
import com.restaurant.common.models.embeddable.Status;
import com.restaurant.menu.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.bson.types.ObjectId;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    public Optional<MenuItem> findMenuItemById(ObjectId id) {
        return menuItemRepository.findById(id);
    }

    public List<MenuItem> findAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public List<MenuItem> findAllNotDeletedMenuItems() {
        return menuItemRepository.findAllNotDeleted();
    }

    public MenuItem updateMenuItem(ObjectId id, MenuItem updatedMenuItem) {
        Optional<MenuItem> existingMenuItemOptional = menuItemRepository.findById(id);
        if (existingMenuItemOptional.isPresent()) {
            MenuItem existingMenuItem = existingMenuItemOptional.get();
            existingMenuItem.setName(updatedMenuItem.getName());
            existingMenuItem.setDescription(updatedMenuItem.getDescription());
            existingMenuItem.setModifierGroups(updatedMenuItem.getModifierGroups());
            existingMenuItem.setIngredients(updatedMenuItem.getIngredients());
            existingMenuItem.setPackaging(updatedMenuItem.getPackaging());
            existingMenuItem.setAllergens(updatedMenuItem.getAllergens());
            existingMenuItem.setClassifications(updatedMenuItem.getClassifications());
            existingMenuItem.setTags(updatedMenuItem.getTags());
            existingMenuItem.setImageUrl(updatedMenuItem.getImageUrl());
            existingMenuItem.setBarcode(updatedMenuItem.getBarcode());
            existingMenuItem.setPreparationTime(updatedMenuItem.getPreparationTime());
            existingMenuItem.setPrice(updatedMenuItem.getPrice());
            return menuItemRepository.save(existingMenuItem);
        } else {
            throw new RuntimeException("MenuItem not found with id: " + id);
        }
    }

    public boolean deleteMenuItem(ObjectId id) {
        Optional<MenuItem> menuItemOptional = menuItemRepository.findById(id);
        if (menuItemOptional.isPresent()) {
            MenuItem menuItem = menuItemOptional.get();
            menuItem.setStatus(Status.DELETED);
            menuItemRepository.save(menuItem);
            return true;
        } else {
            return false;
        }
    }
}