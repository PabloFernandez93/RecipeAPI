package com.itf.training.recipeservice.recipes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Recipe {
    // public Recipe(Long id, String title, String href, String ingredients, String thumbnail) {
    //     this.id = id;
    //     this.title = title;
    //     this.href = href;
    //     this.ingredients = ingredients;
    //     this.thumbnail = thumbnail;
    // }

    private Long id;
    private String title;
    private String href;
    private String ingredients;
    private String thumbnail;
}
