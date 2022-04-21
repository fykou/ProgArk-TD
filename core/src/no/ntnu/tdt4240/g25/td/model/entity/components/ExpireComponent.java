package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;
import com.artemis.annotations.PooledWeaver;

@PooledWeaver
public class ExpireComponent extends Component {
    public float timeLeft;

    public ExpireComponent(float timeToDie) {
        this.timeLeft = timeToDie;
    }

    public ExpireComponent() {
        this.timeLeft = 5;
    }
}
