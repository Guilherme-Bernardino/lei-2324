package graphics;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * EN
 * Simulation menu that includes menus and buttons pertaining to simulation and its runtime alterations.
 *
 * PT
 * Menu de simulação que inclui menus e botões referentes à simulação e alterações de tempo de execução.
 *
 * @author guilh
 */
public class SimulationMenu extends MenuBar {

    public SimulationMenu(CenterContainer centerContainer){
        super();
        initMenu(centerContainer);
    }

    /**
     * Initiate a top menu for simulation buttons and menus.
     *
     * @param centerContainer
     */
    public void initMenu(CenterContainer centerContainer){
        Menu simulateMenu = new Menu("Simulate");
        MenuItem menuItem1 = new MenuItem("Simulate Step");
        MenuItem menuItem2 = new MenuItem("Simulate 10 Steps");
        MenuItem menuItem3 = new MenuItem("Simulate 20 Steps");
        MenuItem menuItem4 = new MenuItem("Simulate 50 Steps");
        MenuItem menuItem5 = new MenuItem("Simulate 100 Steps");

        menuItem1.setOnAction(event -> GraphicSimulation.simulateOneStep(centerContainer));

        menuItem2.setOnAction(event -> GraphicSimulation.simulateSteps( 10 ,centerContainer));

        menuItem3.setOnAction(event -> GraphicSimulation.simulateSteps( 20 ,centerContainer));

        menuItem4.setOnAction(event -> GraphicSimulation.simulateSteps( 50 ,centerContainer));

        menuItem5.setOnAction(event -> GraphicSimulation.simulateSteps( 100 ,centerContainer));


        ImageView pauseImg = new ImageView(new Image("./img/pause2.png"));
        pauseImg.setFitHeight(15);
        pauseImg.setFitWidth(15);

        ImageView startImg = new ImageView(new Image("./img/start.png"));
        startImg.setFitHeight(15);
        startImg.setFitWidth(15);

        ToggleButton toggleButton = new ToggleButton("Start");
        toggleButton.setGraphic(startImg);
        toggleButton.setSelected(false);
        toggleButton.setOnAction(event -> {
            boolean isSelected = toggleButton.isSelected();
            if (isSelected) {
                toggleButton.setText("Pause");
                toggleButton.setGraphic(pauseImg);
                GraphicSimulation.resumeSimulation(centerContainer);

            } else {
                toggleButton.setText("Start");
                toggleButton.setGraphic(startImg);
                GraphicSimulation.pauseSimulation();
            }
        });

        GraphicSimulation.speedUp(1);
        Menu menuSpeedMultiplier = new Menu("Speed Multiplier");
        ToggleGroup speedToggleGroup = new ToggleGroup();

        ToggleButton toggleButton1x = new ToggleButton("1x");
        ToggleButton toggleButton2x = new ToggleButton("2x");
        ToggleButton toggleButton4x = new ToggleButton("4x");
        ToggleButton toggleButton8x = new ToggleButton("8x");
        ToggleButton toggleButton16x = new ToggleButton("16x");
        ToggleButton toggleButton32x = new ToggleButton("32x");

        toggleButton1x.setToggleGroup(speedToggleGroup);
        toggleButton2x.setToggleGroup(speedToggleGroup);
        toggleButton4x.setToggleGroup(speedToggleGroup);
        toggleButton8x.setToggleGroup(speedToggleGroup);
        toggleButton16x.setToggleGroup(speedToggleGroup);
        toggleButton32x.setToggleGroup(speedToggleGroup);

        toggleButton1x.setSelected(true);

        MenuItem customMenuItem = new MenuItem();
        customMenuItem.setGraphic(new HBox(toggleButton1x, toggleButton2x, toggleButton4x, toggleButton8x, toggleButton16x, toggleButton32x));

        toggleButton1x.setOnAction(event -> GraphicSimulation.speedUp(1));
        toggleButton2x.setOnAction(event -> GraphicSimulation.speedUp(2));
        toggleButton4x.setOnAction(event -> GraphicSimulation.speedUp(4));
        toggleButton8x.setOnAction(event -> GraphicSimulation.speedUp(8));
        toggleButton16x.setOnAction(event -> GraphicSimulation.speedUp(16));
        toggleButton32x.setOnAction(event -> GraphicSimulation.speedUp(32));

        menuSpeedMultiplier.getItems().add(customMenuItem);

        simulateMenu.getItems().addAll(menuItem1, menuItem2, menuItem3, menuItem4, menuItem5, new SeparatorMenuItem(), menuSpeedMultiplier);
        simulateMenu.setGraphic(toggleButton);

        getMenus().add(simulateMenu);
    }
}
