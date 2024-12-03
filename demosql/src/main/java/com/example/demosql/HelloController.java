package com.example.demosql;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.print.Book;
import java.sql.*;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField first;

    @FXML
    private TextField last;

    @FXML
    private TextField address;



    @FXML
    private TableView<Person> InforTable;

    @FXML
    private TableColumn<Person, String> firstCol;

    @FXML
    private TableColumn<Person, String> lastCol;

    @FXML
    private TableColumn<Person, String> addCol;

    private final ObservableList<Person> PersonList = FXCollections.observableArrayList();


    @FXML
    private void initialize() {
        // Initialize table columns
        firstCol.setCellValueFactory(new PropertyValueFactory<>("first"));
        lastCol.setCellValueFactory(new PropertyValueFactory<>("last"));
        addCol.setCellValueFactory(new PropertyValueFactory<>("add"));

        InforTable.setItems(PersonList);
        displayPerson();
    }



    @FXML
    protected void onHelloButtonClick() {
        String firstName  = first.getText();
        String lastName = last.getText();
        String add = address.getText();
        welcomeText.setText(firstName + " "+ lastName + " " + add);


        String url = "jdbc:mysql://localhost:4306/School";
        String username = "root";
        String password = "";
        String insertQuery = "INSERT INTO Name (FirstName , LastName , Address) VALUES (?, ? , ?)";


        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1,firstName );
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, add);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println(" successfully!");
                displayPerson();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Database connection or query execution failed.");
        }

    }

    private void displayPerson() {
        PersonList.clear();

        String url = "jdbc:mysql://localhost:4306/School";
        String username = "root";
        String password = "";

        String selectQuery = "SELECT * FROM Name";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String first = resultSet.getString("FirstName");
                String last = resultSet.getString("LastName");
                String address = resultSet.getString("Address");


                PersonList.add(new Person(first,last,address));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }









}