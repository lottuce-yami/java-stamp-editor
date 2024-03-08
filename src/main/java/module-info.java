module moe.lottuce.stampeditor {
    requires javafx.controls;
    requires javafx.fxml;

    opens moe.lottuce.stampeditor to javafx.fxml;
    opens moe.lottuce.stampeditor.controllers to javafx.fxml;

    exports moe.lottuce.stampeditor;
    exports moe.lottuce.stampeditor.controllers;
}