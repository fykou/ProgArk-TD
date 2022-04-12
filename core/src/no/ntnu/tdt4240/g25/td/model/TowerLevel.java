package no.ntnu.tdt4240.g25.td.model;

public enum TowerLevel {
    MK1(1),
    MK2(2),
    MK3(3),
    MK4(4);

    public final int level;

    TowerLevel(int level) {
        this.level = level;
    }
}
