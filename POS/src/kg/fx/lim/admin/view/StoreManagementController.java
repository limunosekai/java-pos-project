package kg.fx.lim.admin.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import kg.fx.lim.login.view.DatabaseController;
import kg.fx.lim.model.User;

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
	private TableView<User> tv;
	@FXML
	private TableColumn<User,Integer> tc_code;
	@FXML
	private TableColumn<User,String> tc_name;
	@FXML
	private TableColumn<User,String> tc_id;
	@FXML
	private TableColumn<User,String> tc_passwd;
	@FXML
	private TableColumn<User,String> tc_tel;
	@FXML
	private TableColumn<User,String> tc_address;
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
	private int result = 0;

	// ------------------------------------생성자
	public StoreManagementController() {

	}

	// 메소드
	/**
	 * ------------------------------------등록 버튼
	 */
	public void handleEnrollBtn(ActionEvent e) {
		
		// Input 유효성 검사
		if(isInputValid()) {
			// 사용자가 입력한 값들 변수에 담기
			int code = Integer.parseInt(StoreCode.getText());
			String name = StoreName.getText();
			String id = StoreId.getText();
			String passwd = StorePassword.getText();
			String tel = StoreTel.getText();
			String address = StoreAddress.getText();
			
			// user 객체 생성
			User user = new User();
			user.setCode(code);
			user.setName(name);
			user.setId(id);
			user.setPasswd(passwd);
			user.setTel(tel);
			user.setAddress(address);
			
			// 데이터베이스 컨트롤러 객체 생성
			DatabaseController db = new DatabaseController();
			
			// DB에 저장
			result = db.saveUserData(user);
			
			// DB유효성 검사
			if(result > 0) {
				Alert ok = new Alert(AlertType.INFORMATION);
				ok.setTitle("등록 성공");
				ok.setHeaderText("등록 성공");
				ok.setContentText("등록에 성공했습니다.");
				ok.showAndWait();
			} else {
				Alert fail = new Alert(AlertType.ERROR);
				fail.setTitle("등록 실패");
				fail.setHeaderText("등록 실패");
				fail.setContentText("등록에 실패했습니다.\n관리자에게 문의해주세요.\n전산실 이승재");
				fail.showAndWait();
			}
		} 
		// 초기화
		initTextField();
	}
	
	/**
	 * ------------------------------------수정 버튼
	 */
	public void handleEditBtn(ActionEvent e) {
		
		// 사용자가 입력한 값들 변수에 담기
		int code = Integer.parseInt(StoreCode.getText());
		String name = StoreName.getText();
		String id = StoreId.getText();
		String passwd = StorePassword.getText();
		String tel = StoreTel.getText();
		String address = StoreAddress.getText();
		
		// user 객체 생성
		User user = new User();
		user.setCode(code);
		user.setName(name);
		user.setId(id);
		user.setPasswd(passwd);
		user.setTel(tel);
		user.setAddress(address);
	}
	
	/**
	 * ------------------------------------삭제 버튼
	 */
	public void handleDeleteBtn(ActionEvent e) {
		
	}
	
	/**
	 * ------------------------------------테이블뷰 정보 표시
	 */
	public void showTableView() {
//		tc_code.setCellValueFactory(cellData -> cellData.);
	}
	
	/**
	 * ------------------------------------텍스트필드 초기화
	 */
	public void initTextField() {
		StoreCode.setText("");
		StoreName.setText("");
		StoreId.setText("");
		StorePassword.setText("");
		StoreTel.setText("");
		StoreAddress.setText("");
	}
	
	/**
	 * ------------------------------------유효성 검사
	 */
	private boolean isInputValid() {
		String errorMessage = "";
		
		if(StoreCode.getText() == null || StoreCode.getText().length() == 0) {
			errorMessage += "매장 번호란이 비어있습니다.\n";
		}
		if(StoreName.getText() == null || StoreName.getText().length() == 0) {
			errorMessage += "매장 이름란이 비어있습니다.\n";
		}
		if(StoreId.getText() == null || StoreId.getText().length() == 0) {
			errorMessage += "매장 ID란이 비어있습니다.\n";
		}
		if(StorePassword.getText() == null || StorePassword.getText().length() == 0) {
			errorMessage += "매장 비밀번호란이 비어있습니다\n";
		} 
		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}
	
	
}
