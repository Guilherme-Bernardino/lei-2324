import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Purchase implements Serializable {

    private final int purchaseId;
    private final Member member;
    private final LocalDateTime date;
    private final ShoppingCart cart;
    private final float discount;

    public Purchase(Member member, int purchaseId) {
        this.member = validateMember(member);
        this.purchaseId = purchaseId;
        this.date = LocalDateTime.now();
        this.cart = new ShoppingCart((ArrayList<CoffeeBox>) member.getCart().getCartContent().clone());
        this.discount = member.getDiscount();

        member.incrementCoffePurchase();
        member.getCart().cleanCart();
    }

    public float totalPrice() {
        return cart.getTotalPrice(discount);
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    private Member validateMember(Member member){
        if(member == null)
            throw new CoffeeShopIllegalArgumentException(ErrorCode.MEMBER_NIF_CANT_BE_NULL);
        return member;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        StringBuilder sb = new StringBuilder();
        sb.append("Compra nº ").append(purchaseId);
        sb.append("\nComprado por: ").append(member.getLoginName()).append("\tNIF: ").append(member.getNif());
        sb.append("\nEm: ").append(date.format(formatter));
        sb.append("\n\nDescrição:");

        cart.getCartContent().forEach(b -> {
            sb.append("\n- ").append(b.toString());
        });

        sb.append("\n\nValor total: ").append(totalPrice()).append("€");
        return sb.toString();
    }

}
