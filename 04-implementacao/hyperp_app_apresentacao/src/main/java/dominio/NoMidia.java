/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

/**
 *
 * @author andre
 */
public class NoMidia {

    private String _tipoRecurso = ""; // [video, img, extras, quiz]
    private Media _video;
    private Image _image;
    private String _titulo = "";

    public NoMidia(String url, String tipoRecurso, String titulo) {
        
        switch (tipoRecurso) {
            case "video":
                _tipoRecurso = tipoRecurso;
                _video = new Media(url);
                _titulo = titulo;
                break;
            case "img":
                _tipoRecurso = tipoRecurso;
                _image = new Image(url);
                _titulo = titulo;
                break;
            case "extras":
                _tipoRecurso = "video";
                _video = new Media(url);
                _titulo = titulo;
                break;
            case "quiz":
                _tipoRecurso = tipoRecurso;
                _image = new Image(url);
                _titulo = titulo;
                break;
            default:
                break;
        }
    }

    public String getTipo() {
        return _tipoRecurso;
    }

    public Media getVideo() {
        return _video;
    }
    
    public Image getImage(){
        return _image;
    }
    
    public String getTitulo() {
        return _titulo + " - " + _tipoRecurso;
    }
    
    
    
}
