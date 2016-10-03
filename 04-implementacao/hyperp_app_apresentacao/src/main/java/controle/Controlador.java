/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dominio.NoMidia;
import java.io.File;
import java.io.FileNotFoundException;
import servico.Quiz;
import com.hypersoft.visao.ui.View;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 *
 * @author andre
 */
public class Controlador {

    private static String playListAtual = "principal"; //principal ou secundaria
    private static List<NoMidia> playListPrincipal = new ArrayList<>();
    private static List<NoMidia> playListSecundaria = new ArrayList<>();
    
    private static boolean modoQuiz = false;
    
    private static int midiaPrincipalAtual = 0;
    private static int midiaSecundariaAtual = 0;

    private static String urlPadrao = "file:///C:/SMH/";
    
    private static boolean leituraArquivo = false;
    
    public static void setMidiaPrincipalAtual(int i){
        midiaPrincipalAtual = i;
    }
    
    // Carrega todos os recursos necessarios para a apresentação
    public static void carregarMidias() {

        if (leituraArquivo){
            
            
            try {
                
                //Get scanner instance
                Scanner scanner;
                scanner = new Scanner(new File("C:\\SMH\\recursos.csv"));

                //Get all tokens and store them in some data structure
                //I am just printing them
                while (scanner.hasNextLine()) 
                {
                    String texto = scanner.nextLine();
                    String[] linhaConteudo = texto.split(",");
                    playListPrincipal.add(new NoMidia(urlPadrao + linhaConteudo[0], linhaConteudo[1], linhaConteudo[2]));
                }

                //Do not forget to close the scanner  
                scanner.close();
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        } else {
            
                // Adiciona os recursos em uma lista
                /*
                playListPrincipal = new ArrayList() {
                {
                add(new NoMidia(urlPadrao + "img/fun_aguardando.png", "img", "Inicio"));
                add(new NoMidia(urlPadrao + "img/fun_selecao.png", "img", "Disciplina"));
                add(new NoMidia(urlPadrao + "Aula_1.mp4", "video", "Aula 1 - Introdução "));
                add(new NoMidia(urlPadrao + "img/office.png", "quiz", "Quiz"));
                add(new NoMidia(urlPadrao + "Aula_2.mp4", "video", "Aula 2 - Continuação"));
                add(new NoMidia(urlPadrao + "img/atalhos-1.png", "img", "Aula 1 - Atalhos parte 1 "));
                add(new NoMidia(urlPadrao + "img/atalhos-2.png", "img", "Aula 1 - Atalhos parte 2 "));
                add(new NoMidia(urlPadrao + "img/barra.png", "quiz", "Quiz"));
                add(new NoMidia(urlPadrao + "img/controle-alteracoes.png", "img", "Aula 1 - Alterações "));
                add(new NoMidia(urlPadrao + "img/imprimir.png", "img", "Aula 2 - Imprimir "));
                add(new NoMidia(urlPadrao + "extras/gerador-de-texto.mp4", "extras", "Conteúdo Extra - Gerador de Texto "));
                add(new NoMidia(urlPadrao + "extras/aumentar-e-diminuir-texto.mp4", "extras", "Conteúdo Extra - Aumentar e Diminuir Texto "));
                add(new NoMidia(urlPadrao + "extras/limpar-formatacao.mp4", "extras", "Conteúdo Extra - Limpar Formatacao "));
                add(new NoMidia(urlPadrao + "extras/deletar-palavra.mp4", "extras", "Conteúdo Extra - Deletar Palavra "));
                add(new NoMidia(urlPadrao + "extras/crtl-c.mp4", "extras", "Conteúdo Extra - Crtl+C "));
                add(new NoMidia(urlPadrao + "extras/copyrigth.mp4", "extras", "Conteúdo Extra - Copyrigth "));
                add(new NoMidia(urlPadrao + "img/conteudo.png", "quiz", "Quiz"));
                add(new NoMidia(urlPadrao + "extras/tabela.mp4", "extras", "Conteúdo Extra - Tabela "));
                add(new NoMidia(urlPadrao + "extras/formulas.mp4", "extras", "Conteúdo Extra - Formulas "));
                add(new NoMidia(urlPadrao + "extras/pesquisa-web.mp4", "extras", "Conteúdo Extra - Pesquisa Web "));
                add(new NoMidia(urlPadrao + "extras/traducao.mp4", "extras", "Conteúdo Extra - Traducao "));
                add(new NoMidia(urlPadrao + "img/fun_finalizado.png", "img", "Fim"));
                }
                };
                */
                
            playListPrincipal = new ArrayList() {
                {
                    add(new NoMidia("file:///../src/main/resources/img/img/fun_aguardando.png", "img", "Inicio"));
                    add(new NoMidia("file:///../src/main/resources/img/img/fun_selecao.png", "img", "Disciplina"));
                    add(new NoMidia(new File("gerador-de-texto.mp4").toURI().toString(), "extras", "Conteúdo Extra - Gerador de Texto "));
                    add(new NoMidia("file:///../src/main/resources/img/img/conteudo.png", "quiz", "Disciplina"));
                    add(new NoMidia(new File("aumentar-e-diminuir-texto.mp4").toURI().toString(), "extras", "Conteúdo Extra - Gerador de Texto "));
                    add(new NoMidia("file:///../src/main/resources/img/img/barra.png", "quiz", "Disciplina"));
                    add(new NoMidia("file:///../src/main/resources/img/img/fun_finalizado.png", "img", "Disciplina"));  
                }
            };
        }
    }

    public static void avancarIndice() {
        if (playListAtual.equals("principal")) {
            if (midiaPrincipalAtual < playListPrincipal.size() - 1) {
                midiaPrincipalAtual++;
            }
            //if (midiaPrincipalAtual > playListPrincipal.size() - 1) { midiaPrincipalAtual = 0; }

        } else if (playListAtual.equals("secundaria")) {
            System.out.println("Exibir conteudo extra");
        }
    }

    public static void retrocederIndice() {
        if (playListAtual.equals("principal")) {
            if (midiaPrincipalAtual > 0) {
                midiaPrincipalAtual--;
            }
            //if (midiaPrincipalAtual < 0) { midiaPrincipalAtual = (playListPrincipal.size() - 1); }

        } else if (playListAtual.equals("secundaria")) {
            System.out.println("Exibir conteudo extra");
        }

    }

    public static NoMidia getNoAtual() {
        return playListPrincipal.get(midiaPrincipalAtual);
    }

    public static void play() {
        if (getNoAtual().getTipo().equals("video")) {
            View.mediaPlayer.currentTimeProperty().addListener((ObservableValue<? extends Duration> observableValue, Duration duration, Duration current) -> {
                View.slider.setValue(current.toSeconds());
            });

            View.slider.setOnMouseClicked((MouseEvent mouseEvent) -> {
                View.mediaPlayer.seek(Duration.seconds(View.slider.getValue()));
            });
            View.mediaPlayer.play();
        }
    }

    public static void pause() {
        View.mediaPlayer.pause();

    }

    public static void proximo() {

        avancarIndice();
        pause();
        switch (getNoAtual().getTipo()) {
            case "video":
                if (modoQuiz) {
                    Quiz.finalizarQuiz();
                    modoQuiz = false;
                }   View.btnPlay.setVisible(true);
                View.btnPause.setVisible(true);
                View.slider.setVisible(true);
                View.mediaView.setVisible(true);
                View.imageView.setVisible(false);
                View.mediaPlayer = new MediaPlayer(getNoAtual().getVideo());
                View.mediaView.setMediaPlayer(View.mediaPlayer);
                play();
                break;
            case "img":
                if (modoQuiz) {
                    Quiz.finalizarQuiz();
                    modoQuiz = false;
                }   View.btnPlay.setVisible(false);
                View.btnPause.setVisible(false);
                View.slider.setVisible(false);
                View.mediaView.setVisible(false);
                View.imageView.setVisible(true);
                View.imageView.setImage(getNoAtual().getImage());
                break;
            case "quiz":
                View.btnPlay.setVisible(false);
                View.btnPause.setVisible(false);
                View.slider.setVisible(false);
                View.mediaView.setVisible(false);
                View.imageView.setVisible(true);
                View.imageView.setImage(getNoAtual().getImage());
                modoQuiz = true;
                Quiz.iniciarQuiz();
                break;
            default:
                break;
        }
    }

    public static void anterior() {
        retrocederIndice();
        pause();
        switch (getNoAtual().getTipo()) {
            case "video":
                if (modoQuiz) {
                    Quiz.finalizarQuiz();
                    modoQuiz = false;
                }   View.btnPlay.setVisible(true);
                View.btnPause.setVisible(true);
                View.slider.setVisible(true);
                View.mediaView.setVisible(true);
                View.imageView.setVisible(false);
                View.mediaPlayer = new MediaPlayer(getNoAtual().getVideo());
                View.mediaView.setMediaPlayer(View.mediaPlayer);
                play();
                break;
            case "img":
                if (modoQuiz) {
                    Quiz.finalizarQuiz();
                    modoQuiz = false;
                }   View.btnPlay.setVisible(false);
                View.btnPause.setVisible(false);
                View.slider.setVisible(false);
                View.mediaView.setVisible(false);
                View.imageView.setVisible(true);
                View.imageView.setImage(getNoAtual().getImage());
                break;
            case "quiz":
                View.btnPlay.setVisible(false);
                View.btnPause.setVisible(false);
                View.slider.setVisible(false);
                View.mediaView.setVisible(false);
                View.imageView.setVisible(true);
                View.imageView.setImage(getNoAtual().getImage());
                modoQuiz = true;
                Quiz.iniciarQuiz();
                break;
            default:
                break;
        }
    }
}
