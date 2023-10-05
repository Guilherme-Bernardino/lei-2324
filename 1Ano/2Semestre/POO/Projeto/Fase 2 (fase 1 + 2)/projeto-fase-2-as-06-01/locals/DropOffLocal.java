package locals;

import coords.Dimension;
import coords.Position;
import products.Package;
import products.*;
import vehicles.AGC;
import vehicles.AGV;
import vehicles.TowingVehicle;
import vehicles.ULC;

import java.util.*;

/**
 * EN
 * The DropOffLocal is a Local derived class, used for setting a drop-off local for the distribution center.
 * This local serves for unloading the vehicles transportables into this local's transportable list.
 * Since it implements the Localizable interface, this object can be visually represented in a 2d matrix, using its
 * dimension and position.
 *
 * PT
 * O DropOffLocal é uma classe derivada de Local, usada para definir um local de entrega para o centro de distribuição.
 * Este local serve para descarregar os pacotes dos veículos na lista transportável deste local.
 * Como implementa a interface Localizable, este objeto pode ser representado visualmente numa matriz 2d, usando a sua
 * dimensão e posição.
 *
 * @author guilh
 */
public class DropOffLocal extends Local{

    List<Product> productsList;
    List<Transportable> transportables;

    Set<AGV> agvSet;

    public DropOffLocal(Position position, Dimension dimension) {
        super(position, dimension);
        this.productsList = new ArrayList<>();
        this.transportables = new ArrayList<>();
        this.agvSet = new HashSet<>();
    }

    public DropOffLocal(){
        super(null,null);
        this.productsList = new ArrayList<>();
        this.transportables = new ArrayList<>();
        this.agvSet = new HashSet<>();
    }

    /**
     * Adds a new Product object to the list.
     *
     * @param product
     * @return true if added, false otherwise
     */
    public boolean add(Product product){
        return productsList.add(product);
    }

    /**
     * Add multiple products to the list.
     *
     * @param newProductsList
     * @return true if added, false otherwise
     */
    public boolean addProducts(List<Product> newProductsList){
        return this.productsList.addAll(newProductsList);
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
                if (i == startX || i == endX - 1 || j == startY || j == endY - 1)  {
                    matrix[i][j] = 'D';
                } else {
                    matrix[i][j] = '.';
                }
            }
            matrix[i][11] = '.';
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
     * Returns the list of transportables
     *
     * @return list of transportables
     */
    public List<Transportable> getPackageList() {
        return transportables;
    }

    /**
     * Sets a new transportable list.
     *
     * @param transportables
     */
    public void setPackageList(List<Transportable> transportables) {
        this.transportables = transportables;
    }


    /**
     * This method is used when this local can unload their packaging into
     * a specific vehicle through parameter.
     *
     * @param vehicle
     * @return true if added, false otherwise
     */
    public boolean unload(AGV vehicle) {
        if (vehicle == null) {
            return false;
        }

        if(vehicle instanceof ULC){
            for (Transportable t: transportables) {
                if(t instanceof Pallet){
                    List<Transportable> pallet = new ArrayList<>(1);
                    pallet.add(t);
                    transportables.remove(t);
                    return vehicle.load(pallet);
                }
            }
        }

        if(vehicle instanceof AGC){
            for (Transportable t: transportables) {
                if(t instanceof Sack || t instanceof Box){
                    List<Transportable> newList = new ArrayList<>();
                    newList.add(t);
                    transportables.remove(t);
                    return vehicle.load(newList);
                }
            }
        }

        if(vehicle instanceof TowingVehicle){
            for (Transportable t: transportables) {
                if(t instanceof Sack || t instanceof Box){
                    List<Transportable> newList = new ArrayList<>();
                    newList.add(t);
                    transportables.remove(t);
                    return vehicle.load(newList);
                }
            }
        }

        return false;
    }

    /**
     * Packs the products.
     */
    public void packProducts(){
        Iterator<Product> iterator = productsList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getType() == ProductType.ROUPA) {
                Package sack = new Sack();
                sack.packProducts(product);
                transportables.add(sack);
                iterator.remove();
            } else if (product.getType() == ProductType.LIVRO) {
                Package box = new Box();
                box.packProducts(product);
                transportables.add(box);
                iterator.remove();
            } else if (product.getType() == ProductType.BRINQUEDO_GRANDE || product.getType() == ProductType.ELETRONICO_GRANDE) {
                Package cardboardBox = new CardboardBox();
                cardboardBox.packProducts(product);
                transportables.add(cardboardBox);
                iterator.remove();
            } else {
                Package pack = new Box();
                pack.packProducts(product);
                transportables.add(pack);
                iterator.remove();
            }
        }
    }

    /**
     * Creates a pallet and packs all cardboard boxes.
     */
    public void packPallet() {
        Package pallet = new Pallet();
        Iterator<Transportable> iterator = transportables.iterator();
        while (iterator.hasNext()) {
            Transportable transportable = iterator.next();
            if (transportable instanceof CardboardBox) {
                ((Pallet) pallet).packBoxes((CardboardBox) transportable);
                iterator.remove();
            }
        }
        transportables.add(pallet);
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
                agv.setDropOfLocal(this);
            } else {
                agv.setDropOfLocal(null);
                detectedAGVs.remove(agv);
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type: DropOff"+ "\n");
        sb.append("Position: " + getPosition().getX() + "," + getPosition().getY() + "\n");
        sb.append("Dimension: " + getDimension().getX() + "," + getDimension().getY());
        return sb.toString();
    }
}
