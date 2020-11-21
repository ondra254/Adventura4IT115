package cz.vse.seso00.model;

/**
 *  Třída Prikazdloz implementuje pro hru příkaz odlož.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author     Ondřej Šesták
 * @version    červen 2020 (1.0)
 */
public class PrikazOdloz implements IPrikaz {

    private static final String NAZEV = "odlož";
    private final HerniPlan plan;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazOdloz(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "odlož". Otestuje jestli jsi zadal parametr,
     *                              jestli máš konkrétní věc v batohu.
     *
     * Odloží věc v prostoru ve kterém se nacházíš.
     *
     * @param parametry - název věci kterou chceš odložit
     *
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Nevím co mám odložit";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec vec = plan.getBatoh().odeberVec(nazevVeci);

        if (vec == null) {
            return "Tuhle věc v batohu nemáš";
        }
        else {
            aktualniProstor.pridejVec(vec);
            plan.getBatoh().odeberVec(vec);
            return "Odložil jsi " + nazevVeci;
        }
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
