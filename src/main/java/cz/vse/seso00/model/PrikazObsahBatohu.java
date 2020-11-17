package cz.vse.seso00.model;

/**
 *  Třída PrikazObsahBatohu implementuje pro hru příkaz batoh.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author     Ondřej Šesták
 * @version    červen 2020 (1.0)
 */
public class PrikazObsahBatohu implements IPrikaz {
    Batoh batoh;

 private static final String NAZEV = "batoh";

    /**
     *  Konstruktor třídy
     *
     *  @param batoh batoh který nosíš
     */
    public PrikazObsahBatohu(Batoh batoh) {this.batoh = batoh;}

    /**
     *  Provádí příkaz "batoh".
     *  Zobrazí obsah a kapacitu batohu.
     *
     * @return zpráva o obsahu a kapacitě batohu
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return batoh.obsahuje();
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