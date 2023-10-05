import coords.Reader;
import locals.DistributionCenter;
import locals.StorageLocal;
import products.Package;
import products.Product;
import products.Transportable;
import sensors.Direction;
import vehicles.AGV;
import vehicles.ULC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Simulation {

    private static char[][] matrix;

    private static int simulationStep = 0;
    private static DistributionCenter distributionCenter;

    public static void init() {
        int rows = 35;
        int columns = 60;
        matrix = new char[rows][columns];

        //Legenda
        System.out.println(String.format("%22s", "Legend"));
        System.out.println(String.format("%-30s %s", "Locals:", "Vehicles:"));

        System.out.println(String.format("%-30s %s", "Distribution Center : *", "AGC : A"));
        System.out.println(String.format("%-30s %s", "Warehouse : +", "ULC : U"));
        System.out.println(String.format("%-30s %s", "PickUp : P ", "Towing : T"));
        System.out.println(String.format("%-30s %s", "DropOff : D ", ""));
        System.out.println(String.format("%-30s %s", "Storage : S ", ""));

        System.out.println("-------------------------------------------------------------Initial-------------------------" +
                "-----------------------------------------------");

        for (int i = 0; i < rows; i++) {
            Arrays.fill(matrix[i], '.');
        }
        Reader reader = new Reader();

        distributionCenter = reader.readDistributionCenter(Reader.LOCALS_PATH);
        distributionCenter.display(matrix);

        distributionCenter.addLocals(reader.readLocals(Reader.LOCALS_PATH));
        distributionCenter.getWarehouse().display(matrix);
        distributionCenter.getDropOffLocal().display(matrix);
        distributionCenter.getPickUpLocal().display(matrix);
        distributionCenter.getWarehouse().setStorageLocalList(reader.readStorageLocals(Reader.LOCALS_PATH));

        for (StorageLocal sl : distributionCenter.getWarehouse().getStorageLocalList()) {
            sl.display(matrix);
        }

        distributionCenter.addVehicles(reader.readVehicles(Reader.VEHICLES_PATH));

        for (AGV vehicle : distributionCenter.getVehicles()) {
            vehicle.display(matrix);
        }

        List<Product> productsList = reader.readProducts(Reader.PRODUCTS_PATH);

        for (Product product: productsList) {
            distributionCenter.getDropOffLocal().add(product);
        }

        distributionCenter.getDropOffLocal().packProducts();
        /*for (Transportable p: distributionCenter.getDropOffLocal().getPackageList()) {
            Package np = (Package) p;
            System.out.println(np);
        }*/

        distributionCenter.getDropOffLocal().packPallet();

        displayMatrix();
    }

    public static void simulateStep() {

        simulationStep++;

        System.out.println("------------------------------------------------------------Step" + simulationStep +"-------------------------" +
                "-----------------------------------------------");

        //Detect if there is vehicles inside.
        distributionCenter.getDropOffLocal().detectAGV(distributionCenter);
        distributionCenter.getPickUpLocal().detectAGV(distributionCenter);
        distributionCenter.getWarehouse().detectAGVInStorageLocals(distributionCenter);


        for (AGV vehicle : distributionCenter.getDropOffLocal().getVehicleSet()) {
            distributionCenter.getDropOffLocal().unload(vehicle);
            //System.out.println(vehicle);
            //System.out.println(vehicle.getPackages() + "\n");
        }

        for (AGV vehicle : distributionCenter.getPickUpLocal().getVehicleSet()) {
            distributionCenter.getPickUpLocal().load(vehicle);
           // System.out.println(vehicle);
            //System.out.println(distributionCenter.getPickUpLocal().getProductList() + "\n");
        }

        for (StorageLocal local: distributionCenter.getWarehouse().getStorageLocalList()) {
            local.display(matrix);
        }

        clearVehiclePositions();

        /*for (AGV vehicle: distributionCenter.getVehicles()) {
            if(vehicle instanceof ULC){
                ((ULC) vehicle).increaseSpeed();
                ((ULC) vehicle).increaseSpeed();
            }
            vehicle.setDirection(Direction.UP);
            vehicle.move();
            vehicle.display(matrix);
        }*/
        /*
        distributionCenter.getVehicles().get(0).setDirection(Direction.RIGHT);
        distributionCenter.getVehicles().get(0).move();
        distributionCenter.getVehicles().get(0).display(matrix);
        System.out.println(distributionCenter.getVehicles().get(0));
        System.out.println(distributionCenter.getVehicles().get(0).getPackages());*/

        List<Direction> newPath = new ArrayList<>();
        for (int i=0; i < 42;i++){
            newPath.add(Direction.RIGHT);
        }

        for (AGV vehicle: distributionCenter.getVehicles()) {
            if(vehicle instanceof ULC){
                ((ULC) vehicle).setPath(newPath);
                vehicle.move();
            }
            vehicle.display(matrix);
        }

        // Uncover code

        for (StorageLocal l :distributionCenter.getWarehouse().getStorageLocalList()) {
            System.out.println(l.getPackageList());
        }

        displayMatrix();
    }

    public static void simulateSteps(int numSteps) {
        for (int step = 0; step < numSteps; step++) {
            simulateStep();
        }
    }


    private static void clearVehiclePositions() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 'A' | matrix[i][j] == 'U' | matrix[i][j] == 'T') {
                    matrix[i][j] = '.';
                }
            }
        }
    }

    private static void displayMatrix() {
        // Print the matrix to the console
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        init();
        //simulateStep();
        simulateSteps(43);
    }
}

