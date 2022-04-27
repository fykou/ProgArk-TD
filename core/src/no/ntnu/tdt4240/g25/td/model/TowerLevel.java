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

    public TowerLevel nextLevel() {
        switch (this) {
            case MK1:
                return MK2;
            case MK2:
                return MK3;
            case MK3:
                return MK4;
        }
        return null;
    }
}
