package qsim.bloch;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

import java.io.IOException;


/**
 * Bloch Sphere component builder
 * Created by alonsod86 on 6/6/17.
 */
public class BlochFactory {
    private static double w = .005;
    private static double l = 1;

    private static double r = 10;

    /**
     * Set the scale factor for every object to be created
     * @param sphereRadius
     * @param axesScaleFactor
     */
    public static void initialize(double sphereRadius, double axesScaleFactor) {
        BlochFactory.r = sphereRadius;
        BlochFactory.l = 2 * axesScaleFactor;
    }

    /**
     * Builds the standard Z, PauliOperator and Y axes for the bloch sphere
     * @return
     */
    public static Group buildStandardAxes() {
        Group axesGroup = new Group();
        Group z = BlochFactory.buildAxis(Color.GRAY, null, "Z");
        Group y = BlochFactory.buildAxis(Color.GREEN, Rotate.X_AXIS, "Y");
        Group x = BlochFactory.buildAxis(Color.BLUE, Rotate.Z_AXIS, "X");

        axesGroup.getChildren().addAll(z, x, y);
        return axesGroup;
    }

    /**
     * Returns an object encapsulating an axis (basis vector + label)
     * @param color
     * @param axis
     * @return
     */
    public static Group buildAxis(Color color, Point3D axis, String label) {
        double length = l*r, width = w*length;
        Group vector = new Group();

        // label
        Text text = new Text(label);
        text.setFont(Font.font ("Arial", 5));
        text.setFill(color);
        text.setTranslateY(-length/4);

        // axis
        Cylinder ax = new Cylinder(width, length);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(color);
        ax.setMaterial(material);

        // rotation over base axis
        applyOrthogonalTransformation(axis, vector);

        vector.getChildren().addAll(ax, text);
        return vector;
    }

    /**
     * Builds a new state vector pointing along some axis
     * @param color
     * @param axis
     * @return
     */
    public static Group buildStateVector(Color color, Point3D axis) {
        Group vector = new Group();
        double length = r, width = w*length*3;

        // axis
        Cylinder ax = new Cylinder(width, length);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(color);
        ax.setMaterial(material);
        ax.setTranslateY(-length/2);

        // pointer
        MeshView pointer = buildPointer();
        pointer.setTranslateY(-length);
        pointer.setMaterial(material);

        // rotation over base axis
        applyOrthogonalTransformation(axis, vector);
        vector.getChildren().addAll(ax, pointer);
        return vector;
    }

    /**
     * Builds a semi-transparent bloch sphere with the specified radius by the factory initializer
     * @return
     */
    public static Sphere buildSphere() {
        Sphere bloch = new Sphere(r);
        bloch.setDrawMode(DrawMode.LINE);
        return bloch;
    }

    /**
     * Axis arrow builder
     * @return
     * @throws IOException
     */
    private static MeshView buildPointer() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(BlochFactory.class.getResource("/pyramid.fxml"));
        MeshView pyramidModel = null;
        try {
            pyramidModel = fxmlLoader.<MeshView>load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pyramidModel;
    }

    /**
     * Given a vector, applies a specific rotation to build an orthogonal final state
     * @param axis
     * @param vector
     */
    private static void applyOrthogonalTransformation(Point3D axis, Group vector) {
        if (axis!=null) {
            Rotate rotation = new Rotate();
            rotation.setAxis(axis);
            rotation.setAngle(90);
            vector.getTransforms().add(rotation);
        }
    }
}
