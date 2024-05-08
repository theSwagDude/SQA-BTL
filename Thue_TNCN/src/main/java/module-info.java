module com.example.thue_tnct {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.thue_tnct to javafx.fxml;
    exports com.example.thue_tnct;
    exports controller;
    opens controller to javafx.fxml;
    opens model;
}