/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import persistencia.Log;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author admin
 */

public class TrataEspectador extends Thread {
    
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    public TrataEspectador(Socket socket) throws IOException { 
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            String resposta, nomeEspectador;
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
             nomeEspectador = in.readUTF();
             System.out.println("nome espectador logado :"+nomeEspectador);
            
            while (true) {
                resposta = in.readUTF();
                    
                switch (resposta) {
                    case "A":
                        Quiz.adicionarA();
                        Log.addLog("O espectador " + nomeEspectador + 
                                ", enviou a alternativa A.");
                        break;
                    case "B":
                        Quiz.adicionarB();
                        Log.addLog("O espectador " + nomeEspectador + 
                                ", enviou a alternativa B.");
                        break;
                    case "C":
                        Quiz.adicionarC();
                        Log.addLog("O espectador " + nomeEspectador + 
                                ", enviou a alternativa C.");
                        break;
                    case "D":
                        Quiz.adicionarD();
                        Log.addLog("O espectador " + nomeEspectador + 
                                ", enviou a alternativa D.");
                        break;
                    default:
                        Log.addLog("O espectador " + nomeEspectador + 
                                ", não respondeu o quiz.");

                }
                resposta="";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // Envia mensagem para todos clientes
    public String enviarMensagem(String mensagem) throws InterruptedException {
        try {
            out.writeUTF(mensagem);
            out.flush();
            System.out.println("enviar mensagem recebeu: "+mensagem);
            //Thread.sleep(3000);
           
        } catch (IOException ex) {
            System.out.println("Erro ao enviar mensagem para um espectador.");
        }
        return mensagem;
    }

}