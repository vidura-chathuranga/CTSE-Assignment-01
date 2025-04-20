package com.restaurant.menu.controller;

import com.restaurant.menu.models.MenuItem;
import com.restaurant.menu.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/menuItems")
@RequiredArgsConstructor
@Log4j2
public class MenuItemController {
    private final MenuItemService menuItemService;

    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) {
        MenuItem createdMenuItem = menuItemService.createMenuItem(menuItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMenuItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> findMenuItemById(@PathVariable("id") String id) {
        Optional<MenuItem> menuItemOptional = menuItemService.findMenuItemById(new ObjectId(id));
        return menuItemOptional.map(menuItem -> ResponseEntity.ok().body(menuItem))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> findAllMenuItems() {
        List<MenuItem> menuItems = menuItemService.findAllMenuItems();
        return ResponseEntity.ok().body(menuItems);
    }

    @GetMapping("/notDeleted")
    public ResponseEntity<List<MenuItem>> findAllNotDeletedMenuItems() {
        List<MenuItem> menuItems = menuItemService.findAllNotDeletedMenuItems();
        return ResponseEntity.ok().body(menuItems);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable("id") String id, @RequestBody MenuItem updatedMenuItem) {
        MenuItem menuItem = menuItemService.updateMenuItem(new ObjectId(id), updatedMenuItem);
        if (menuItem != null) {
            return ResponseEntity.ok().body(menuItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable("id") String id) {
        boolean deleted = menuItemService.deleteMenuItem(new ObjectId(id));
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}