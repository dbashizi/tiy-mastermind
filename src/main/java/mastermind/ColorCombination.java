package mastermind;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Represents a combination of colors, which could be from a guess or from the first
 * player's turn
 */
public class ColorCombination {

    private List<MastermindColor> colors;

    public ColorCombination(List<MastermindColor> colors) {
        this.colors = colors;
    }

    public List<MastermindColor> getColors() {
        return colors;
    }

    public void setColors(List<MastermindColor> colors) {
        this.colors = colors;
    }

    @Override
    public boolean equals(Object targetObject) {
        ColorCombination target = (ColorCombination)targetObject;
        if (targetObject == this) {
            return true;
        }
        if (!(targetObject instanceof ColorCombination)) {
            return false;
        }
        if (colors.size() != target.getColors().size()) {
            return false;
        }
        int colorIndex = 0;
        boolean areEqual = true;
        for (MastermindColor color : colors) {
            MastermindColor targetColor = target.getColors().get(colorIndex);
            if (color != targetColor) {
                areEqual = false;
                break;
            }
            colorIndex++;
        }

        return areEqual;
    }

    /**
     * Returns a random combination of 4 colors from MastermindColor, and ensures
     * that no 2 colors are the same
     * @return
     */
    public static ColorCombination getRandomUniqueCombination() {
        List<MastermindColor> uniqueRandomCombination = new ArrayList<MastermindColor>();
        // we'll use the HashSet to make sure we never have two identical colors in
        // the collection we create
        HashSet<Integer> uniqueNumbers = new HashSet<Integer>();
        MastermindColor[] colorOptions = MastermindColor.values();
        int colorIndexRange = colorOptions.length -1;
        for (int optionIndex = 0; optionIndex < 4; optionIndex++) {
            // we'll use the randomColorIndex to randomly select one of the
            // values of the color enum
            int randomColorIndex;
            // continue to choose a random value until it's one that doesn't match
            // an existing value
            while (true) {
                randomColorIndex = (int) (Math.random() * colorIndexRange);
                if (!uniqueNumbers.contains(new Integer(randomColorIndex))) {
                    uniqueRandomCombination.add(colorOptions[randomColorIndex]);
                    uniqueNumbers.add(new Integer(randomColorIndex));
                    break;
                }
            }
        }

        return new ColorCombination(uniqueRandomCombination);
    }

    /**
     * Returns a specific color combination (always the same)
     * ** Use for testing **
     * @return
     */
    public static ColorCombination getFixedCombination() {
        List<MastermindColor> combination = new ArrayList<MastermindColor>();
        MastermindColor[] colorOptions = MastermindColor.values();
        combination.add(colorOptions[4]);
        combination.add(colorOptions[1]);
        combination.add(colorOptions[3]);
        combination.add(colorOptions[5]);

        return new ColorCombination(combination);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (MastermindColor color : colors) {
            sb.append(color.toString() + "\n");
        }

        return sb.toString();
    }
}
