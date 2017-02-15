package vcb.librarianfx;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLController
        implements Initializable
{

    static JDBCLibrary jdbc = new JDBCLibrary();
    Stage stage = null;
    Parent root = null;
    Scene scene = null;

    @FXML
    private Label nologin, messageLabelAddLib;

    @FXML
    private Button buttonLibrarian, buttonAdmin, backBtn, loginBtn; //buttons for Select and Login Scenes

    @FXML
    private Button buttonAddLib, buttonViewLib, buttonDeleteLib, buttonExitAO; // buttons for Admin Options Scene

    @FXML
    private Button buttonAddLib1, buttonBackAddLib; // buttons for Add Librarian scene

    @FXML
    private void handleButtonLibrarian(ActionEvent event) throws IOException
    {
        if (event.getSource() == buttonLibrarian) {
            System.out.println("Continuing as Librarian...");
            getFrame(buttonLibrarian, "FXMLLibrarian.fxml", "Librarian Login");
        }
        if (event.getSource() == backBtn) {
            System.out.println("Going back...");
            getFrame(backBtn, "FXMLLoginSelection.fxml", "Library Management System");
        }
        getScene();
    }

    @FXML
    private void handleButtonAdmin(ActionEvent event) throws IOException, SQLException
    {
        if (event.getSource() == buttonAdmin) {
            System.out.println("Continuing as Admin...");
            getFrame(buttonAdmin, "FXMLAdmin.fxml", "Admin Login");
        }
        if (event.getSource() == backBtn) {
            System.out.println("Going back...");
            getFrame(backBtn, "FXMLLoginSelection.fxml", "Library Management System");
        }
        getScene();
    }

    @FXML
    TextField userinput, userAddLib, emailAddLib, addressAddLib, cityAddLib, contactAddLib;

    @FXML
    PasswordField passinput, passAddLib;

    @FXML
    private void adminLogin(ActionEvent event) throws IOException, SQLException
    {
        String user = userinput.getCharacters().toString();
        String pass = passinput.getCharacters().toString();

        jdbc.getConnection();

        if (jdbc.getAdminPass() == null || jdbc.getAdminUser() == null) {
            nologin.setText("Please enter user and password!");

        }
        else if (user.equals(jdbc.getAdminUser()) && pass.equals(jdbc.getAdminPass())) {
            System.out.println("Continue to admin options...");
            getFrame(loginBtn, "FXMLAdminOptions.fxml", "Admin Options");
            getScene();
        }
        else {
            nologin.setText("User/password missing/incorect!");
            userinput.clear();
            passinput.clear();
        }
        jdbc.closeConnection();
    }

    @FXML
    private void handleAdminOption(ActionEvent event) throws IOException
    {
        if (event.getSource() == buttonExitAO) {
            System.out.println("Exiting to Login Selection...");
            getFrame(buttonExitAO, "FXMLLoginSelection.fxml", "Library Management System");
        }
        if (event.getSource() == buttonAddLib) {
            System.out.println("Continuing to add librarian...");
            getFrame(buttonAddLib, "FXMLAddLibrarian.fxml", "Add Librarian");
        }
        getScene();
    }

    @FXML
    private void handleAddLibrarian(ActionEvent event) throws IOException
    {
        String user, pass, email, address, city, contact;
        jdbc.getConnection();
        if (event.getSource() == buttonAddLib1) {
            stage = (Stage) buttonAddLib1.getScene().getWindow();
            user = userAddLib.getCharacters().toString().trim();
            pass = passAddLib.getCharacters().toString().trim();
            email = emailAddLib.getCharacters().toString().trim();
            address = addressAddLib.getCharacters().toString().trim();
            city = cityAddLib.getCharacters().toString().trim();
            contact = contactAddLib.getCharacters().toString().trim();
            if (user.isEmpty() || pass.isEmpty() || email.isEmpty()) {
                messageLabelAddLib.setText("Complete the required fields(*)!");
            }
            else {
                jdbc.addLibrarianToDB(user, pass, email, address, city, contact);
                messageLabelAddLib.setText("Librarian Added Successfully!");
                System.out.println("Librarian added...");
            }
        }
        jdbc.closeConnection();
        if (event.getSource() == buttonBackAddLib) {
            getFrame(buttonBackAddLib, "FXMLAdminOptions.fxml", "Add Librarian");
            getScene();
        }
    }

    private void getScene()
    {
        scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.show();
    }

    private void getFrame(Button button, String resource, String title) throws IOException
    {
        stage = (Stage) button.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(resource));
        stage.setTitle(title);
    }

    @FXML
    private void handleDeleteLibrarian(ActionEvent event) throws IOException
    {

    }

    @FXML
    private void handleViewLibrarians(ActionEvent event) throws IOException
    {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }
}
