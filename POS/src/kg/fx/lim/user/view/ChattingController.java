package kg.fx.lim.user.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import kg.fx.lim.model.Protocol;

/**
---------------------------------------------------------------------------
* Nintendo POS 1.0
* ChattingController(User)
* @author 임성현
---------------------------------------------------------------------------
*/

public class ChattingController implements Runnable, Initializable {
	// -----------------------------------멤버필드
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	@FXML
	private TextField tf;
	@FXML
	private TextArea ta;
	@FXML
	private ToggleButton tb;
	@FXML
	private Button sendBtn;
	@FXML
	private Label userName;
	private Stage dialogStage;
	private String getMsg;
	private String sendMsg;
	private String id;
	private boolean isStop = false;
	// -----------------------------------생성자
	public ChattingController() {	
	}
	
	// -----------------------------------메소드
	@Override
	public void run() {
		try {
			while(!isStop) {
				getMsg = in.readLine();
				// 스레드 동시성 - 작업 스레드 실행 후 UI변경 후작업
				Platform.runLater(new Runnable() {
				    @Override
				    public void run(){
				    	ta.appendText(getMsg + "\n");
				    	dialogStage.setOnCloseRequest(event -> {
							handleClose();
						});
				    	try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				    }
				});
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			closeResource();
		}
	}
	
	/**
	 * --------------------------------------자원 반납
	 */
	private void handleClose() {
		out.println(Protocol.EXIT+"::"+id);
		isStop = true;
	}

	/**
	 * -------------------------------------초기화
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tf.requestFocus();
		// 서버 접속 시도
		try {
			socket = new Socket("localhost", 5004);
			System.out.println("서버 접속 성공");
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
		} catch(IOException e) {
			Alert fail = new Alert(AlertType.ERROR);
			fail.setTitle("연결 실패");
			fail.setHeaderText("서버 연결 실패");
			fail.setContentText("서버에 연결할 수 없습니다.");
			fail.showAndWait();
			Platform.exit();
		}
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * -------------------------------------보내기 버튼
	 */
	@FXML
	public void handleSendBtn() {
		if(tf.getText().length() == 0 || tf.getText().equals("")){
			return;
		}
		handleSend();
	}
	
	/**
	 * -------------------------------------보내기 메소드
	 */
	@FXML
	public void handleSend() {
		id = userName.getText();
		String receiver = "admin::";
		if(!tb.isSelected()) {
			// 전체 보내기
			sendMsg = Protocol.SEND_MESSAGE+"::"+id+"::"+tf.getText();
			out.println(sendMsg);
			tf.clear();
		} else {
			// 관리자 귓속말
			sendMsg = Protocol.SEND_SECRET_MESSAGE+"::"+id+"::"+receiver+tf.getText();
			out.println(sendMsg);
			tf.clear();
		}
	}
	
	/**
	 * --------------------------------------- 채팅방 참여
	 */
	public void enterChat() {
		id = getUserName();
		out.println(Protocol.ENTER+"::"+id);
	}
	
	/**
	 * ---------------------------------------자원 반납
	 */
	public void closeResource() {
		try {if(in != null) in.close();}catch(IOException e){}
		if(out != null) out.close();
		try {if(socket != null)socket.close();}catch (IOException e) {}
	}
	
	/**
	 * ---------------------------------------유저이름 설정
	 */
	public void setUserName(String name) {
		userName.setText(name);
	}
	
	/**
	 * ---------------------------------------유저이름 가져오기
	 */
	public String getUserName() {
		String name = userName.getText();
		return name;
	}

	/**
	 * -------------------------------------참조 유지
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}
