package org.emp.gl.gui;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.*;
import java.awt.*;

public class HorlogeGraphique extends JFrame implements TimerChangeListener {

    private TimerService timerService;
    private JLabel labelHeure;
    private JLabel labelDate;

    public HorlogeGraphique(TimerService timerService) {
        this.timerService = timerService;
        
        // S'inscrire comme observer
        timerService.addTimeChangeListener(this);
        
        // Initialiser l'interface graphique
        initializeGUI();
        
        // Mettre à jour l'heure initiale
        updateTime();
    }

    private void initializeGUI() {
        // Configuration de la fenêtre
        setTitle("Horloge Graphique");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null); // Centrer la fenêtre
        setResizable(false);

        // Création des composants
        labelHeure = new JLabel("", SwingConstants.CENTER);
        labelDate = new JLabel("", SwingConstants.CENTER);
        
        // Style des labels
        labelHeure.setFont(new Font("Arial", Font.BOLD, 48));
        labelHeure.setForeground(Color.BLUE);
        
        labelDate.setFont(new Font("Arial", Font.PLAIN, 18));
        labelDate.setForeground(Color.DARK_GRAY);

        // Layout
        setLayout(new BorderLayout());
        add(labelHeure, BorderLayout.CENTER);
        add(labelDate, BorderLayout.SOUTH);

        // Afficher la fenêtre
        setVisible(true);
    }

    private void updateTime() {
        // Mettre à jour l'heure affichée
        String heure = String.format("%02d:%02d:%02d", 
            timerService.getHeures(), 
            timerService.getMinutes(), 
            timerService.getSecondes());
        
        String date = java.time.LocalDate.now().toString();
        
        // Mettre à jour les labels dans le thread EDT
        SwingUtilities.invokeLater(() -> {
            labelHeure.setText(heure);
            labelDate.setText(date);
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Mettre à jour l'interface à chaque changement de temps
        updateTime();
    }

    public void fermer() {
        timerService.removeTimeChangeListener(this);
        dispose();
    }
}