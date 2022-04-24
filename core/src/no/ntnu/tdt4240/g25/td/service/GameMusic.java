package no.ntnu.tdt4240.g25.td.service;

public enum GameMusic {
    GAME("sounds/Race to Mars.mp3"),
    MENU("sounds/AMBIENCE_TUNNEL_WIND_LOOP.wav");

    public final String path;

    GameMusic(String path) {
        this.path = path;
    }
}
