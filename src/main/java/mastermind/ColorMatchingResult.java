package mastermind;

/**
 * Created by dbash on 1/23/2017.
 */
public class ColorMatchingResult {
    private boolean colorMatches;
    private boolean placementMatches;

    public ColorMatchingResult(boolean colorMatches, boolean placementMatches) {
        this.colorMatches = colorMatches;
        this.placementMatches = placementMatches;
    }

    public boolean isColorMatches() {
        return colorMatches;
    }

    public void setColorMatches(boolean colorMatches) {
        this.colorMatches = colorMatches;
    }

    public boolean isPlacementMatches() {
        return placementMatches;
    }

    public void setPlacementMatches(boolean placementMatches) {
        this.placementMatches = placementMatches;
    }
}
