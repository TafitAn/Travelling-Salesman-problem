module com.example.traveling {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;


    opens com.example.traveling to javafx.fxml;
    exports com.example.traveling;
}