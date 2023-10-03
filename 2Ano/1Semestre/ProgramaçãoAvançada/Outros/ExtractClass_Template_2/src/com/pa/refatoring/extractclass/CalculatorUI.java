package com.pa.refatoring.extractclass;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class CalculatorUI extends GridPane {
    //move field

    private Slider slider;
    private TextField totalTf;
    private Button multiplyBtn;
    private Button clearBtn;

    private Calculator calculator = new Calculator();

    public CalculatorUI() {
        initComponents();
    }

    private void initComponents() {
        //... Initialize components

        slider = new Slider(0, 100, 1);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMinorTickCount(20);
        slider.setMajorTickUnit(20);
        totalTf = new TextField();
        multiplyBtn = new Button("Multiply");
        clearBtn = new Button("Reset");
        setTriggers();
        totalTf.setText(calculator.getTotal()+"");
        totalTf.setEditable(false);
        //... Layout the components.

        this.setPadding(new Insets(15, 15, 15, 15));
        this.setVgap(10);
        this.setHgap(10);
        this.add(new Label("INPUT"), 0, 0);
        this.add(slider, 1, 0);
        this.add(multiplyBtn, 2, 0);
        this.add(new Label("TOTAL"), 0, 1);
        this.add(totalTf, 1, 1);
        this.add(clearBtn, 2, 1);

    }

    private void setTriggers() {
        multiplyBtn.setOnAction(
                event -> {
                    calculator.multiplyBy(getUserInput());
                    update();
                });
        clearBtn.setOnAction(
                event -> {
                    calculator.reset();
                    update();
                });
    }

    public void update() {

        totalTf.setText(calculator.getTotal()+"");
    }

    public int getUserInput() {
        int value = (int) slider.getValue();
        return value;
    }

    public void showError(String errMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error Message");
        alert.setContentText(errMessage);
        alert.showAndWait();
    }

    public void resetInput() {
        slider.setValue(1.0);
    }


}
