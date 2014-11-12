package eu.telecomnancy.ui;

import eu.telecomnancy.sensor.ISensor;
import eu.telecomnancy.sensor.SensorNotActivatedException;
import eu.telecomnancy.sensor.TemperatureSensor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SensorView extends JPanel implements Observer {
    private ISensor sensor;

    private JLabel value = new JLabel("N/A °C");
    private JButton on = new JButton("On");
    private JButton off = new JButton("Off");
    private JButton update = new JButton("Acquire");

    public SensorView(ISensor c) {
        this.sensor = c;
        if (this.sensor instanceof TemperatureSensor)
            ((TemperatureSensor) this.sensor).addObserver(this);
        this.setLayout(new BorderLayout());

        value.setHorizontalAlignment(SwingConstants.CENTER);
        Font sensorValueFont = new Font("Sans Serif", Font.BOLD, 18);
        value.setFont(sensorValueFont);

        this.add(value, BorderLayout.CENTER);

        on.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sensor.on();
            }
        });

        off.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sensor.off();
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sensor.update();
                } catch (SensorNotActivatedException sensorNotActivatedException) {
                    sensorNotActivatedException.printStackTrace();
                }
            }
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 3));
        buttonsPanel.add(update);
        buttonsPanel.add(on);
        buttonsPanel.add(off);

        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        try {
            this.value.setText(this.sensor.getValue() + " °C");
        } catch (SensorNotActivatedException ex) {
            Logger.getLogger(ConsoleUI.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }
}
