import java.io.IOException;

public class AppStart {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        CoffeeShop coffeeShop = new CoffeeShop();

        try{
           // coffeeShop.createMemberAccount("Guilherme", "1321");
            //coffeeShop.createMemberAccount("João", "234121323");
            //coffeeShop.createMemberAccount("Maria", null);
           // coffeeShop.createMemberAccount(null, "233234234");

            //coffeeShop.createMemberAccount("sadsadasdad", "123456789");
            coffeeShop.createMemberAccount("sadsadasdad", "123456789");

            coffeeShop.addToCart("sadsadasdad", 2, CoffeeType.DECAF);

            coffeeShop.finishPurchase("sadsadasdad");

            //System.out.println(coffeeShop.getBalance());

            CoffeeShopFileHandler.printToFile("balançoDeCompras.txt", coffeeShop.getBalance());

            int lastPurchase = coffeeShop.getPurchases().size()-1;

            CoffeeShopFileHandler.printToFile("Fatura_" + lastPurchase + ".txt", coffeeShop.getPurchases().get(lastPurchase).toString());

            CoffeeShopFileHandler.saveShop("shop.backup", coffeeShop);

            CoffeeShop newShop = CoffeeShopFileHandler.loadShop("shop.backup");

            System.out.println(newShop.getBalance());

            //coffeeShop.getMember("dsadsa");
        } catch (CoffeeShopIllegalArgumentException e){
            System.out.println(e.getErrorCode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
