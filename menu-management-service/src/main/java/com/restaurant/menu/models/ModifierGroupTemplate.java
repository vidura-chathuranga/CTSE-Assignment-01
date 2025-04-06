package com.restaurant.menu.models;

import com.restaurant.common.models.Audit;
import com.restaurant.menu.models.embeddable.Modifier;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Document(collection = "MODIFIER_GROUP_TEMPLATE")
public class ModifierGroupTemplate extends Audit {

    @NotNull
    private String name;

    private List<@Valid Modifier> modifiers;

    @PositiveOrZero
    private int maxCount;

    @PositiveOrZero
    private int minCount;
}