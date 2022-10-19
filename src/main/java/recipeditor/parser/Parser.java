package recipeditor.parser;

import recipeditor.command.*;


import recipeditor.recipe.RecipeList;
import recipeditor.ui.AddMode;
import recipeditor.ui.EditMode;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {
    private static Logger logger = Logger.getLogger("LOGS");

    public static Command parseCommand(String input) {
        String[] parsed = input.split(" ");
        String commandWord = parsed[0].toLowerCase();

        switch (commandWord) {
        case AddCommand.COMMAND_TYPE:
            return parseAddCommand();
        case ListCommand.COMMAND_TYPE:
            return new ListCommand();
        case ExitCommand.COMMAND_TYPE:
            return new ExitCommand();
        case DeleteCommand.COMMAND_TYPE:
        case EditCommand.COMMAND_TYPE:
            return parseEditCommand(parsed);
        case ViewCommand.COMMAND_TYPE:
            return parseListAlterCommand(parsed, commandWord);
        case FindCommand.COMMAND_TYPE:
            return parseFindCommand(parsed);
        default:
            return new InvalidCommand();
        }
    }

    private static Command parseAddCommand() {
        AddMode add = new AddMode(); // Switch to Add Mode in here
        add.enterAddMode();
        add.exitAddMode();
        logger.log(Level.INFO, "Is the recipe valid? " + add.isValid);
        return new AddCommand(add.isValid, add.addedRecipe); // Pass validty and potential recipe to AddCommand
    }

    private static Command parseListAlterCommand(String[] parsed, String commandWord) {
        if (parsed.length == 2) {
            try {
                int index = Integer.parseInt(parsed[1]) - 1; // to account for 0-based indexing in recipelist
                if (commandWord.equals(ViewCommand.COMMAND_TYPE)) {
                    return new ViewCommand(index);
                }
                return new DeleteCommand(index);
            } catch (Exception e) {
                System.out.format("Exception: Wrong command Format%n"
                        + "Try the command in correct format: view/delete <index of task>%n");
                return new InvalidCommand();
            }
        }
        return new InvalidCommand();
    }

    private static Command parseEditCommand(String[] parsed) {
        EditMode edit = new EditMode();
        edit.enterEditMode(parsed[1]);
        edit.exitEditMode();
        return new EditCommand(RecipeList.getRecipeIndexFromTitle(parsed[1]), edit.getEditedRecipe());
    }

    private static Command parseFindCommand(String[] parsed) {
        if (parsed.length < 2) {
            return new InvalidCommand();
        }
        String FlagAndInputString = convertStringArrayToString(parsed);
        String[] FlagAndInput = FlagAndInputString.split(" ", 2);
        char flag = FlagAndInput[0].charAt(0);
        String input = FlagAndInput[1];
        return new FindCommand(flag, input);
    }

    private static String convertStringArrayToString(String[] stringArray) {
        StringBuilder output = new StringBuilder();
        // Finding the flag in the string array input
        if (stringArray[1].contains("-")) {
            String[] flagAndInput = stringArray[1].split("-");
            String flag = flagAndInput[1];
            output.append(flag + " ");
        }
        for(int i = 2; i < stringArray.length; i++) {
            if (i == stringArray.length - 1){
                output.append(stringArray[i]);
            } else {
                output.append(stringArray[i] + " ");
            }
        }
        return output.toString();
    }

    //    private void checkForExcessArgument(String[] args, int length)
    //            throws ExcessArgumentException {
    //        if (args.length > length) {
    //            throw new ExcessArgumentException();
    //        }
    //    }
    
}
