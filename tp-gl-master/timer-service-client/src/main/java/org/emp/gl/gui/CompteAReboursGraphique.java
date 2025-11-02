package org.emp.gl.gui;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.*;
import java.awt.*;

public class CompteAReboursGraphique extends JFrame implements TimerChangeListener {

    private TimerService timerService;
    private int valeur;
    private String nom;
    private JLabel labelCompte;
    private JProgressBar progressBar;

    public CompteAReboursGraphique(String nom, int valeurInitiale, TimerService timerService) {
        this.nom = nom;
        this.valeur = valeurInitiale;
        this.timerService = timerService;
        
        timerService.addTimeChangeListener(this);
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Compte à Rebours - " + nom);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationByPlatform(true);

        labelCompte = new JLabel("", SwingConstants.CENTER);
        labelCompte.setFont(new Font("Arial", Font.BOLD, 24));
        
        progressBar = new JProgressBar(0, valeur);
        progressBar.setValue(valeur);
        progressBar.setStringPainted(true);

        setLayout(new BorderLayout());
        add(labelCompte, BorderLayout.CENTER);
        add(progressBar, BorderLayout.SOUTH);

        updateDisplay();
        setVisible(true);
    }

    private void updateDisplay() {
        SwingUtilities.invokeLater(() -> {
            labelCompte.setText(nom + " : " + valeur);
            progressBar.setValue(valeur);
            
            
            if (valeur <= 3) {
                labelCompte.setForeground(Color.RED);
            } else if (valeur <= 7) {
                labelCompte.setForeground(Color.ORANGE);
            } else {
                labelCompte.setForeground(Color.BLACK);
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // FILTRER : seulement pour les secondes
        if (org.emp.gl.timer.service.TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            if (valeur > 0) {
                valeur--;
                updateDisplay();
                
                if (valeur == 0) {
                    timerService.removeTimeChangeListener(this);
                    SwingUtilities.invokeLater(() -> {
                        labelCompte.setText("TERMINÉ !");
                        labelCompte.setForeground(Color.GREEN);
                        JOptionPane.showMessageDialog(this, "Compte à rebours " + nom + " terminé !");
                    });
                }
            }
        }
    }
}