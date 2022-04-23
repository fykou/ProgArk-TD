package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;
import com.artemis.PooledComponent;
import com.artemis.annotations.PooledWeaver;

public class StateComponent extends PooledComponent {
    public static final int STATE_IDLE = 0;
    public static final int STATE_MOVING = 1;
    public static final int STATE_ATTACKING = 2;
    public static final int STATE_DYING = 3;

    public int state; // private, as the timer needs to be reset when the state changes
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
    public void set(int newState, boolean isLooping) {
        state = newState;
        time = 0f;
        this.isLooping = isLooping;
    }

    @Override
    protected void reset() {
        state = STATE_IDLE;
        time = 0f;
        isLooping = false;
    }

}
