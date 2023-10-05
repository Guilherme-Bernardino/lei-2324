import java.io.Serializable;

public class Member implements Serializable{

    private String loginName;
    private String nif;
    private MemberType type;
    private static int totalPurchasedCoffe = 0;

    private ShoppingCart cart;

    public Member(String loginName, String nif) {
        this.loginName = validateName(loginName);
        this.nif = validateNif(nif);
        this.type = MemberType.OTHER;
        this.cart = new ShoppingCart();
    }
    
    public String getLoginName() {
        return loginName;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public String getNif() {
        return nif;
    }

    public void incrementCoffePurchase() {
        totalPurchasedCoffe += cart.getCoffeeQuantity();
        type = changeTypeMember();
    }

    private MemberType changeTypeMember() {
        if (totalPurchasedCoffe > 500) {
            return MemberType.AMBASSADOR;
        } else if (totalPurchasedCoffe > 200) {
            return MemberType.CONNOISSEUR;
        } else if (totalPurchasedCoffe > 10) {
            return MemberType.AMATEUR;
        } else {
            return MemberType.OTHER;
        }
    }

    public float getDiscount() {
        return type.getDiscount();
    }

    private String validateName(String name){
        if (name == null)
            throw new CoffeeShopIllegalArgumentException(ErrorCode.LOGIN_NAME_CANT_BE_NULL);
        if (name.length() < 5)
            throw new CoffeeShopIllegalArgumentException(ErrorCode.LOGIN_NAME_MUST_BE_BIGGER);

        return name;
    }

    private String validateNif(String nif){
        if (nif == null)
            throw new CoffeeShopIllegalArgumentException(ErrorCode.MEMBER_NIF_CANT_BE_NULL);
        if (nif.length() != 9)
            throw new CoffeeShopIllegalArgumentException(ErrorCode.MEMBER_NIF_IS_INCORRECT);
        return nif;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n*** Informação de utilizador ***");
        sb.append(String.format("\n%-27s", "Nome de utilizador:")).append(loginName);
        sb.append(String.format("\n%-27s", "NIF:")).append(nif);
        sb.append(String.format("\n%-27s", "Tipo de conta:")).append(type);
        sb.append(String.format("\n%-27s", "Desconto atribuido:")).append(type.getDiscount()).append("%");
        sb.append(String.format("\n%-27s", "Total de cafés comprados:")).append(totalPurchasedCoffe).append("\n");

        return sb.toString();
    }

}
