package kg.fx.lim.user.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PaymentDialogController {
	// 멤버필드
	@FXML
	private Button cardBtn;
	@FXML
	private Button cashBtn;
	@FXML
	private Button cancelBtn;
	private Stage dialogStage;
	private boolean okClicked = false;
	// false 카드결제, true 현금결제
	private boolean paymentMethod = false;
	private int amount = 0;
	// 생성자
	
	// 메소드
	/**
	 * --------------------------------------취소 버튼 클릭시
	 */
	@FXML
	public void handleCancelBtn() {
		dialogStage.close();
		okClicked = false;
	}
	
	/**
	 * --------------------------------------카드 버튼 클릭시
	 */
	@FXML
	public int handleCardBtn() {
		amount = 0;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("PaymentAmountDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("결제 금액 입력");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			PaymentAmountDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			dialogStage.showAndWait();
			
			amount = (int) controller.handleOkBtn();
			
			if (!controller.isOkClicked()) {
				okClicked = false;
				paymentMethod = false;
				return 0;
			} else {
				amount = controller.getAmount();
				okClicked = true;
				paymentMethod = false;
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		dialogStage.close();
		return amount;
	}
	
	/**
	 * --------------------------------------현금 버튼 클릭시
	 */
	@FXML
	public int handleCashBtn() {
		amount = 0;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("PaymentAmountDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("결제 금액 입력");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			PaymentAmountDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			dialogStage.showAndWait();
			
			if (!controller.isOkClicked()) {
				okClicked = false;
				paymentMethod = false;
				return 0;
			} else {
				amount = controller.getAmount();
				okClicked = true;
				paymentMethod = true;
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		dialogStage.close();
		return amount;
	}
	
	/**
	 * --------------------------------------ok/cancel check
	 */
	public boolean isOkClicked() {
		return okClicked;
	}
	
	/**
	 * --------------------------------------get amount
	 */
	public int getAmount() {
		return amount;
	}
	
	/**
	 * --------------------------------------결제 방법 선택
	 */
	public boolean paymentMethod() {
		return paymentMethod;
	}
	
	/**
	 * --------------------------------------다이얼로그 스테이지 설정
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
}
