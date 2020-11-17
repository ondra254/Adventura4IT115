package cz.vse.seso00.model;

import java.util.HashMap;
import java.util.Map;

/**
 *  Třída Npc - popisuje vlastnosti všech Npc
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author     Ondřej Šesták
 * @version    červen 2020 (1.0)
 */
public class Npc {

    private final String nazev;
    private final String rika;
    private final Vec chce;
    private Vec dava;
    private final Boolean boj;
    private final Boolean obchod;
    private Boolean vymeneno = false;

    /**
     *  Konstruktor třídy
     *
     *  @param nazev nazev npc
     *  @param rika co npc ríká
     *  @param chce vec kterou npc chce
     *  @param dava vec kterou npc dava
     *  @param boj hodnota zda jde s npc bojovat
     *  @param obchod hodnota zda jde s npc obchodovat
     */
    public Npc(String nazev, String rika, Vec chce, Vec dava, Boolean boj, Boolean obchod) {
        this.nazev = nazev;
        this.rika = rika;
        this.chce = chce;
        this.dava = dava;
        this.boj = boj;
        this.obchod = obchod;
    }

    /**
     * připravení věcí které postavq chce a dává na výměnu
     * @return mapa s věcmi na výměnu
     */
    public Map<String, Vec> vymenitVec() {
        vymeneno = true;
        Map<String, Vec> veci = new HashMap<>();
        veci.put("odeber", chce);
        veci.put("pridej", dava);
        return veci;
    }

    /**
     * vrátí název postavy
     * @return text název postavy
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * vrátí co postava říká
     * @return text, který postava říká
     */
    public String getRika() {
        return  rika;
    }

    /**
     * vrátí co postava chce
     * @return vec, kterou postava chce
     */
    public Vec getChce() {
        return chce;
    }

    /**
     * vrátí co postava dává
     * @return vec, kterou postava dává
     */
    public Vec getDava() {
        return dava;
    }

    /**
     * zobrazí jestli s postavou lze bojovat
     * @return true pokud ano, false pokud ne
     */
    public Boolean getBoj() {
        return boj;
    }

    /**
     * zobrazí jestli jde s postavou obchodovat
     * @return true pokud ano, false pokud ne
     */
    public Boolean getObchod() {
        return obchod;
    }

    /**
     * vrátí zda již proběhla výměna věcí
     * @return true pokud ano, false pokud ne
     */
    public Boolean getVymeneno() {
        return vymeneno;
    }

    /**
     * nastaví kterou vec postava dává
     * @param dava vec, kterou postava dává
     */
    public void setDava(Vec dava) {
        this.dava = dava;
    }
}
