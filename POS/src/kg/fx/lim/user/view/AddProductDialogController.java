package kg.fx.lim.user.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class AddProductDialogController {
	// 멤버필드
	@FXML
	private Button okBtn;
	@FXML
	private Button cancelBtn;
	@FXML
	private ListView<String> lv;
	private Stage dialogStage;
	private boolean okClicked = false;
	
	// 생성자
	public AddProductDialogController() {
		
	}
	
	// 메소드
	public void handleOkBtn(ActionEvent e) {
		okClicked = true;
		dialogStage.close();
	}
	public void handleCancelBtn(ActionEvent e) {
		dialogStage.close();
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	/**
	 * 다이얼로그 스테이지 설정
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}
