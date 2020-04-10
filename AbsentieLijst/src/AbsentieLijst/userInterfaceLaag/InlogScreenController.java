package AbsentieLijst.userInterfaceLaag;

import AbsentieLijst.*;
import com.sun.javafx.scene.control.DatePickerContent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class InlogScreenController {
    public ResourceBundle resources;
    @FXML
    public AnchorPane pane;
    @FXML
    public Label AaanmeldenL;
    @FXML
    public TextField Gebruikersnaam;
    @FXML
    public Button buttonAanmelden;
    @FXML
    public PasswordField Wachtwoord;
    @FXML
    public Label Correct;
    @FXML
    public Button buttonCancel;
    @FXML
    public CheckBox docentBox;

    School HU = School.getSchool();

    @FXML
    void loginKnop(ActionEvent event) throws IOException {
        if (docentBox.isSelected()) {
            for (Docent docent : HU.getDocenten()) {
                System.out.println(docent.toString());
                if (Gebruikersnaam.getText().equals(docent.getInlogcode()) && Wachtwoord.getText().equals(docent.getWachtwoord())) {
                    aanroepHoofdmenuDocent(event);
                    docent.setIngelogd(true);
                } else {
                    Correct.setText("Ongeldige combinatie gebruikersnaam/wachtwoord");
                }
            }
        } else {
            for (Klas klas : HU.getKlassen()) {
//                System.out.println(klas);
                for (Student student : klas.getStudenten()) {
//                    System.out.println(student);
                    if (Gebruikersnaam.getText().equals(student.getStudentNaam()) && Wachtwoord.getText().equals(student.getWachtwoord())) {
                        aanroepHoofdmenu(event);
                        student.setIngelogd(true);
                        //setStudent(student);


                    } else {
                        Correct.setText("Ongeldige combinatie gebruikersnaam/wachtwoord");
                    }
                }
            }
        }
    }

//    public void setStudent(Student input){
////        System.out.println(clicked + "setCellData");
//        naamStudent = input;
//
//    }
//
//    public static Student getStudent(){
//        System.out.println(naamStudent + " getnaam");
//        return naamStudent;
//}
    @FXML


    public void aanroepHoofdmenu(ActionEvent event) throws IOException {
        Pane hoofdM = FXMLLoader.load(getClass().getResource("/AbsentieLijst/userInterfaceLaag/HoofdmenuStudent.fxml"));
        Scene hoofdscene = new Scene(hoofdM);
        Stage hoofdmenu = (Stage) ((Node)event.getSource()).getScene().getWindow();
        hoofdmenu.setTitle("Hoofdmenu student");
        hoofdmenu.setScene(hoofdscene);
        hoofdmenu.centerOnScreen();
        hoofdmenu.setResizable(false);
        hoofdmenu.getIcons().add(new Image("AbsentieLijst/Footage/calendar.png"));
        hoofdmenu.show();
    }

    public void aanroepHoofdmenuDocent(ActionEvent event) throws IOException {
        Pane hoofdMD = FXMLLoader.load(getClass().getResource("/AbsentieLijst/userInterfaceLaag/HoofdmenuDocent.fxml"));
        Scene hoofdscene = new Scene(hoofdMD);
        Stage hoofdmenuD = (Stage) ((Node) event.getSource()).getScene().getWindow();
        hoofdmenuD.setTitle("Hoofdmenu Docent");
        hoofdmenuD.setResizable(false);;
        hoofdmenuD.setScene(hoofdscene);
        hoofdmenuD.getIcons().add(new Image("AbsentieLijst/Footage/calendar.png"));
        hoofdmenuD.show();

    }

    public void cancel() {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }
}



