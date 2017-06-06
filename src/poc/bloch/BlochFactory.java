package poc.bloch;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;


/**
 * Created by alonsod86 on 6/6/17.
 */
public class BlochFactory {
    public static Cylinder buildAxis(double width, double length, Color color, Point3D axis) {
        Cylinder ax = new Cylinder(width, length);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(color);

        if (axis!=null) {
            Rotate rotation = new Rotate();
            rotation.setAxis(axis);
            rotation.setAngle(90);
            ax.getTransforms().add(rotation);
        }

        ax.setMaterial(material);
        return ax;
    }

}
