/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import persistencia.Log;
import controle.Controlador;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author admin
 */
public class TrataApresentador extends Thread{
    
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    
    public TrataApresentador(Socket socket) throws IOException { 
        this.socket = socket;
    }
    
    @Override
    public void run() {
        try {
            String solicitacao;
            DataInputStream in = new DataInputStream(socket.getInputStream());
            //DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            Controlador.proximo();

            String conteudo = in.readUTF();
            Log.addLog("Servidor: conteudo recebido: " + conteudo);
            String modulo = in.readUTF();
            Log.addLog("Servidor: modulo recebido: " + modulo);

            Controlador.proximo();

            while (true)
            {
                solicitacao = in.readUTF();

                switch (solicitacao) {
                    case "play":
                        Controlador.play();
                        break;
                    case "pause":
                        Controlador.pause();
                        break;
                    case "next":
                        Controlador.proximo();
                        break;
                    case "back":
                        Controlador.anterior();
                        break;
                    default:

                }
            }
        } catch (IOException e) {
            Log.addLog("Servidor: Apresentador saiu!");
            Servidor.apresentadorConectado = false;
        }
    }
    
}
