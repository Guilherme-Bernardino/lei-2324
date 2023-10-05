import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class CoffeeShop implements Serializable {

    private final HashSet<Member> members;
    private final ArrayList<Purchase> purchases;

    public CoffeeShop() {
        this.members = new HashSet<>();
        this.purchases = new ArrayList<>();
    }


    private Member searchMember(String name){
        for (Member m : members) {
            if(m.getLoginName().equalsIgnoreCase(name)){
                return m;
            }
        }
        return null;
    }

    public boolean createMemberAccount(String loginName, String nif){
        if(searchMember(loginName) != null){
            throw new CoffeeShopIllegalArgumentException(ErrorCode.MEMBER_ALREADY_EXISTS);
        }

        Member newMember = new Member(loginName,nif);
        return members.add(newMember);
    }

    public Member getMember(String name){
        Member memberToSearch = searchMember(name);

        if(memberToSearch == null){
            throw new CoffeeShopIllegalArgumentException(ErrorCode.MEMBER_DOES_NOT_EXISTS);
        }
        return memberToSearch;
    }

    public void addToCart(String name, int boxQuantity, CoffeeType type){
        Member member = getMember(name);

        member.getCart().addCoffeToCard(boxQuantity, type);
    }

    public Purchase finishPurchase(String name){
        Member member = getMember(name);

        if(member.getCart().getCartContent().isEmpty()){
            throw new CoffeeShopIllegalArgumentException(ErrorCode.SHOPPING_CART_IS_EMPTY);
        }

        Purchase purchase = new Purchase(getMember(name), purchases.size());
        purchases.add(purchase);

        return purchase;
    }

    public String getBalance(){
        double amount = 0;

        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------\n");
        for (Purchase p : purchases) {
            sb.append(p.toString());
            amount += p.totalPrice();
        }
        sb.append("\n---------------------------------\n");
        sb.append("Total da loja: " + amount + "€\n");
        return sb.toString();
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }
}
