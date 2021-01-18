package kg.fx.lim.user.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kg.fx.lim.MainApp;
import kg.fx.lim.login.view.DatabaseController;
import kg.fx.lim.model.Product;

/**
---------------------------------------------------------------------------
* Nintendo POS 1.0
* InventoryManagementController
* @author 임성현
---------------------------------------------------------------------------
*/

public class InventoryManagementController implements Initializable {
	// ----------------------------------------멤버필드
	@FXML
	private Button logoutBtn;
	@FXML
	private Button homeBtn;
	@FXML
	private Button searchBtn;
	@FXML
	private Button resetBtn;
	@FXML
	private Button storeName;
	@FXML
	private TextField searchBar;
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
	private ObservableList<Product> data;
	
	// -----------------------------------------생성자
	public InventoryManagementController() {
		
	}
	
	// 메소드
	/**
	 * -----------------------------------------로그아웃
	 */
	@FXML
	public void handleLogoutBtn() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("login/view/RootLayout.fxml"));					
			AnchorPane rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			Stage invenStage = (Stage) logoutBtn.getScene().getWindow();
			invenStage.setScene(scene);
			invenStage.setTitle("Login");
			invenStage.setResizable(false);
			invenStage.show();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * -----------------------------------------홈버튼
	 */
	@FXML
	public void handleHomeBtn(ActionEvent e) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("userLayout.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
//			Parent root = FXMLLoader.load(getClass().getResource("userLayout.fxml"));
			
			PosController controller = loader.getController();
			controller.setUserName(getUserName());
			
			Scene scene = new Scene(page);
			Stage invenStage = (Stage) homeBtn.getScene().getWindow();
			invenStage.setScene(scene);
			invenStage.show();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * -----------------------------------------검색버튼
	 */
	@FXML
	public void handleSearchBtn() {
		String search = searchBar.getText();
		DatabaseController db = new DatabaseController();
		ArrayList<Product> productList = db.searchProductByName(search);
		data = FXCollections.observableArrayList(productList);
		tv.setItems(data);
		searchBar.clear();
	}
	
	/**
	 * -----------------------------------------초기화버튼
	 */
	@FXML
	public void handleResetBtn() {
		data.clear();
		searchBar.clear();
		DatabaseController db = new DatabaseController();
		ArrayList<Product> productList = db.loadAllProductList();
		data = FXCollections.observableArrayList(productList);
		tv.setItems(data);
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
	 * --------------------------------------------------userName 화면 표시
	 */
	public void setUserName(String name) {
		storeName.setText(name);
	}
	
	/**
	 * --------------------------------------------------userName 가져오기
	 */
	public String getUserName() {
		String name = storeName.getText();
		return name;
	}

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
		setTableViewData();
	}
}
