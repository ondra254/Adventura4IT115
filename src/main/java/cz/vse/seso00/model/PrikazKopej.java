package cz.vse.seso00.model;

/**
 *  Třída PrikazKopej implementuje pro hru příkaz kopej.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author     Ondřej Šesták
 * @version    červen 2020 (1.0)
 */
public class PrikazKopej implements IPrikaz {

    private static final String NAZEV = "kopej";
    private final HerniPlan plan;
    private final Batoh batoh;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "chodit"
     *  @param batoh batoh který nosíš
     */
    public PrikazKopej(HerniPlan plan, Batoh batoh) {
        this.plan = plan;
        this.batoh = batoh;
    }

    /**
     *  Provádí příkaz "kopej". Otestuje jestli jsi zadal parametr,
     *                          jestli máš lopatu potřebnou pro kopání.
     *  Pokud se v prostoru nachází truhla, vykope ji.
     *
     * @param parametry - jako parametr obsahuje název věci,
     *                    kterou chce vykopat
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Nevím co mám kopat, zkus 'kopej truhla'";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();

        if (batoh.obsahujeVec("lopata")){
            if (aktualniProstor.jeTruhla()) {
                Vec vec = aktualniProstor.odstranNevVec(nazevVeci);
                if (vec == null) {
                    return "Nic jsi nevykopal";
                }
                else{
                    aktualniProstor.pridejVec(vec);
                    return "Vykopal jsi truhlu!";
                }
            }
            return "Nic jsi nevykopal";
        }
        return "Nemáš lopatu";
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev () {
            return NAZEV;
        }
}

