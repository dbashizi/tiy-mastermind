package com.tiy.games;

import mastermind.ColorCombination;
import mastermind.MastermindColor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

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
    public void testColorCombinationEquals() {
        ColorCombination randomCombo = ColorCombination.getFixedCombination();
        System.out.println(randomCombo);
        ColorCombination randomCombo2 = ColorCombination.getFixedCombination();
        System.out.println(randomCombo2);

        assertTrue(randomCombo.equals(randomCombo2));
    }
}
