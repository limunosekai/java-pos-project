package kg.fx.lim.user.view;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kg.fx.lim.MainApp;
import kg.fx.lim.login.view.DatabaseController;
import kg.fx.lim.model.Order;
import kg.fx.lim.model.OrderDetail;
import kg.fx.lim.model.Product;

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
	private Button methodBtn;
	@FXML
	private Label userName;
	@FXML
	private TableView<Product> information;
	@FXML
	private TableColumn<Product,Integer> tc_code;
	@FXML               
	private TableColumn<Product,String> tc_category;
	@FXML               
	private TableColumn<Product,String> tc_name;
	@FXML               
	private TableColumn<Product,Integer> tc_quantity;
	@FXML               
	private TableColumn<Product,Integer> tc_price;
	@FXML               
	private TableColumn<Product,Integer> tc_salePrice;
	@FXML               
	private TableColumn<Product,Integer> tc_discountRate;
	@FXML               
	private TableColumn<Product,Integer> tc_discount;
	@FXML
	private TextField totalQuantity;
	@FXML
	private TextField totalAmount;
	@FXML
	private TextField totalPrice;
	@FXML
	private TextField totaldiscount;
	@FXML
	private TextField totalAmountRight;
	@FXML
	private TextField card;
	@FXML
	private TextField cash;
	@FXML
	private TextField additionalPayment;
	@FXML
	private TextField exchange;
	private int result = 0;
	private int amount = 0;
	private ObservableList<Product> data = FXCollections.observableArrayList();
	private DecimalFormat formatter = new DecimalFormat("###,###");
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
	public void handleAddBtn() {
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
			
			// 다이얼로그 확인버튼 클릭시
			if(!controller.isOkClicked()) {
				return;
			} else {
				// DB에서 값 불러와서 테이블에 표시
				String name = controller.getSelectedItem();	
				DatabaseController db = new DatabaseController();
				Product product = (Product) db.loadProductByName(name);
				data.add(product);
				information.setItems(data);
				setTextField();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * -------------------------------------------------취소 버튼
	 */
	@FXML
	public void handleCancelBtn() {
		Product selectedItem = information.getSelectionModel().getSelectedItem();
		// 선택 안하고 버튼 눌렀을시 처리
		if(selectedItem == null) {
			return;
		}
		// 선택된 객체 삭제 후 테이블에 표시
		data.remove(selectedItem);
		information.setItems(data);
		setTextField();
		// 추가 결제금액 표시
		try {
			Number cashAmount = formatter.parse(cash.getText().equals("") ? "0" : cash.getText());
			Number cardAmount = formatter.parse(card.getText().equals("") ? "0" : card.getText());
			Number paymentAmount = formatter.parse(totalAmountRight.getText().equals("") ? "0" : totalAmountRight.getText());
			
			if((cashAmount.intValue() + cardAmount.intValue()) == 0) {
				additionalPayment.clear();
				return;
			} else {
				if((paymentAmount.intValue() - (cashAmount.intValue() + cardAmount.intValue())) < 0) {
					additionalPayment.clear();
					return;
				}
				if((paymentAmount.intValue() - (cashAmount.intValue() + cardAmount.intValue())) > 0) {
					additionalPayment.setText(String.valueOf(formatter.format(paymentAmount.intValue() - (cashAmount.intValue() + cardAmount.intValue()))));
					return;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * -------------------------------------------------초기화 버튼
	 */
	@FXML
	public void handleInitBtn() {
		initTextField();
		data.clear();
		information.setItems(data);
	}
	
	/**
	 * -------------------------------------------------판매관리 버튼
	 */
	@FXML
	public void handleSalesBtn() {
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
	public void handleInventoryBtn() {
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
	 * --------------------------------------------------결제방법 버튼
	 */
	@FXML
	public void handleMethodBtn() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("PaymentDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("결제");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			PaymentDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			dialogStage.showAndWait();
			
			// 결제 방법
			if(!controller.isOkClicked()) {// 취소 버튼 클릭시
				return;
			} else {// 카드,현금 버튼 클릭시
				try {
					// format 초기화, 값이 없을 경우 0으로 초기화
					Number cashAmount = formatter.parse(cash.getText().equals("") ? "0" : cash.getText());
					Number cardAmount = formatter.parse(card.getText().equals("") ? "0" : card.getText());
					Number paymentAmount = formatter.parse(totalAmountRight.getText().equals("") ? "0" : totalAmountRight.getText());
					
					if(controller.paymentMethod()) {// 현금 결제시
						amount = (int) controller.getAmount();
						cash.setText(String.valueOf(formatter.format(cashAmount.intValue() + amount)));
					} else { // 카드 결제시
						amount = (int) controller.getAmount();
						card.setText(String.valueOf(formatter.format(cardAmount.intValue() + amount)));
					}
					int temp = paymentAmount.intValue() - (cashAmount.intValue() + cardAmount.intValue() + amount);
					
					// 결제해야할 금액, 거스름돈 계산하여 화면에 표시
					if(temp > 0) {
						additionalPayment.setText(String.valueOf(formatter.format(temp)));
					} else if(temp < 0) {
						additionalPayment.clear();
						exchange.setText(String.valueOf(formatter.format(Math.abs(temp))));					
					} else {
						additionalPayment.clear();
						exchange.clear();
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * --------------------------------------------------결제 버튼
	 */
	@FXML
	public void handlePayBtn() {
		// 목록에 상품이 없을시
		if(data.size() == 0) {
			alertFail("결제 오류", "상품을 최소 1개 이상 선택해주세요.");
			return;
		}
		// 결제 다이얼로그창
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("PayConfirmDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("결제 확인");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			PayConfirmDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			dialogStage.showAndWait();
			
			// 결제 취소
			if (!controller.isOkClicked()) {
				alertFail("결제 취소", "결제가 취소되었습니다.");
				return;
			} else {
				DatabaseController db = new DatabaseController();
				// 반복문을 돌며 DB에 데이터 업데이트
				for (int i=0; i<data.size(); i++) {
					result = db.updateSoldProduct(data.get(i));
					//DB 검사
					if(result <= 0) {
						// 실패 메시지 출력
						alertFail("전산 등록 실패", "전산 등록에 실패했습니다.\n관리자에게 문의해주세요.\n전산실 이승재");
						break;
					}
				}
				
				// 주문 정보 저장
				Date date = new Date(); 
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
//				String currentTime = format.format( date );
//				Timestamp t = Timestamp.valueOf(currentTime);
				
				// -9시간
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.HOUR,-9);
				String time = format.format(cal.getTime());
				
				Order order = new Order();
				String name = userName.getText();
				order.setUserCode(db.loadUserCodeByName(name));
				try {
					Number amount = formatter.parse(totalAmount.getText());
					order.setTotalAmount(amount.intValue());
					order.setOrderDate(time);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				result = db.saveOrder(order);
				if(result <= 0 ) {
					alertFail("주문 등록 실패", "주문 등록에 실패했습니다.\n관리자에게 문의해주세요.\n전산실 이승재");
					return;
				}
				int orderNumber = 0;
				orderNumber = db.loadOrderNumber(time);
				// 주문 디테일 정보 저장
				for(int i=0; i<data.size(); i++) {
					OrderDetail detail = new OrderDetail();
					detail.setOrderNumber(orderNumber);
					detail.setOrderProductQuantity(data.get(i).getQuantity());
					detail.setProductCode(data.get(i).getCode());
					result = db.saveOrderDetail(detail);
					if(result <= 0 ) {
						break;
					}
				}
				if(result <= 0) {
					alertFail("주문상세 등록 실패", "주문상세 등록에 실패했습니다.\n관리자에게 문의해주세요.\n전산실 이승재");
					return;
				} else {
					// 결제완료창
					alertOk("결제 완료", "결제가 완료되었습니다.");
					// GUI, list 초기화
					data.clear();
					information.setItems(data);
					initTextField();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * --------------------------------------------------userName 화면 표시
	 */
	public void setTextField() {
		// 텍스트필드 화면에 표시
		int price = 0;
		int discount = 0;
		int salePrice = 0;
		int quantity = 0;
		// 누적 값 계산
		for(int i=0; i<data.size(); i++) {
			price += data.get(i).getPrice();
			if(data.get(i).getSalePrice() == 0) {
				salePrice += data.get(i).getPrice();
			}
			discount += data.get(i).getDiscount();
			salePrice += data.get(i).getSalePrice();
			quantity += data.get(i).getQuantity();
		}
		totalPrice.setText(String.valueOf(formatter.format(price)));
		totalAmount.setText(String.valueOf(formatter.format(salePrice)));
		totalQuantity.setText(String.valueOf(quantity));
		totaldiscount.setText(String.valueOf(formatter.format(discount)));
		totalAmountRight.setText(String.valueOf(formatter.format(salePrice)));
	}
	
	/**
	 * --------------------------------------------------userName 화면 표시
	 */
	public void setUserName(String id) {
		userName.setText(id);
	}
	
	/**
	 * ------------------------------------OK 알림창
	 */
	public void alertOk(String msg, String text) {
		Alert ok = new Alert(AlertType.INFORMATION);
		ok.setTitle(msg);
		ok.setHeaderText(msg);
		ok.setContentText(text);
		ok.showAndWait();
	}
	
	/**
	 * ------------------------------------ERROR 알림창
	 */
	public void alertFail(String msg, String text) {
		Alert fail = new Alert(AlertType.WARNING);
		fail.setTitle(msg);
		fail.setHeaderText(msg);
		fail.setContentText(text);
		fail.showAndWait();
	}
	
	/**
	 * ------------------------------------텍스트필드 초기화
	 */
	public void initTextField() {
		totalQuantity.clear();
		totalAmount.clear();
		totalPrice.clear();
		totaldiscount.clear();
		totalAmountRight.clear();
		card.clear();
		cash.clear();
		exchange.clear();
		additionalPayment.clear();
	}
	
	/**
	 * --------------------------------------------------초기화 : load 후 자동싷행
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tc_code.setCellValueFactory(new PropertyValueFactory<>("code"));
		tc_category.setCellValueFactory(new PropertyValueFactory<>("category"));
		tc_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		tc_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		tc_price.setCellValueFactory(new PropertyValueFactory<>("price"));
		tc_salePrice.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
		tc_discountRate.setCellValueFactory(new PropertyValueFactory<>("discountRate"));
		tc_discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
	}
}
