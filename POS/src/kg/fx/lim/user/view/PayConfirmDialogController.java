package kg.fx.lim.user.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PayConfirmDialogController {
	// 멤버필드
	@FXML
	private Button okBtn;
	@FXML
	private Button cancelBtn; 
	private boolean okClicked = false;
	private Stage dialogStage;
	
	// 생성자
	public PayConfirmDialogController() {
		
	}
	
	// 메소드
	/**
	 * --------------------------------------취소 버튼 클릭시
	 */
	@FXML
	public void handleCancelBtn() {
		okClicked = false;
		dialogStage.close();
	}
	
	/**
	 * --------------------------------------확인 버튼 클릭시
	 */
	@FXML
	public void handleOkBtn() {
		okClicked = true;
		dialogStage.close();
	}
	
	/**
	 * --------------------------------------ok/cancel check
	 */
	public boolean isOkClicked() {
		return okClicked;
	}
	
	/**
	 * --------------------------------------다이얼로그 스테이지 설정
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}
