module com.example.stackworkflow {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.stackworkflow to javafx.fxml;
    exports com.example.stackworkflow;
}