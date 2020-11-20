package cz.vse.seso00.model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 * <p>
 * Tato třída je součástí jednoduché textové hry.
 * <p>
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Ondřej Šesták
 * @version    červen 2020 (1.0)
 */
public class Prostor {

    private final String nazev;
    private final String popis;
    private final Boolean truhla;
    private final Set<Prostor> vychody;   // obsahuje sousední místnosti
    private final Map<String, Vec> veci;  //<klíč, Hodnota>
    private final Map<String, Npc> npcs;
    private final Map<String, Vec> neviditelneVeci;

    //Koste, instance Vec Koste
    //Jablko, instance Vec Jablko

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     *              víceslovný název bez mezer.
     * @param popis Popis prostoru.
     * @param truhla Hodnota zda se v prostoru nachází truhla.
     */
    public Prostor(String nazev, String popis, Boolean truhla) {
        this.nazev = nazev;
        this.popis = popis;
        this.truhla = truhla;
        vychody = new HashSet<>();
        veci = new HashMap<>();
        npcs = new HashMap<>();
        neviditelneVeci = new HashMap<>();
    }

    public Map<String, Vec> getVeci() {
        return veci;
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     * <p>
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (Objects.equals(this.nazev, druhy.nazev));
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }


    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;
    }

    public String getPopis() {
        return popis;
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return  "************************************************************************************" +
                "\nJsi v mistnosti/prostoru " + popis + ".\n"
                + popisVychodu() + "\n"
                + popisVeci() + "\n"
                + popisNpc();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "Východy:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }

    /**
     * Vrací textový řetězec, který popisuje věci v prostoru, například:
     * "Věci: koště ".
     *
     * @return Seznam věcí
     */
    private String popisVeci() {
        String vracenyText = "Věci:";
        for (String nazevVeci : veci.keySet()) {
            vracenyText += " " + nazevVeci;
        }
        return vracenyText;
    }

    /**
     * Vrací textový řetězec, který popisuje npc v prostoru, například:
     * "Postavy: opilec ".
     *
     * @return Seznam npc
     */
    private String popisNpc() {
        String vracenyText = "Postavy:";
        for (String nazevNpc : npcs.keySet()) {
            vracenyText += " " + nazevNpc;
        }
        return vracenyText;

    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor> hledaneProstory =
                vychody.stream()
                        .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                        .collect(Collectors.toList());
        if (hledaneProstory.isEmpty()) {
            return null;
        } else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    /**
     * přidá věc do prostoru
     * @param vec kterou chceš do prostoru přidat
     */
    public void pridejVec(Vec vec) {
        veci.put(vec.getNazev(), vec);
    }

    /**
     * přidá neviditelnou věc do prostoru
     * @param vec kterou chceš do prostoru přidat
     */
    public void pridejNevVec(Vec vec) {
        neviditelneVeci.put(vec.getNazev(),vec);
    }

    /**
     * zjistí jestli se v prostoru nachází konkrétní věc
     * @param nazevVeci o kterou se zajímame
     * @return true pokud se věc v prostoru nachází, false pokud se věc v prostoru nenachází
     */
    public boolean obsahujeVec(String nazevVeci) {
        return veci.containsKey(nazevVeci);
    }

    /**
     * odstraní věc z prostoru
     * @param jmenoVeci kterou chceme odstranit
     * @return pokud se věc podařilo ostranit tak vrátí její název,
     *         pokud se ji nepodařilo odstranit, vrátí null
     */
    public Vec odstranVec (String jmenoVeci) {
        Vec nalezenaVec;
        if (veci.containsKey(jmenoVeci)) {
            nalezenaVec = veci.get(jmenoVeci);
            if (nalezenaVec.isPrenositelna() ) {
                veci.remove(jmenoVeci);
                return nalezenaVec;
            }
            return null;
        }
        return null;
    }

    /**
     * odstraní neviditelnou věc z prostoru
     * @param jmenoVeci kterou chceme odstranit
     * @return pokud se věc podařilo ostranit tak vrátí její název,
     *         pokud se ji nepodařilo odstranit, vrátí null
     */
    public Vec odstranNevVec (String jmenoVeci) {
        Vec nalezenaVec;
        if (neviditelneVeci.containsKey(jmenoVeci)) {
        nalezenaVec = neviditelneVeci.get(jmenoVeci);
            neviditelneVeci.remove(jmenoVeci);
            return nalezenaVec;
        }
        return null;
    }

    /**
     * přidá npc do prostoru
     * @param npc které chceš do prostoru přidat
     */
    public void pridejNpc(Npc npc) {
        npcs.put(npc.getNazev(),npc);
    }

    /**
     * zjistí jestli se v prostoru nachází konkrétní npc
     * @param nazevNpc o které se zajímame
     * @return true pokud se npc v prostoru nachází, false pokud se npc v prostoru nenachází
     */
    public Boolean obsahujeNpc(String nazevNpc) {
        return npcs.containsKey(nazevNpc);
    }

    /**
     * odstraní npc z prostoru
     * @param jmenoNpc které chceš z prostoru odstranit
     */
    public void odstranNpc (String jmenoNpc) {
        npcs.remove(jmenoNpc);
    }

    /**
     * vrátí npc podle názvu
     * @param nazevNpc o které se zajímáme
     * @return Npc podle zadaného názvu
     */
    public Npc vratNpc(String nazevNpc) {
        return npcs.get(nazevNpc);
    }

    /**
     * Vrítí hodnotu zda se v prostoru nachází truhla
     * @return true pokud se v prostoru truhla nachází,
     * false pokud se v prostoru truhla nenachází
     */
    public Boolean jeTruhla() {
        return truhla;
    }
}
