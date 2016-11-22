/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import servico.TrataApresentador;
import servico.TrataEspectador;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class Servidor extends Thread {
    
    public static ArrayList<TrataEspectador> listaEspectador = new ArrayList<>();
    
    public static Socket socketApresentador = null;
    public static DataOutputStream outResultado = null;
    public static boolean apresentadorConectado;
    private final static int PORTA = 9000;
    private ServerSocket server;
    
    @Override
    public void run(){
        iniciarServidor();
    }
    
    public boolean iniciarServidor() {
        boolean executando = true;

        try {
            this.server = new ServerSocket(PORTA);
            Socket socket;

            InetAddress addr = InetAddress.getLocalHost();
            System.out.println("----------- SERVIDOR ABERTO " + addr.getHostAddress() + " PORTA " + PORTA + " -----------");
            System.out.println("Esperando Conexões.");

            try {
                while (executando) {
                    socket = server.accept();
                    System.out.println("Sevidor: conectou o IP " + socket.getInetAddress().getHostAddress());

                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    String tipoCliente = in.readUTF();
                    System.out.println("Servidor: mensagem recebida: " + tipoCliente);

                    switch (tipoCliente) {
                        case "apresentador":
                            if (!verificaApresentadorConectado()) {
                                apresentadorConectado = true;
                                out.writeUTF("autorizado");
                                socketApresentador = socket;
                                
                                TrataApresentador apresentador = new TrataApresentador(socket);
                                apresentador.start();
                            } else {
                                out.writeUTF("recussado");
                                socket.close();
                            }
                            break;
                        case "espectador":
                            TrataEspectador cliente = new TrataEspectador(socket);
                            cliente.start();
                            listaEspectador.add(cliente);
                            break;
                        default:
                            System.out.println("Servidor: erro ao receber tipo de cliente.");
                    }
                }
                System.out.println("Servidor: finalizado.");
                server.close();
            } catch (IOException e) {
                System.err.println("Servidor: erro-> " + e.getMessage());
                executando = false;
            }
        } catch (Exception e) {
            System.out.println("Servidor: erro ao iniciar servidor.");
            e.printStackTrace();
        }
        
        return true;

    }
    
    public boolean fecharServidor(){
        try {
            this.server.close();
        } catch(IOException e){
            System.err.println("Erro ao fechar servidor");
            return false;
        }
        return true;
    }
    
    private boolean verificaApresentadorConectado(){
        if (this.apresentadorConectado == true) 
            return true;
        
        return false;
    }
    
    public ServerSocket getServer(){
        return server;
    }
    
    public boolean getApresentadorConectado(){
        return this.apresentadorConectado;
    }

    public static boolean replicarMensagemEspectador(String mensagem) throws InterruptedException {
        
            System.out.println(listaEspectador.size());
            //synchronized (listaAlunos) {
            for (TrataEspectador cl : listaEspectador) {
                try {
                    //enviar mensagem para cliente
                    cl.enviarMensagem(mensagem);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Servidor: Envia a mensagem " + mensagem + " para espectador.");
            }
            
        //}
           return true; 
    }
    
}
