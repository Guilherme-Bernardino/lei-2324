package locals;

import coords.Dimension;
import coords.Position;
import products.Package;
import products.Transportable;
import vehicles.AGV;
import vehicles.ULC;

import java.util.*;

/**
 * EN
 * The StorageLocal is a Local derived class, used for setting a shelf local for the warehouse.
 * This local is used for storing products. It can add a product and return the list of products.
 * Since it implements the Localizable interface, this object can be visually represented in a 2d matrix, using its
 * dimension and position.
 *
 * PT
 * O StorageLocal é uma classe derivada de Local, usada para definir um local de prateleira para o depósito.
 * Este local é utilizado para armazenar produtos. Pode adicionar um produto e retornar a lista de produtos.
 * Como implementa a interface Localizable, este objeto pode ser representado visualmente numa matriz 2d, usando a sua
 * dimensão e posição.
 *
 * @author guilh
 */
public class StorageLocal extends Local{

    List<Package> packageList;

    Set<AGV> agvSet;

    public StorageLocal(Position position, Dimension dimension) {
        super(position, dimension);
        this.packageList = new ArrayList<>();
        this.agvSet = new HashSet<>();
    }

    /**
     * Adds a new Product object to the list.
     *
     * @param pack
     * @return true if added, false otherwise
     */
    public boolean add(Package pack){
        return packageList.add(pack);
    }

    /**
     * Adds multiple packages to the list.
     *
     * @param packages
     * @return true if added, false otherwise
     */
    public boolean addPackages(List<Transportable> packages){
        if(packages == null || packages.isEmpty()){
            return false;
        }
        for (Transportable pack : packages) {
            add((Package) pack);
        }
        return true;
    }

    @Override
    public Position getPosition() {
        return super.position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public void display(char[][] matrix) {
        int centerX = position.getX();
        int centerY = position.getY();
        int width = dimension.getX();
        int height = dimension.getY();

        int startX = centerX - width / 2;
        int endX = startX + width;
        int startY = centerY - height / 2;
        int endY = startY + height;

        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {
                if(i==startX){
                    matrix[i][j] = '|';
                }else{
                    matrix[i][j] = 'S';
                }
            }
        }
    }

    @Override
    public Dimension getDimension() {
        return super.dimension;
    }

    @Override
    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    /**
     * Returns the list of packages.
     *
     * @return list of packages
     */
    public List<Package> getPackageList() {
        return packageList;
    }

    /**
     * Sets a new list of packages.
     *
     * @param packageList
     */
    public void setPackageList(List<Package> packageList) {
        this.packageList = packageList;
    }

    /**
     * Method used to detect if there are vehicles inside its parameters.
     *
     * @param distributionCenter
     */
    public void detectAGV(DistributionCenter distributionCenter) {
        Set<AGV> detectedAGVs = new HashSet<>();

        for (AGV agv : distributionCenter.getVehicles()) {
            Position agvPosition = agv.getPosition();
            if (isPositionInsideLocal(agvPosition)) {
                detectedAGVs.add(agv);
                List<Transportable> newList = agv.getPackages();
                for (Transportable p: newList) {
                    if(p !=null){
                        if(!p.isReadyForShipping()){
                            add((Package) p);
                            agv.getPackages().remove(p);
                            if(agv instanceof ULC){
                                ((ULC) agv).setPallet(null);
                            }
                        }
                    }
                }
            } else {
                agv.setDropOfLocal(null);
            }
        }
        agvSet = detectedAGVs;
    }

    /**
     * Auxiliary method. Checks if vehicles position corresponds to the inside area of this local.
     *
     * @param position
     * @return true if it is, false otherwise
     */
    private boolean isPositionInsideLocal(Position position) {
        int centerX = getPosition().getX();
        int centerY = getPosition().getY();
        int width = getDimension().getX();
        int height = getDimension().getY();

        int startX = centerX - width / 2;
        int endX = startX + width;
        int startY = centerY - height / 2;
        int endY = startY + height;

        int posX = position.getX();
        int posY = position.getY();

        return (posX >= startX && posX < endX && posY >= startY && posY < endY);
    }

    /**
     * Returns the set of Vehicle currently standing inside this location.
     */
    public Set<AGV> getVehicleSet() {
        return agvSet;
    }

    /**
     * Loads vehicles with ready for shipping items.
     *
     * @param vehicle
     */
    public void loadVehicle(AGV vehicle) {
        Iterator<Package> iterator = packageList.iterator();
        while (iterator.hasNext()) {
            Package p = iterator.next();
            if (p.isReadyForShipping()) {
                boolean check = vehicle.retrieveFromShelf(p);
                if(check){
                    iterator.remove();
                }
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type: Storage       ");
        sb.append("Pos: " + getPosition().getX() + "," + getPosition().getY() + "       ");
        sb.append("Dim: " + getDimension().getX() + "," + getDimension().getY() + "\n");
        return sb.toString();
    }
}
