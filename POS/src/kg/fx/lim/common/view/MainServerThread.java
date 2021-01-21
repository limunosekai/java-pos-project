package kg.fx.lim.common.view;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import kg.fx.lim.model.Protocol;

/**
---------------------------------------------------------------------------
* Nintendo POS 1.0
* MainServerThread
* @author 임성현
---------------------------------------------------------------------------
*/

public class MainServerThread extends Thread {
	// ------------------------------------멤버 필드
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private ArrayList<MainServerThread> list;
	private String userId;
	
	// ------------------------------------생성자
	public MainServerThread(Socket socket) {
		this.socket = socket;
	}
	
	// ------------------------------------메소드
	@Override
	public synchronized void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			while(true) {
				String msg = in.readLine();
				String[] words = msg.split("::");
				if(words[0].equals(Protocol.ENTER)) {
					userId = words[1];
					msg = "** "+userId+" 님이 입장했습니다. **";
					broadcast(msg);
				} else if(words[0].equals(Protocol.SEND_MESSAGE)) {
					msg = "["+words[1]+"] : "+words[2];
					broadcast(msg);
				} else if(words[0].equals(Protocol.SEND_SECRET_MESSAGE)) {
					userId = words[1];
					String receiver = words[2];
					msg = "<<"+words[1]+">> : "+words[3];
					sendSecretMessage(msg,userId,receiver);
				} else if(words[0].equals(Protocol.EXIT)) {
					userId = words[1];
					msg = "** "+userId+" 님이 퇴장했습니다. **";
					broadcast(msg);
					list.remove(this);
					in.close();
					out.close();
					socket.close();
				}
			}
		} catch(IOException e) {
			list.remove(this);
			String name = socket.getInetAddress().getHostName();
			try {
				broadcast(name+"과의 연결이 끊어졌습니다.");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void sendSecretMessage(String msg,String userId,String receiver) throws IOException {
		for(MainServerThread t : list) {
			if(t.userId.equals(receiver)) {
				t.sendMsg(msg);
			}
			if(t.userId.equals(userId)) {
				t.sendMsg(msg);
			}
		}
		return;
	}

	public void broadcast(String msg) throws IOException{
		if(list.size() == -1) {
			return;
		}
		for(MainServerThread t : list) {
			t.sendMsg(msg);
		}
		return;
	}
	
	public void sendMsg(String msg) throws IOException {
		out.println(msg);
	}
	
	public void setList(ArrayList<MainServerThread> list) {
		this.list = list;
	}
}
