package kg.fx.lim.login.view;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import kg.fx.lim.MainApp;
import kg.fx.lim.user.view.PosController;

/**
 * ---------------------------------------------------------------------------
 * Nintendo POS 1.0 
 * LoginController
 * @author 임성현
 * ---------------------------------------------------------------------------
 */

public class LoginController {
	// ---------------------------------------멤버필드
	@FXML
	private TextField userNameField;
	@FXML
	private PasswordField passWordField;
	@FXML
	private Button loginBtn;
	private String userId;
	private String password;
	private BorderPane adminLayout;
	private AnchorPane userLayout;
	private AnchorPane homePage;
	private MainApp mainApp;

	// ---------------------------------------생성자
	public LoginController() {
	}

	// 메소드
	/**
	 * --------------------------------------로그인 버튼 클릭시
	 */
	@FXML
	private void handleLogin(ActionEvent e) throws SQLException {

		// id 입력 오류시
		if (userNameField.getText().isEmpty()) {
			Alert idFail = new Alert(AlertType.WARNING);
			idFail.setTitle("Failed to access");
			idFail.setHeaderText("ID 입력오류");
			idFail.setContentText("ID를 입력해주세요");
			idFail.showAndWait();
			return;
		}
		// password 입력 오류시
		if (passWordField.getText().isEmpty()) {
			Alert passwdFail = new Alert(AlertType.WARNING);
			passwdFail.setTitle("Failed to access");
			passwdFail.setHeaderText("비밀번호 입력오류");
			passwdFail.setContentText("비밀번호를 입력해주세요");
			passwdFail.showAndWait();
			return;
		}

		userId = userNameField.getText();
		password = passWordField.getText();

		DatabaseController db = new DatabaseController();

		// DB에서 입력한 id에 맞는 password 가져오기
		String DB_password = db.loadPassword(userId);
		
		// 기존 스테이지 가져오기
		Stage primaryStage = (Stage) loginBtn.getScene().getWindow();
		// password 검사
		if (password.equals(DB_password)) {
			// admin 사용자 로그인시 화면전환
			if (userId.equals("admin")) {
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(MainApp.class.getResource("admin/view/MainViewLayout.fxml"));
					adminLayout = (BorderPane) loader.load();
					FXMLLoader home = new FXMLLoader();
					home.setLocation(MainApp.class.getResource("admin/view/HomePage.fxml"));
					homePage = (AnchorPane) home.load();
					adminLayout.setCenter(homePage);
					Scene scene = new Scene(adminLayout);
					Stage adminStage = new Stage();
					adminStage.setTitle("Administrator");
					adminStage.setResizable(false);
					adminStage.setScene(scene);
					adminStage.show();
					primaryStage.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			} else { // 일반 사용자 로그인시 화면전환
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(MainApp.class.getResource("user/view/UserLayout.fxml"));
					userLayout = (AnchorPane) loader.load();
					Scene scene = new Scene(userLayout);
					Stage userStage = new Stage();
					userStage.setTitle(userId);
					userStage.setResizable(false);
					
					// 이름 표시
					DatabaseController db2 = new DatabaseController();
					PosController controller = loader.getController();
					String DB_userName = db2.loadUserName(userId);
					controller.setUserName(DB_userName);
					
					userStage.setScene(scene);
					userStage.show();
					primaryStage.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		} else {
			Alert fail = new Alert(AlertType.ERROR);
			fail.setHeaderText("로그인 실패");
			fail.setContentText("아이디 또는 비밀번호를 확인해주세요.");
			fail.showAndWait();
		}
	}

	/**
	 * ----------------------------------------참조를 유지하기 위해 MainApp 호출
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * ----------------------------------------userId 반환
	 */
	public String getUserId() {
		return userId;
	}

}
