package com.restaurant.menu.models;

import com.restaurant.common.models.Audit;
import com.restaurant.menu.models.embeddable.CatalogItemUsage;
import com.restaurant.menu.models.embeddable.ModifierGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Document(collection = "MENU_ITEM")
public class MenuItem extends Audit {
    @NotNull
    private String name;

    @NotNull
    private String description;

    private List<@Valid ModifierGroup> modifierGroups;

    private List<@Valid CatalogItemUsage> ingredients;

    private List<@Valid CatalogItemUsage> packaging;

    private List<ObjectId> allergens;

    private List<ObjectId> classifications;

    private List<String> tags;

    private String imageUrl;

    private String barcode;

    @Positive
    private int preparationTime;

    @PositiveOrZero
    private double price;
}