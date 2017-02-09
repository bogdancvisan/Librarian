package vcb.librarianfx;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp
        extends Application
{

    @Override
    public void start(final Stage stage) throws Exception
    {
        Scene scene1;
        scene1 = new Scene((Parent) loginPage(), 500, 200);
        stage.setTitle("Library Management System");
        stage.setScene(scene1);
        stage.show();
    }

    public Node loginPage() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLLoginSelection.fxml"));
        Parent root = loader.load();
        return root;
    }

    public Node adminLogin() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAdmin.fxml"));
        Parent root = loader.load();
        return root;
    }

    public static void main(String[] args)
    {

        //JDBCLibrary newJdbc = new JDBCLibrary();
        //newJdbc.getConnection();
       // System.out.println(newJdbc.getAdminPass() + " " + newJdbc.getAdminUser());
        launch(args);
        
        
       // newJdbc.closeConnection();
    }
}
