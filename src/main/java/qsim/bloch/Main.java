package qsim.bloch;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import qsim.bloch.core.gates.X;
import qsim.bloch.ui.UI;

public class Main extends Application {

    private UI ui;
    private Group stateVector;

    public Main() {
        this.ui = new UI();
    }

    public Group createContent() {
        Group all = new Group();
        Group axes;
        Sphere sphere;

        // build sphere and standard Z, PauliOperator, Y axes
        sphere = BlochFactory.buildSphere();
        axes = BlochFactory.buildStandardAxes();

        // apply mouse driven transformations
        axes.getTransforms().addAll(ui.getRotationAxes());
        sphere.getTransforms().addAll(ui.getRotationAxes());

        // create state vector |0>
        stateVector = BlochFactory.buildStateVector(Color.RED, null);
        stateVector.getTransforms().addAll(ui.getRotationAxes());

        all.getChildren().addAll(sphere, axes, stateVector);
        return all;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        double sceneSize = 10;

        BlochFactory.initialize(sceneSize, 1.3);
        ui.start(primaryStage, createContent(), sceneSize);

        X xg = new X();


    }

    public static void main(String[] args) {
        launch(args);
    }
}