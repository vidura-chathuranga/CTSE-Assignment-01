package com.restaurant.menu.models.embeddable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

public class Modifier {

    @NotNull
    private String name;

    @NotNull
    private String description;

    private List<@Valid CatalogItemUsage> ingredients;

    private List<@Valid CatalogItemUsage> packaging;

    @PositiveOrZero
    private int maxLimit;

    @PositiveOrZero
    private double price;
}