package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

/**
 * Created by tiocansino on 4/6/17.
 */
public class DemoJavaFX3D1 extends Application {

    @Override
    public void start(Stage stage) {

        //Drawing a Sphere
        Sphere sphere = new Sphere();

        //Setting the properties of the Sphere
        sphere.setRadius(50.0);

        sphere.setTranslateX(200);
        sphere.setTranslateY(150);

        //Creating a Group object
        Group root = new Group(sphere);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 300);

        //Setting title to the Stage
        stage.setTitle("Drawing a Sphere - draw fill");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
