package kg.fx.lim.admin.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import kg.fx.lim.login.view.DatabaseController;

/**
---------------------------------------------------------------------------
* Nintendo POS 1.0
* HomePageController
* @author 임성현
---------------------------------------------------------------------------
*/

public class HomePageController implements Initializable {
	// --------------------------------------멤버필드
	@FXML
	private BarChart<String,Integer> bc;
	@FXML
	private CategoryAxis store;
	@FXML
	private TextField total;
	@FXML
	private TextField best;
	
	// --------------------------------------생성자
	public HomePageController() {
		
	}

	// --------------------------------------메소드
	
	/**
	 * --------------------------------------데이터 차트에 표시
	 */
	public void theDayBeforeReport() {
		DatabaseController db = new DatabaseController();
		int totalAmount = 0;
		//-------------------------------작일 베스트 아이템
		
		
		
		//-------------------------------Bar Chart	
		ArrayList<String> stores = db.loadAllUserName();
		ObservableList<String> storeNames = FXCollections.observableArrayList();
		storeNames.addAll(stores);
		
		
		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		series.setName("작일 매출 총액");
		// XYChart.Data 객체를 만들어 series에 추가
		for(int i=0; i< stores.size(); i++) {
			// Bar Chart X축 Y축에 데이터 구하기
			series.getData().add(new XYChart.Data<>(storeNames.get(i), db.loadTheDayBeforeSales(stores.get(i))));
			// 작일 매출 총액 계산
			totalAmount += db.loadTheDayBeforeSales(stores.get(i));
		}
		bc.getData().add(series);
		total.setText(String.valueOf(totalAmount));
		
		
		
	}
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		theDayBeforeReport();
	}
}
