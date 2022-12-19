module com.mycompany.examen_interfaces_adolfo_salado {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.examen_interfaces_adolfo_salado to javafx.fxml;
    exports com.mycompany.examen_interfaces_adolfo_salado;
}
