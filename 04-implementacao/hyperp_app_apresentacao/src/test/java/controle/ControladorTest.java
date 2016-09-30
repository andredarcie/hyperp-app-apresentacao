/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import com.hypersoft.visao.ui.View;
import dominio.NoMidia;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
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
            
        // Initialise Java FX

        System.out.printf("About to launch FX App\n");
        Thread t;
        t = new Thread("JavaFX Init Thread") {
            @Override
            public void run() {
                Application.launch(View.class, new String[0]);
            }
        };
        t.setDaemon(true);
        t.start();
        System.out.printf("FX App thread started\n");
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(ControladorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Controlador.setMidiaPrincipalAtual(3);
        
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
    }

    /**
     * Test of avancarIndice method, of class Controlador.
     */
    @Test
    public void testAvancarIndice() {
        System.out.println("avancarIndice");
        Controlador.avancarIndice();
    }

    /**
     * Test of retrocederIndice method, of class Controlador.
     */
    @Test
    public void testRetrocederIndice() {
        System.out.println("retrocederIndice");
        Controlador.retrocederIndice();
    }

    /**
     * Test of getNoAtual method, of class Controlador.
     */
    @Test
    public void testGetNoAtual() {
        System.out.println("getNoAtual");
        NoMidia expResult = null;
        Controlador.carregarMidias();
        NoMidia result = Controlador.getNoAtual();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of play method, of class Controlador.
     */
    @Test
    public void testPlay() {
        System.out.println("play");
        Controlador.play();
    }

    /**
     * Test of pause method, of class Controlador.
     */
    @Test
    public void testPause() {
        System.out.println("pause");
        Controlador.pause();
    }

    /**
     * Test of proximo method, of class Controlador.
     */
    @Test
    public void testProximo() {
        System.out.println("proximo");
        Controlador.proximo();
    }

    /**
     * Test of anterior method, of class Controlador.
     */
    @Test
    public void testAnterior() {
        System.out.println("anterior");
        Controlador.anterior();
    }
    
}
