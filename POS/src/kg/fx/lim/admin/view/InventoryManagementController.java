package kg.fx.lim.admin.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import kg.fx.lim.login.view.DatabaseController;
import kg.fx.lim.model.Product;

/**
---------------------------------------------------------------------------
* Nintendo POS 1.0
* InventoryManagementController
* @author 임성현
---------------------------------------------------------------------------
*/

public class InventoryManagementController implements Initializable{
	// -----------------------------------------멤버필드
	@FXML
	private TableView<Product> tv;
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
	private int result = 0;
	private ObservableList<Product> data;
	
	// -------------------------------------------생성자
	public InventoryManagementController() {
	}
	
	// 메소드
	/**
	 * -------------------------------------------등록 버튼 클릭시
	 */
	@FXML
	public void handleEnrollBtn() {
		// Input 유효성 검사
		if(isInputValid()) {
			// 데이터베이스 컨트롤러 객체 생성
			DatabaseController db = new DatabaseController();
			
			// 사용자가 입력한 값들 변수에 담기
			int code = Integer.parseInt(productCode.getText());
			String selectedCategory = category.getSelectionModel().getSelectedItem();
			String name = productName.getText();
			int quantity = Integer.parseInt(productQuantity.getText());
			int price = Integer.parseInt(productPrice.getText());
			int salePrice = Integer.parseInt(productSalePrice.getText());
			int discountRate = Math.round(((float)(price-salePrice)/price)*100);
			int discount = price - salePrice;
			if(salePrice == 0) {
				discount = 0;
				discountRate = 0;
			}
			// 중복 검사
			if(!db.duplicateProductCodeCheck(code)) {
				// product 객체 생성
				Product product = new Product();
				product.setCode(code);
				product.setCategory(selectedCategory);
				product.setName(name);
				product.setQuantity(quantity);
				product.setPrice(price);
				product.setSalePrice(salePrice);
				product.setDiscountRate(discountRate);
				product.setDiscount(discount);
				
				// DB에 저장
				result = db.saveProductData(product);
				
				// DB 검사
				if(result > 0) {
					// 테이블뷰에 추가
					data.add(product);
					tv.setItems(data);
					// 성공 메시지 출력
					alertOk("등록 성공", "등록에 성공했습니다.");
				} else {
					// 실패 메시지 출력
					alertFail("등록 실패", "등록에 실패했습니다.\n관리자에게 문의해주세요.\n전산실 이승재");
				}	
			} else {
				alertFail("상품번호 중복", "상품번호가 중복됩니다.\n다시 입력해주세요");
			}
		} 
		// 초기화
		initTextField();
	}
	
	/**
	 * ------------------------------------------------수정 버튼 클릭시
	 */
	@FXML
	public void handleEditBtn() {
		
		// Input 유효성 검사
		if(isInputValid()) {
			// 데이터베이스 컨트롤러 객체 생성
			DatabaseController db = new DatabaseController();
			
			// 사용자가 입력한 값들 변수에 담기
			int code = Integer.parseInt(productCode.getText());
			String selectedCategory = category.getSelectionModel().getSelectedItem();
			String name = productName.getText();
			int quantity = Integer.parseInt(productQuantity.getText());
			int price = Integer.parseInt(productPrice.getText());
			int salePrice = Integer.parseInt(productSalePrice.getText());
			int discountRate = Math.round(((float)(price - salePrice) / price) * 100);
			if(salePrice == 0) {
				discountRate = 0;
			}	
			int discount = price - salePrice;
			if(salePrice == 0) {
				discount = 0;
			}
			
			// product 객체 생성
			Product product = new Product();
			product.setCode(code);
			product.setCategory(selectedCategory);
			product.setName(name);
			product.setQuantity(quantity);
			product.setPrice(price);
			product.setSalePrice(salePrice);
			product.setDiscount(discount);
			product.setDiscountRate(discountRate);
			
			// DB에 저장
			result = db.updateProductData(product);
			
			// DB 검사
			if(result > 0) {
				// 테이블뷰에 추가
				int index = tv.getSelectionModel().getSelectedIndex();
				data.set(index, product);
				tv.setItems(data);
				// 성공 메시지 출력
				alertOk("수정 성공", "수정에 성공했습니다.");
			} else {
				// 실패 메시지 출력
				alertFail("수정 실패", "수정에 실패했습니다.\n관리자에게 문의해주세요.\n전산실 이승재");
			}	
		} else {
			alertFail("상품번호 중복", "상품번호가 중복됩니다.\n다시 입력해주세요");
		}
		// 초기화
		initTextField();
	}
	
	/**
	 * ------------------------------------------------삭제 버튼 클릭시
	 */
	@FXML
	public void handleDeleteBtn() {
		int index = tv.getSelectionModel().getSelectedIndex();
		Product delProduct = data.get(index);
		
		DatabaseController db = new DatabaseController();
		int result = db.deleteProductData(delProduct);
		if (result > 0) {
			data.remove(index);
			alertOk("삭제 성공", "삭제에 성공했습니다.");
		} else {
			alertFail("삭제 실패", "삭제에 실패했습니다./n관리자에게 문의해주세요.\n전산실 이승재");
		}
		initTextField();
	}
	
	/**
	 * ------------------------------------테이블 클릭시
	 */
	@FXML
	public void tableClick() {
		productCode.setEditable(false);
		try {
			// 선택한 셀의 인덱스 가져오기
			int index = tv.getSelectionModel().getSelectedIndex();
			
			// 인덱스의 데이터값 화면에 출력
			Product product = data.get(index);
			productCode.setText(String.valueOf(product.getCode()));;
			category.getSelectionModel().select(product.getCategory());
			productName.setText(product.getName());
			productQuantity.setText(String.valueOf(product.getQuantity()));
			productPrice.setText(String.valueOf(product.getPrice()));
			productSalePrice.setText(String.valueOf(product.getSalePrice()));
		} catch (IndexOutOfBoundsException e) {
			alertFail("영역선택 범위초과", "리스트의 값을 선택하세요.");
		}
	}
	
	/**
	 * ------------------------------------테이블에 데이터세팅
	 */
	public void setTableViewData() {
		DatabaseController db = new DatabaseController();
		ArrayList<Product> productList = db.loadAllProductList();
		data = FXCollections.observableArrayList(productList);
		tv.setItems(data);
	}
	
	/**
	 * ------------------------------------텍스트필드 초기화
	 */
	public void initTextField() {
		productCode.setText("");
		productName.setText("");
		productPrice.setText("");
		productSalePrice.setText("");
		productQuantity.setText("");
		category.getSelectionModel().selectFirst();
		productCode.setEditable(true);
	}
	
	/**
	 * ------------------------------------유효성 검사
	 */
	private boolean isInputValid() {
		String errorMessage = "";
		
		if(productCode.getText() == null || productCode.getText().length() == 0) {
			errorMessage += "상품 번호란이 비어있습니다.\n";
		}
		if(productName.getText() == null || productName.getText().length() == 0) {
			errorMessage += "상품 이름란이 비어있습니다.\n";
		}
		if(productPrice.getText() == null || productPrice.getText().length() == 0) {
			errorMessage += "상품 가격란이 비어있습니다.\n";
		}
		if(category.getSelectionModel().getSelectedItem() == null || 
				category.getSelectionModel().getSelectedItem().length() == 0) {
			errorMessage += "카테고리를 선택하세요.\n";
		} 
		if (errorMessage.length() == 0) {
			return true;
		} else {
			alertFail("입력 오류", errorMessage);
			return false;
		}
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
	 * ------------------------------------초기화 : load된 후 실행
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		category.setItems(FXCollections.observableArrayList("퍼즐","아케이드","시뮬레이션","어드벤쳐","격투","슈팅","롤플레잉","음악","스포츠"));
		tc_code.setCellValueFactory(new PropertyValueFactory<>("code"));
		tc_category.setCellValueFactory(new PropertyValueFactory<>("category"));
		tc_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		tc_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		tc_price.setCellValueFactory(new PropertyValueFactory<>("price"));
		tc_salePrice.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
		tc_discountRate.setCellValueFactory(new PropertyValueFactory<>("discountRate"));
		tc_discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
		setTableViewData();
	}
}
