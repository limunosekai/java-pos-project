package kg.fx.lim.common.view;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
---------------------------------------------------------------------------
* Nintendo POS 1.0
* MainServer
* @author 임성현
---------------------------------------------------------------------------
*/

public class MainServer {
	//-----------------------------------------멤버 필드
	private ArrayList<MainServerThread> list = new ArrayList<>();
	private ServerSocket ss = null;
	
	// ----------------------------------------생성자
	public MainServer() {
		try {
			ss = new ServerSocket(5004);
			System.out.println("서버 대기중");
			while(true) {
				Socket socket = ss.accept();
				InetAddress ip = socket.getInetAddress();
				String name = ip.getHostName();
				System.out.println(name+" 접속");
				// 스레드 객체 생성
				MainServerThread t = new MainServerThread(socket);
				list.add(t);
				t.setList(list);
				t.start();
			}
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
