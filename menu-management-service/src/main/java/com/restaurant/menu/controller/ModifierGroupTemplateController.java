package com.restaurant.menu.controller;

import com.restaurant.common.models.embeddable.Status;
import com.restaurant.menu.models.ModifierGroupTemplate;
import com.restaurant.menu.service.ModifierGroupTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/modifier-group-templates")
@RequiredArgsConstructor
@Log4j2
public class ModifierGroupTemplateController {

    private final ModifierGroupTemplateService modifierGroupTemplateService;

    @PostMapping
    public ResponseEntity<ModifierGroupTemplate> createModifierGroupTemplate(@RequestBody ModifierGroupTemplate modifierGroupTemplate) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modifierGroupTemplateService.createModifierGroupTemplate(modifierGroupTemplate));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ModifierGroupTemplate> getModifierGroupTemplateById(@PathVariable ObjectId id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(modifierGroupTemplateService.getModifierGroupTemplateById(id));
    }

    @GetMapping
    public ResponseEntity<List<ModifierGroupTemplate>> getAllModifierGroupTemplates() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(modifierGroupTemplateService.getAllModifierGroupTemplates());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ModifierGroupTemplate> updateModifierGroupTemplate(@PathVariable ObjectId id, @RequestBody ModifierGroupTemplate modifierGroupTemplate) {
        return ResponseEntity.status(HttpStatus.OK).
                body(modifierGroupTemplateService.updateModifierGroupTemplate(id, modifierGroupTemplate));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ModifierGroupTemplate> deleteModifierGroupTemplate(@PathVariable("id") ObjectId id,
                                                                             @RequestHeader("If-Match") long version) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(modifierGroupTemplateService.updateStatus(id, version, Status.DELETED));
    }

    @PatchMapping(path = "/{id}/status/{status}")
    public ResponseEntity<ModifierGroupTemplate> updateModifierGroupTemplateStatus(@PathVariable ObjectId id,
                                                                                   @RequestHeader("If-Match") long version,
                                                                                   @PathVariable Status status) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(modifierGroupTemplateService.updateStatus(id, version, status));
    }
}