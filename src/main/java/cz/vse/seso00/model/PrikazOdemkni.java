package cz.vse.seso00.model;

/**
 *  Třída PrikazOdemkni implementuje pro hru příkaz odemkni.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author     Ondřej Šesták
 * @version    červen 2020 (1.0)
 */
public class PrikazOdemkni implements IPrikaz {

    private static final String NAZEV = "odemkni";
    private final HerniPlan plan;
    private final Hra hra;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "chodit"
     *  @param hra hra kterou hraješ
     */
    public PrikazOdemkni(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra= hra;
    }

    /**
     *  Provádí příkaz "odemkni". Otestuje jestli jsi zadal parametr,
     *                            jestli se v prostoru nachází konkrétní věc,
     *                            jestli máš klíč.
     *
     *  Odemkne truhlu a ukončí hru výhrou
     *
     * @param parametry - název věci kterou chceš odemknout
     *
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Nevím co mám odemknout";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();

        if (aktualniProstor.obsahujeVec(nazevVeci)){
                if (aktualniProstor.obsahujeVec("truhla")) {
                    if(plan.getBatoh().obsahujeVec("klíč")) {
                    plan.setVyhra(true);
                    hra.setKonecHry();
                    return "Odemkl jsi truhlu!!!";
                    }
                    return "Potřebuješ klíč aby jsi odemknul truhlu.";
                }
                return "Tato věc nějde odemknout";
        }
        return "Tuto věc nevidím";
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
