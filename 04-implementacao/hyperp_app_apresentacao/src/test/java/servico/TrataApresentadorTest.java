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
public class TrataApresentadorTest {
    
    public TrataApresentadorTest() {
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
     * Test of run method, of class TrataApresentador.
     * Verifica se a conexao foi bem sucedida.
     */
    @Test
    public void testRun() throws InterruptedException, IOException {
        Servidor server = new Servidor();
        server.start();
        
        boolean testar = false;
        try{
            Socket client = new Socket("localhost", 9000);
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());
            
            out.writeUTF("apresentador");
            testar = client.isConnected() && in.readUTF().equals("autorizado");
            
            in.close();
            out.close();
            client.close();
        } catch(IOException e){
            //
        }
        
        server.getServer().close();
        assertEquals(true, testar);
    }
    
    @Test
    public void testSolicitacoes() throws IOException {
        Servidor server = new Servidor();
        server.start();
        
        boolean testar = false;
        try{
            Socket client = new Socket("localhost", 9000);
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());
            
            out.writeUTF("apresentador");
            testar = client.isConnected() && in.readUTF().equals("autorizado");
            
            //parte nova a ser testada
            try { 
                out.writeUTF("play");
                out.writeUTF("pause");
                out.writeUTF("next");
                out.writeUTF("back");
                
                testar = true;
            } catch (Exception e){
                testar = false;
            }
            //
            
            in.close();
            out.close();
            client.close();
        } catch(IOException e){
            //
        }
        
        server.getServer().close();
        assertEquals(true, testar);
    }

}
