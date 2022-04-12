package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;

public class StateComponent extends Component {
    public static final int STATE_IDLE = 0;
    public static final int STATE_MOVING = 1;
    public static final int STATE_ATTACKING = 2;
    public static final int STATE_DYING = 3;
    public static final int STATE_DEAD = 4;


    private int state; // private, as the timer needs to be reset when the state changes
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

    /**
     * Returns the current state of the entity.
     * @return the int representing the state
     */
    public int get() {
        return state;
    }

    /**
     * Sets the state of the entity and resets the time.
     * @param newState the new statethe new state
     */
    public void set(int newState) {
        state = newState;
        time = 0f;
    }

}
