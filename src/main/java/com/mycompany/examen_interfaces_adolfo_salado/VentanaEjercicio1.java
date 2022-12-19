package com.mycompany.examen_interfaces_adolfo_salado;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VentanaEjercicio1 implements Initializable {

    @FXML
    private ComboBox<String> cbSexo;
    @FXML
    private TextField tfNombre;
    @FXML
    private Spinner<Integer> spEdad;
    @FXML
    private Spinner<Integer> spPeso;
    @FXML
    private Spinner<Integer> spAltura;
    @FXML
    private ComboBox<String> cbActividad;
    @FXML
    private TableView<Usuario> tabla;
    @FXML
    private TableColumn<Usuario, String> tcSexo;
    @FXML
    private TableColumn<Usuario, Integer> tcEdad;
    @FXML
    private TableColumn<Usuario, Integer> tcPeso;
    @FXML
    private TableColumn<Usuario, Integer> tcAltura;
    @FXML
    private TableColumn<Usuario, String> tcActividad;
    @FXML
    private TableColumn<Usuario, Double> tcGer;
    @FXML
    private TableColumn<Usuario, Double> tcGet;
    @FXML
    private Label lblInfo;

    private List<Usuario> usuariosTabla = new ArrayList<>();

    @FXML
    private TableColumn<Usuario, String> tcNombre;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rellenarSpinnerEdad();
        rellenarSpinnerAltura();
        rellenarSpinnerPeso();
        rellenarComboSexo();
        rellenarComboActividad();

        /* Se rellenan columnas de la tabla */
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        tcEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        tcPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        tcAltura.setCellValueFactory(new PropertyValueFactory<>("altura"));
        tcActividad.setCellValueFactory(new PropertyValueFactory<>("actividad"));
        tcGer.setCellValueFactory(new PropertyValueFactory<>("ger"));
        tcGet.setCellValueFactory(new PropertyValueFactory<>("get"));
    }

    private void rellenarSpinnerEdad() {
        SpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        spEdad.setValueFactory(svf);

    }

    private void rellenarSpinnerAltura() {
        SpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(100, 250);
        spAltura.setValueFactory(svf);

    }

    private void rellenarSpinnerPeso() {
        SpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 250);
        spPeso.setValueFactory(svf);
    }

    private void rellenarComboSexo() {
        ObservableList<String> sexos = FXCollections.observableArrayList(
                "Hombre", "Mujer"
        );

        cbSexo.setItems(sexos);
    }

    private void rellenarComboActividad() {
        ObservableList<String> actividades = FXCollections.observableArrayList(
                "Muy ligera", "Ligera", "Moderada", "Intensa"
        );

        cbActividad.setItems(actividades);
    }

    private Double calcularGer(Usuario usuario) {
        Double ger = 0D;

        if (usuario.getSexo().equals("Hombre")) {
            ger = 66.473 + 13.751 * usuario.getPeso() + 5.0033 * usuario.getAltura() - 6.755 * usuario.getEdad();
        } else if (usuario.getSexo().equals("Mujer")) {
            ger = 655.0955 + 9.463 * usuario.getPeso() + 1.8596 * usuario.getAltura() - 4.6756 * usuario.getEdad();
        }

        return ger;
    }

    private Double calcularGet(Usuario usuario) {
        Double get = 0D;

        if (usuario.getSexo().equals("Hombre")) {
            if (usuario.getActividad().equals("Muy ligera")) {
                get = usuario.getGer() * 1.3;
            } else if (usuario.getActividad().equals("Ligera")) {
                get = usuario.getGer() * 1.6;
            } else if (usuario.getActividad().equals("Moderada")) {
                get = usuario.getGer() * 1.7;
            } else if (usuario.getActividad().equals("Intensa")) {
                get = usuario.getGer() * 2.1;
            }
        } else if (usuario.getSexo().equals("Mujer")) {
            if (usuario.getActividad().equals("Muy ligera")) {
                get = usuario.getGer() * 1.3;
            } else if (usuario.getActividad().equals("Ligera")) {
                get = usuario.getGer() * 1.5;
            } else if (usuario.getActividad().equals("Moderada")) {
                get = usuario.getGer() * 1.6;
            } else if (usuario.getActividad().equals("Intensa")) {
                get = usuario.getGer() * 1.9;
            }
        }

        return get;
    }

    @FXML
    private void addTable(ActionEvent event) {
        if (tfNombre.getText() != null &&
                spEdad.getValue() != null &&
                cbSexo.getValue() != null &&
                spAltura.getValue() != null &&
                spPeso.getValue() != null &&
                cbActividad.getValue() != null) {

            Usuario usuario = new Usuario();
            usuario.setNombre(tfNombre.getText());
            usuario.setSexo(cbSexo.getValue());
            usuario.setEdad(spEdad.getValue());
            usuario.setPeso(spPeso.getValue());
            usuario.setAltura(spAltura.getValue());
            usuario.setActividad(cbActividad.getValue());
            usuario.setGer(calcularGer(usuario));
            usuario.setGet(calcularGet(usuario));

            usuariosTabla.add(usuario);

            ObservableList<Usuario> olUsuarios = FXCollections.observableArrayList();
            olUsuarios.addAll(usuariosTabla);

            tabla.getItems().clear();
            tabla.setItems(olUsuarios);

            tfNombre.setText("");

        } else {
            lblInfo.setText("No ha introducido los valores correctamente.");
            lblInfo.setStyle("-fx-fill: red");
        }
    }
}
