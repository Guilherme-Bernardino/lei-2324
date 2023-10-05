package locals;

import coords.Dimension;
import coords.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * EN
 * The Warehouse class is a Local derived class, used for harboring the storage locals in a form of a list.
 * Warehouses can add storage locals, and return the storage locals.
 * Since it implements the Localizable interface, this object can be visually represented in a 2d matrix, using its
 * dimension and position.
 *
 * PT
 * A classe Warehouse é uma classe derivada de Local, usada para abrigar os locais de armazenamento na forma de uma lista.
 * Armazéns podem adicionar locais de armazenamento e retornar os locais de armazenamento.
 * Como implementa a interface Localizable, este objeto pode ser representado visualmente numa matriz 2d, usando a sua
 * dimensão e posição.
 *
 * @author guilh
 */
public class Warehouse extends Local{

    List<StorageLocal> storageLocalList;

    public Warehouse(Position position, Dimension dimension) {
        super(position, dimension);
        this.storageLocalList = new ArrayList<>();
    }

    public Warehouse(){
        super(null,null);
    }

    /**
     * Adds a new Storage Local object to the list.
     *
     * @param storageLocal
     * @return true if added, false otherwise
     */
    public boolean add(StorageLocal storageLocal){
        return storageLocalList.add(storageLocal);
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
                    matrix[i][j] = '+';
                } else {
                    matrix[i][j] = '.';
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
     * Returns the list of storage locals.
     *
     * @return list of storage locals
     */
    public List<StorageLocal> getStorageLocalList() {
        return storageLocalList;
    }

    /**
     * Sets a new storage local list.
     *
     * @param storageLocalList
     */
    public void setStorageLocalList(List<StorageLocal> storageLocalList) {
        this.storageLocalList = storageLocalList;
    }

    public void detectAGVInStorageLocals(DistributionCenter distributionCenter) {
        for (StorageLocal local: storageLocalList) {
            local.detectAGV(distributionCenter);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type: Warehouse" + "\n");
        sb.append("Position: " + getPosition().getY() + "," + getPosition().getY() + "\n");
        sb.append("Dimension: " + getDimension().getX() + "," + getDimension().getY());
        return sb.toString();
    }


}
