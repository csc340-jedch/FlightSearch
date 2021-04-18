package ui;

import api.Flight;
import api.GetFlightData;
import db.FlightInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONException;

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

    @FXML
    protected void initialize() {
        // Populate the departure airport combobox
        String[] airports = { "RDU" };
        airportComboBox.getItems().addAll(airports);

        // Set column ids
        carrierIDColumn.setCellValueFactory(new PropertyValueFactory<>("carrierID"));
        carrierColumn.setCellValueFactory(new PropertyValueFactory<>("carrier"));
        priceColumnColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
    }

    public void searchButtonClicked(ActionEvent actionEvent) throws JSONException {
        String date = flightDatePicker.getValue().toString();
        String airport = airportComboBox.getValue().toString();

        // Make sure all fields are filled out
        if (date.isEmpty() || airport.isEmpty()) {
            ui.Controller.showMessage("Please fill out all flight information.", "Fill out all fields");
            return;
        }

        // Get valid flights
        GetFlightData flightData = new GetFlightData();
        Flight[] flights = flightData.getFlights(date, airport);

        // Show the valid flights
        ObservableList<Flight> list = FXCollections.observableArrayList();
        list.addAll(flights);
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

            // Get the selected flight information
            ObservableList list = flightTable.getSelectionModel().getSelectedCells();
            TablePosition tablePosition = (TablePosition)list.get(0);
            String carrierId = (String)tablePosition.getTableColumn().getCellData("cost");

            // Get the username
            String username = Controller.getUsername();

            // Save the flight
            FlightInformation.saveFlight(username, carrierId);
            System.out.println("Saved flight: {Username:" + username +",CarrierId:" + carrierId + "}");
        }
        System.out.println("Selected flight: " + selectedIndex);
    }
}
