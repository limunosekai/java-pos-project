package kg.fx.lim.admin.view;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import kg.fx.lim.common.view.DatabaseController;

/**
---------------------------------------------------------------------------
* Nintendo POS 1.0
* SalesManagementController
* @author 임성현
---------------------------------------------------------------------------
*/

public class SalesManagementController implements Initializable {
	// -----------------------------------멤버필드
	@FXML
	private LineChart<String,Integer> lc;
	@FXML
	private CategoryAxis day;
	@FXML
	private PieChart pc;
	@FXML
	private BarChart<String,Integer> bc;
	@FXML
	private CategoryAxis store;
	@FXML
	private TextField best;
	ObservableList<String> monthNames = FXCollections.observableArrayList();
	ObservableList<Data> list;
	
	// ------------------------------------생성자
	public SalesManagementController() {
	}
	
	// 메소드
	/**
	 * ------------------------------------차트에 데이터 표시
	 */
	public void showSalesReport() {
		int code = 0;
		DatabaseController db = new DatabaseController();
		
		//-------------------------------월간 베스트 아이템
		String bestItem = db.loadMonthlyBestItem();
		best.setText(bestItem);
		
		//-------------------------------Bar Chart, 월간 매출 총액
		ArrayList<String> stores = db.loadAllUserName();
		ObservableList<String> storeNames = FXCollections.observableArrayList();
		storeNames.addAll(stores);
		
		XYChart.Series<String, Integer> bc_series = new XYChart.Series<>();
		bc_series.setName("월 매출 총액");
		// XYChart.Data 객체를 만들어 series에 추가
		for(int i=0; i< stores.size(); i++) {
			// Bar Chart X축 Y축에 데이터 구하기
			bc_series.getData().add(new XYChart.Data<>(storeNames.get(i), db.loadForAMonthSales(db.loadUserCodeByName(stores.get(i)))));
		}
		bc.getData().add(bc_series);
		
		//-------------------------------Line Chart, 일자별 매출 총액
		// 월 이름을 배열로 가져옴
		String[] months = DateFormatSymbols.getInstance(Locale.KOREAN).getMonths();
		// 리스트로 변환하고 observableList에 추가
		monthNames.addAll(Arrays.asList(months));
		// 수평축에 월 이름을 카테고리로 할당
		day.setCategories(monthNames);
		
		XYChart.Series<String, Integer> lc_series = new XYChart.Series<>();
		lc_series.setName("월별 매출 총액");
		// 월별로 XYChart.Data 객체를 만들어 series에 추가
		try {
			for(int i=0; i< months.length; i++) {
				lc_series.getData().add(new XYChart.Data<>(monthNames.get(i), db.loadForAMonthTotalSales().get(i)));
			}
		} catch (IndexOutOfBoundsException e) {}
		lc.getData().add(lc_series);
		
		//-------------------------------Pie Chart, 카테고리별 매출 현황
		list = FXCollections.observableArrayList();
		String[] category = {"퍼즐","아케이드","시뮬레이션","어드벤쳐","격투","슈팅","롤플레잉","음악","스포츠"};
		for(int i=0; i<category.length; i++) {
			list.add(new PieChart.Data(category[i],db.loadCategorySales(category[i])));
		}
        pc.setTitle("카테고리별 매출 현황"); // 타이틀
        pc.setData(list); // 데이터 적용
	}
	
	/**
	 * ------------------------------------초기화
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showSalesReport();
		
	}
}
