package no.ntnu.tdt4240.g25.td.service;

public enum SoundFx {
    TOUCH("sounds/BUTTON_03.wav"),
    SAVESETTINGS("sounds/UI Message Appear 01.wav"),
    HIGHSCORE_FLOURISH("sounds/Activate Glyph Forcefield.wav"),
    HIGHSCORE_CONFIRMED("sounds/DSGNStngr_Kill Confirm Metallic_02.wav"),
    GAMESTART("sounds/UI Message Appear 01.wav");
//        BUILD("sounds/build.wav"),
//        EXPLODE("sounds/explode.wav"),
//        FIRE("sounds/fire.wav"),
//        HIT("sounds/hit.wav"),
//        MOVE("sounds/move.wav"),
//        SELECT("sounds/select.wav"),
//        WALL("sounds/wall.wav");

    public final String path;

    SoundFx(String path) {
        this.path = path;
    }
}
