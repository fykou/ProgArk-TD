package no.ntnu.tdt4240.g25.td.service;

public enum GameMusic {
    GAME("sounds/Race to Mars.ogg"),
    MENU("sounds/AMBIENCE_TUNNEL_WIND_LOOP.ogg"),
    SETTINGS("sounds/AMBIENCE_HEARTBEAT_LOOP.ogg");

    public final String path;

    GameMusic(String path) {
        this.path = path;
    }
}
