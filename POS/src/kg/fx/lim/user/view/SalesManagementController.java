package kg.fx.lim.user.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kg.fx.lim.MainApp;

/**
---------------------------------------------------------------------------
* Nintendo POS 1.0
* SalesManagementController
* @author 임성현
---------------------------------------------------------------------------
*/

public class SalesManagementController {
	// ----------------------------------------멤버필드
	@FXML
	private Button logoutBtn;
	@FXML
	private Button homeBtn;
	@FXML
	private TextField todaySales;
	@FXML
	private LineChart<String,Integer> lc;
	@FXML
	private TableView<String> tv;
	
	// -----------------------------------------생성자
	public SalesManagementController() {
	}
	
	// 메소드
	/**
	 * -----------------------------------------로그아웃
	 */
	@FXML
	public void handleLogoutBtn(ActionEvent e) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("login/view/RootLayout.fxml"));					
			AnchorPane rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			Stage salesStage = (Stage) logoutBtn.getScene().getWindow();
			salesStage.setScene(scene);
			salesStage.setTitle("Login");
			salesStage.setResizable(false);
			salesStage.show();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	/**
	 * -----------------------------------------홈버튼
	 */
	@FXML
	public void handleHomeBtn(ActionEvent e) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("userLayout.fxml"));
			Scene scene = new Scene(root);
			Stage salesStage = (Stage) homeBtn.getScene().getWindow();
			salesStage.setScene(scene);
			salesStage.show();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
