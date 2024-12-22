package com.example.bbs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {
    private final ObservableList<Student> students = FXCollections.observableArrayList();
    @FXML
    private Label welcomeText;
    @FXML
    private TextField name;
    @FXML
    private TextField age;
    @FXML
    private TextField score;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, Integer> ageColumn;
    @FXML
    private TableColumn<Student, Float> scoreColumn;

    @FXML
    protected void onSave() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        studentTable.setItems(students);
    }

    @FXML
    public void addStudent() {
        String nameStudent = name.getText();
        int ageStudent = Integer.parseInt(age.getText());
        float scoreStudent = Float.parseFloat(score.getText());
        students.add(new Student(nameStudent, ageStudent, scoreStudent));
        name.clear();
        age.clear();
        score.clear();
    }

    @FXML
    protected void onSortScore() {
        ObservableList<Student> sortedList = FXCollections.observableArrayList(students);
        for (int i = 0; i < sortedList.size() - 1; i++) {
            for (int j = 0; j < sortedList.size() - i - 1; j++) {
                if (sortedList.get(j).getScore() > sortedList.get(j + 1).getScore()) {
                    Student temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }
        onSave();
        students.setAll(sortedList);
    }


}
