package Project_04;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class GM extends Application {


    @Override
    public void start(Stage stage) {

        Circle c1 = new Circle();
        c1.setCenterX(200.0f);
        c1.setCenterY(180.0f);
        c1.setRadius(130.0f);
        c1.setFill(Color.GOLD);
        c1.setStroke(Color.MAROON);
        c1.setStrokeWidth(6.0);

        Circle c3 = new Circle();
        c3.setCenterX(200.0f);
        c3.setCenterY(50.0f);
        c3.setRadius(30.0f);
        c3.setFill(Color.BLUE);
        c3.setStroke(Color.MAROON);
        c3.setStrokeWidth(4.0);

        Circle c2 = new Circle();
        c2.setCenterX(200.0f);
        c2.setCenterY(180.0f);
        c2.setRadius(70.0f);
        c2.setFill(Color.SEAGREEN);
        c2.setStroke(Color.BLACK);
        c2.setStrokeWidth(4.0);

        Circle c4 = new Circle();
        c4.setCenterX(230.0f);
        c4.setCenterY(120.0f);
        c4.setRadius(20.0f);
        c4.setFill(Color.GREENYELLOW);
        c4.setStroke(Color.BLACK);
        c4.setStrokeWidth(3.0);

        Slider slider = new Slider(0, 1,0.5);
        slider.setMajorTickUnit(0.25);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);

        Pane center = new Pane(c1, c2, c3, c4);
        BorderPane root = new BorderPane();
        root.setBottom(slider);
        root.setCenter(center);

        AnimationTimer outside = new AnimationTimer() {
            DoubleProperty angle = new SimpleDoubleProperty();
            DoubleProperty time = new SimpleDoubleProperty();
            DoubleProperty place = new SimpleDoubleProperty();
            final double R = c1.getRadius();
            final double X0 = c1.getCenterX();
            final double Y0 = c1.getCenterY();

            @Override
            public void handle(long l) {
                if(time.getValue() > 0){
                    double currentTime = (l - time.getValue() ) * 1e-9  / (2*100) * slider.getValue();
                    place.set(2*Math.PI*R*currentTime);
                }

                time.set(l);

                c3.setCenterX(R*Math.cos(angle.getValue())+X0);
                c3.setCenterY(R*Math.sin(angle.getValue())+Y0);
                angle.setValue(angle.getValue()+place.getValue());

            }
        };

        AnimationTimer inside = new AnimationTimer() {
            DoubleProperty angle = new SimpleDoubleProperty(300);
            DoubleProperty time = new SimpleDoubleProperty(0);
            DoubleProperty place = new SimpleDoubleProperty(0.01);
            final double R = c2.getRadius();
            final double X0 = c2.getCenterX();
            final double Y0 = c2.getCenterY();

            @Override
            public void handle(long l) {
                if( time.getValue() > 0){
                    double currentTime = (l - time.getValue() ) * 1e-9  / 100 * (1-slider.getValue());
                    place.set(2*Math.PI*R*currentTime);
                }

                    time.set(l);

                c4.setCenterX(R*Math.cos(angle.getValue())+X0);
                c4.setCenterY(R*Math.sin(angle.getValue())+Y0);
                angle.setValue(angle.getValue()-place.getValue());
            }
        };

        Scene scene = new Scene(root, 400, 450);
        stage.setTitle("Spinning circles");
        stage.setScene(scene);

        stage.show();
        inside.start();
        outside.start();

    }
        public static void main(String args[]){
            launch(args);
        }
    }