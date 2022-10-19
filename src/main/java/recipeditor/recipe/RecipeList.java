package recipeditor.recipe;

import java.util.ArrayList;

public class RecipeList {
    private static ArrayList<Recipe> recipes = new ArrayList<>();

    public RecipeList(ArrayList<Recipe> load) {
        recipes = new ArrayList<>();
        recipes.addAll(load);
    }

    public RecipeList() {
        this(null);
    }

    public static Recipe getRecipe(int index) {
        assert index >= 0 && index <= recipes.size();
        return recipes.get(index);
    }

    public static void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public static void deleteRecipe(int index) {
        assert index >= 0 && index <= recipes.size();
        recipes.remove(index);
    }

    public static void editRecipe(int index, Recipe newRecipe) {
        recipes.set(index, newRecipe);
    }

    public static Recipe getRecipeFromTitle(String recipleTitle) {
        for (Recipe r : recipes) {
            if (r.getTitle().equals(recipleTitle)) {
                return r;
            }
        }
        return null;
    }

    public static int getRecipeIndexFromTitle(String recipeTitle) {
        int i = 0;
        for (Recipe r : recipes) {
            if (r.getTitle().equals(recipeTitle)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static ArrayList<String> findRecipeTitles(String findInput) {
        ArrayList<String> foundRecipeTitlesList = new ArrayList<>();
        for (Recipe r : recipes) {
            // find recipe title according to recipe title
            if (r.getTitle().contains(findInput)) {
                foundRecipeTitlesList.add(r.getTitle());
            }
            // find recipe title according to ingredient name
            ArrayList<Ingredient> recipeIngredients = r.getIngredients();
            for (Ingredient i : recipeIngredients) {
                if (i.getName().contains(findInput)) {
                    foundRecipeTitlesList.add(r.getTitle());
                }
            }
        }
        return foundRecipeTitlesList;
    }

    public static int getSize() {
        return recipes.size();
    }
}
