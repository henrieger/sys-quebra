module com.paradigmas {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires com.google.gson;

    opens com.paradigmas to javafx.fxml;
    exports com.paradigmas;

    opens com.paradigmas.Models;
}
