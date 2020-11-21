package cz.vse.seso00.model;

import cz.vse.seso00.model.Batoh;
import cz.vse.seso00.model.Vec;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída BatohTest slouží ke komplexnímu otestování
 * třídy Batoh
 *
 * @author     Ondřej Šesták
 * @version    červen 2020 (1.0)
 */
public class BatohTest {

    Batoh batoh = new Batoh();
    Vec vec1 = new Vec("hrebik","vec1",true);
    Vec vec2 = new Vec("prkno","vec2",true);
    Vec vec3 = new Vec("vidle","vec3",true);
    Vec vec4 = new Vec("lopata","vec4",true);
    Vec vec5 = new Vec("koste","vec5",true);
    Vec vec6 = new Vec("pistole","vec6",true);
    Vec neprenositelna = new Vec("neprenositelna","neprenositelna",false);

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        batoh.vlozVec(new Vec("vecicka","text",true));

    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    /**
     * Testuje metodu vlozVec a obsahujeVec
     */
    @Test
    public void testVlozVec() {
//        //vec neni prenositelna
//        assertFalse(batoh.vlozVec(neprenositelna));
//        assertFalse(batoh.obsahujeVec("neprenositelna"));
//
//        //vkládání když je v batohu místo
//        assertTrue(batoh.vlozVec(vec1));
//        assertTrue(batoh.obsahujeVec("hrebik"));
//        assertTrue(batoh.vlozVec(vec2));
//        assertTrue(batoh.obsahujeVec("prkno"));
//        assertTrue(batoh.vlozVec(vec3));
//        assertTrue(batoh.obsahujeVec("vidle"));
//        assertTrue(batoh.vlozVec(vec4));
//        assertTrue(batoh.obsahujeVec("lopata"));
//
//        //překročena kapacita
//        assertFalse(batoh.vlozVec(vec5));
//        assertFalse(batoh.obsahujeVec("koste"));
//        assertFalse(batoh.vlozVec(vec6));
//        assertFalse(batoh.obsahujeVec("pistole"));
    }

    /**
     * Testuje metodu odeberVec
     */
    @Test
    public void testOdeberVec() {
//        assertTrue(batoh.vlozVec(vec1));
//        assertTrue(batoh.obsahujeVec("hrebik"));
//        assertEquals(vec1,batoh.odeberVec(vec1));
//
//        assertTrue(batoh.vlozVec(vec1));
//        assertTrue(batoh.obsahujeVec("hrebik"));
//        assertEquals(vec1,batoh.odeberVec("hrebik"));
    }

    /**
     * Testuje metodu plny
     */
    @Test
    public void testPlny(){
        batoh.vlozVec(vec1);
        batoh.vlozVec(vec2);
        batoh.vlozVec(vec3);
        assertFalse(batoh.plny());
        batoh.vlozVec(vec4);
        assertTrue(batoh.plny());
    }
}
