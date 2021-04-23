package ui;

import api.Flight;
import api.GetFlightData;
import api.NotDirectFlightException;
import db.FlightInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class SearchFlight {
    public Button searchFlight;

    public TableView flightTable;
    public Button backButton;
    public ComboBox airportComboBox;
    public TableColumn carrierIDColumn;
    public TableColumn carrierColumn;
    public TableColumn priceColumnColumn;
    public DatePicker flightDatePicker;
    public Button saveFlightButton;

    @FXML
    protected void initialize() {
        // Populate the departure airport combobox
        String zip = "27713";
        String[] airports = GetFlightData.getLocalAirports(zip);
        airportComboBox.getItems().addAll(airports);

        // Set column ids
        carrierIDColumn.setCellValueFactory(new PropertyValueFactory<>("carrierID"));
        carrierColumn.setCellValueFactory(new PropertyValueFactory<>("carrier"));
        priceColumnColumn.setCellValueFactory(new PropertyValueFactory<>("quote"));
    }

    public void searchButtonClicked(ActionEvent actionEvent) throws JSONException, NotDirectFlightException {
        // Make sure all fields are filled out
        if (flightDatePicker.getValue() == null || airportComboBox.getValue() == null) {
            ui.Controller.showMessage("Please fill out all flight information.", "Fill out all fields");
            return;
        }

        String date = flightDatePicker.getValue().toString();
        String airport = airportComboBox.getValue().toString();

        // Get valid flights
        GetFlightData flightData = new GetFlightData(date, airport);
        List<Flight> flights = flightData.getFlights();

        if (flights.isEmpty()) {
            Controller.showMessage("No valid flights for given specifications.", "No flights");
        } else {
            // Show the valid flights
            ObservableList<Flight> list = FXCollections.observableArrayList();
            list.addAll(flights);
            flightTable.setItems(list);
        }
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
            String carrierId = (String)tablePosition.getTableColumn().getCellData("carrierID");

            // Get the username
            String username = Controller.getUsername();

            // Save the flight
            FlightInformation.saveFlight(username, carrierId);
            System.out.println("Saved flight: {Username:" + username +",CarrierId:" + carrierId + "}");
        }
        System.out.println("Selected flight: " + selectedIndex);
    }
}
