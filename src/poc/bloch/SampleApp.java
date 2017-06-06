package poc.bloch;

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

public class SampleApp extends Application {
    private double mousePosX, mousePosY = 0;
    private Sphere sphere;
    private Scene scene;
    private Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
    private Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
    private Rotate rotateZ = new Rotate(0, Rotate.Z_AXIS);
    private Group axes = new Group();

    public Parent createContent() throws Exception {
        sphere = new Sphere(10);
        sphere.setDrawMode(DrawMode.LINE);

        // create and position camera
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll(
                new Rotate(-20, Rotate.Y_AXIS),
                new Rotate(-20, Rotate.X_AXIS),
                new Translate(0, 0, -50));

        // group all three axes into one object
        Group axesGroup = new Group();
        axesGroup.getChildren().add(camera);
        Cylinder z = BlochFactory.buildAxis(.2, 20, Color.GRAY, null);
        Cylinder y = BlochFactory.buildAxis(.2, 20, Color.GREEN, Rotate.X_AXIS);
        Cylinder x = BlochFactory.buildAxis(.2, 20, Color.BLUE, Rotate.Z_AXIS);

        axes.getChildren().addAll(x, y, z);
        axes.getTransforms().addAll(rotateZ, rotateY, rotateX);
        sphere.getTransforms().addAll(rotateZ, rotateY, rotateX);
        axesGroup.getChildren().add(axes);
        axesGroup.getChildren().add(sphere);

        SubScene subScene = new SubScene(axesGroup, 600, 600, true,
                SceneAntialiasing.BALANCED);
        subScene.setFill(Color.TRANSPARENT);
        subScene.setCamera(camera);

        return new Group(subScene);
    }

    private void handleCameraRotation() {
        scene.setOnMousePressed((MouseEvent me) -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
        });

        scene.setOnMouseDragged((MouseEvent me) -> {
            double dx = (mousePosX - me.getSceneX()) ;
            double dy = (mousePosY - me.getSceneY());
            if (me.isPrimaryButtonDown()) {
                rotateX.setAngle(rotateX.getAngle() -
                        (dy / sphere.getRadius() * 360) * (Math.PI / 180));
                rotateY.setAngle(rotateY.getAngle() -
                        (dx / sphere.getRadius() * -360) * (Math.PI / 180));
            }
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
        });
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        //primaryStage.setResizable(false);
        scene = new Scene(createContent());
        handleCameraRotation();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}