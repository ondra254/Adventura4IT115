package cz.vse.seso00.model;

import java.util.Map;

/**
 *  Třída PrikazNapadnout implementuje pro hru příkaz napadnout.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author     Ondřej Šesták
 * @version    červen 2020 (1.0)
 */
public class PrikazNapadnout implements IPrikaz {

    private static final String NAZEV = "napadnout";
    private final HerniPlan plan;
    private final Batoh batoh;
    private final Hra hra;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "chodit"
     *  @param batoh batoh který nosíš
     *  @param hra hra kterou hraješ
     */
    public PrikazNapadnout(HerniPlan plan, Batoh batoh, Hra hra){
        this.plan = plan;
        this.batoh = batoh;
        this.hra = hra;}

    /**
     *  Provádí příkaz "napadnout". Otestuje jestli jsi zadal parametr,
     *                              jestli se v prostoru nachází konkrétní npc,
     *                              jestli jde s daným npc bojovat.
     *
     *  Když jsi ozbrojený tak npc zabije a vezme jeho věci, (pokud je batoh plný, věci spadnou na zem).
     *  Když nejsi ozbrojený tak tě npc zabije a prohraješ hru.
     *
     * @param parametry - nazev npc které chceš napadnout
     *
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Nevím koho mám napadnout";
        }

        Prostor aktualniProstor = plan.getAktualniProstor();
        Npc npc = aktualniProstor.vratNpc(parametry[0]);
        String jmeno = parametry[0];

        if (aktualniProstor.obsahujeNpc(jmeno)) {
            Vec dava = npc.getDava();
            if (npc.getBoj()) {
                if (batoh.obsahujeVec("meč")) {
                    Map<String, Vec> vymena = npc.vymenitVec();
                    aktualniProstor.odstranNpc(npc.getNazev());

                    if (batoh.plny()) {
                        aktualniProstor.pridejVec(dava);
                    }
                    else {
                        batoh.vlozVec(vymena.get("pridej")); }
                    return "Zabil jsi " + npc.getNazev() + " a dostal jsi " + dava.getNazev();
                }
                    plan.setProhra(true);
                    hra.setKonecHry();
                    return "Zemřel jsi (zabil tě " + npc.getNazev() + ")";
            }
            return "S touto osobou se nedá bojovat";
        }
        return "Tuto osobu nikde nevidím.";
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

