package eu.telecomnancy.sensor;


public class LegacyTemperatureSensorAdapter implements ISensor {
    private LegacyTemperatureSensor adaptedSensor;
    
    public LegacyTemperatureSensorAdapter(LegacyTemperatureSensor adaptedSensor) {
        this.adaptedSensor = adaptedSensor;
    }
    

    @Override
    public void on() {
        if(!this.adaptedSensor.getStatus())
            this.adaptedSensor.onOff();

    }

    @Override
    public void off() {
        if(this.adaptedSensor.getStatus())
            this.adaptedSensor.onOff();
    }

    @Override
    public boolean getStatus() {
        return this.adaptedSensor.getStatus();
    }

    @Override
    public void update() throws SensorNotActivatedException {
        return;
    }

    @Override
    public double getValue() throws SensorNotActivatedException {
        return this.adaptedSensor.getTemperature();
    }


}
