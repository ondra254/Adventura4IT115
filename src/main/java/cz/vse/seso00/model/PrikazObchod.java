package cz.vse.seso00.model;

import java.util.Map;

/**
 *  Třída PrikazObchod implementuje pro hru příkaz obchd.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author     Ondřej Šesták
 * @version    červen 2020 (1.0)
 */
public class PrikazObchod implements IPrikaz {

    private static final String NAZEV = "obchod";
    private final HerniPlan plan;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazObchod(HerniPlan plan){
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "obchod". Otestuje jestli jsi zadal parametr,
     *                           jestli je v prostoru konkrétní npc,
     *                           jestli jde s daným npc obchodovat,
     *                           jestli máš věc kterou npc chce.
     *
     *  Provede obchod (výměnu)
     *
     * @param parametry - název postavy se kterou chceš obchodovat
     *
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Nevím s kým mám obchodovat";
        }

        if (parametry.length == 1){
            return "Musíš zadat co nabízíš";
        }

        Prostor aktualniProstor = plan.getAktualniProstor();
        Npc npc = aktualniProstor.vratNpc(parametry[0]);
        String jmeno = parametry[0];
        String davam = parametry[1];

        if (aktualniProstor.obsahujeNpc(jmeno)) {
            Vec dava = npc.getDava();
            Vec chce = npc.getChce();
            if (davam.equals(chce.getNazev())) {
                if (npc.getObchod()) {
                    if (!npc.getVymeneno()) {
                        if (plan.getBatoh().obsahujeVec(chce.getNazev())) {
                            Map<String, Vec> vymena = npc.vymenitVec();
                            plan.getBatoh().odeberVec(vymena.get("odeber").getNazev());
                            plan.getBatoh().vlozVec(vymena.get("pridej"));
                            npc.setDava(null);
                            return "Dostal jsi " + dava.getNazev() + " za " + chce.getNazev();
                        }
                        return "Chce " + npc.getChce().getNazev() + " za " + npc.getDava().getNazev() + "\n" +
                                npc.getRika();
                    }
                    return "S tímto obchodníkem jsi již obchodoval, nenabízí nic dalšího";
                }
                return "S touto osobou nelze obchodovat";
            }
            return "tohle obchodník nechce!";
        }
        return "Tuto osobu nikde nevidím.";
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
