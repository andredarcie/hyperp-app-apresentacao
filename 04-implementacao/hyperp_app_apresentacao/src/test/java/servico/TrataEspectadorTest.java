/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
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
public class TrataEspectadorTest {
    
    public TrataEspectadorTest() {
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
     * Test of run method, of class TrataEspectador.
     */
    @Test
    public void testRun() throws IOException {
        Servidor server = new Servidor();
        server.start();
        
        boolean teste = false;
        try{
            Socket client = new Socket("localhost", 9000);
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());
            
            out.writeUTF("espectador");
            teste = client.isConnected();
            
            in.close();
            out.close();
            client.close();
        } catch(IOException e){
            //
        }
        
        server.getServer().close();
        assertEquals(true, teste);
    }

    /**
     * Test of enviarMensagem method, of class TrataEspectador.
     */
    @Test
    public void testEnviarMensagem() throws Exception {
        Servidor server = new Servidor();
        server.start();
        
        boolean teste = false;
        try{
            Socket client = new Socket("localhost", 9000);
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());
            
            out.writeUTF("espectador");
            
            Servidor.replicarMensagemEspectador("mensagem teste");
            
            //parte nova a ser testada
            try { 
                server.listaEspectador.get(0).enviarMensagem("mensagem teste");
                
                teste = true;
            } catch (Exception e){
                teste = false;
            }
            //
            
            in.close();
            out.close();
            client.close();
        } catch(IOException e){
            //
        }
        
        server.getServer().close();
        assertEquals(true, teste);
    }
    
}
