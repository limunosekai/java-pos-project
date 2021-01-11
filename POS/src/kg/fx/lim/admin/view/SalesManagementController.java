package kg.fx.lim.admin.view;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

/**
---------------------------------------------------------------------------
* Nintendo POS 1.0
* SalesManagementController
* @author 임성현
---------------------------------------------------------------------------
*/

public class SalesManagementController {
	// -----------------------------------멤버필드
	@FXML
	private LineChart<String,Integer> lc;
	@FXML
	private PieChart pc;
	@FXML
	private BarChart<String,Integer> bc;
	@FXML
	private Label bestItem;
	
	// ------------------------------------생성자
	public SalesManagementController() {
	}
	
	// 메소드
	
}
