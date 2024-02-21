module moe.lottuce.stampeditor {
    requires javafx.controls;
    requires javafx.fxml;


    opens moe.lottuce.stampeditor to javafx.fxml;
    exports moe.lottuce.stampeditor;
}