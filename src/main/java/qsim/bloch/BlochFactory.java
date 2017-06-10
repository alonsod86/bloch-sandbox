package qsim.bloch;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
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

    /**
     * Returns an object encapsulating an axis
     * @param size
     * @param color
     * @param axis
     * @return
     */
    public static Group buildAxis(double size, Color color, Point3D axis, String label) throws IOException {
        double width = w*size, length = l*size;
        Group vector = new Group();

        // label
        Text text = new Text(label);
        text.setFont(Font.font ("Verdana", 50));
        text.setFill(color);
        text.setTranslateY(-length/4);

        // axis
        Cylinder ax = new Cylinder(width, length);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(color);
        ax.setMaterial(material);

        // pointer
        MeshView pointer = buildPointer();
        pointer.setTranslateY(-length/2);
        pointer.setMaterial(material);

        if (axis!=null) {
            Rotate rotation = new Rotate();
            rotation.setAxis(axis);
            rotation.setAngle(90);
            vector.getTransforms().add(rotation);
        }

        vector.getChildren().addAll(ax, pointer);
        return vector;
    }


    /**
     * Axis arrow builder
     * @return
     * @throws IOException
     */
    public static MeshView buildPointer() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(BlochFactory.class.getResource("/pyramid.fxml"));
        MeshView pyramidModel = fxmlLoader.<MeshView>load();
        return pyramidModel;
    }
}
