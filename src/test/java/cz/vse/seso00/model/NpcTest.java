package cz.vse.seso00.model;

import cz.vse.seso00.model.Npc;
import cz.vse.seso00.model.Vec;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*******************************************************************************
 * Testovací třída NpcTest slouží ke komplexnímu otestování
 * třídy Npc
 *
 * @author     Ondřej Šesták
 * @version    červen 2020 (1.0)
 */
public class NpcTest {

 Vec pivo =new Vec("pivo","pivko",true);
 Vec penize = new Vec("penize","dvacka",true);
 Npc pepa = new Npc("Pepa","ahoj",pivo,penize,true,false);

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
  * Testuje metody getNazev, getRika, getChce, getDava, getBoj a getObchod
  */
  @Test
  public void testNpc() {
  assertEquals("Pepa",pepa.getNazev());
  assertEquals("ahoj",pepa.getRika());
  assertEquals(pivo,pepa.getChce());
  assertEquals(penize,pepa.getDava());
  assertEquals(true,pepa.getBoj());
  assertEquals(false,pepa.getObchod());


  }
}
