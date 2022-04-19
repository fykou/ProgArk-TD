package no.ntnu.tdt4240.g25.td;


import java.util.HashMap;
import java.util.Map;

public class DesktopInterfaceClass implements FirebaseInterface{
    @Override
    public void SomeFunction() {
        System.out.println("From the Desktop package: wubaduba");
    }

    @Override
    public void FirstFireBaseTest() {}

    @Override
    public void SetOnValueChangeListener() {

    }

    @Override
    public void SetValueInDb(String target, String value) {

    }
}
