package demo30;

import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;

import com.huawei.insa2.comm.cmpp.message.CMPPActiveMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPActiveRepMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPCancelRepMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPQueryRepMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPTerminateMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPTerminateRepMessage;
import com.huawei.insa2.comm.cmpp30.message.CMPP30ConnectRepMessage;
import com.huawei.insa2.comm.cmpp30.message.CMPP30DeliverMessage;
import com.huawei.insa2.comm.cmpp30.message.CMPP30SubmitRepMessage;
import com.huawei.insa2.comm.sgip.message.SGIPBindRepMessage;
import com.huawei.insa2.util.DateUtil;

public class SMSC extends JFrame implements ActionListener {

	private JTabbedPane tabbedPane;
	private ChinaMobilePanel chinaMobilePanel;
	private ChinaUnicomPanel chinaUnicomPanel;

	public SMSC(String title) {
		super(title);
		init();
	}

	public void init() {
		tabbedPane = new JTabbedPane();
		chinaMobilePanel = new ChinaMobilePanel();
		chinaUnicomPanel = new ChinaUnicomPanel();
		tabbedPane.add("中国移动", chinaMobilePanel);
		tabbedPane.add("中国联通", chinaUnicomPanel);

		Container c = getContentPane();

		c.add(tabbedPane);
		setSize(600, 530);
		setVisible(true);
		setResizable(false);
		Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getScreenDevices()[0].getDefaultConfiguration().getBounds();
		setLocation((int) (r.getWidth() - (double) 600) / 2, (int) (r
				.getHeight() - (double) 450) / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(chinaUnicomPanel.getSocket()!=null)
		new SGIPSMSListener(chinaUnicomPanel.getSocket()).start();
		if(chinaMobilePanel.getSocket()!=null)
		new CMPPSMSListener(chinaMobilePanel.getSocket()).start();

	}

	public void actionPerformed(ActionEvent ae) {

	}

	/**
	 * Read CMPP Response Message
	 * 
	 * @param is
	 * @throws IOException
	 */
	public void readCMPPResponse(InputStream is) throws IOException {
		DataInputStream in = new DataInputStream(is);

		int total_Length = in.readInt();
		int command_Id = in.readInt();
		byte buf[] = new byte[total_Length - 8];
		in.readFully(buf);
		System.out.println("command id = " + command_Id);
		if (command_Id == 0x80000001)
			CMPP30ConnectResponseListener(new CMPP30ConnectRepMessage(buf));
		if (command_Id == 5)
			CMPP30DeliverResponseListener(new CMPP30DeliverMessage(buf));
		if (command_Id == 0x80000004)
			CMPP30SubmitResponseListener(new CMPP30SubmitRepMessage(buf));
		if (command_Id == 0x80000006)
			new CMPPQueryRepMessage(buf);
		if (command_Id == 0x80000007)
			new CMPPCancelRepMessage(buf);
		if (command_Id == 0x80000008)
			CMPPActiveResponseListener(new CMPPActiveRepMessage(buf));
		if (command_Id == 8)
			CMPPActiveListener(new CMPPActiveMessage(buf));
		if (command_Id == 2)
			new CMPPTerminateMessage(buf);
		if (command_Id == 0x80000002)
			CMPPTerminateResponseListener(new CMPPTerminateRepMessage(buf));

	}

	/**
	 * Read SGIP Response Message
	 * 
	 * @param is
	 * @throws IOException
	 */
	public void readSGIPResponse(InputStream is) throws IOException {
		DataInputStream in = new DataInputStream(is);

		int total_Length = in.readInt();
		int command_Id = in.readInt();
		byte buf[] = new byte[total_Length - 8];
		in.readFully(buf);
		System.out.println("command id = " + command_Id);
		if (command_Id == 0x80000001)
			SGIPBindResponseListener(new SGIPBindRepMessage(buf));

	}

	/**
	 * following method are deal with cmpp response message
	 * 
	 * @param message
	 */
	public void CMPP30ConnectResponseListener(CMPP30ConnectRepMessage message) {
		chinaMobilePanel.getBackMessageTextArea().append(
				"\nCONNECT MESSAGE : "
						+ DateUtil.format(new java.sql.Timestamp(System
								.currentTimeMillis()), "yyyy-MM-dd hh:mm:ss")
						+ " : " + message.toString());

	}

	public void CMPP30DeliverResponseListener(CMPP30DeliverMessage message) {
		chinaMobilePanel.getBackMessageTextArea().append(
				"\nDELIVER_RESPONSE MESSAGE : "
						+ DateUtil.format(new java.sql.Timestamp(System
								.currentTimeMillis()), "yyyy-MM-dd hh:mm:ss")
						+ " : " + message.toString());
		System.out.println(new String(message.getMsgContent()));
		try {
			//Socket socket = new Socket("localhost",5222);
			sendToOpenfire(new String(message.getMsgContent()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CMPP30SubmitResponseListener(CMPP30SubmitRepMessage message) {
		chinaMobilePanel.getBackMessageTextArea().append(
				"\nSUBMIT_RESPONSE MESSAGE : "
						+ DateUtil.format(new java.sql.Timestamp(System
								.currentTimeMillis()), "yyyy-MM-dd hh:mm:ss")
						+ " : " + message.toString());
		message.getResult();
	}

	public void CMPPActiveResponseListener(CMPPActiveRepMessage message) {
		chinaMobilePanel.getBackMessageTextArea().append(
				"\n"
						+ DateUtil.format(new java.sql.Timestamp(System
								.currentTimeMillis()), "yyyy-MM-dd hh:mm:ss")
						+ " : " + message.toString());

	}

	public void CMPPActiveListener(CMPPActiveMessage message) {
		System.out.println("testing...");
	}

	public void CMPPTerminateResponseListener(CMPPTerminateRepMessage message) {
		System.out.println(message.toString());
	}

	/**
	 * following method are deal with sgip response message
	 * 
	 * @param message
	 */
	public void SGIPBindResponseListener(SGIPBindRepMessage message) {
		chinaUnicomPanel.getBackMessageTextArea().append(
				"\nSGIP BIND MESSAGE : "
						+ DateUtil.format(new java.sql.Timestamp(System
								.currentTimeMillis()), "yyyy-MM-dd hh:mm:ss")
						+ " : " + message.toString());
	}

	/**
	 * SGIP Listener
	 * 
	 * @author User
	 * 
	 */
	class SGIPSMSListener extends Thread {
		private Socket socket;

		public SGIPSMSListener(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			while (true) {
				try {
					InputStream is = socket.getInputStream();
					readSGIPResponse(is);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * CMPP Listener
	 * 
	 * @author User
	 * 
	 */
	class CMPPSMSListener extends Thread {
		private Socket socket;

		public CMPPSMSListener(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			while (true) {
				try {
					InputStream is = socket.getInputStream();
					readCMPPResponse(is);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	public boolean sendToOpenfire(String msg_body) throws Exception{
		//SessionManager sessionManager = SparkManager.getSessionManager();
		int port = 5222;
		String serverName = "localhost";
		//LocalPreferences localPref = SettingsManager.getLocalPreferences();
		ConnectionConfiguration config = new ConnectionConfiguration(serverName);
		config.setReconnectionAllowed(true);
        config.setRosterLoadedAtLogin(true);
        config.setSendPresence(false);
        XMPPConnection connection = new XMPPConnection(config);
        connection.connect();
        //String resource = localPref.getResource();
       // if (!ModelUtil.hasLength(resource)) {
        //    resource = "spark";
       // }                   
        //System.out.println("connection " + connection);
        connection.login("jason", "123456", "spark");
        //sessionManager.setServerAddress(connection.getServiceName());
        //sessionManager.initializeSession(connection, "jason", "123456");
        //sessionManager.setJID(connection.getUser());
        //connection.addConnectionListener(SparkManager.getSessionManager());
        
        //System.out.println("connection status = "+SparkManager.getConnection().isConnected());
        Message m = new Message();
    	m.setThread("Thread-101");
    	m.setBody(msg_body);
    	m.setFrom("jason@cpc012/spark");
    	m.setTo("test@cpc012");
    	m.setType(Message.Type.chat);
    	connection.sendPacket(m);
        return true;
	}
	public static void main(String[] args) {
		new SMSC("二维短信息中心");
	}

}
