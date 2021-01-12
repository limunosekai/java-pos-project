package kg.fx.lim.user.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kg.fx.lim.MainApp;

/**
---------------------------------------------------------------------------
* Nintendo POS 1.0
* PosController
* @author 임성현
---------------------------------------------------------------------------
*/

public class PosController implements Initializable {
	// -------------------------------------------멤버필드
	@FXML
	private Button logoutBtn;
	@FXML
	private Button addBtn;
	@FXML
	private Button cancelBtn;
	@FXML
	private Button initBtn;
	@FXML
	private Button salesBtn;
	@FXML
	private Button inventoryBtn;
	@FXML
	private Button payBtn;
	@FXML
	private Label userName;
	@FXML
	private TableView<String> information;
//	@FXML
//	private TableColumn<String> firstNameColumn;
//	@FXML
//	private TableColumn<String> lastNameColumn;
	@FXML
	private TableView<String> price;
	@FXML
	private TextField totalQuantity;
	@FXML
	private TextField totalAmount;
	
	// ---------------------------------------------생성자
	public PosController() {
	}
	// 메소드
	/**
	 * ---------------------------------------------로그아웃 기능
	 */
	@FXML
	public void handleLogoutBtn(ActionEvent e) {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("login/view/RootLayout.fxml"));					
			AnchorPane rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			Stage posStage = (Stage) logoutBtn.getScene().getWindow();
			posStage.setScene(scene);
			posStage.setTitle("Login");
			posStage.setResizable(false);
			posStage.show();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * ----------------------------------------------추가 버튼
	 */
	@FXML
	public boolean handleAddBtn() {
		// 다이얼로그 호출
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("AddProductDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("추가");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			AddProductDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	/**
	 * -------------------------------------------------취소 버튼
	 */
	@FXML
	public void handleCancelBtn() {
		
	}
	
	/**
	 * -------------------------------------------------초기화 버튼
	 */
	@FXML
	public void handleInitBtn() {
		information.getItems().clear();
	}
	
	/**
	 * -------------------------------------------------판매관리 버튼
	 */
	@FXML
	public void handleSalesBtn(ActionEvent e) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("SalesManagementLayout.fxml"));
			Scene scene = new Scene(root);
			Stage userStage = (Stage) salesBtn.getScene().getWindow();
			userStage.setScene(scene);
			userStage.setTitle("판매 관리");
			userStage.setResizable(false);
			userStage.show();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * -------------------------------------------------재고관리 버튼
	 */
	@FXML
	public void handleInventoryBtn(ActionEvent e) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("InventoryManagementLayout.fxml"));
			Scene scene = new Scene(root);
			Stage userStage = (Stage) salesBtn.getScene().getWindow();
			userStage.setScene(scene);
			userStage.setTitle("재고 관리");
			userStage.setResizable(false);
			userStage.show();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * --------------------------------------------------결제 버튼
	 */
	@FXML
	public void handlePayBtn(ActionEvent e) {
		// 결제완료창
		Alert pay = new Alert(AlertType.INFORMATION);
		pay.setTitle("결제 완료");
		pay.setContentText("결제가 완료되었습니다.");
		pay.showAndWait();
	}
	
	/**
	 * --------------------------------------------------userName 화면 표시
	 */
	public void setUserName(String id) {
		userName.setText(id);
	}
	
	/**
	 * --------------------------------------------------초기화 : load 후 자동싷행
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
}
