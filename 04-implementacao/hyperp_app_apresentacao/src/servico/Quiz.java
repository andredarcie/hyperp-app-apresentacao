/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import servico.Servidor;
import java.io.DataOutputStream;

/**
 *
 * @author andre
 */
public class Quiz {
    
    private static int _alternativaA, _alternativaB, _alternativaC, _alternativaD;
    
    public static void iniciarQuiz() {
        
        resetarAlternativas();
        
        try {
            Servidor.replicarMensagemEspectador("iniciar");
            System.out.println("Quiz iniciou, Servidor envia 'iniciar' ");
        } catch (Exception ex) {
            System.out.println("Servidor: Erro ao enviar inicio do quiz! ");
        }
    }

    public static void finalizarQuiz() {
        
        
        try {
            Servidor.replicarMensagemEspectador("finalizar");
            System.out.println("Quiz finalizou, Servidor envia 'finalizar' ");
        } catch (Exception ex) {
            System.out.println("Quiz finalizou, Mas servidor não foi capaz de enviar 'finalizar' ");
        }
        
        try {
            
            Servidor.outResultado = new DataOutputStream(Servidor.socketApresentador.getOutputStream());
            int total = _alternativaA + _alternativaB + _alternativaC + _alternativaD;

            if (total != 0) {
                _alternativaA = (_alternativaA / total) * 100;
                _alternativaB = (_alternativaB / total) * 100;
                _alternativaC = (_alternativaC / total) * 100;
                _alternativaD = (_alternativaD / total) * 100;

                //System.out.println("Quiz finalizou, Servidor envia 'finalizar' ");
                
                System.out.println("Envia para o professor: " + _alternativaA + "," + _alternativaB + "," + _alternativaC + "," + _alternativaD);
                Servidor.outResultado.writeUTF(_alternativaA + "," + _alternativaB + "," + _alternativaC + "," + _alternativaD);

            } else {

                //System.out.println("Quiz finalizou, Servidor envia 'finalizar' ");
                System.out.println("Envia para o professor: alunos não responderam!");
                Servidor.outResultado.writeUTF("Resultado: os alunos não responderam.");
            }
        } catch (Exception ex) {
            System.out.println("Erro ao enviar resultado para professor.");
        }
    }
    
    public static void resetarAlternativas(){
        _alternativaA = _alternativaB = _alternativaC = _alternativaD = 0;
    }
    
    public static void adicionarA(){
        _alternativaA++;
    }
    
    public static void adicionarB(){
        _alternativaB++;
    }
    
    public static void adicionarC(){
        _alternativaC++;
    }
    
    public static void adicionarD(){
        _alternativaD++;
    }
        
        
}
