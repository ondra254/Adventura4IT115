package cz.vse.seso00.model;


import cz.vse.seso00.model.Npc;
import cz.vse.seso00.model.Prostor;
import cz.vse.seso00.model.Vec;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková, Ondřej Šesták
 * @version    červen 2020 (1.0)
 */
public class ProstorTest
{
    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře,
     */
    @Test
    public  void testLzeProjit() {
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě",false);
        Prostor prostor2 = new Prostor("bufet", "bufet, kam si můžete zajít na svačinku",false);
        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        assertEquals(prostor2, prostor1.vratSousedniProstor("bufet"));
        assertEquals(null, prostor2.vratSousedniProstor("pokoj"));
    }

    /**
     * Testuje metody getNazev, jeTruhla
     */
    @Test
    public void testProstor(){
        Prostor park = new Prostor("park","parčík",true);
        assertEquals("park",park.getNazev());
        assertEquals(true,park.jeTruhla());
    }

    /**
     * Testuje metody pridejVec, obsahujeVec, odstranVec
     */
    @Test
    public void testVec(){
        Prostor park = new Prostor("park","parčík",true);
        Vec vec1 = new Vec("vec1","vec1", true);
        Vec vec2 = new Vec("vec2","vec2", true);
        park.pridejVec(vec1);
        park.pridejVec(vec2);
        assertTrue(park.obsahujeVec("vec1"));
        assertTrue(park.obsahujeVec("vec2"));
        assertFalse(park.obsahujeVec("vec3"));
        park.odstranVec("vec1");
        assertFalse(park.obsahujeVec("vec3"));


    }

    /**
     * Testuje metody pridejNpc, obsahujeNpc, vratNpc, odstranNpc
     */
    @Test
    public void testNpc(){
        Prostor park = new Prostor("park","parčík",true);
        Vec vec1 = new Vec("vec1","vec1", true);
        Vec vec2 = new Vec("vec2","vec2", true);
        Npc npc1 = new Npc("npc1","npc1",vec1,vec2,true,true);
        Npc npc2 = new Npc("npc2","npc2",vec1,vec2,true,true);
        Npc npc3 = new Npc("npc3","npc3",vec1,vec2,true,true);
        park.pridejNpc(npc1);
        park.pridejNpc(npc2);
        assertTrue(park.obsahujeNpc("npc1"));
        assertTrue(park.obsahujeNpc("npc2"));
        assertFalse(park.obsahujeNpc("npc3"));
        assertEquals(npc1,park.vratNpc("npc1"));
        park.odstranNpc("npc1");
        assertFalse(park.obsahujeNpc("npc1"));

    }
}
