package kg.fx.lim.user.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kg.fx.lim.MainApp;

/**
---------------------------------------------------------------------------
* Nintendo POS 1.0
* InventoryManagementController
* @author 임성현
---------------------------------------------------------------------------
*/

public class InventoryManagementController {
	// ----------------------------------------멤버필드
	@FXML
	private Button logoutBtn;
	@FXML
	private Button homeBtn;
	@FXML
	private Button searchBtn;
	@FXML
	private Button resetBtn;
	@FXML
	private Button storeName;
	@FXML
	private TextField searchBar;
	@FXML
	private TableView<String> tv;
	
	// -----------------------------------------생성자
	public InventoryManagementController() {
		
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
			Stage invenStage = (Stage) logoutBtn.getScene().getWindow();
			invenStage.setScene(scene);
			invenStage.setTitle("Login");
			invenStage.setResizable(false);
			invenStage.show();
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
			Stage invenStage = (Stage) homeBtn.getScene().getWindow();
			invenStage.setScene(scene);
			invenStage.show();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * -----------------------------------------검색버튼
	 */
	@FXML
	public void handleSearchBtn(ActionEvent e) {
		
	}
	
	/**
	 * -----------------------------------------초기화버튼
	 */
	@FXML
	public void handleResetBtn(ActionEvent e) {
		
	}
	
	/**
	 * --------------------------------------------------userName 화면 표시
	 */
	public void setUserName(String name) {
		storeName.setText(name);
	}
}
