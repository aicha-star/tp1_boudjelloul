package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.CompteARebours;
import org.emp.gl.gui.HorlogeGraphique;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import java.util.Random;
import javax.swing.*;

public class App {

    public static void main(String[] args) {
        
        TimerService timerService = new DummyTimeServiceImpl();

        
        SwingUtilities.invokeLater(() -> {
            new HorlogeGraphique(timerService);
        });

        
        Horloge horloge1 = new Horloge("Horloge 1", timerService);
        Horloge horloge2 = new Horloge("Horloge 2", timerService);

        
        new CompteARebours(5, timerService);

       
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int val = 10 + rand.nextInt(11);
            new CompteARebours(val, timerService);
        }
        
        System.out.println("Toutes les applications sont lancées!");
    }
}