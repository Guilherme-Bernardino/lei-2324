/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.pa;

import pt.pa.model.BookmarkInvalidOperation;
import pt.pa.model.BookmarkManager;

/**
 * @author brunomnsilva
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BookmarkManager manager = new BookmarkManager();

        try {
            manager.addBookmarkFolder("bookmarks", "Jornais");
            manager.addBookmarkFolder("jornais", "Finanças");
            manager.addBookmarkFolder("bookmarks", "Redes Sociais");
            manager.addBookmarkFolder("bookmarks", "Diversos");

            manager.addBookmarkEntry("jornais", "Público", "http://www.publico.pt");
            manager.addBookmarkEntry("jornais", "Expresso", "http://www.expresso.pt");
            manager.addBookmarkEntry("finanças", "Diário Económico", "http://economico.sapo.pt/");

            manager.addBookmarkEntry("redes sociais", "Facebook", "http://www.facebook.com");
            manager.addBookmarkEntry("redes sociais", "Instagram", "http://www.instagram.com");

            manager.addBookmarkEntry("diversos", "Gmail", "http://www.gmail.com");
            manager.addBookmarkEntry("diversos", "StackOverflow", "http://www.stackoverflow.com");

            manager.addBookmarkEntry("bookmarks", "IPS", "http://www.ips.pt");

            System.out.println(manager);

            manager.addBookmarkFolder("jornais", "Desportivos");
            manager.addBookmarkEntry("desportivos", "A Bola", "http://www.abola.pt");
            manager.addBookmarkEntry("DesporTIVOS", "Record", "http://www.record.xl.pt");
        } catch (BookmarkInvalidOperation exception) {
            System.err.println(exception.getMessage());
        }

        try {
            // Check move Method
            manager.addBookmarkFolder("bookmarks", "Extra");

            // Mova a pasta "Redes sociais" para este novo Folder. Verifique o resultado.
            System.out.println("------------ ANTES de moveEntryToFolder(\"Redes sociais\", \"Extra\") ---------------");
            System.out.println(manager);
            manager.moveEntryToFolder("Redes Sociais", "Extra");
            System.out.println("------------ DEPOIS de moveEntryToFolder(\"Redes sociais\", \"Extra\") ---------------");
            System.out.println(manager);
        } catch (BookmarkInvalidOperation exception) {
            System.err.println(exception.getMessage());
        }

        // Mova a pasta "Redes sociais" para o link "IPS". Verifique o resultado.
        try {
            System.out.println("\n\n------------ Tentar mover o folder \"Redes Sociais\" para o link \"IPS\" moveEntryToFolder(\"Redes sociais\", \"IPS\") ---------------");
            manager.moveEntryToFolder("Redes Sociais", "IPS");
            System.out.println(manager);
        } catch (BookmarkInvalidOperation ex) {
            System.err.println(ex.getMessage() + "\n + " + ex.toString());
        }

        // Mova a pasta "Extra" para a pasta "Redes sociais". Verifique o resultado.
        try {
            System.out.println("\n\n------------ Tentar mover o folder \"Extra\" para um nó seu descendente \"Redes Sociais\" moveEntryToFolder(\"Extra\", \"Redes sociais\") ---------------");

            manager.moveEntryToFolder("Extra", "Redes sociais");
            System.out.println("------------ DEPOIS de moveEntryToFolder(\"Extra\", \"Redes sociais\") ---------------");
            System.out.println(manager);
        } catch (BookmarkInvalidOperation ex) {
            System.err.println(ex.getMessage() + "\n + " + ex.toString());
        }
    }

}
