package recipeditor.command;

import recipeditor.recipe.RecipeList;

import java.util.ArrayList;

public class FindCommand extends Command {
    public static final String COMMAND_TYPE = "/find";
    private static final String COMMAND_SYNTAX = "/find <ingredient/title>";
    private static final String COMMAND_FUNCTION = "For the given ingredient or title,"
            + " find recipes which contains it.";

    public static final String CORRECT_FORMAT = "The input should be '/find (recipeTitle or ingredientName).'";
    public String findInput;


    public FindCommand() {
        super(COMMAND_SYNTAX, COMMAND_FUNCTION);
    }

    public FindCommand(String findInput) {
        this();
        this.findInput = findInput;
    }

    /**
     * Execute find command, which find the name of recipes containing a given string
     * in its title or ingredients.
     *
     * @return CommandResult list of found results
     */
    public CommandResult execute() {
        ArrayList<String> foundRecipeList = RecipeList.findRecipeTitles(findInput);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < foundRecipeList.size(); i++) {
            output.append(String.format("%n%d. %s", i + 1, foundRecipeList.get(i)));
        }
        return new CommandResult(output.toString());
    }
}
