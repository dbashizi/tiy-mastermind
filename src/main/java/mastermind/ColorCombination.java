package mastermind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Represents a combination of colors, which could be from a guess or from the first
 * player's turn
 */
public class ColorCombination {

    private List<MastermindColor> colors;
    /** Used to easily check if a color is part of this color combo */
    private HashSet<MastermindColor> uniqueColors = new HashSet<MastermindColor>();

    public ColorCombination(List<MastermindColor> colors) {
        this.colors = colors;
        this.uniqueColors.addAll(colors);
    }

    public List<MastermindColor> getColors() {
        return colors;
    }

    public void setColors(List<MastermindColor> colors) {
        this.colors = colors;
        uniqueColors = new HashSet<MastermindColor>();
        uniqueColors.addAll(colors);
    }

    /**
     * Returns a list of ColorMatchingResults that indicate a) if a color matches
     * b) if a placement matches as well
     *
     * @param target
     * @return
     */
    public List<ColorMatchingResult> compare(ColorCombination target) {
        List<ColorMatchingResult> matchingResults = new ArrayList<ColorMatchingResult>();
        // if the two color combos are exactly the same, return a full matching results list
        if (this.equals(target)) {
            for (MastermindColor color : getColors()) {
                matchingResults.add(new ColorMatchingResult(true, true));
            }
            return matchingResults;
        }

        // Note: this logic does not currently handle duplicate colors
        for (int colorIndex = 0; colorIndex < getColors().size(); colorIndex++) {
            ColorMatchingResult result = new ColorMatchingResult(false, false);
            if (colors.get(colorIndex) == target.getColors().get(colorIndex)) {
                result.setPlacementMatches(true);
                matchingResults.add(result);
            } else if (uniqueColors.contains(target.getColors().get(colorIndex))) {
                result.setColorMatches(true);
                matchingResults.add(result);
            }
        }

        // shuffle the matching results, so that the player can't anticipate which
        // matching result corresponds to which pin
        Collections.shuffle(matchingResults);

        return matchingResults;
    }

    /**
     * Override the equals() method to return true if and only if the two objects
     * being compared represent exactly the same colors, in exactly the same order
     *
     * @param targetObject
     * @return
     */
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
     * Returns a ColorCombination instance that represents the colors passed in,
     * in the right order
     *
     * @param color1
     * @param color2
     * @param color3
     * @param color4
     * @return
     */
    public static ColorCombination getColorCombination(MastermindColor color1,
                                                       MastermindColor color2,
                                                       MastermindColor color3,
                                                       MastermindColor color4) {
        List<MastermindColor> colors = new ArrayList<MastermindColor>();
        colors.add(color1);
        colors.add(color2);
        colors.add(color3);
        colors.add(color4);

        return new ColorCombination(colors);
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

    /**
     * Returns a specific color combination (always the same)
     * ** Use for testing **
     * @return
     */
    public static ColorCombination getSecondFixedCombination() {
        List<MastermindColor> combination = new ArrayList<MastermindColor>();
        MastermindColor[] colorOptions = MastermindColor.values();
        combination.add(colorOptions[2]);
        combination.add(colorOptions[1]);
        combination.add(colorOptions[6]);
        combination.add(colorOptions[4]);

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
