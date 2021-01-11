package kg.fx.lim.admin.view;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kg.fx.lim.MainApp;
import kg.fx.lim.login.view.DatabaseController;

/**
---------------------------------------------------------------------------
* Nintendo POS 1.0
* MainViewController
* @author 임성현
---------------------------------------------------------------------------
*/

public class MainViewController {
	// ---------------------------------------------멤버필드
	@FXML 
	public BorderPane bp;
	@FXML
	private Button storeBtn;
	@FXML
	private Button salesBtn;
	@FXML
	private Button inventoryBtn;
	@FXML
	private Button logoutBtn;
	@FXML
	private Button homeBtn;
	@FXML
	private Label totalAmount;
	@FXML
	private Label bestItem;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private BarChart<String,Integer> previousDaySalesChart;
	private ObservableList<String> userNames = FXCollections.observableArrayList();
	private ArrayList<String> DB_userNames;
	
	private DatabaseController db;
	
	
	// ----------------------------------------------생성자
	public MainViewController() {
		
	}
	
	// 메소드
	/**
	 * -----------------------------------------홈버튼
	 */
	@FXML
	public void handleHomeBtn(ActionEvent e) {
		loadPage("HomePage");
	}
	
	/**
	 * -----------------------------------------매장관리 버튼
	 */
	@FXML
	public void handleStoreBtn(ActionEvent e) {
		loadPage("StoreManagementPage");
	}
	
	/**
	 * -----------------------------------------매출관리 버튼
	 */
	@FXML
	public void handleSalesBtn(ActionEvent e) {
		loadPage("SalesManagementPage");
	}
	
	/**
	 * -----------------------------------------재고관리 버튼
	 */
	@FXML
	public void handleInventoryBtn(ActionEvent e) {
		loadPage("InventoryManagementPage");
	}
	
	/**
	 * -----------------------------------------막대그래프 데이터셋팅
	 */
	@FXML
	public void setBarChartData() {
		DB_userNames = db.loadAllUserName();
		userNames.addAll(DB_userNames);
		xAxis.setCategories(userNames);
	}
	/**
	 * -----------------------------------------MainView 내 화면전환
	 */
	public void loadPage(String page) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(page+".fxml"));
			bp.setCenter(root);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ------------------------------------------로그아웃
	 */
	@FXML
	public void handleLogout(ActionEvent e) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("login/view/RootLayout.fxml"));					
			AnchorPane rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			Stage adminStage = (Stage) logoutBtn.getScene().getWindow();
			adminStage.setScene(scene);
			adminStage.setTitle("Login");
			adminStage.setResizable(false);
			adminStage.show();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
