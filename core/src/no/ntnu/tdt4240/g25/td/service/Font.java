package no.ntnu.tdt4240.g25.td.service;

public enum Font {
    GILROY("fonts/gilroy.ttf"),
    SMALL("small.ttf"),
    MEDIUM("medium.ttf"),
    LARGE("large.ttf");

    public final String path;

    Font(String path) {
        this.path = path;
    }
}
