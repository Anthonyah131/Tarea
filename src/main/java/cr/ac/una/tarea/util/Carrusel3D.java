/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea.util;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 *
 * @author ANTHONY
 */
public class Carrusel3D {

    private AnchorPane apTours;
    private List<Image> fotos;
    private int currentIndex;
    private Timeline timeline;

    public Carrusel3D(List<Image> fotos, AnchorPane apTours) {
        this.fotos = fotos;
        this.apTours = apTours;
        this.currentIndex = 0;
    }

    public void startCarrusel() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            if (apTours.getChildren().size() - 1 >= 1) {
                if (currentIndex >= apTours.getChildren().size() - 1) {
                    currentIndex = 0;
                    for (int i = apTours.getChildren().size() - 1; i >= 1; i--) {
                        Node node = apTours.getChildren().get(i);
                        if (node instanceof StackPane) {
                            StackPane pane = (StackPane) node;
                            translateAnimation(1, pane, 2000);
                        }
                    }
                } else {
                    Node node = apTours.getChildren().get(currentIndex + 1);
                    if (node instanceof StackPane) {
                        StackPane pane = (StackPane) node;
                        translateAnimation(1, pane, -2000);
                        currentIndex++;
                    }
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void stopCarrusel() {
        timeline.stop();
    }

    public void translateAnimation(double duration, Node node, double width) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node);
        translateTransition.setByX(width);
        translateTransition.play();
    }

    public void inicializarCarrusel() {
        for (Image foto : fotos) {
            ImageView imageView = new ImageView(foto);
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(180);
            
            HBox hboxContenedor = new HBox();
            hboxContenedor.setSpacing(10);
            hboxContenedor.setAlignment(Pos.CENTER);
            hboxContenedor.getChildren().add(imageView);
            
            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(hboxContenedor);
            stackPane.getStyleClass().add("stackpane-contenedor");
            stackPane.setAlignment(Pos.CENTER);
            
            AnchorPane.setTopAnchor(stackPane, 0.0);
            AnchorPane.setBottomAnchor(stackPane, 0.0);
            AnchorPane.setLeftAnchor(stackPane, 0.0);
            AnchorPane.setRightAnchor(stackPane, 0.0);
            apTours.getChildren().add(stackPane);
        }
        this.currentIndex = apTours.getChildren().size() - 1;
    }
}
