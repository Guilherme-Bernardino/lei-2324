import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Listings extends ArrayList<Listable> implements Searchable  {

    public int getNumberOfListings(){
        return this.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Listable a: this) {
            sb.append(a.toString() + "\n");
        }

        return sb.toString();
    }

    public void show(){
        System.out.println(this);
    }

    @Override
    public List<Listable> search(String searchText) {
        List<Listable> newList = new ArrayList<>();

        for (Listable a : this) {
            if(a.toString().contains(searchText)){
                newList.add(a);
            }
        }

        return newList;
    }

    @Override
    public List<Listable> searchWhere(String where) {
        List<Listable> newList = new ArrayList<>();

        for (Listable a : this) {
            if(a.getWhere().equalsIgnoreCase(where)){
                newList.add(a);
            }
        }

        return newList;
    }

    @Override
    public List<Listable> searchFree() {
        List<Listable> newList = new ArrayList<>();

        for (Listable a : this) {
            if(a.isFree()){
                newList.add(a);
            }
        }

        return newList;
    }
}
