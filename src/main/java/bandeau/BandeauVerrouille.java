package bandeau;

public class BandeauVerrouille  extends Thread {
    private Bandeau bandeau;
    private Scenario scenario;

    public BandeauVerrouille(Bandeau bandeau, Scenario scenario) {
        this.bandeau = bandeau;
        this.scenario = scenario;
    }

    @Override
    public void run() {
        scenario.playOn(bandeau);
    }
}
