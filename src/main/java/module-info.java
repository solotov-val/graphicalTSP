module bx.fallmerayer.graphicaltsp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens bx.fallmerayer.graphicaltsp to javafx.fxml;
    exports bx.fallmerayer.graphicaltsp;
}