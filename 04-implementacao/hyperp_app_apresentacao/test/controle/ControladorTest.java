/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dominio.NoMidia;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author admin
 */
public class ControladorTest {
    
    public ControladorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of carregarMidias method, of class Controlador.
     */
    @Test
    public void testCarregarMidias() {
        System.out.println("carregarMidias");
        Controlador.carregarMidias();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of avancarIndice method, of class Controlador.
     */
    @Test
    public void testAvancarIndice() {
        System.out.println("avancarIndice");
        Controlador.avancarIndice();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retrocederIndice method, of class Controlador.
     */
    @Test
    public void testRetrocederIndice() {
        System.out.println("retrocederIndice");
        Controlador.retrocederIndice();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNoAtual method, of class Controlador.
     */
    @Test
    public void testGetNoAtual() {
        System.out.println("getNoAtual");
        NoMidia expResult = null;
        NoMidia result = Controlador.getNoAtual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of play method, of class Controlador.
     */
    @Test
    public void testPlay() {
        System.out.println("play");
        Controlador.play();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pause method, of class Controlador.
     */
    @Test
    public void testPause() {
        System.out.println("pause");
        Controlador.pause();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of proximo method, of class Controlador.
     */
    @Test
    public void testProximo() {
        System.out.println("proximo");
        Controlador.proximo();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of anterior method, of class Controlador.
     */
    @Test
    public void testAnterior() {
        System.out.println("anterior");
        Controlador.anterior();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
