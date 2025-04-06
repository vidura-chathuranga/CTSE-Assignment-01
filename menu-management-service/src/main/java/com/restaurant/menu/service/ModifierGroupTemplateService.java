package com.restaurant.menu.service;

import com.restaurant.common.models.embeddable.Status;
import com.restaurant.menu.models.ModifierGroupTemplate;
import com.restaurant.menu.repository.CommonDataRepository;
import com.restaurant.menu.repository.ModifierGroupTemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ModifierGroupTemplateService {

    private final ModifierGroupTemplateRepository modifierGroupTemplateRepository;
    private final CommonDataRepository<ModifierGroupTemplate> commonDataRepository;

    @Transactional
    public ModifierGroupTemplate updateStatus(final ObjectId id, final long version, final Status status) {
        final ModifierGroupTemplate modifierGroupTemplate = (ModifierGroupTemplate) commonDataRepository.
                updateStatus(id, version, status, ModifierGroupTemplate.class);

        if (modifierGroupTemplate != null && status == Status.DELETED) {
            // TODO delete the modifierGroupTemplate from corresponding menu items
        }

        return modifierGroupTemplate;
    }

    @Transactional
    public ModifierGroupTemplate createModifierGroupTemplate(@NotNull @Valid ModifierGroupTemplate modifierGroupTemplate) {
        return modifierGroupTemplateRepository.save(modifierGroupTemplate);
    }

    public ModifierGroupTemplate getModifierGroupTemplateById(ObjectId id) {
        return modifierGroupTemplateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ModifierGroupTemplate not found with id: " + id));
    }

    public List<ModifierGroupTemplate> getAllModifierGroupTemplates() {
        return modifierGroupTemplateRepository.findAll();
    }

    @Transactional
    public ModifierGroupTemplate updateModifierGroupTemplate(ObjectId id, @NotNull @Valid ModifierGroupTemplate modifierGroupTemplate) {
        ModifierGroupTemplate existingTemplate = getModifierGroupTemplateById(id);
        existingTemplate.setName(modifierGroupTemplate.getName());
        existingTemplate.setModifiers(modifierGroupTemplate.getModifiers());
        existingTemplate.setMaxCount(modifierGroupTemplate.getMaxCount());
        existingTemplate.setMinCount(modifierGroupTemplate.getMinCount());

        // TODO update the modifierGroupTemplate in menu items

        return modifierGroupTemplateRepository.save(existingTemplate);
    }
}