import java.io.*;

public abstract class CoffeeShopFileHandler {

    public static File printToFile(String fileName, String textToSave) throws IOException {
        if(textToSave == null){
            throw new CoffeeShopIllegalArgumentException(ErrorCode.FILE_CANT_BE_NULL_OR_EMPTY);
        }

        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(textToSave);
        fileWriter.close();

        return file;
    }

    public static void saveShop(String fileName, CoffeeShop shop) throws IOException {

        FileOutputStream fileOut = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(shop);
        out.close();
        fileOut.close();
        System.out.println("CoffeeShop saved!");
    }

    public static CoffeeShop loadShop(String fileName) throws IOException, ClassNotFoundException {
        CoffeeShop shop = null;

        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        shop = (CoffeeShop) in.readObject();
        in.close();
        fileIn.close();
        System.out.println("CoffeeShop loaded!");

        return shop;
    }
}
