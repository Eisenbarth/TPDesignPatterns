package eu.telecomnancy;

import eu.telecomnancy.sensor.ISensor;
import eu.telecomnancy.sensor.LegacyTemperatureSensorAdapter;
import eu.telecomnancy.sensor.LegacyTemperatureSensor;
import eu.telecomnancy.ui.ConsoleUI;

public class AppAdapter {

    public static void main(String[] args) {
        ISensor sensor = new LegacyTemperatureSensorAdapter(new LegacyTemperatureSensor());
        new ConsoleUI(sensor);
    }

}
