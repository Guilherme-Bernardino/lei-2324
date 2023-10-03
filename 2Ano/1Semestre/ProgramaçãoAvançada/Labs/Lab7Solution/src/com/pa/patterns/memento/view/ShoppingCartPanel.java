package com.pa.patterns.memento.view;

import com.pa.patterns.memento.model.NoMementoException;
import com.pa.patterns.memento.model.Product;
import com.pa.patterns.memento.model.ShoppingCartController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.Date;

import static sun.management.Agent.error;

public class ShoppingCartPanel {

    private ShoppingCartController shoppingCartController;
    private ListView<Product> listViewCartContents;

    private Button buttonUndo;
    private Button buttonReset;
    private GridPane gridPaneMain;
    private Button buttonAddProduct;
    private TextField textFieldProductName;
    private TextField textFieldPrice;

    public GridPane getGridPaneMain() {
        return gridPaneMain;
    }

    public ShoppingCartPanel() {
        shoppingCartController = new ShoppingCartController();
        gridPaneMain = new GridPane();

        // Add product
        GridPane gridPaneAddProduct = new GridPane();
        Label labelAddProduct = new Label("Add products to cart");
        labelAddProduct.setStyle("-fx-font-weight: bold");
        gridPaneAddProduct.add(labelAddProduct, 0, 0);
        gridPaneAddProduct.add(new Label("Name"), 0, 1);
        textFieldProductName = new TextField();
        gridPaneAddProduct.add(textFieldProductName, 1, 1);
        gridPaneAddProduct.add(new Label("Price"), 0, 2);
        textFieldPrice = new TextField();
        gridPaneAddProduct.add(textFieldPrice, 1, 2);
        HBox hBoxAddProductButtons = new HBox();
        buttonAddProduct = new Button("Add");
        hBoxAddProductButtons.getChildren().add(buttonAddProduct);
        hBoxAddProductButtons.setAlignment(Pos.CENTER_RIGHT);
        hBoxAddProductButtons.setStyle("-fx-padding: 2px 0 0 0");
        gridPaneAddProduct.add(hBoxAddProductButtons, 1, 4);

        gridPaneMain.add(gridPaneAddProduct, 0, 0);

        // Shopping cart
        GridPane gridPaneCartContents = new GridPane();
        Label labelCartContents = new Label("Cart contents");
        labelCartContents.setStyle("-fx-font-weight: bold");
        listViewCartContents = new ListView<>();
        gridPaneCartContents.add(labelCartContents, 0, 0);
        gridPaneCartContents.add(listViewCartContents, 0, 1);

        buttonReset = new Button("Reset");
        HBox hBoxReset = new HBox();

        buttonUndo = new Button("Undo");
        HBox hBoxUndo = new HBox();

        hBoxUndo.getChildren().add(buttonUndo);
        hBoxUndo.setAlignment(Pos.CENTER_RIGHT);
        hBoxUndo.setStyle("-fx-padding: 2px 0 0 0");

        hBoxReset.getChildren().add(buttonReset);
        hBoxReset.setAlignment(Pos.CENTER_RIGHT);
        hBoxReset.setStyle("-fx-padding: 2px 0 0 0");


        gridPaneCartContents.add(hBoxReset, 0,2);
        gridPaneCartContents.add(hBoxUndo, 0, 3);
        GridPane.setHgrow(listViewCartContents, Priority.ALWAYS);

        setTriggers();

        gridPaneMain.add(gridPaneCartContents, 0, 1);

        updateProductCartList();

        gridPaneMain.setStyle("-fx-padding: 5px");
    }

    private void setTriggers() {

        buttonAddProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (textFieldProductName.getText().isEmpty() || textFieldPrice.getText().isEmpty()) {
                    error("Missing product information.");
                } else {
                    try {
                        String name = textFieldProductName.getText();
                        double price = Double.parseDouble(textFieldPrice.getText());
                        shoppingCartController.addProduct(name, price);
                        updateProductCartList();
                        cleanInput();
                    } catch (NumberFormatException nfe) {
                        showError("Invalid price value.");
                    }
                }
            }
        });


        buttonUndo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    shoppingCartController.undo();
                } catch (NoMementoException e) {
                    showError("No previous state recorded.");
                }
                updateProductCartList();
            }
        });

        buttonReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    shoppingCartController.reset();
                } catch (NoMementoException e) {
                    showError("No previous state recorded.");
                }
                updateProductCartList();
            }
        });

    }

    private void cleanInput() {
        this.textFieldPrice.setText("");
        this.textFieldProductName.setText("");
    }

    private void updateProductCartList() {
        listViewCartContents.getItems().clear();
        for (Product product : shoppingCartController.getProducts()) {
            listViewCartContents.getItems().add(product);
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Shopping Cart Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
