package qsim.bloch.core.gates;

import javafx.scene.transform.Rotate;

/**
 * Created by alonso on 11/06/2017.
 */
public class X extends PauliOperator {
    public X() {
        super.rotationAxis = Rotate.X_AXIS;
        super.angle = 90;
    }
}
