module moe.lottuce.stampeditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires com.fasterxml.jackson.databind;

    opens moe.lottuce.stampeditor to javafx.fxml;
    opens moe.lottuce.stampeditor.controller to javafx.fxml;

    exports moe.lottuce.stampeditor;
    exports moe.lottuce.stampeditor.controller;
    exports moe.lottuce.stampeditor.drawable;
    exports moe.lottuce.stampeditor.io;
}