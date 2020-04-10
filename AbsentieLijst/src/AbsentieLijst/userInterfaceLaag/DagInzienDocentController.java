
package AbsentieLijst.userInterfaceLaag;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import AbsentieLijst.Afspraak;
import AbsentieLijst.Docent;
import AbsentieLijst.School;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DagInzienDocentController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button buttonTerug;
    @FXML
    private Label date;
    @FXML
    private DatePicker dp;
    @FXML
    private ListView timeBlock;
    @FXML
    private ListView activiteitBlock;
    @FXML
    private LocalDate cellData;

    @FXML
    void initialize() {
        handleButtonAction();
        handleStart();
    }

    School HU = School.getSchool();


    @FXML
    public void handleStart() {
        cellData = HoofdmenuDocentController.getCellData();
        DatePicker d = dp;
        d.setValue(cellData);
        LocalDate i = cellData;
        if (i == null) {
            i = LocalDate.now();
        }
//        System.out.println(i);

        // action event
        date.setText("Datum :" + i); // get the selected date
        ArrayList<String> tijden = new ArrayList<>();
        ArrayList<String> omschrijvingen = new ArrayList<>();

        for (Docent docent : HU.getDocenten()) {
            ArrayList<Afspraak> afspraken = docent.getAfspraken();
            for (Afspraak afspraak : afspraken) {
                if (afspraak.getDatum().isEqual(i)) {
                    String textAfspraak = afspraak.toString();
                    String[] gesplit = textAfspraak.split(",");
                    tijden.add(gesplit[1]);
                    omschrijvingen.add(gesplit[0]);
                    System.out.println(omschrijvingen);
                }
            }
        }
        ArrayList<Label> labels = new ArrayList<>();
        for (String tijd : tijden) {
            Label label = new Label(tijd);
            label.setFont(new Font("Calibri", 12));
            label.setPadding(new Insets(10, 10, 10, 10));
            labels.add(label);
        }
        Collections.reverse(labels);
        timeBlock.setItems(FXCollections.observableArrayList(labels));

        ArrayList<Label> labels2 = new ArrayList<>();
        for (String omschrijving : omschrijvingen) {
            Label label2 = new Label(omschrijving);
            label2.setFont(new Font("Calibri", 12));
            label2.setPadding(new Insets(10, 10, 10, 10));
            labels2.add(label2);
        }
        Collections.reverse(labels2);
        activiteitBlock.setItems(FXCollections.observableArrayList(labels2));

    }





    @FXML
    public void handleButtonAction() {
//        // label to show the date
//        date.setText("no date selected");
//        LocalDate today = LocalDate.now();
//
//        date.setText(today.toString());
        HoofdmenuDocentController data = new HoofdmenuDocentController();
        LocalDate cellData = data.getCellData();
        DatePicker d = dp;
        d.setValue(cellData);
        LocalDate i = cellData;
        date.setText("Datum: " + i);

        // action event
        LocalDate finalCellData = cellData;
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                LocalDate i = finalCellData; // get the date picker value
                date.setText("Datum :" + i); // get the selected date
                ArrayList<String> tijden = new ArrayList<>();
                ArrayList<String> omschrijvingen = new ArrayList<>();

                for (Docent docent : HU.getDocenten()) {
                    ArrayList<Afspraak> afspraken = docent.getAfspraken();
                    for (Afspraak afspraak : afspraken) {
                        if (afspraak.getDatum().isEqual(i)) {
                            String textAfspraak = afspraak.toString();
                            String[] gesplit = textAfspraak.split(",");
                            tijden.add(gesplit[1]);
                            omschrijvingen.add(gesplit[0]);
                            System.out.println(omschrijvingen);
                        }
                    }
                }
                ArrayList<Label> labels = new ArrayList<>();
                for (String tijd : tijden) {
                    Label label = new Label(tijd);
                    label.setFont(new Font("Calibri", 12));
                    label.setPadding(new Insets(10, 10, 10, 10));
                    labels.add(label);
                }
                Collections.reverse(labels);
                timeBlock.setItems(FXCollections.observableArrayList(labels));

                ArrayList<Label> labels2 = new ArrayList<>();
                for (String omschrijving : omschrijvingen) {
                    Label label2 = new Label(omschrijving);
                    label2.setFont(new Font("Calibri", 12));
                    label2.setPadding(new Insets(10, 10, 10, 10));
                    labels2.add(label2);
                }
                Collections.reverse(labels2);
                activiteitBlock.setItems(FXCollections.observableArrayList(labels2));
            }
        };
        d.setOnAction(event);
    }


    public void Terug() {
        Stage stage = (Stage) buttonTerug.getScene().getWindow();
        stage.close();
    }
}
