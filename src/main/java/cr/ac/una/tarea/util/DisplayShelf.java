/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea.util;

import java.util.List;
import java.util.stream.Stream;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.control.Slider;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author ANTHONY
 */
public class DisplayShelf {

    public void mostrarCarrucel(List<Image> fotos, AnchorPane apTours) {
//        List<Image> fotos = new ArrayList<>();
//
//        Image image4 = new Image("cr/ac/una/tarea/resources/Playa.jpg");
//        Image image5 = new Image("cr/ac/una/tarea/resources/Playa2.jpg");
//        Image image6 = new Image("cr/ac/una/tarea/resources/Playa3.jpg");
//
//        Image image7 = new Image("cr/ac/una/tarea/resources/imgs/animals/animal1.jpg");
//        Image image8 = new Image("cr/ac/una/tarea/resources/imgs/animals/animal2.jpg");
//        Image image9 = new Image("cr/ac/una/tarea/resources/imgs/animals/animal3.jpg");
//        Image image10 = new Image("cr/ac/una/tarea/resources/imgs/animals/animal4.jpg");
//        Image image11 = new Image("cr/ac/una/tarea/resources/imgs/animals/animal5.jpg");
//        Image image12 = new Image("cr/ac/una/tarea/resources/imgs/animals/animal6.jpg");
//        Image image13 = new Image("cr/ac/una/tarea/resources/imgs/animals/animal1.jpg");
//        Image image14 = new Image("cr/ac/una/tarea/resources/imgs/animals/animal2.jpg");
//        Image image15 = new Image("cr/ac/una/tarea/resources/imgs/animals/animal3.jpg");
//        Image image16 = new Image("cr/ac/una/tarea/resources/imgs/animals/animal4.jpg");
//        Image image17 = new Image("cr/ac/una/tarea/resources/imgs/animals/animal5.jpg");
//        Image image18 = new Image("cr/ac/una/tarea/resources/imgs/animals/animal6.jpg");
//
//        fotos.add(image4);
//        fotos.add(image5);
//        fotos.add(image6);
//
//        fotos.add(image7);
//        fotos.add(image8);
//        fotos.add(image9);
//        fotos.add(image10);
//        fotos.add(image11);
//        fotos.add(image12);
//        fotos.add(image13);
//        fotos.add(image14);
//        fotos.add(image15);
//        fotos.add(image16);
//        fotos.add(image17);
//        fotos.add(image18);

        int[] index = {0};
        Unit[] images = fotos.stream()
                .map(img -> new Unit(img.getUrl(), index[0]++))
                .toArray(Unit[]::new);

        Group container = new Group();
        container.setStyle("-fx-background-color:derive(black, 20%)");
        container.getChildren().addAll(images);

        Slider slider = new Slider(0, images.length - 1, 0);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setBlockIncrement(1);
        slider.setSnapToTicks(true);

        container.getChildren().add(slider);

        apTours.getChildren().add(container);

        slider.translateXProperty().bind(apTours.widthProperty().divide(2).subtract(slider.widthProperty().divide(2)));
        slider.setTranslateY(10);

        // FxTransformer.sliders(new DoubleProperty[] {x, z, rotation}, new String[] {"x", "z", "rotation"}, stage, -360, 360).show();
        // new FxTransformer(images, IntStream.range(0, images.length).mapToObj(i -> "images["+i+"]").toArray(String[]::new), stage, -500, 1000).show();
        slider.valueProperty().addListener((p, o, n) -> {
            if (n.doubleValue() == n.intValue()) {
                Stream.of(images).forEach(u -> u.update(n.intValue(), apTours.getWidth(), apTours.getHeight()));
            }
        });

        container.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getTarget() instanceof Unit) {
                slider.setValue(((Unit) e.getTarget()).index);
            }
        });

        slider.setValue(3);
    }

    private static class Unit extends ImageView {

        final static Reflection reflection = new Reflection();
        final static Point3D rotationAxis = new Point3D(0, 90, 1);

        static {
            reflection.setFraction(0.5);
        }

        final int index;
        final Rotate rotate = new Rotate(0, rotationAxis);
        final TranslateTransition transition = new TranslateTransition(Duration.millis(300), this);

        public Unit(String imageUrl, int index) {
            super(imageUrl);
            setEffect(reflection);
            setUserData(index);

            setPreserveRatio(false);
            setFitHeight(100);
            setFitWidth(100);
            this.index = index;
            getTransforms().add(rotate);
        }

        public void update(int currentIndex, double width, double height) {
            int ef = index - currentIndex;
            double middle = width / 2 - 100;
//            double middle = Math.min(width / 2 - 100, 450 / 2 - 100);
//            double middle = 125;
            System.out.println("middle: " + middle);
            boolean b = ef < 0;

//            setTranslateY(height / 2 - getImage().getHeight() / 2);
            setTranslateY(height / 2 - 100 / 2);
            double x, z, theta, pivot;

            if (ef == 0) {
                z = -100;
                x = middle;
                theta = 0;
                pivot = b ? 200 : 0;
            } else {
                x = middle + ef * 82 + (b ? -147 : 147);
                z = -78.588;
                pivot = b ? 200 : 0;
                theta = b ? 46 : -46;
            }
            rotate.setPivotX(pivot);
            rotate.setAngle(theta);

            transition.pause();
            transition.setToX(x);
            transition.setToZ(z);
            transition.play();
        }

    }

}
