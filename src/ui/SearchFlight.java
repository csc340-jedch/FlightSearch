package ui;

import api.Flight;
import api.GetFlightData;
import api.NoNearbyAirportsException;
import api.NotDirectFlightException;
import db.ConnectToDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import static db.FlightInformation.saveFlight;

public class SearchFlight {
    public Button searchFlight;

    public TableView<Flight>flightTable;
    public Button backButton;
    public ComboBox<String> airportComboBox;
    public TableColumn<String, String> carrierIDColumn;
    public TableColumn<String, String> carrierColumn;
    public TableColumn<String, String> priceColumnColumn;
    public DatePicker flightDatePicker;
    public Button saveFlightButton;

    @FXML
    protected void initialize() throws NoNearbyAirportsException, JSONException {
        // Populate the departure airport combobox
        String zip = ConnectToDB.getDatabaseValue(ConnectToDB.TBL_CLIENTS, ConnectToDB.COL_USERNAME, Controller.getUsername(), ConnectToDB.COL_ZIP);
        String[] airports = GetFlightData.getLocalAirports(zip);
        airportComboBox.getItems().addAll(airports);

        // Set column ids
        carrierIDColumn.setCellValueFactory(new PropertyValueFactory<>("carrierID"));
        carrierColumn.setCellValueFactory(new PropertyValueFactory<>("carrier"));
        priceColumnColumn.setCellValueFactory(new PropertyValueFactory<>("quote"));
    }

    public void searchButtonClicked(ActionEvent _actionEvent) throws JSONException, NotDirectFlightException {
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

    public void backButtonClick(ActionEvent _actionEvent) throws IOException {
        Controller controller = Controller.getInstance();
        controller.changePane("menu");
    }

    public void saveFlightClicked(ActionEvent _actionEvent) {
        int selectedIndex = flightTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex == -1) {
            Controller.showMessage("Please select a flight.", "Flight not selected");
        } else {
            Controller.showMessage("Your flight has been saved.", "Flight saved");

            //This grabs the info from the API to be stored in database.
            Flight item = flightTable.getItems().get(flightTable.getSelectionModel().getSelectedIndex());

            // Get the username
            String username = Controller.getUsername();
            int carrierID = item.getCarrierID();
            String carrier = item.getCarrier();
            int quote =item.getQuote();

            // Save the flight
            saveFlight(username, carrierID, carrier, quote);

        }
        System.out.println("Selected flight: " + selectedIndex);
    }
}
