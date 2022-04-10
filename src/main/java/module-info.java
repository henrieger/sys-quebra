module com.paradigmas {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.paradigmas to javafx.fxml;
    exports com.paradigmas;
}
