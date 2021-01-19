package kg.fx.lim;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kg.fx.lim.common.view.LoginController;
import kg.fx.lim.common.view.MainServer;

/**
 * ---------------------------------------------------------------------------
 * Nintendo POS ver 1.0
 * MainApp
 * @author 임성현
 * ---------------------------------------------------------------------------
 */

public class MainApp extends Application {
	// --------------------------------------------멤버필드
	private Stage primaryStage;
	private AnchorPane rootLayout;

	// --------------------------------------------생성자
	public MainApp() {
	}

	// --------------------------------------------메소드
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Login");

		initRootLayout();
		ServerThread thread = new ServerThread();
		thread.start();
	}

	/**
	 * -------------------------------------RootLayout 초기화
	 */
	public void initRootLayout() {
		try {
			// FXML에서 상위 레이아웃을 가져옴
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("common/view/RootLayout.fxml"));
			rootLayout = (AnchorPane) loader.load();

			// 상위 레이아웃을 포함하는 scene을 stage에 세팅
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			// 로그인 컨트롤러 배치
			LoginController controller = loader.getController();
			controller.setMainApp(this);

			primaryStage.setResizable(false);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ----------------------------------------메인 스테이지 반환
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * ----------------------------------------Main 메소드
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
