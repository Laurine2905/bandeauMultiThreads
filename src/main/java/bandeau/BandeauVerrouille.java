package bandeau;

import java.util.concurrent.locks.Lock;

public class BandeauVerrouille  extends Thread {
    private Bandeau bandeau;
    private Scenario scenario;
    private Lock bandeauLock;
    public BandeauVerrouille(Bandeau bandeau, Scenario scenario, Lock bandeauLock) {
        this.bandeau = bandeau;
        this.scenario = scenario;
        this.bandeauLock = bandeauLock;
    }

    @Override
    public void run() {
        bandeauLock.lock();
        try {
        scenario.playOn(bandeau);
        } finally {
            bandeauLock.unlock();
        }
    }
}
