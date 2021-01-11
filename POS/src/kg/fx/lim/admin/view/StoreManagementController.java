package kg.fx.lim.admin.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
---------------------------------------------------------------------------
* Nintendo POS 1.0
* StoreManagementController
* @author 임성현
---------------------------------------------------------------------------
*/

public class StoreManagementController {
	// -----------------------------------멤버필드
	@FXML
	private TableView<String> tv;
	@FXML
	private TextField StoreCode;
	@FXML
	private TextField StoreName;
	@FXML
	private TextField StoreId;
	@FXML
	private TextField StorePassword;
	@FXML
	private TextField StoreTel;
	@FXML
	private TextField StoreAddress;
	@FXML
	private Button enrollBtn;
	@FXML
	private Button editBtn;
	@FXML
	private Button deleteBtn;

	// ------------------------------------생성자
	public StoreManagementController() {

	}

	// 메소드
	/**
	 * ------------------------------------등록 버튼
	 */
	public void handleEnrollBtn(ActionEvent e) {
		
	}
	
	/**
	 * ------------------------------------수정 버튼
	 */
	public void handleEditBtn(ActionEvent e) {
		
	}
	
	/**
	 * ------------------------------------삭제 버튼
	 */
	public void handleDeleteBtn(ActionEvent e) {
		
	}
}
