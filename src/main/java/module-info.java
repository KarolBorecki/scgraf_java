module com.scgraf {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.scgraf to javafx.fxml;
    exports com.scgraf;
}