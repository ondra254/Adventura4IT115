package cz.vse.seso00.model;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Ondřej Šesták
 * @version    červen 2020 (1.0)
 */
public class HerniPlan {
    
    private Prostor aktualniProstor;
    Batoh batoh = new Batoh();
    private Boolean vyhra = false;
    private Boolean prohra = false;

     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
    }

    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {




        // vytvářejí se jednotlivé prostory
        Prostor jeskyne = new Prostor("jeskyně","Temná jeskyně",false);
        Prostor palmovyHaj = new Prostor("palmový_háj", "Háj s banánovníky a kokosovými palmami",false);
        Prostor plazLebek = new Prostor("pláž_lebek","Pláž na ostrově Lebek",true);
        Prostor plazSmuly = new Prostor("pláž_smůly", "Pláž na ostrově Smůly",false);
        Prostor hospoda = new Prostor("hospoda", "Hospůdka u Lachtana",false);
        Prostor hora = new Prostor("hora","Vysoká hora",false);
        Prostor zbrojirna = new Prostor("zbrojírna","Zatuchlá zbrojírna",false);
        Prostor most = new Prostor("most","Most spojující Ostrov Lebek a Ostrov Smůly",false);

        // přiřazují se průchody mezi prostory (sousedící prostory)
        jeskyne.setVychod(palmovyHaj);
        jeskyne.setVychod(plazLebek);
        palmovyHaj.setVychod(jeskyne);
        palmovyHaj.setVychod(plazLebek);
        plazLebek.setVychod(jeskyne);
        plazLebek.setVychod(palmovyHaj);
        plazLebek.setVychod(most);
        plazSmuly.setVychod(hospoda);
        plazSmuly.setVychod(hora);
        plazSmuly.setVychod(zbrojirna);
        plazSmuly.setVychod(most);
        hospoda.setVychod(plazSmuly);
        hospoda.setVychod(hora);
        hospoda.setVychod(zbrojirna);
        hora.setVychod(plazSmuly);
        hora.setVychod(hospoda);
        zbrojirna.setVychod(plazSmuly);
        zbrojirna.setVychod(hospoda);
        most.setVychod(plazLebek);
        most.setVychod(plazSmuly);

        //Vytvoření věcí
        Vec kokos = new Vec("kokos","velký kokos",true);
        Vec kost = new Vec("kost", "lidská kost", true);
        Vec stul = new Vec("stůl", "velký dubový stůl", false);
        Vec zidle = new Vec("židle","rozvrzaná židle", false);
        Vec pivo = new Vec("pivo","chlazená Plznička",true);
        Vec regal = new Vec("regál", "regál se zbraněmi", false);
        Vec zlatyNuget = new Vec("zlatý_nuget","zlatý nuget",true);
        Vec kamen = new Vec("kámen","zde",true);
        Vec balvan = new Vec("balvan","obří balvan",false);
        Vec koste = new Vec("koště","staré koště",true);
        Vec skrin = new Vec("skříň","velká dubová almara",false);
        Vec banan = new Vec("banán","žlutý banán", true);
        Vec vrak = new Vec("vrak","vrak lodi",false);
        Vec mec = new Vec("meč","rezavý meč", true);
        Vec lopata = new Vec("lopata","obyčejná lopata", true);
        Vec klic = new Vec("klíč","klíč od truhly",true);
        Vec truhla = new Vec("truhla","truhla s pokladem",false);
        Vec prkno = new Vec("prkno","zlomené prkno", true);
        Vec podtacek = new Vec( "podtácek", "podtácek pod pivo", true);
        Vec smrt = new Vec("smrt","smrt",true);

        //Přidání věcí do prostoru
        hora.pridejVec(zlatyNuget);
        hora.pridejVec(kamen);
        hora.pridejVec(balvan);
        zbrojirna.pridejVec(koste);
        hospoda.pridejVec(skrin);
        hospoda.pridejVec(stul);
        hospoda.pridejVec(zidle);
        hospoda.pridejVec(podtacek);
        zbrojirna.pridejVec(regal);
        palmovyHaj.pridejVec(kokos);
        palmovyHaj.pridejVec(banan);
        plazLebek.pridejVec(vrak);
        plazLebek.pridejNevVec(truhla);
        most.pridejVec(prkno);
        jeskyne.pridejVec(kost);

        //Vytvoření npc
        Npc hospodsky = new Npc("hospodský", "Potřeboval bych kokos, když mi ho seženeš tak ti za něj dám lopatu.",kokos,lopata,false, true);
        Npc zbrojir = new Npc("zbrojíř", "Jsem ochotný ti prodat tento meč za kus zlata, zkus se podívat na nedalekou horu.",zlatyNuget,mec,false,true);
        Npc padouch = new Npc("padouch","jsem padouch",smrt ,klic,true,false);
        Npc opilec = new Npc("opilec","Podle legend zde na souostroví ztroskotala loď s pokladem.",banan,pivo,false,true);

        //Přidání npc do prostoru
        hospoda.pridejNpc(hospodsky);
        hospoda.pridejNpc(opilec);
        zbrojirna.pridejNpc(zbrojir);
        jeskyne.pridejNpc(padouch);

        aktualniProstor = most;  // hra začíná na mostě
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }

    /**
     * vrátí batoh
     * @return vrací batoh
     */
    public Batoh getBatoh() {
        return batoh;
    }

    /**
     * vrátí výhru
     * @return vrací true pokud je vyhra true, false pokud je vyhra false
     */
    public Boolean isVyhra() {
        return vyhra;
    }

    /**
     * vrátí prohru
     * @return vrací true pokud je prohra true, false pokud je prohra false
     */
    public Boolean isProhra() {
        return prohra;
    }

    /**
     * nastaví vyhru
     * @param stav nová hodnota true/false
     */
    public void setVyhra(Boolean stav) {
        this.vyhra = stav;
    }

    /**
     * nastaví prohru
     * @param stav nová hodnota true/false
     */
    public void setProhra(Boolean stav) {
        this.prohra = stav;
    }
}

