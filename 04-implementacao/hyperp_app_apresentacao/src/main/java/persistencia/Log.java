/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class Log {
    
    public static List<String> _conteudoRelatorio = new ArrayList<>();
    
    public static void gerarArquivoLog(){
          
        try {
            
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Brazil/East"));
            String dia = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
            String mes = Integer.toString(calendar.get(Calendar.MONTH));
            String ano = Integer.toString(calendar.get(Calendar.YEAR));
            
            FileWriter arq = new FileWriter("C:\\SMH\\log-" + dia + "!" + mes + "!" + ano + ".txt");
            PrintWriter gravarArq = new PrintWriter(arq);

            gravarArq.println("Data do Log: " 
                + Calendar.getInstance().getTime().toString());
            
            gravarArq.println("Gerado por: " + System.getProperty("user.name"));  
                    
            gravarArq.println("");
            
            _conteudoRelatorio.stream().forEach((contRel) -> {
                gravarArq.println(contRel);
            });
            
            arq.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void addLog(String conteudo){
        
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Brazil/East"));
        
        String hora = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
        String minuto = Integer.toString(calendar.get(Calendar.MINUTE));
        String segundo = Integer.toString(calendar.get(Calendar.SECOND));
                
        _conteudoRelatorio.add(hora + ":" + minuto + ":" + segundo + " - " + conteudo);
        
        // Exibindo por padrão também no console
        System.out.println(conteudo);
    }
    
    
}
