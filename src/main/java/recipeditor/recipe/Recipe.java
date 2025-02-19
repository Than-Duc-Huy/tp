package recipeditor.recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

public class Recipe {
    private final ArrayList<Ingredient> ingredients;
    private final ArrayList<String> steps;
    private final Logger logger = Logger.getLogger("LOGS");
    private String title = "";
    private String description = "";

    public Recipe(String title, String description) {
        this.title = title;
        this.description = description;
        ingredients = new ArrayList<>();
        steps = new ArrayList<>();
    }

    public Recipe(String title) {
        this.title = title;
        ingredients = new ArrayList<>();
        steps = new ArrayList<>();
    }

    public Recipe() {
        ingredients = new ArrayList<>();
        steps = new ArrayList<>();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients.addAll(ingredients);
    }

    public void swapIngredients(int index1, int index2) throws IndexOutOfBoundsException {
        Collections.swap(ingredients, index1, index2);
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public Ingredient getIngredient(int index) throws IndexOutOfBoundsException {
        return this.ingredients.get(index);
    }

    public Ingredient getIngredientByName(String ingredientName) {
        for (Ingredient i : ingredients) {
            if (i.getName().equals(ingredientName)) {
                return i;
            }
        }
        return null;
    }

    public void deleteIngredient(int index) throws IndexOutOfBoundsException {
        this.ingredients.remove(index);
    }

    public void setIngredient(int index, Ingredient ingredient) throws IndexOutOfBoundsException {
        this.ingredients.set(index, ingredient);
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void addSteps(ArrayList<String> steps) {
        this.steps.addAll(steps);
    }

    public void swapSteps(int index1, int index2) throws IndexOutOfBoundsException {
        Collections.swap(steps, index1, index2);
    }

    public void addStep(String step) {
        steps.add(step);
    }

    public String getStep(int index) throws IndexOutOfBoundsException {
        return this.steps.get(index);
    }

    public void deleteStep(int index) throws IndexOutOfBoundsException {
        this.steps.remove(index);
    }

    public void setStep(int index, String step) throws IndexOutOfBoundsException {
        this.steps.set(index, step);
    }

    public String getIngredientAttributesFormatted() {
        StringBuilder recipeIngredientStringFormatted = new StringBuilder();
        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            String textShown = String.format("%s.%s/ %s /%s%n", i
                    + 1, ingredient.getName(), ingredient.getAmount(), ingredient.getUnit().trim());
            recipeIngredientStringFormatted.append(textShown);
        }
        //        logger.log(Level.INFO, "Get ingredients in" + title);
        return recipeIngredientStringFormatted.toString();
    }

    public String getStepAttributesFormatted() {
        StringBuilder recipeStepStringFormatted = new StringBuilder();
        for (int i = 0; i < steps.size(); i++) {
            String textShown = String.format("%d. %s%n", i + 1, getStep(i));
            recipeStepStringFormatted.append(textShown);
        }
        //        logger.log(Level.INFO, "Get steps");
        return recipeStepStringFormatted.toString();
    }

    public String getRecipeSaveableFormatted() {
        String recipeAttributesStringFormatted = "# TITLE \n"
                + title + "\n\n" + "# DESCRIPTION \n"
                + description.trim() + "\n\n" + "# INGREDIENTS <ingredient name> / <amount> / <unit> \n"  //Remove trim
                + getIngredientAttributesFormatted() + "\n" + "# STEPS \n"
                + getStepAttributesFormatted() + "\n\n";
        //        logger.log(Level.INFO, "Get attributes of " + title);
        return recipeAttributesStringFormatted;
    }

    public String getRecipeAttributesFormatted() {
        String recipeAttributesStringFormatted = "TITLE:\n"
                + title + "\n\n" + "DESCRIPTION:\n"
                + description.trim() + "\n\n" + "INGREDIENTS: "
                + "\n" + getIngredientAttributesFormatted() + "\n" + "STEPS: "
                + "\n" + getStepAttributesFormatted() + "\n\n";
        //        logger.log(Level.INFO, "Get attributes of " + title);
        return recipeAttributesStringFormatted;
    }

    public boolean isNotRecipeValid() {
        return (this.getTitle().isBlank() || this.getDescription().isBlank() || this.getIngredients().isEmpty()
                || this.getSteps().isEmpty());
    }
}
