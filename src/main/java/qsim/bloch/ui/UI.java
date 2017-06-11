package qsim.bloch.ui;

import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

/**
 * Created by alonsod86 on 11/06/17.
 */
public class UI {
    private Scene globalScene;
    private Stage primaryStage;

    private Rotate rotateX;
    private Rotate rotateY;
    private Rotate rotateZ;

    private PerspectiveCamera camera;
    private SubScene blochScene;

    private double mousePosX, mousePosY, sceneSize = 0;

    public UI() {
        this.rotateX = new Rotate(0, Rotate.X_AXIS);
        this.rotateY = new Rotate(0, Rotate.Y_AXIS);
        this.rotateZ = new Rotate(0, Rotate.Z_AXIS);

        // create and position camera
        this.camera = new PerspectiveCamera(true);
        this.camera.getTransforms().addAll(
                new Rotate(-20, Rotate.Y_AXIS),
                new Rotate(-20, Rotate.X_AXIS),
                new Translate(0, 0, -80));
    }

    public void start(Stage primaryStage, Group blochSphere, double sceneSize) {
        this.primaryStage = primaryStage;
        this.sceneSize = sceneSize;

        // build subscene with the component of the bloch sphere (axes and state vector |0>)
        this.blochScene = new SubScene(blochSphere, 1024, 1024, true,
                SceneAntialiasing.BALANCED);
        this.blochScene.setFill(Color.TRANSPARENT);
        this.blochScene.setCamera(camera);
        this.globalScene = new Scene(new Group(blochScene));

        // handle mouse events and show the globalScene
        handleCameraRotation();
        primaryStage.setResizable(false);
        primaryStage.setScene(globalScene);
        primaryStage.show();
    }

    private void handleCameraRotation() {
        globalScene.setOnMousePressed((MouseEvent me) -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
        });

        globalScene.setOnMouseDragged((MouseEvent me) -> {
            double dx = (mousePosX - me.getSceneX()) ;
            double dy = (mousePosY - me.getSceneY());
            if (me.isPrimaryButtonDown()) {
//                rotateX.setAngle(rotateX.getAngle() -
//                        (dy / sphere.getRadius() * 360) * (Math.PI / 180));
                rotateY.setAngle(rotateY.getAngle() -
                        (dx / sceneSize * -360) * (Math.PI / 180));
            }
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
        });
    }

    /**
     * Returns the rotation transformation on every basis axis of the bloch sphere, in it's current status
     * @return
     */
    public Rotate[] getRotationAxes() {
        return new Rotate[]{rotateZ, rotateY, rotateX};
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
