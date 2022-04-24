module com.paradigmas {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    opens com.paradigmas to javafx.fxml;
    exports com.paradigmas;
}
