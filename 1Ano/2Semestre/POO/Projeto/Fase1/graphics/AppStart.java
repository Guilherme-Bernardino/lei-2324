package graphics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AppStart extends Application{

    public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) throws IOException {
            BorderPane root = new BorderPane();
            CenterContainer centerContainer = new CenterContainer();
            MenuBar menuBar = initMenu();

            root.setTop(menuBar);
            root.setCenter(centerContainer);


            Scene scene =  new Scene(root, centerContainer.getWidth(), centerContainer.getHeight());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Simulation!");
            primaryStage.setResizable(false);
            primaryStage.show();
            initMenu();

        }

    public MenuBar initMenu(){
        MenuBar menubar = new MenuBar();

        Menu menuName = new Menu("Simulate"); //creating Menu
        MenuItem menuItem1 = new MenuItem("Simulate Step"); //creating Menu Item
        MenuItem menuItem2 = new MenuItem("Simulate 10 Steps"); //creating Menu Item
        MenuItem menuItem3 = new MenuItem("Simulate 20 Steps"); //creating Menu Item
        MenuItem menuItem4 = new MenuItem("Simulate 50 Steps"); //creating Menu Item
        MenuItem menuItem5 = new MenuItem("Simulate 100 Steps"); //creating Menu Item


        menuName.getItems().add(menuItem1);
        menuName.getItems().add(menuItem2);
        menuName.getItems().add(menuItem3);
        menuName.getItems().add(menuItem4);
        menuName.getItems().add(menuItem5);
        menubar.getMenus().add(menuName);

        return menubar;
    }
}
