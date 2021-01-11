package kg.fx.lim.admin.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
---------------------------------------------------------------------------
* Nintendo POS 1.0
* InventoryManagementController
* @author 임성현
---------------------------------------------------------------------------
*/

public class InventoryManagementController {
	// -----------------------------------------멤버필드
	@FXML
	private TableView<String> tv;
	@FXML
	private TextField productCode;
	@FXML
	private TextField productName;
	@FXML
	private TextField productQuantity;
	@FXML
	private TextField productPrice;
	@FXML
	private TextField productSalePrice;
	@FXML
	private Button enrollBtn;
	@FXML
	private Button editBtn;
	@FXML
	private Button deleteBtn;
	@FXML
	private ChoiceBox<String> category;
	
	// -------------------------------------------생성자
	public InventoryManagementController() {
		
	}
	
	// -------------------------------------------메소드
	@FXML
	public void handleEnrollBtn() {
		
	}
	@FXML
	public void handleEditBtn() {
		
	}
	@FXML
	public void handleDeleteBtn() {
		
	}
}
