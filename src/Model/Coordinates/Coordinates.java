package Model.Coordinates;


/**
 * @author Michalis Ierodiakonou
 */
public class Coordinates {
    /**
     *
     */
    private int y;
    private int x;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * <b>Accessor</b>: Returns Y-position
     * @return y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * <b>Transformer</b>: Sets Y-position
     * @param y y-coordinate on the board between 0-9
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * <b>Accessor</b>: Returns X-position
     * @return x-coordinate
     */

    public int getX() {
        return x;
    }

    /**
     * <b>Transformer</b>: Sets X-position
     * @param x x-coordinate on the board between 0-7
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return if the Coordinates are in the board or not
     */
    public boolean isValid() {
        if( (this.x < 0 || this.x > 9) || (this.y < 0 || this.y > 7))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
}
