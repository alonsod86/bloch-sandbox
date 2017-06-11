package qsim.bloch.core;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;

/**
 * Created by alonsod86 on 11/06/17.
 */
public class Transformations {

    public static void xGate(Group stateVector) {
        Rotate rotation = new Rotate();
        rotation.setAxis(Rotate.X_AXIS);
        rotation.setAngle(180);
        stateVector.getTransforms().add(rotation);
    }
}
