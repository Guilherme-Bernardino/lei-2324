package products;

import java.util.ArrayList;
import java.util.List;

/**
 * EN
 * The Pallet is a Package derived class, used for storing a multiple cardboard boxes.
 * Instead of adding products, it adds cardboard boxes.
 *
 * PT
 * A Pallet é uma classe derivada de Package, usada para armazenar vários caixas de cartão.
 * Em vez de adicionar produtos, adiciona caixas de cartão.
 *
 * @author guilh
 */
public class Pallet extends Package{

    List<CardboardBox> cardboardBoxes;

    private static int number = 0;

    public Pallet() {
        this.uniqueId = "P-" + ++number;
        this.cardboardBoxes = new ArrayList<>();
    }

    @Override
    public boolean packProducts(Product product) {
        return false;
    }

    /**
     * Packs the boxes into the pallet, adding to a list.
     *
     * @param box
     * @return true if added, false otherwise
     */
    public boolean packBoxes(CardboardBox box){
        if(box == null){
            return false;
        }
        totalWeight += box.getTotalWeight();
        return cardboardBoxes.add(box);
    }


    /**
     * Returns an array of cardboards boxes.
     *
     * @return array of cardboardboxes
     */
    public CardboardBox[] getBoxes() {
        return cardboardBoxes.toArray(new CardboardBox[10]);
    }

    @Override
    public double getTotalWeight() {
        return totalWeight;
    }

    @Override
    public String toString() {
        return "Pallet{" +
                "cardboardBoxes=" + cardboardBoxes +
                ", uniqueId='" + uniqueId +
                ", totalWeight=" + totalWeight +
                ", isReady=" + isReady +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Pallet)) {
            return false;
        }
        Pallet other = (Pallet) obj;
        return uniqueId.equals(other.uniqueId);
    }

    @Override
    public int hashCode() {
        return 13 * uniqueId.hashCode();
    }
}
