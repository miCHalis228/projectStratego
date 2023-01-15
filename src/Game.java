import Controller.Controller;

/**
 * @author Michalis Ierodiakonou
 * The game application
 */
public class Game {
    /**
     * <b>Application</b>
     * @param args always empty (null)
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.startGame();
    }
}
