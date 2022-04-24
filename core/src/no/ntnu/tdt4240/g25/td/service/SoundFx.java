package no.ntnu.tdt4240.g25.td.service;

public enum SoundFx {
    TOUCH("sounds/BUTTON_03.ogg"),
    SAVESETTINGS("sounds/UI Message Appear 01.ogg"),
    HIGHSCORE_CONFIRMED("sounds/DSGNStngr_Kill Confirm Metallic_02.ogg"),
    EXPLODE("sounds/EXPLDsgn_Explosion Impact_14.ogg"),
    FIRE_TYPE_1("sounds/fireSounds/LASRGun_Classic Blaster A Fire_03.ogg"),
    FIRE_TYPE_2("sounds/fireSounds/LASRGun_Plasma Rifle Fire_03.ogg");
//        BUILD("sounds/build.wav"),
//        HIT("sounds/hit.wav"),
//        MOVE("sounds/move.wav"),
//        SELECT("sounds/select.wav"),
//        WALL("sounds/wall.wav");

    public final String path;

    SoundFx(String path) {
        this.path = path;
    }
}
