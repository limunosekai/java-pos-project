package kg.fx.lim.admin.view;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.TextField;

/**
---------------------------------------------------------------------------
* Nintendo POS 1.0
* HomePageController
* @author 임성현
---------------------------------------------------------------------------
*/

public class HomePageController {
	// --------------------------------------멤버필드
	@FXML
	private BarChart<String,Integer> bc;
	@FXML
	private TextField total;
	@FXML
	private TextField best;
	
	// --------------------------------------생성자
	public HomePageController() {
		
	}
}
