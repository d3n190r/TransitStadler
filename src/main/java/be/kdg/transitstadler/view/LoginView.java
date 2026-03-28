package be.kdg.transitstadler.view;

import be.kdg.transitstadler.database.DatabaseConnector;
import be.kdg.transitstadler.view.utils.LayoutUtils;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginView extends VBox {
    private ComboBox<String> cbDatabase;

    private Label lblDatabase;
    private Label lblUsername;
    private Label lblPassword;

    private TextField tfUsername;
    private TextField tfPassword;

    private Button btnLogin;

    public LoginView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.setNodeMarkup();
    }

    private void initialiseNodes() {
        this.cbDatabase = new ComboBox<>();
        cbDatabase.getItems().addAll(DatabaseConnector.getDatabaseNames());

        this.lblDatabase = new Label("Database location:");
        this.lblUsername = new Label("Username:");
        this.lblPassword = new Label("Password:");

        this.tfUsername = new TextField();
        this.tfPassword = new TextField();

        this.btnLogin = new Button("Login");
    }

    private void layoutNodes() {
        this.getChildren().addAll(this.lblDatabase, this.cbDatabase, this.lblUsername, this.tfUsername, this.lblPassword, this.tfPassword, this.btnLogin);
    }

    private void setNodeMarkup() {
        this.lblDatabase.setMinWidth(200);
        this.lblUsername.setMinWidth(200);
        this.lblPassword.setMinWidth(200);
        this.tfUsername.setMinWidth(200);
        this.tfPassword.setMinWidth(200);

        LayoutUtils.applyMarginsToChildren(this, 5);
    }

    public ComboBox<String> getCbDatabase() {return cbDatabase;}

    public TextField getTfUsername() {return tfUsername;}

    public TextField getTfPassword() {return tfPassword;}

    public Button getBtnLogin() {return btnLogin;}
}
