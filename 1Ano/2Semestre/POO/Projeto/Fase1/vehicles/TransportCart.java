package vehicles;

import java.util.ArrayList;
import java.util.List;
import products.Package;
import products.Transportable;

/**
 * PT
 * The TransportCart class is meant to be used alongside the TowingVehicle, as in it serves an appendage.
 * This unit carries the transportables, and can add to the list, and return said list.
 *
 * EN
 * A classe TransportCart deve ser usada juntamente com o TowingVehicle, pois serve como um apêndice.
 * Esta unidade transporta os transportáveis e pode adicionar à lista e retornar à referida lista.
 *
 * @author guilh
 */
public class TransportCart {

    private List<Transportable> packageList;

    private final int WEIGHT_LIMIT = 200;

    public TransportCart(){
        this.packageList = new ArrayList<>();
    }

    /**
     * Add a transportable object to the transportables list.
     *
     * @param e
     * @return true if added, false otherwise
     */
    public boolean addPackage(Transportable e) {
        if(e == null){
            return false;
        }
        return packageList.add(e);
    }


    /**
     * Adds multiple transportables. Accounts for weight limit.
     *
     * @param packages
     * @return true if added, false otherwise
     */
    public boolean addPackages(List<Transportable> packages) {
        double weight = 0;

        if(packages == null || packages.isEmpty()){
            return false;
        }

        if(weight >= WEIGHT_LIMIT){
            return false;
        }

        for (Transportable p : packages){
            weight += p.getTotalWeight();
            addPackage(p);
        }

        return true;
    }

    /**
     * Returns list of transportables.
     *
     * @return list of transportables
     */
    public List<Transportable> getPackageList() {
        return packageList;
    }

    /**
     * Sets a new list of transportables.
     *
     * @param packageList
     */
    public void setPackageList(List<Transportable> packageList) {
        this.packageList = packageList;
    }
}
