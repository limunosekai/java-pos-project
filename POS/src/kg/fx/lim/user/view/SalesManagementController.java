package kg.fx.lim.user.view;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
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
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kg.fx.lim.MainApp;
import kg.fx.lim.login.view.DatabaseController;
import kg.fx.lim.model.OrderProductList;

/**
---------------------------------------------------------------------------
* Nintendo POS 1.0
* SalesManagementController
* @author 임성현
---------------------------------------------------------------------------
*/

public class SalesManagementController implements Initializable {
	// ----------------------------------------멤버필드
	@FXML
	private Button logoutBtn;
	@FXML
	private Button homeBtn;
	@FXML
	private Label storeName;
	@FXML
	private TextField todaySales;
	@FXML
	private LineChart<String,Integer> lc;
	@FXML
	private TableView<OrderProductList> tv;
	@FXML
	private TableColumn<OrderProductList,Integer> tc_orderNumber;
	@FXML               
	private TableColumn<OrderProductList,String> tc_orderDate;
	@FXML               
	private TableColumn<OrderProductList,String> tc_category;
	@FXML               
	private TableColumn<OrderProductList,String> tc_name;
	@FXML               
	private TableColumn<OrderProductList,Integer> tc_quantity;
	@FXML               
	private TableColumn<OrderProductList,Integer> tc_price;
	@FXML               
	private TableColumn<OrderProductList,Integer> tc_salePrice;
	@FXML               
	private TableColumn<OrderProductList,Integer> tc_discountRate;
	@FXML               
	private TableColumn<OrderProductList,Integer> tc_discount;
	private ObservableList<OrderProductList> data;
	private DecimalFormat formatter = new DecimalFormat("###,###");
	// -----------------------------------------생성자
	public SalesManagementController() {
	}
	
	// 메소드
	/**
	 * -----------------------------------------로그아웃
	 */
	@FXML
	public void handleLogoutBtn(ActionEvent e) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("login/view/RootLayout.fxml"));					
			AnchorPane rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			Stage salesStage = (Stage) logoutBtn.getScene().getWindow();
			salesStage.setScene(scene);
			salesStage.setTitle("Login");
			salesStage.setResizable(false);
			salesStage.show();
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
			Parent root = FXMLLoader.load(getClass().getResource("userLayout.fxml"));
			Scene scene = new Scene(root);
			Stage salesStage = (Stage) homeBtn.getScene().getWindow();
			salesStage.setScene(scene);
			salesStage.show();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * -----------------------------------------차트에 데이터 표시
	 */
	public void showTodaySales() {
		int code = 0;
		DatabaseController db = new DatabaseController();
		
		// 당일 매출총액
		code = db.loadUserCodeByName(getUserName());
		if(db.loadTodaySales(code) == 0) {
			todaySales.setText("0");
		} else {
			todaySales.setText(String.valueOf(formatter.format(db.loadTodaySales(code))));
		}
		
		// 매출 리스트
		ArrayList<OrderProductList> list = db.loadAllOrderProductList(code);
		data = FXCollections.observableArrayList(list);
		tv.setItems(data);
		
		// 차트
		
	}
	
	/**
	 * --------------------------------------------------userName 화면 표시
	 */
	public void setUserName(String name) {
		storeName.setText(name);
	}
	
	/**
	 * --------------------------------------------------userName 반환
	 */
	public String getUserName() {
		String name = storeName.getText();
		return name;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tc_orderNumber.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
		tc_orderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		tc_category.setCellValueFactory(new PropertyValueFactory<>("orderProductCategory"));
		tc_name.setCellValueFactory(new PropertyValueFactory<>("orderProductName"));
		tc_quantity.setCellValueFactory(new PropertyValueFactory<>("orderProductQuantity"));
		tc_price.setCellValueFactory(new PropertyValueFactory<>("orderProductPrice"));
		tc_salePrice.setCellValueFactory(new PropertyValueFactory<>("orderProductSalePrice"));
		tc_discountRate.setCellValueFactory(new PropertyValueFactory<>("orderProductDiscountRate"));
		tc_discount.setCellValueFactory(new PropertyValueFactory<>("orderProductDiscount"));
	}
}
