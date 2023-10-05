package vehicles;

import coords.Position;
import locals.DropOffLocal;
import locals.PickUpLocal;
import products.Package;
import products.Pallet;
import products.Transportable;
import sensors.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * EN
 * The ULC is an AGV derived class, used to identify an Unit Load Carrier, a vehicle.
 * This vehicle uses a pallet and can add pallet.
 * Since it implements the Localizable interface, this object can be visually represented in a 2d matrix, using its
 * dimension and position.
 *
 * PT
 * O ULC é uma classe derivada do AGV, usada para identificar um Unit Load Carrier, um veículo.
 * Este veículo usa palete e pode adicionar palete.
 * Como implementa a interface Localizable, este objeto pode ser representado visualmente numa matriz 2d, usando a sua
 * dimensão e posição.
 *
 * @author guilh
 */
public class ULC extends AGV {

    private Pallet pallet;

    public ULC() {
        super();
        pallet = null;
    }

    public ULC(Pallet pallet) {
        super();
        this.pallet = pallet;
    }

    public Pallet getPallet() {
        return pallet;
    }

    public void setPallet(Pallet pallet) {
        this.pallet = pallet;
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

    /**
     * This method is used for speed incrementation, it can
     * go up to 3x the default speed.
     *
     * @return increased speed
     */
    public int increaseSpeed(){
        if(speed != 3) {
           this.speed += 1;
        }else{
            this.speed = 1;
        }
        return speed;
    }


    @Override
    public List<Transportable> getPackages() {
        List<Transportable> newList = new ArrayList<>(1);
        newList.add(pallet);
        return newList;
    }

    /**
     * Adds a single pallet to this object.
     *
     * @param pallet
     * @return true if added, false otherwise
     */
    public boolean addPallet(Pallet pallet){
        if(pallet == null){
            return false;
        }

        this.pallet = pallet;

        return true;
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

        return addPallet((Pallet) packageList.get(0));
    }

    @Override
    public boolean retrieveFromShelf(Package pack) {
        if(pack == null){
            return false;
        }
        if(pack instanceof Pallet){
            pallet = (Pallet) pack;
            return true;
        }
        return false;
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
                    matrix[i][j] = 'U';
                } else {
                    matrix[i][j] = '.';
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type: Unit Load Carrier ");
        sb.append("Position: " + positionProperty.get().getX() + "," + positionProperty.get().getY() + "\n");
        return sb.toString();
    }
}
