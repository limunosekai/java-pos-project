package kg.fx.lim.user.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PaymentAmountDialogController {

	// 멤버필드
	@FXML
	private Button okBtn;
	@FXML
	private Button cancelBtn;
	@FXML
	private TextField inputAmount;
	private Stage dialogStage;
	private int amount = 0;
	private boolean okClicked = false;
	// 생성자
	
	// 메소드
	/**
	 * --------------------------------------취소 버튼 클릭시
	 */
	@FXML
	public void handleCancelBtn() {
		amount = 0;
		okClicked = false;
		dialogStage.close();
	}
	
	/**
	 * --------------------------------------확인 버튼 클릭시
	 */
	@FXML
	public int handleOkBtn() {
		amount = 0;
		if (inputAmount.getText().equals(null) || inputAmount.getText().length() == 0) {
			Alert error = new Alert(AlertType.WARNING);
			error.setTitle("결제금액 입력오류");
			error.setHeaderText("결제금액을 입력하세요");
			error.setContentText("결제금액을 입력하세요.");
			error.showAndWait();
			okClicked = false;
			return 0;
		}
		amount = Integer.parseInt(inputAmount.getText());
		okClicked = true;
		dialogStage.close();
		return amount;
	}
	
	/**
	 * --------------------------------------get amount
	 */
	public int getAmount() {
		return amount;
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
