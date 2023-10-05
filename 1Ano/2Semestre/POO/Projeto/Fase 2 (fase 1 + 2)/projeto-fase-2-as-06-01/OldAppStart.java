import products.Product;

import coords.*;
import locals.*;
import vehicles.*;

import java.util.List;

public class OldAppStart {

    public static void main(String[] args) {
        Reader reader = new Reader();

        DistributionCenter distributionCenter = reader.readDistributionCenter(Reader.LOCALS_PATH);

        distributionCenter.addLocals(reader.readLocals(Reader.LOCALS_PATH));
        distributionCenter.addVehicles(reader.readVehicles(Reader.VEHICLES_PATH));

        distributionCenter.getWarehouse().setStorageLocalList(reader.readStorageLocals(Reader.LOCALS_PATH));

        if (!distributionCenter.getVehicles().isEmpty()) {
            System.out.println("Vehicles:");
            for (AGV vehicle : distributionCenter.getVehicles()) {
                System.out.println(vehicle);
                System.out.println();
            }
        } else {
            System.out.println("No vehicles found.");
        }

        if (distributionCenter != null) {
            System.out.println(distributionCenter);

            List<Local> storageLocals = distributionCenter.getLocals();
            System.out.println("Locals:");
            for (Local local : storageLocals) {
                System.out.println(local);
                System.out.println();
            }
            System.out.println("------------------------------");
            for (StorageLocal storageLocal: distributionCenter.getWarehouse().getStorageLocalList()){
                System.out.println(storageLocal);
                System.out.println();
            }
            System.out.println("------------------------------");
        } else {
            System.out.println("No warehouse or storage locals found.");
        }

        List<Product> products = reader.readProducts(Reader.PRODUCTS_PATH);

        if (!products.isEmpty()) {
            System.out.println("Products:");
            for (Product product : products) {
                System.out.println(product);
                System.out.println();
            }
        } else {
            System.out.println("No products found.");
        }

    }

}
