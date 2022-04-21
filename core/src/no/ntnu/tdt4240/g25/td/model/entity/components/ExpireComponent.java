package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;

public class ExpireComponent extends Component {
    public int timeLeft;

    public ExpireComponent(int expireTime) {
        this.timeLeft = expireTime;
    }

    public ExpireComponent() {
        this.timeLeft = 5;
    }
}
