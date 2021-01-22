package kg.fx.lim.admin.view;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import kg.fx.lim.common.DAO.DatabaseController;
import kg.fx.lim.model.Protocol;

/**
---------------------------------------------------------------------------
* Nintendo POS 1.0
* ChattingController(admin)
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
	private Button sendBtn;
	@FXML
	private Button exitBtn;
	@FXML
	private ChoiceBox<String> choice;
	private ArrayList<String> names = new ArrayList<>();
	private String getMsg;
	private String sendMsg;
	private String id = "admin::";
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
				ta.appendText(getMsg + "\n");
				
//				if(Thread.interrupted()) {
//					closeResource();
//					break;
//				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ------------------------------------초기화
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// choiceBox 초기화
		DatabaseController db = new DatabaseController();
		names.add("전체");
		names.addAll(db.loadAllUserName());
		choice.setItems(FXCollections.observableArrayList(names));
		choice.getSelectionModel().selectFirst();
		tf.requestFocus();
		// 서버 접속 시도
		try {
			socket = new Socket("localhost", 5004);
			System.out.println("서버 접속 성공");
		
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			
			out.println(Protocol.ENTER+"::"+id);
		} catch(IOException e) {
			Alert fail = new Alert(AlertType.ERROR);
			fail.setTitle("연결 실패");
			fail.setHeaderText("서버 연결 실패");
			fail.setContentText("서버에 연결할 수 없습니다.");
			fail.showAndWait();
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
		String name = choice.getSelectionModel().getSelectedItem();
		if(name.equals("전체")) {
			sendMsg = Protocol.SEND_MESSAGE+"::"+id+tf.getText();
			out.println(sendMsg);
			tf.clear();
		} else {
			sendMsg = Protocol.SEND_SECRET_MESSAGE+"::"+id+name+"::"+tf.getText();
			out.println(sendMsg);
			tf.clear();
		}
	}
	
	/**
	 * --------------------------------------나가기 버튼
	 */
	@FXML
	public void handleExitBtn() {
		ta.appendText("재입장을 하시려면 채팅버튼을 눌러주세요.\n");
		sendMsg = Protocol.EXIT+"::"+id;
		out.println(sendMsg);
		isStop = true;
	}
	
	/**
	 * ---------------------------------------자원 반납
	 */
	public void closeResource() {
		try {if(in != null) in.close();}catch(IOException e){}
		if(out != null) out.close();
		try {if(socket != null)socket.close();}catch (IOException e) {}
	}
}
