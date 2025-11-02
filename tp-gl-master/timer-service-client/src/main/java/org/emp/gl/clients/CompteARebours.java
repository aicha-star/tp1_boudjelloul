package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;
import java.beans.PropertyChangeEvent;

public class CompteARebours implements TimerChangeListener {

    private int valeur;
    private TimerService timerService;
    private String name;

    public CompteARebours(String name, int valeurInitiale, TimerService timerService) {
        this.name = name;
        this.valeur = valeurInitiale;
        this.timerService = timerService;
        timerService.addTimeChangeListener(this);
        System.out.println(name + " initialisé avec " + valeurInitiale);
    }

    
    public CompteARebours(int valeurInitiale, TimerService timerService) {
        this("CompteARebours-" + valeurInitiale, valeurInitiale, timerService);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
        if (org.emp.gl.timer.service.TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            if (valeur > 0) {
                valeur--;
                System.out.println(name + " : " + valeur);
                
                if (valeur == 0) {
                    timerService.removeTimeChangeListener(this);
                    System.out.println(name + " terminé !");
                }
            }
        }
    }
}