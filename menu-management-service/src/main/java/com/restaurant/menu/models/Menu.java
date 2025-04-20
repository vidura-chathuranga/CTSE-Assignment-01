package com.restaurant.menu.models;

import com.restaurant.common.models.Audit;
import com.restaurant.menu.models.embeddable.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

// Menu model class inherits properties from Audit class.
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Document(collection = "MENU")
public class Menu extends Audit {

    @NotNull
    private String name;

    private String description;

    @NotEmpty
    private List<Category> categories;

    private List<String> tags;
}