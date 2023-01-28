package com.itf.training.recipeservice.recipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private static List<Recipe> recipes = new ArrayList<>(Arrays.asList(
            new Recipe(1L, "Garlic Butter Rice", "http://www.recipezaar.com/Garlic-Butter-Rice-240456",
                    "cube chicken bouillon, garlic, margarine, water, rice", "http://img.recipepuppy.com/37625.jpg"),
            new Recipe(2L, "Grandma's Tomato Soup", "http://allrecipes.com/Recipe/Grandmas-Tomato-Soup/Detail.aspx",
                    "butter, flour, salt, sugar, tomato juice, water, egg noodles",
                    "http://img.recipepuppy.com/19234.jpg"),
            new Recipe(3L, "Fish Marinade for People Who Hate Fish",
                    "http://www.recipezaar.com/Fish-Marinade-for-People-Who-Hate-Fish-265932",
                    "basil, garlic, lemon juice, olive oil, paprika, black pepper, salt, shallot",
                    "http://img.recipepuppy.com/565418.jpg"),
            new Recipe(4L, "Baked King Fish/ Cod in Tomato sauce",
                    "http://www.recipezaar.com/Baked-King-Fish-Cod-in-Tomato-sauce-86892",
                    "chili powder, cumin, fennel seed, garam masala, garlic, ginger, green chilies, mustard seed, onions, salt, tomato, turmeric, vegetable oil",
                    "http://img.recipepuppy.com/122495.jpg"),
            new Recipe(5L, "Fish Vinaigrette",
                    "http://www.recipezaar.cimport org.springframework.web.bind.annotation.GetMapping;om/Fish-Vinaigrette-88936",
                    "bay leaf, capers, cod, parsley, tarragon, lemon juice, lemon juice, lemon zest, olive oil, onions, black pepper, peppercorn, salt, tarragon vinegar, tarragon vinegar, water",
                    "http://img.recipepuppy.com/565769.jpg"),
            new Recipe(6L, "Coconut Fish A La Dominicana Recipe",
                    "http://www.grouprecipes.com/45244/coconut-fish-a-la-dominicana.html",
                    "adobo seasoning, cilantro, red snapper, coconut milk, oregano, salt, orange, seeds, tomato sauce",
                    "http://img.recipepuppy.com/348803.jpg"),
            new Recipe(7L, "Fried Rice", "http://www.recipezaar.com/Fried-Rice-202571",
                    "ham, rice, eggs, ginger, garlic, onions, peas, salt, green onion, vegetable oil",
                    "http://img.recipepuppy.com/586068.jpg"),
            new Recipe(18L, "Spiced Tomato Drink", "http://allrecipes.com/Recipe/Spiced-Tomato-Drink/Detail.aspx",
                    "allspice, brown sugar, cinnamon, lemon juice, black pepper, salt",
                    "http://img.recipepuppy.com/16496.jpg"),
            new Recipe(19L, "Kansas Tomato Sandwich", "http://allrecipes.com/Recipe/Kansas-Tomato-Sandwich/Detail.aspx",
                    "butter, lettuce, salt, tomato, american cheese, bread", "http://img.recipepuppy.com/15863.jpg")));

    @GetMapping
    public List<Recipe> index() {
        return recipes;
    }

    @GetMapping("/{id}")
    public Recipe get(@PathVariable("id") Long id) {
        return recipes.stream().filter(x -> x.getId() == id).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found."));
    }

    @PostMapping
    public Recipe create(@RequestBody Recipe recipe) {
        recipe.setId(recipes.stream().mapToLong(recipe1 -> recipe1.getId()).max().orElse(0) + 1);
        recipes.add(recipe);
        return recipe;
    }

    @PutMapping("/{id}")
    public Recipe update(@PathVariable Long id, @RequestBody Recipe recipe) {
        recipes.stream().filter(recipe1 -> recipe1.getId() == id).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found."));
        recipe.setId(id);
        recipes = recipes.stream().map(recipe1 -> recipe1.getId() == id ? recipe : recipe1)
                .collect(Collectors.toList());
        return recipe;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        recipes.stream().filter(recipe -> recipe.getId() == id).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found."));
        recipes = recipes.stream().filter(recipe -> recipe.getId() != id).collect(Collectors.toList());
    }

}
