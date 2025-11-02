package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;
import java.beans.PropertyChangeEvent;

public class Horloge implements TimerChangeListener {

    private String name;
    private TimerService timerService;

    public Horloge(String name, TimerService timerService) {
        this.name = name;
        this.timerService = timerService;
        timerService.addTimeChangeListener(this);
        System.out.println("Horloge " + name + " initialisée!");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            System.out.println(name + " : " +
                    timerService.getHeures() + ":" +
                    timerService.getMinutes() + ":" +
                    timerService.getSecondes() + "." +
                    timerService.getDixiemeDeSeconde());
        }
    }
}