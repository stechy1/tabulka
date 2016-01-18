package cz.stechy1.simpletable;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public TableView<DataModel> table;
    public TableColumn<DataModel, String> prgNameColumn;
    public TableColumn<DataModel, String> prgTimeColumn;
    public TabPane tabpane;

    private DataManager manager;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prgNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        prgTimeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        manager = new DataManager(table.getItems());
    }

    public void addData() {
        Optional<DataModel> result = createDialog().showAndWait();
        result.ifPresent(data -> manager.add(data));
    }

    private Dialog<DataModel> createDialog() {
        Dialog<DataModel> d = new Dialog<>();
        d.setHeaderText("Vyberte...");
        Label nameLabel = new Label("Název souboru");
        TextField nameField = new TextField();
        nameField.setPromptText("název souboru");
        Label timeLabel = new Label("Čas spuštění");
        TextField timeField = new TextField();
        timeField.setPromptText("hh:mm");
        VBox v = new VBox(nameLabel, nameField, timeLabel, timeField);
        //d.setGraphic(v);
        d.getDialogPane().setContent(v);

        ButtonType addButtonType = new ButtonType("Přidat", ButtonBar.ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        final Node addButton = d.getDialogPane().lookupButton(addButtonType);
        addButton.setDisable(true);

        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty());
        });


        d.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                try {
                    DataModel data = new DataModel(nameField.getText(), timeField.getText());
                    manager.add(data);
                } catch (Exception e) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText(e.getMessage());
                    a.showAndWait();
                }
            }
            return null;
        });


        return d;
    }
}
