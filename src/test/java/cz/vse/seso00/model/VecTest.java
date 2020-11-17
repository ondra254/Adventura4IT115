package cz.vse.seso00.model;

import cz.vse.seso00.model.Vec;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*******************************************************************************
 * Testovací třída VecTest slouží ke komplexnímu otestování
 * třídy Vec
 *
 * @author     Ondřej Šesták
 * @version    červen 2020 (1.0)
 */
public class VecTest {
 Vec pero = new Vec("pero","zelené pero",true);

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

 /**
  * Testuje metody getNazev a isPrenositelna
  */
    @Test
    public void testVec() {
     assertEquals("pero",pero.getNazev());
     assertEquals(true,pero.isPrenositelna());
    }
}
