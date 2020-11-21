package cz.vse.seso00.model;

/**
 *  Třída PrikazSeber implementuje pro hru příkaz seber.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author     Ondřej Šesták
 * @version    červen 2020 (1.0)
 */
public class PrikazSeber implements IPrikaz {

    private static final String NAZEV = "seber";
    private final HerniPlan plan;
//    private final Batoh batoh;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "chodit"
//     *  @param batoh batoh který nosíš
     */
    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
//        this.batoh = batoh;

    }

    /**
     *  Provádí příkaz "napadnout". Otestuje jestli jsi zadal parametr,
     *                              jestli se konkrétní věc nachází v aktuálním prostoru,
     *                              jestli je věc přenositelná,
     *                              jestli se věc vejde do batohu.
     *
     *  Sebere věc, odstraní ji z aktuálního prostoru a přidá ji do batohu.
     *
     * @param parametry - název věci kterou chceš sebrat
     *
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Nevím co mám sebrat";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();

        if (aktualniProstor.obsahujeVec(nazevVeci)) {

            Vec vec = aktualniProstor.odstranVec(nazevVeci);

            if (vec == null) {
                return "Taková věc není přenositelná!";
            }
            else if (plan.getBatoh().plny()) {
                aktualniProstor.pridejVec(vec);
                return "Batoh je plný";
            }
            else {
                plan.getBatoh().vlozVec(vec);
                return "Sebral jsi " + nazevVeci;
            }
        }return "Taková věc tu není!";
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