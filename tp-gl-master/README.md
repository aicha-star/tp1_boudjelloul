CS(8)
[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/t19xNtmg)
 *********** 
 ****rapport de tp *****
 Étape 1:
 Problèmes Rencontrés :
 1/Accès concurrent à la liste des observers

 2/Désinscription pendant notification → Exception ConcurrentModificationException
 .....voici le code  // Dans DummyTimeServiceImpl
List<TimerChangeListener> listeners = new LinkedList<>();
for (TimerChangeListener l : listeners) { // ERREUR CONCURRENTE
    l.propertyChange(...);
}



Étape 2:
1/Remplacer la gestion manuelle des listeners par PropertyChangeSupport

2/Thread-safe natif

3/Gère automatiquement les désinscriptions
et voici le code corrige 
// AVANT
List<TimerChangeListener> listeners = new LinkedList<>();

// APRÈS  
private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
propertyChangeSupport.firePropertyChange(...);



Étape 3 - Bonus Interface Graphique
Composants Créés :
1/HorlogeGraphique : Fenêtre avec heure en temps réel

2/CompteAReboursGraphique : Fenêtres avec barre de progression