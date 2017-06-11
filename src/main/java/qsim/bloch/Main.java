package qsim.bloch;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import qsim.bloch.ui.UI;

public class Main extends Application {

    private UI ui;

    public Main() {
        this.ui = new UI();
    }

    public Group createContent() {
        Group all = new Group();
        Group axes;
        Sphere sphere;

        // build sphere and standard Z, X, Y axes
        sphere = BlochFactory.buildBlochSphere();
        axes = BlochFactory.buildStandardAxes();

        // apply mouse driven transformations
        axes.getTransforms().addAll(ui.getRotationAxes());
        sphere.getTransforms().addAll(ui.getRotationAxes());

        // create state vector |0>
        Group stateVector = BlochFactory.buildState(Color.RED, null);
        all.getChildren().addAll(sphere, axes, stateVector);
        return all;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        double sceneSize = 10;

        BlochFactory.initBlochSphere(sceneSize, 1.3);
        ui.start(primaryStage, createContent(), sceneSize);
    }

    public static void main(String[] args) {
        launch(args);
    }
}