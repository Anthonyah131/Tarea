/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea.util;

import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author ANTHONY
 */
public class ImageSwitcher {
    private List<Image> images;
    private ImageView imageView;
    private Timeline timeline;

    public ImageSwitcher(List images, ImageView imageView) {
        this.images = images;
        this.imageView = imageView;

        // Creamos una línea de tiempo que actualiza el ImageView cada 2 segundos
        timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            int index = (imageView.getImage() == null ? 0 : getIndex() + 1) % images.size();
            imageView.setImage((Image) images.get(index));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }
    
    public ImageSwitcher() {
    }

    // Método para iniciar la línea de tiempo
    public void start() {
        timeline.play();
    }

    // Método para detener la línea de tiempo
    public void stop() {
        timeline.stop();
    }

    // Método para obtener el índice de la imagen actual
    private int getIndex() {
        for (int i = 0; i < images.size(); i++) {
            if (images.get(i) == imageView.getImage()) {
                return i;
            }
        }
        return -1;
    }
}

