package com.tiy.games;

import mastermind.ColorCombination;
import mastermind.ColorMatchingResult;
import mastermind.MastermindColor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dbash on 1/19/2017.
 */
public class CoreTests {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testRandomColorCombination() {
        ColorCombination randomCombo = ColorCombination.getRandomUniqueCombination();
        System.out.println(randomCombo);

        int numCombos = randomCombo.getColors().size();
        HashSet<MastermindColor> uniqueColors = new HashSet<MastermindColor>();
        for (MastermindColor color : randomCombo.getColors()) {
            if (uniqueColors.contains(color)) {
                break;
            }
            uniqueColors.add(color);
        }
        assertEquals(numCombos, uniqueColors.size());
    }

    @Test
    public void testGuessMatching() {
        ColorCombination fixedCombo = ColorCombination.getFixedCombination();
        System.out.println(fixedCombo);
        ColorCombination fixedCombo2 = ColorCombination.getFixedCombination();
        System.out.println(fixedCombo2);

        List<ColorMatchingResult> results = fixedCombo.compare(fixedCombo2);
        for (ColorMatchingResult result : results) {
            assertTrue(result.isColorMatches() && result.isPlacementMatches());
        }

        ColorCombination userGuessCombo = ColorCombination.getSecondFixedCombination();
        System.out.println(userGuessCombo);
        results = fixedCombo.compare(userGuessCombo);
        int numFullMatch = 0;
        int numColorOnlyMatch = 0;
        int index = 0;
        assertEquals(2, results.size());
        for (ColorMatchingResult result : results) {
            if (result.isPlacementMatches()) {
                numFullMatch++;
                System.out.println("full match index: " + index);
            } else if (result.isColorMatches()) {
                numColorOnlyMatch++;
                System.out.println("partial match index: " + index);
            }
            index++;
        }
        assertEquals(1, numFullMatch);
        assertEquals(1, numColorOnlyMatch);

        // Note: should test for the randomness of the order of the matching results here ...
    }

    @Test
    public void testColorCombinationEquals() {
        ColorCombination fixedCombo = ColorCombination.getFixedCombination();
        System.out.println(fixedCombo);
        ColorCombination fixedCombo2 = ColorCombination.getFixedCombination();
        System.out.println(fixedCombo2);

        assertTrue(fixedCombo.equals(fixedCombo2));
    }

    @Test
    public void testSpecificColorCombination() {
        MastermindColor color1 = MastermindColor.BLACK;
        MastermindColor color2 = MastermindColor.RED;
        MastermindColor color3 = MastermindColor.YELLOW;
        MastermindColor color4 = MastermindColor.PINK;
        List<MastermindColor> colors = new ArrayList<MastermindColor>();
        colors.add(color1);
        colors.add(color2);
        colors.add(color3);
        colors.add(color4);
        ColorCombination manualColorCombo = new ColorCombination(colors);
        ColorCombination factoryMethodCombo = ColorCombination.getColorCombination(color1, color2, color3, color4);
        assertEquals(manualColorCombo, factoryMethodCombo);
    }
}
