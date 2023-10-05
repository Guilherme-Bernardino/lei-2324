package vehicles;

import coords.Position;
import locals.DropOffLocal;
import locals.PickUpLocal;
import products.Package;
import products.Transportable;
import sensors.Direction;

import java.util.List;

/**
 * EN
 * The TowingVehicle is an AGV derived class, a vehicle that does not transport any package or products, but a transport cart.
 * This vehicle uses a TransportCart, the main place where it stores its transportables.
 * Since it implements the Localizable interface, this object can be visually represented in a 2d matrix, using its
 * dimension and position.
 *
 * PT
 * O TowingVehicle é uma classe derivada do AGV, um veículo que não transporta nenhuma embalagem ou produto, mas sim uma carreta de transporte.
 * Este veículo utiliza um TransportCart, principal local onde armazena seus transportáveis.
 * Como implementa a interface Localizable, este objeto pode ser representado visualmente numa matriz 2d, usando a sua
 * dimensão e posição.
 *
 * @author guilh
 */
public class TowingVehicle extends AGV {

    private TransportCart transportCart;

    public TowingVehicle() {
        super();
        this.transportCart = new TransportCart();
    }

    public TowingVehicle(TransportCart transportCart){
        super();
        this.transportCart = transportCart;
    }

    @Override
    public void setPickUpLocal(PickUpLocal pickUp) {
        if(pickUp == null){
            return;
        }
        this.pickUp = pickUp;
    }

    @Override
    public void setDropOfLocal(DropOffLocal dropOf) {
        if(dropOf == null){
            return;
        }
        this.dropOf = dropOf;
    }

    @Override
    public void move() {
        if (path != null && !path.isEmpty()) {
            direction = path.remove(0);

            if(direction == Direction.UP){
                position.setY(position.getY() - speed);
            }

            if(direction == Direction.DOWN){
                position.setY(position.getY() + speed);
            }

            if(direction == Direction.LEFT){
                position.setX(position.getX() - speed);
            }

            if(direction == Direction.RIGHT){
                position.setX(position.getX() + speed);
            }

            if (path.isEmpty()) {
                direction = Direction.STOP;
            }
        } else {
            direction = Direction.STOP;
        }
    }

    @Override
    public List<Transportable> getPackages() {
        return transportCart.getPackageList();
    }

    /**
     * Returns the Transport Cart.
     *
     * @return transportCart
     */
    public TransportCart getTransportCart() {
        return transportCart;
    }

    @Override
    public boolean unload(){
        if(pickUp == null){
            return false;
        }

        return pickUp.load(this);
    }

    @Override
    public boolean load(List<Transportable> packageList) {
        if(dropOf == null){
            return false;
        }

        return transportCart.addPackages(packageList);
    }

    @Override
    public boolean retrieveFromShelf(Package pack) {
        if(pack == null){
            return false;
        }
        return transportCart.addPackage(pack);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public void display(char[][] matrix) {
        int centerX = position.getX();
        int centerY = position.getY();
        int width = 1;
        int height = 1;

        int startX = centerX - width / 2;
        int endX = startX + width;
        int startY = centerY - height / 2;
        int endY = startY + height;

        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {
                if (i == startX || i == endX - 1 || j == startY || j == endY - 1)  {
                    matrix[i][j] = 'T';
                } else {
                    matrix[i][j] = '.';
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type: Towing Vehicle ");
        sb.append("Position: " + position.getX() + "," + position.getY() + "\n");
        return sb.toString();
    }
}
