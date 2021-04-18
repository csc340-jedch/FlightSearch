package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class SearchFlight {
    public Button searchFlight;

    public TableView flightTable;
    public TableColumn destColumn;
    public TableColumn dateColumn;
    public TableColumn costColumn;
    public Button backButton;
    public ComboBox airportComboBox;
    public TableColumn carrierIDColumn;
    public TableColumn carrierColumn;
    public TableColumn priceColumnColumn;
    public DatePicker flightDatePicker;

    public void searchButtonClicked(ActionEvent actionEvent) {
        String date = flightDatePicker.getValue().toString();
        String airport = airportComboBox.getValue().toString();


        /*
        if (date == null || dest == null) {
            ui.Controller.showMessage("Please fill out all flight information.", "Fill out all fields");
            return;
        }*/

        carrierIDColumn.setCellValueFactory(new PropertyValueFactory<>("carrierID"));
        carrierColumn.setCellValueFactory(new PropertyValueFactory<>("carrier"));
        priceColumnColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));

        Flight flight1 = new Flight(1, "B", 300 );
        Flight flight2 = new Flight(2, "D", 450);

        ObservableList<Flight> list = FXCollections.observableArrayList();
        list.add(flight1);
        list.add(flight2);
        flightTable.setItems(list);
    }

    public void backButtonClick(ActionEvent actionEvent) throws IOException {
        Controller controller = Controller.getInstance();
        controller.changePane("menu");
    }

    public void saveFlightClicked(ActionEvent actionEvent) {
        int selectedIndex = flightTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex == -1) {
            Controller.showMessage("Please select a flight.", "Flight not selected");
        } else {
            Controller.showMessage("Your flight has been saved.", "Flight saved");
        }
        System.out.println("Selected flight: " + selectedIndex);
    }
}
