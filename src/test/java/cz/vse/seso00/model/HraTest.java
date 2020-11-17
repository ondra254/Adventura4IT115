package cz.vse.seso00.model;

import cz.vse.seso00.model.Batoh;
import cz.vse.seso00.model.Hra;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková, Ondřej Šesták
 * @version    červen 2020 (1.0)
 */
public class HraTest {
    private Hra hra1;
    private Batoh batoh1;

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
        hra1 = new Hra();
        batoh1 = new Batoh();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     *
     * Test výhry
     */
    @Test
    public void testVyhra() {

        assertEquals(false,hra1.getHerniPlan().isVyhra());
        assertEquals(false,hra1.konecHry());
        assertEquals("most",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi pláž_lebek");
        assertEquals(false,hra1.konecHry());
        assertEquals("pláž_lebek",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi palmový_háj");
        assertEquals("palmový_háj",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber kokos");
        hra1.zpracujPrikaz("jdi pláž_lebek");
        assertEquals("pláž_lebek",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi most");
        assertEquals("most",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi pláž_smůly");
        assertEquals("pláž_smůly",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi hora");
        assertEquals(false,hra1.konecHry());
        assertEquals("hora",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber zlatý_nuget");
        hra1.zpracujPrikaz("jdi hospoda");
        assertEquals("hospoda",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("obchod hospodský");
        hra1.zpracujPrikaz("jdi zbrojírna");
        assertEquals("zbrojírna",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("obchod zbrojíř");
        hra1.zpracujPrikaz("jdi pláž_smůly");
        assertEquals(false,hra1.konecHry());
        assertEquals("pláž_smůly",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi most");
        assertEquals("most",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi pláž_lebek");
        assertEquals("pláž_lebek",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi jeskyně");
        assertEquals(false,hra1.konecHry());
        assertEquals("jeskyně",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("napadnout padouch");
        hra1.zpracujPrikaz("jdi pláž_lebek");
        assertEquals("pláž_lebek",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("kopej truhla");
//        assertEquals(true,hra1.getHerniPlan().getAktualniProstor().obsahujeVec("truhla"));
//        hra1.zpracujPrikaz("odemkni truhla");
//        assertEquals(false,hra1.getHerniPlan().isProhra());
//        assertEquals(true,hra1.getHerniPlan().isVyhra());
//        assertEquals(true,hra1.konecHry());
    }

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     *
     * Test prohry
     */
    @Test
    public void testProhra() {
        assertEquals(false,hra1.getHerniPlan().isProhra());
        assertEquals(false,hra1.konecHry());
        hra1.zpracujPrikaz("jdi pláž_lebek");
        hra1.zpracujPrikaz("jdi jeskyně");
        assertEquals("jeskyně",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("napadnout padouch");
        assertEquals(false,hra1.getHerniPlan().isVyhra());
        assertEquals(true,hra1.getHerniPlan().isProhra());
        assertEquals(true,hra1.konecHry());
    }

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     *
     * Test příkazu konec
     */
    @Test
    public void testKonec() {
        hra1.zpracujPrikaz("konec");
        assertEquals(false,hra1.getHerniPlan().isVyhra());
        assertEquals(false,hra1.getHerniPlan().isProhra());
        assertEquals(true,hra1.konecHry());
    }
}
