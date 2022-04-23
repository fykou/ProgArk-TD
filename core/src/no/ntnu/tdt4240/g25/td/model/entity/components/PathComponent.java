package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class PathComponent extends Component {

    boolean bLeft, bRight, bUp = true, bDown;
    float previousCoordinate;
    int checkpointNumber = 0;
    float currentCheckpoint = 300;

    public int getCheckpointNumber() {return checkpointNumber;}
    public float getCurrentCheckpoint() {return currentCheckpoint;}
    public float getPreviousCoordinate() {return previousCoordinate;}
    public boolean getLeft() {return bLeft;}
    public boolean getRight() {return bRight;}
    public boolean getUp() {return bUp;}
    public boolean getDown() {return bDown;}

    public void setCheckpointNumber(int number) {checkpointNumber = number;}
    public void setCurrentCheckpoint(float checkpoint) {currentCheckpoint = checkpoint;}
    public void setPreviousCoordinate(float coordinate) {previousCoordinate = coordinate;}
    public void setLeft(boolean left) {bLeft = left;}
    public void setRight(boolean right) {bRight = right;}
    public void setUp(boolean up) {bUp = up;}
    public void setDown(boolean down) {bDown = down;}
}

