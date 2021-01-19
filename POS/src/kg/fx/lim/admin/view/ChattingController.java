package kg.fx.lim.admin.view;

import java.io.*;
import java.net.*;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChattingController implements Runnable, Initializable {
	// -----------------------------------멤버필드
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	@FXML
	private TextField tf;
	@FXML
	private TextArea ta;
	String getMsg;
	String sendMsg;
	// -----------------------------------생성자
	public ChattingController() {	
	}
	
	// -----------------------------------메소드
	@Override
	public void run() {
		try {
			while((getMsg = in.readLine()) != null) {
				ta.appendText(getMsg + "\n");			
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tf.requestFocus();
		try {
			socket = new Socket("localhost", 5003);
			System.out.println("서버 접속 성공");
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@FXML
	public void handleSend() {
		sendMsg = tf.getText();
		out.println(sendMsg);
		tf.clear();
	}

}
