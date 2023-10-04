package bandeau;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExerciceAvecThreads {

    public static void main(String[] args) {
        ExerciceAvecThreads instance = new ExerciceAvecThreads();
        instance.exemple();
    }

    public void exemple() {

        Scenario s = makeScenario();
        Scenario s2 = makeScenario2();
        // On cree les bandeaux
        Bandeau b1 = new Bandeau();
        Bandeau b2 = new Bandeau();
        Bandeau b3 = new Bandeau();
        Lock bandeauLock1 = new ReentrantLock();
        Lock bandeauLock2 = new ReentrantLock();
        Lock bandeauLock3 = new ReentrantLock();
        System.out.println("CTRL-C pour terminer le programme");
        // On doit jouer le scénario en même temps sur les trois bandeaux
       BandeauVerrouille threadB1 = new BandeauVerrouille(b1, s, bandeauLock1 );
        BandeauVerrouille threadB2 = new BandeauVerrouille(b2, s2, bandeauLock2);
        BandeauVerrouille threadB3 = new BandeauVerrouille(b3, s, bandeauLock3);

        threadB1.start();
        threadB2.start();
        threadB3.start();

        try{
            // bloque le thread
            threadB1.join();
            threadB2.join();
            threadB3.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private Scenario makeScenario() {
        // On crée un scenario
        Scenario s = new Scenario();
        // On lui ajoute des effets
        s.addEffect(new RandomEffect("Le jeu du pendu", 700), 1);
        // s.addEffect(new TeleType("Je m'affiche caractère par caractère", 100), 1);
        // s.addEffect(new Blink("Je clignote 10x", 100), 10);
        // s.addEffect(new Zoom("Je zoome", 50), 1);
        // s.addEffect(new FontEnumerator(10), 1);
        // s.addEffect(new Rainbow("Comme c'est joli !", 30), 1);
        s.addEffect(new Rotate("2 tours à droite", 180, 4000, true), 2);
        // s.addEffect(new Rotate("2 tours à gauche", 180, 4000, false), 2);
        return s;
    }

    private Scenario makeScenario2() {
        // On crée un scenario
        Scenario s = new Scenario();
        // On lui ajoute des effets
        //s.addEffect(new RandomEffect("Le jeu du pendu", 700), 1);
        s.addEffect(new TeleType("Je m'affiche caractère par caractère", 100), 1);
        s.addEffect(new Blink("Je clignote 10x", 100), 10);
        // s.addEffect(new Zoom("Je zoome", 50), 1);
        // s.addEffect(new FontEnumerator(10), 1);
        // s.addEffect(new Rainbow("Comme c'est joli !", 30), 1);
        //s.addEffect(new Rotate("2 tours à droite", 180, 4000, true), 2);
        // s.addEffect(new Rotate("2 tours à gauche", 180, 4000, false), 2);
        return s;
    }
}
