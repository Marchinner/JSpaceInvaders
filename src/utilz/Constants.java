package utilz;

import java.awt.*;

public class Constants {

    public static final class GAME {
        public static final Color BACKGROUND_COLOR = Color.BLACK;
    }

    public static final class GAME_ENGINE {
        public static final int FPS_LIMIT = 60;
        public static final int UPS_LIMIT = 120;
    }

    public static final class GAME_WINDOW {
        public static final String TITLE = "Java Space Invaders";
        public static final int WIDTH = 800;
        public static final int HEIGHT = 600;
        public static final Dimension DIMENSION = new Dimension(WIDTH, HEIGHT);
        public static final int HORIZONTAL_CENTERED = WIDTH / 2;
    }

    public static final class SPRITE_ATLAS {
        public static final String PLAYER_SHIP = "/player_ship.png";
    }
}
