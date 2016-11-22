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

    }
    
    
    /**
     * Teste responsavel por identificar se um apresentador conectou
     */
    @Test
    public void testVerificaApresentadorConectado() throws Exception {

    }
    
    /**
     * Testa o bloqueio caso um apresentador ja esteja conectado
     */
    @Test
    public void testBloqueioVariasConexoesDeApresentadores() throws Exception {

    }

    /**
     * Test of replicarMensagemEspectador method, of class Servidor.
     */
    @Test
    public void testReplicarMensagemEspectador() throws Exception {

    }

    /**
     * Test of fecharServidor method, of class Servidor.
     */
    @Test
    public void testFecharServidor() throws InterruptedException {

    }
    
}
