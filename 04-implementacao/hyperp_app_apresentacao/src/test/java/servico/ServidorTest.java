/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
     * Teste responsavel por criar o servidor e verificar se esta disponivel para receber conexoes
     */
    @Test
    public void testIniciarServidor() throws InterruptedException, IOException {
        Servidor server = new Servidor();
        server.start();
        
        //tempo pra uma possivel sincronia com a thread Servidor
        Thread.sleep(1000);
        
        assertEquals(true, server.getServer().isBound());
        
        server.getServer().close();
    }
    
    
    /**
     * Teste responsavel por identificar se um apresentador conectou
     */
    @Test
    public void testVerificaApresentadorConectado() throws Exception {
        Servidor server = new Servidor();
        server.start();
        
        //tempo pra uma possivel sincronia com a thread Servidor
        //Thread.sleep(1000);
        
        boolean teste = false;
        try{
            Socket client = new Socket("localhost", 9000);
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            
            out.writeUTF("apresentador");
            
            //tempo pra uma possivel sincronia com a thread Servidor
            Thread.sleep(1000);
            
            teste = server.getApresentadorConectado();
            
            out.close();
            client.close();
        } catch(IOException e){
            //
        }
        
        server.getServer().close();
        assertEquals(true, teste);
    }
    
    /**
     * Testa o bloqueio caso um apresentador ja esteja conectado
     */
    @Test
    public void testBloqueioVariasConexoesDeApresentadores() throws Exception {
        Servidor server = new Servidor();
        server.start();
        
        boolean teste = false;
        try{
            Socket client1 = new Socket("localhost", 9000);
            DataOutputStream out1 = new DataOutputStream(client1.getOutputStream());
            DataInputStream in1 = new DataInputStream(client1.getInputStream());

            out1.writeUTF("apresentador");
            System.out.println("##########  " + in1.readUTF());
            
            Socket client2 = new Socket("localhost", 9000);
            DataOutputStream out2 = new DataOutputStream(client2.getOutputStream());
            DataInputStream in2 = new DataInputStream(client2.getInputStream());
            
            out2.writeUTF("apresentador");
            if(in2.readUTF().equals("recussado"))
                teste = true;

            in1.close();
            out1.close();
            client1.close();
            
            in2.close();
            out2.close();
            client2.close();
        } catch (IOException e){
            
        }
        
        server.getServer().close();
        assertEquals(true, teste);
    }

    /**
     * Test of replicarMensagemEspectador method, of class Servidor.
     */
    @Test
    public void testReplicarMensagemEspectador() throws Exception {
        Servidor server = new Servidor();
        server.start();
        
        boolean teste = false;
        try{
            Socket client = new Socket("localhost", 9000);
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());
            
            out.writeUTF("espectador");
            
            //parte nova a ser testada
            try { 
                Servidor.replicarMensagemEspectador("mensagem teste");
                
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

    /**
     * Test of fecharServidor method, of class Servidor.
     */
    @Test
    public void testFecharServidor() throws InterruptedException {
        Servidor server = new Servidor();
        server.start();
        
        //tempo pra uma possivel sincronia com a thread Servidor
        Thread.sleep(1000);
        
        server.fecharServidor();
        
        assertEquals(true, server.getServer().isClosed());
    }
    
}
