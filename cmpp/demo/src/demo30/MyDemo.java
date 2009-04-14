package demo30;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

import com.huawei.insa2.comm.PEvent;
import com.huawei.insa2.comm.PException;
import com.huawei.insa2.comm.PMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPConnectMessage;
import com.huawei.insa2.comm.cmpp30.CMPP30Reader;
import com.huawei.insa2.comm.cmpp30.message.CMPP30ConnectRepMessage;
import com.huawei.insa2.comm.sgip.SGIPReader;
import com.huawei.insa2.comm.sgip.message.SGIPBindMessage;
import com.huawei.insa2.comm.sgip.message.SGIPBindRepMessage;

public class MyDemo {
	public static void main(String[] args) throws IOException {
		InetAddress localAddress = InetAddress.getByName("127.0.0.1");
		InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
		Socket socket = new Socket(serverAddress, 7890, localAddress, 8898);
		CMPPConnectMessage request = new CMPPConnectMessage("901234", 1,
				"123456", new Date());
		SGIPBindMessage request_1 = new SGIPBindMessage(1,"opennet","opennet");
		OutputStream out = socket.getOutputStream();
		InputStream in = socket.getInputStream();
		out.write(request.getBytes());
		CMPP30ConnectRepMessage m = (CMPP30ConnectRepMessage)new CMPP30Reader(in).read();
		//SGIPBindRepMessage m = (SGIPBindRepMessage) new SGIPReader(in).read();
		System.out.println("results="+m.getStatus()+" sequenceId="+m.getSequenceId());
		
	}

}
