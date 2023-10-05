package coords;

import locals.*;
import products.Product;
import products.ProductType;
import vehicles.AGC;
import vehicles.AGV;
import vehicles.TowingVehicle;
import vehicles.ULC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * EN
 * The Reader class is used in data file parsing, that parses information contained in a .txt file, with values separated by commas (CSV),
 * and returns the desired object or objects list referred to that data.
 *
 * PT
 * A classe Reader é utilizada na análise de arquivos de dados, que analisa as informações contidas em um arquivo .txt, com valores separados por vírgulas (CSV),
 * e retorna o objeto desejado ou a lista de objetos referentes a esses dados.
 *
 * @author guilh
 */
public class Reader {

    //Data file paths
    public final static String LOCALS_PATH = "./data/locals.txt";
    public final static String PRODUCTS_PATH = "./data/products.txt";
    public final static String VEHICLES_PATH = "./data/vehicles.txt";
    public final static String LOCALS_APP_PATH = "./data/localsApp.txt";
    public final static String VEHICLES_APP_PATH = "./data/vehiclesApp.txt";

    /**
     * Reads the Warehouse data file with positions for Storages and itself.
     *
     * @param filePath
     * @return Warehouse object with Storages
     */
    public Warehouse readWarehouse(String filePath) {
        List<StorageLocal> storageLocals = readStorageLocals(filePath);
        if (storageLocals.isEmpty()) {
            return null;
        }

        StorageLocal warehouseDetails = storageLocals.remove(0);

        Warehouse warehouse = new Warehouse(warehouseDetails.getPosition(), warehouseDetails.getDimension());
        warehouse.setStorageLocalList(storageLocals);

        return warehouse;
    }

    /**
     * Reads the Warehouse data file with positions for Storages and itself.
     *
     * @param filePath
     * @return Warehouse object with Storages
     */
    public DistributionCenter readDistributionCenter(String filePath) {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean headerSkipped = false;

            while ((line = reader.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                String[] values = line.split(",");

                int positionX = Integer.parseInt(values[0]);
                int positionY = Integer.parseInt(values[1]);
                int dimensionX = Integer.parseInt(values[2]);
                int dimensionY = Integer.parseInt(values[3]);
                String type = values[4];

                Position position = new Position(positionX, positionY);
                Dimension dimension = new Dimension(dimensionX, dimensionY);
                DistributionCenter distributionCenter;

                if(type.equals("dc")) {
                    distributionCenter = new DistributionCenter(position, dimension);
                } else{
                    continue;
                }

                return distributionCenter;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Reads a StorageLocals file and returns a list.
     *
     * @param filePath
     * @return list of StorageLocal
     */
    public List<StorageLocal> readStorageLocals(String filePath) {
        List<StorageLocal> storageLocalList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean headerSkipped = false;

            while ((line = reader.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                String[] values = line.split(",");

                int positionX = Integer.parseInt(values[0]);
                int positionY = Integer.parseInt(values[1]);
                int dimensionX = Integer.parseInt(values[2]);
                int dimensionY = Integer.parseInt(values[3]);
                String type = values[4];

                Position position = new Position(positionX, positionY);
                Dimension dimension = new Dimension(dimensionX, dimensionY);
                StorageLocal storageLocal;

                if(type.equals("sl")) {
                    storageLocal = new StorageLocal(position, dimension);
                } else{
                    continue;
                }
                storageLocalList.add(storageLocal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return storageLocalList;
    }

    /**
     * Reads an AVG data file and returns it in a list.
     *
     * @param filePath
     * @return list of AVG
     */
    public List<AGV> readVehicles(String filePath) {
        List<AGV> vehicleList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean headerSkipped = false;

            while ((line = reader.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                String[] values = line.split(",");

                int positionX = Integer.parseInt(values[0]);
                int positionY = Integer.parseInt(values[1]);
                String type = values[2];

                Position position = new Position(positionX, positionY);
                AGV vehicle;

                if (type.equals("AGC")) {
                    vehicle = new AGC();
                } else if (type.equals("ULC")) {
                    vehicle = new ULC();
                } else if (type.equals("TC")) {
                    vehicle = new TowingVehicle();
                } else {
                    continue; // Skip unrecognized types
                }

                vehicle.setPosition(position);
                vehicle.setPositionProperty(position);
                vehicleList.add(vehicle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vehicleList;
    }


    /**
     * Reads a Product data file and returns it in a list.
     *
     * @param filePath
     * @return list of Product
     */
    public List<Product> readProducts(String filePath) {
        List<Product> productList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean headerSkipped = false;

            while ((line = reader.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                String[] values = line.split(",");

                String productName = values[0];
                double weight = Double.parseDouble(values[1]);
                ProductType productType = ProductType.valueOf(values[2]);

                Product product = new Product(productName, weight, productType);
                productList.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productList;
    }

    /**
     * Reads the locals file and returns distribution center locals.
     *
     * @param filePath
     * @return list of locals
     */
    public List<Local> readLocals(String filePath){
        List<Local> localsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean headerSkipped = false;

            while ((line = reader.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                String[] values = line.split(",");

                int positionX = Integer.parseInt(values[0]);
                int positionY = Integer.parseInt(values[1]);
                int dimensionX = Integer.parseInt(values[2]);
                int dimensionY = Integer.parseInt(values[3]);
                String type = values[4];

                Position position = new Position(positionX, positionY);
                Dimension dimension = new Dimension(dimensionX, dimensionY);
                Local local;

                if(type.equals("w")){
                    local = new Warehouse();
                } else if(type.equals("pl")){
                    local = new PickUpLocal();
                } else if(type.equals("dl")) {
                    local = new DropOffLocal();
                } else{
                    continue;
                }

                local.setPosition(position);
                local.setDimension(dimension);
                localsList.add(local);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return localsList;
    }

}
