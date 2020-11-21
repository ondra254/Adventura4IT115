package cz.vse.seso00.model;

import java.util.*;

/**
 *  Třída Batoh -  Batoh slouží k uchovávání a přenášení získaných předmětů
 *  obsah bathu můžeme zobrazit příkazem 'batoh'
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author     Ondřej Šesták
 * @version    červen 2020 (1.0)
 */
public class Batoh {
    private Map<String, Vec> obsah;
    public Batoh() {obsah = new HashMap<>();}
    private static final int kapacita = 5;



    /**
     * zkontroluje jestli není batoh plný,
     * pokud není, tak vloží vec do batohu
     * @param vec vec kterou vkládáme do batohu
     * @return true pokud byla věc vložena, false pokud vložena nebyla
     */
    public Boolean vlozVec(Vec vec) {
        if (vec != null && vec.isPrenositelna()) {
            if (obsah.size() < kapacita) {
                obsah.put(vec.getNazev(), vec);
                return true;
            }
        }return false;
    }

    /**
     * zkontroluje jestli je vec v batohu,
     * pokud je, tak věc z batohu odebere
     * @param vec vec kterou odebíráme z batohu
     * @return vrati vec která byla odebrána
     */
    public Vec odeberVec(Vec vec) {
        if(obsah.containsKey(vec)){
            obsah.remove(vec);}
            return vec;
    }

    public Map<String, Vec> getObsah(){
    return obsah;

    }

    /**
     * zobrazí obsah batohu
     * @return vraci obsah batohu
     */
    public String obsahuje() {
        String text = "V batohu neseš: ";
        if (obsah.size() > 0) {
            for (String nazevVeci : obsah.keySet()) {
                text += nazevVeci + ", ";
            }
        } else {
            text += "nic";
        }
        return text + "\nVěcí v batohu: " + obsah.size() + "/" + kapacita;
    }

    /**
     * zkontroluje jestli je vec v batohu,
     * pokud je, tak věc z batohu odebere a vrátí její název,
     * pokud není tak vrátí null
     * @param nazevVeci název věci kterou chceme odebrat
     * @return vraci název odebrané věci nebo null
     */
    public Vec odeberVec (String nazevVeci) {
        Vec hledanaVec;
        if (obsah.containsKey(nazevVeci)) {
            hledanaVec = obsah.get(nazevVeci);
            obsah.remove(nazevVeci);
            return hledanaVec;
        }return null;
    }

    /**
     * zkontroluje jestli ja batoh plný
     * @return vraci true pokud je plný, false pokud není plný
     */
    public Boolean plny(){
            return kapacita == obsah.size();
        }

    /**
     * zjistí jestly se daná věc nachází v batohu
     * @param nazev název věci o které chceme zjistit zda se v batohu nachází
     * @return vraci true pokud se věc v batohu nachází, false pokud se v batohu nenachází
     */
    public Boolean obsahujeVec(String nazev) {
        return obsah.containsKey(nazev);
    }
}





