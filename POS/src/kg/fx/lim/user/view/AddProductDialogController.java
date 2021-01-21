package kg.fx.lim.user.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import kg.fx.lim.common.view.DatabaseController;

/**
---------------------------------------------------------------------------
* Nintendo POS 1.0
* AddProductDialogController
* @author 임성현
---------------------------------------------------------------------------
*/

public class AddProductDialogController implements Initializable{
	// ------------------------------------------멤버필드
	@FXML
	private Button okBtn;
	@FXML
	private Button cancelBtn;
	@FXML
	private ListView<String> lv;
	private Stage dialogStage;
	private boolean okClicked = false;
	private ObservableList<String> data;
	private String selectedItem = "";
	
	// ------------------------------------------생성자
	public AddProductDialogController() {
	}
	
	// ------------------------------------------메소드
	public String handleOkBtn() {
		selectedItem = lv.getSelectionModel().getSelectedItem();
		try {
			// 유효성 검사
			if (!selectedItem.isEmpty() && selectedItem.length() != 0) {
				okClicked = true;
				dialogStage.close();
			}
		} catch (NullPointerException e) { // 선택 안하고 확인 눌렀을시 처리
			Alert error = new Alert(AlertType.WARNING);
			error.setTitle("WARNING");
			error.setHeaderText("상품 선택 오류");
			error.setContentText("상품을 선택하고 눌러주세요.");
			error.showAndWait();
			okClicked = false;
		}
		return selectedItem;
	}
	public void handleCancelBtn() {
		dialogStage.close();
		okClicked = false;
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	/**
	 * --------------------------------------다이얼로그 스테이지 설정
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public String getSelectedItem() {
		return selectedItem;
	}
	
	/**
	 * -------------------------------------------초기화
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DatabaseController db = new DatabaseController();
		ArrayList<String> productList = db.loadAllProductName();
		data = FXCollections.observableArrayList(productList);
		lv.setItems(data);
	}
}
