package Main.java.controller;

import Main.bdd.DatabaseSingleton;
import Main.java.models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import static Main.java.constantes.SQLConstants.SELECTEMPLOYEE;

public class AdminController implements Initializable {
    @FXML
    public TableColumn NAME_user, FIRSTNAME_user, BADGE_user, ADRESSE_user, DATEBIRTH_user, DATEHIRING_user, NUMTEL_user, ID_user, ISADMIN_user;
    Stage stage;
    Scene scene;
    @FXML
    private Pane pnl_stocks, pnl_users, pnl_money, pnl_product;
    @FXML
    private ImageView img_stock, img_users, img_money, img_product;
    @FXML
    private Button btn_load;
    @FXML
    private TableView dataTB;
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private ObservableList<Employee> data = FXCollections.observableArrayList();

    @FXML
    private void handleButtonAction(MouseEvent mouseDragEvent) {
        if (mouseDragEvent.getSource() == img_stock) {
            pnl_stocks.toFront();
            System.out.println("Test bien appuyé");
        } else if (mouseDragEvent.getSource() == img_money) {
            pnl_money.toFront();
        } else if (mouseDragEvent.getSource() == img_users) {
            pnl_users.toFront();
        } else if (mouseDragEvent.getSource() == img_product) {
            pnl_product.toFront();
        }
    }


    public void MappingLogout(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main/resources/Views/login_page.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDataCell();
        try {
            loadData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void setDataCell() {
        ID_user.setCellValueFactory(new PropertyValueFactory<>("Id"));
        NAME_user.setCellValueFactory(new PropertyValueFactory<>("Name"));
        FIRSTNAME_user.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        BADGE_user.setCellValueFactory(new PropertyValueFactory<>("Badge"));
        ADRESSE_user.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        DATEBIRTH_user.setCellValueFactory(new PropertyValueFactory<>("DateBirth"));
        DATEHIRING_user.setCellValueFactory(new PropertyValueFactory<>("DateEmbauche"));
        NUMTEL_user.setCellValueFactory(new PropertyValueFactory<>("NumTel"));
        ISADMIN_user.setCellValueFactory(new PropertyValueFactory<>("IsAdmin"));
    }


    private void loadData() throws SQLException {
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        db.connect();
        ps = db.prepareStatement(SELECTEMPLOYEE);
        rs = ps.executeQuery();
        while (rs.next()) {
            data.add(new Employee(rs.getInt("ID"), rs.getString("NAME"), rs.getString("FIRSTNAME"), rs.getString("BADGE"),
                    rs.getString("ADRESSE"), rs.getString("DATEBIRTH"), rs.getString("DATEHIRING"), rs.getString("NUMTEL"),
                    rs.getBoolean("ISADMIN")));
        }
        dataTB.setItems(data);
        System.out.println("DATA" + data.get(0).toString());
        System.out.println("DATA" + data.get(1).toString());
    }
}