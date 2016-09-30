/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author admin
 */
public class ServidorTest {
    
    public ServidorTest() {
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
     * Test of iniciarServidor method, of class Servidor.
     */
    @Test
    public void testIniciarServidor() {
        Servidor server = new Servidor();
        assertEquals(10, server.testar(5, 5));
        
        /*
        System.out.println("iniciarServidor");
        Servidor.iniciarServidor();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }

    /**
     * Test of replicarMensagemEspectador method, of class Servidor.
     */
    @Test
    public void testReplicarMensagemEspectador() throws Exception {
        
        /*
        System.out.println("replicarMensagemEspectador");
        String mensagem = "";
        Servidor.replicarMensagemEspectador(mensagem);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }

    private void assertIquals(int i, int testar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
