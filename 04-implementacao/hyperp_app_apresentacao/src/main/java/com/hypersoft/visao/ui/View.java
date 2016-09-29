/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hypersoft.visao.ui;

import controle.Controlador;
import persistencia.Log;
import servico.Servidor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.stage.Screen;
import javafx.stage.WindowEvent;

/**
 *
 * @author Andre N. Darcie, Alexandre Benevides
 */
public class View extends Application {

    public static MediaPlayer mediaPlayer = new MediaPlayer(new Media("file:///C:/SMH/Aula_1.mp4"));
    public static MediaView mediaView = new MediaView();
    public static ImageView imageView = new ImageView();
    public static Pane root;

    public static Rectangle2D primaryScreenBounds;

    public static double screenWidth;
    public static double screenHeight;

    public static final VBox vbox = new VBox();
    public static Slider slider = new Slider();
    public static Button btnPlay;
    public static Button btnPause;

    // Para verificar se o quiz esta ativo
    //public static boolean modoQuiz = false;

    @Override
    public void start(Stage primaryStage) {
        
        // Ajustando o tamanho da aplicaçao para o tamanho da tela
        primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        screenWidth = primaryScreenBounds.getWidth();
        screenHeight = primaryScreenBounds.getHeight();

        // Inicia o servidor socket
        Servidor.iniciarServidor();
        
        // Carrega todos os recursos que vão ser usados na apresentação
        Controlador.carregarMidias();
        
        /*
        Log.addLog("Teste");
        Log.addLog("Teste");
        Log.addLog("Teste");
        */
        
        // Configurando a barra de progresso de video
        slider.setMinWidth(screenWidth - 500);
        vbox.getChildren().add(slider);
        vbox.setLayoutX(150);
        vbox.setLayoutY(screenHeight);

        // Botão de play
        ImageView imgView1 = new ImageView(new Image("file:///C:/SMH/icons/play.png"));
        imgView1.setFitHeight(10);
        imgView1.setFitWidth(10);
        btnPlay = new Button("", imgView1);

        btnPlay.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Controlador.play();
            }
        });

        btnPlay.setLayoutX(0);
        btnPlay.setLayoutY(screenHeight);

        // Botão de pause
        ImageView imgView2 = new ImageView(new Image("file:///C:/SMH/icons/pause.png"));
        imgView2.setFitHeight(10);
        imgView2.setFitWidth(10);
        btnPause = new Button("", imgView2);

        btnPause.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Controlador.pause();
            }
        });

        btnPause.setLayoutX(30);
        btnPause.setLayoutY(screenHeight);

        // Botão de proximo
        ImageView imgView3 = new ImageView(new Image("file:///C:/SMH/icons/next.png"));
        imgView3.setFitHeight(10);
        imgView3.setFitWidth(10);
        Button btnProx = new Button("", imgView3);

        btnProx.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Controlador.proximo();
            }
        });

        btnProx.setLayoutX(90);
        btnProx.setLayoutY(screenHeight);

        // Botão de anterior
        ImageView imgView4 = new ImageView(new Image("file:///C:/SMH/icons/back.png"));
        imgView4.setFitHeight(10);
        imgView4.setFitWidth(10);
        Button btnAnt = new Button("", imgView4);

        btnAnt.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Controlador.anterior();
            }
        });

        btnAnt.setLayoutX(60);
        btnAnt.setLayoutY(screenHeight);

        root = new Pane();
        root.setStyle("-fx-background-color: white;");
        root.getChildren().add(mediaView);
        root.getChildren().add(imageView);
        root.getChildren().add(vbox);
        root.getChildren().add(btnPlay);
        root.getChildren().add(btnPause);
        root.getChildren().add(btnProx);
        root.getChildren().add(btnAnt);
        Scene scene = new Scene(root, 1280, 595);
        //scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Projeto TV - Hyper P");

        final DoubleProperty width = mediaView.fitWidthProperty();
        final DoubleProperty height = mediaView.fitHeightProperty();

        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));

        final DoubleProperty width_img = imageView.fitWidthProperty();
        final DoubleProperty height_img = imageView.fitHeightProperty();

        width_img.bind(Bindings.selectDouble(imageView.sceneProperty(), "width"));
        height_img.bind(Bindings.selectDouble(imageView.sceneProperty(), "height"));

        mediaView.setPreserveRatio(true);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                // Quando chegar no final da apresentação gerar o arquivo de log
                Log.gerarArquivoLog();
                
                Platform.exit();
                System.exit(0);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
        
        // Se o primeiro conteudo for uma imagem, seta o ambiente
        if (Controlador.getNoAtual().getTipo().equals("img")) {
            btnPlay.setVisible(false);
            btnPause.setVisible(false);
            slider.setVisible(false);
            mediaView.setVisible(false);

            imageView.setVisible(true);

            imageView.setImage(Controlador.getNoAtual().getImage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
