package kg.fx.lim;

import kg.fx.lim.common.view.MainServer;

public class ServerThread extends Thread {
	@Override
	public void run() {
		MainServer server = new MainServer();
	}
}
