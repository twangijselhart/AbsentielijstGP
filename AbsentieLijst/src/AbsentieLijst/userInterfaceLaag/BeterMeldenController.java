package AbsentieLijst.userInterfaceLaag;



import java.net.URL;
import java.util.ResourceBundle;

import AbsentieLijst.Klas;
import AbsentieLijst.School;
import AbsentieLijst.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BeterMeldenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    void initialize() {



    }
    School HU = School.getSchool();
    @FXML
    private Button beterMeldenAnnuleren;
    @FXML
    private Button beterMeldenBevestigen;

    public void beterAnnuleren() {
        Stage stage = (Stage) beterMeldenAnnuleren.getScene().getWindow();
        stage.close();
    }

    public void beterBevestigen() {
        for (Klas klas : HU.getKlassen()) { //Vind ingelogde leerling
            //System.out.println(klas);
            for (Student student : klas.getStudenten()) {
                //System.out.println(student);
                if (student.getisIngelogd()) {
                    System.out.println(student);
                    student.getStudentZiek();
                    System.out.println(student.getStudentZiek());
                    student.setStudentZiek(false);
                    System.out.println(student.getStudentZiek());
                    Stage stage = (Stage) beterMeldenAnnuleren.getScene().getWindow();
                    stage.close();
                }
            }
        }
    }
}

