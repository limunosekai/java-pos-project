package kg.fx.lim;

import kg.fx.lim.common.server.MainServer;

/**
---------------------------------------------------------------------------
* Nintendo POS 1.0
* ServerThread
* @author 임성현
---------------------------------------------------------------------------
*/

public class ServerThread extends Thread {
	@Override
	public void run() {
		MainServer server = new MainServer();
	}
}
