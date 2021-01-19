package kg.fx.lim.common.view;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class MainServerThread extends Thread {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private ArrayList<MainServerThread> list;
	
	public MainServerThread(ArrayList<MainServerThread> list, Socket socket) {
		this.socket = socket;
		this.list = list;
	}
	
	@Override
	public synchronized void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			while(true) {
				String msg = in.readLine();
				broadcast(msg);
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
	
	public void broadcast(String msg) throws IOException{
		for(MainServerThread t : list) {
			t.sendMsg(msg);
		}
	}
	
	public void sendMsg(String msg) throws IOException {
		out.println(msg);
	}
}
