package cz.vse.seso00.model;

/**
 *  Třída Npc - popisuje vlastnosti všech věcí
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author     Ondřej Šesták
 * @version    červen 2020 (1.0)
 */
public class Vec {

    private String nazev;
    private final String popis;
    private final boolean prenositelna;

    /**
     *  Konstruktor třídy
     *
     *  @param nazev nazev npc
     *  @param popis popis věci
     *  @param prenositelna hodnota zda je věc přenositelná
     */
    public Vec(String nazev, String popis, Boolean prenositelna) {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelna = prenositelna;
    }

    /**
     * vrátí název věci
     * @return nazev věci
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * nastaví název věci
     * @param nazev věci
     */
    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    /**
     * zjistí zda je věc přenositelná
     * @return true pokud je věc přenositelná, false pokud věc není přenositelná
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }
}