package graphics;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Circle;
import locals.StorageLocal;
import sensors.Direction;
import vehicles.AGV;
import vehicles.ULC;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * EN
 * Graphical version of the simulation class. Runs a simulation and applies visuals changes to the application.
 *
 * PT
 * Versão gráfica da classe de simulação. Executa uma simulação e aplica alterações visuais à aplicação.
 *
 * @author guilh
 */
public class GraphicSimulation {

    public static int simulationStep = 0;
    public static IntegerProperty simulationStepProperty = new SimpleIntegerProperty(simulationStep);
    private static boolean isSimulationRunning = false;

    private static long DEFAULT_PLAYBACK_TIME = 1000;
    private static long playbackTime;

    /**
     * Simulates one step. Each simulation runs all locals checking methods and loading/unloading transportables.
     *
     * @param centerContainer
     */
    public static void simulateOneStep(CenterContainer centerContainer){
        simulationStep++;

        Platform.runLater(() -> simulationStepProperty.set(simulationStep));

        centerContainer.getDistributionCenter().getDropOffLocal().detectAGV(centerContainer.getDistributionCenter());
        centerContainer.getDistributionCenter().getPickUpLocal().detectAGV(centerContainer.getDistributionCenter());
        centerContainer.getDistributionCenter().getWarehouse().detectAGVInStorageLocals(centerContainer.getDistributionCenter());

        for (AGV vehicle : centerContainer.getDistributionCenter().getDropOffLocal().getVehicleSet()) {
            centerContainer.getDistributionCenter().getDropOffLocal().unload(vehicle);
        }

        for (AGV vehicle : centerContainer.getDistributionCenter().getPickUpLocal().getVehicleSet()) {
            centerContainer.getDistributionCenter().getPickUpLocal().load(vehicle);
        }

        for (StorageLocal storageLocal: centerContainer.getDistributionCenter().getWarehouse().getStorageLocalList()) {
            for (AGV vehicle: storageLocal.getVehicleSet()) {
                storageLocal.loadVehicle(vehicle);
            }
        }

        List<Direction> newPath = new ArrayList<>();
        for (int i=0; i < 42;i++){
            newPath.add(Direction.RIGHT);
        }

        for (AGV vehicle: centerContainer.getDistributionCenter().getVehicles()) {
            if(vehicle instanceof ULC){
                vehicle.setPath(newPath);
                vehicle.move();
            }
        }

        for (Map.Entry<AGV, Circle> map : centerContainer.getVehicleContainer().entrySet()) {
            map.getValue().setTranslateX(map.getKey().getPositionProperty().get().getX());
            map.getValue().setTranslateY(map.getKey().getPositionProperty().get().getY());
        }

        //Update ObservableLists
        centerContainer.updateLists();
    }

    /**
     * Simulates multiple steps through parameter.
     *
     * @param numSteps
     * @param centerContainer
     */
    public static void simulateSteps(int numSteps, CenterContainer centerContainer) {
        for (int step = 0; step < numSteps; step++) {
            simulateOneStep(centerContainer);
        }
    }

    /**
     * For the playing and stopping states of the simulation.
     * Kickstart the simulation and runs it continuously.
     *
     * @param centerContainer
     */
    public static void resumeSimulation(CenterContainer centerContainer) {
        isSimulationRunning = true;

        Thread simulationThread = new Thread(() -> {
            while (isSimulationRunning) {
                simulateOneStep(centerContainer);

                try {
                    Thread.sleep(playbackTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        simulationThread.start();
    }

    /**
     * For the playing and stopping states of the simulation.
     * Pause the running simulation.
     */
    public static void pauseSimulation() {
        isSimulationRunning = false;
    }

    /**
     * Playback speed increase through multiplier. Amount is multiplier value i.e.
     * 1x, 2x, 4x, 8x, 16x, 32x the playback time.
     *
     * @param amount
     */
    public static void speedUp(int amount){
        playbackTime = DEFAULT_PLAYBACK_TIME / amount;
    }

    /**
     * Return property for simulation steps.
     *
     * @return simulationStepProperty
     */
    public static IntegerProperty getDoubleProperty(){
        return simulationStepProperty;
    }
}
