package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;

public class StateComponent extends Component {
    public static final int STATE_IDLE = 0;
    public static final int STATE_MOVING = 1;
    public static final int STATE_ATTACKING = 2;
    public static final int STATE_DYING = 3;
    public static final int STATE_DEAD = 4;

    private int state;
    public float time;
    public boolean isLooping;

    public StateComponent(int newState) {
        time = 0f;
        state = newState;
        isLooping = false;
    }

    public StateComponent() {
        this(STATE_IDLE);
    }

    public int get() {
        return state;
    }

    public void set(int newState) {
        state = newState;
    }

}
