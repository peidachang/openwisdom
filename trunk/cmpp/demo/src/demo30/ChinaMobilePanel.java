package demo30;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

import com.huawei.insa2.comm.cmpp.message.CMPPActiveMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPConnectMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPTerminateMessage;
import com.huawei.insa2.comm.cmpp30.message.CMPP30SubmitMessage;

public class ChinaMobilePanel extends JPanel implements ActionListener {
	private JLabel enterpriseId_label;
	private JTextField enterpriseId_text;
	private static XMPPConnection connection ;
	private JLabel enterprisePasswd_label;
	private JTextField enterprisePasswd_text;
	
	private JLabel messageContent_label;
	private JTextArea messageContent_text ;
	
	private JLabel backMessage_label;
	private JTextArea backMessage_text;
	
	private JScrollPane scoll ;
	private JButton login_button;
	private JButton logout_button;
	
	private JButton send_button;
	private JButton clear_button;
	private JButton active_button;
	private InetAddress localAddress;
	private InetAddress serverAddress;
	private Socket socket;
	private static final int REMOTE_PORT = 7890;
	private static final int LOCAL_PORT = 7800;
	
	public ChinaMobilePanel(){
		super();
		init();
	}
	public void init(){
		enterpriseId_label = new JLabel("企业代码");
		enterpriseId_text = new JTextField(9);
		enterpriseId_label.setBounds(5, 20, 55, 20);
		enterpriseId_text.setBounds(65, 20, 80, 20);
		
		enterprisePasswd_label = new JLabel("登录密码");
		enterprisePasswd_text = new JTextField(9);
		login_button = new JButton("登录");
		logout_button = new JButton("注销");
		active_button = new JButton("连接测试");
		send_button = new JButton("发送到SMSC");
		enterprisePasswd_label.setBounds(150, 20, 55, 20);
		enterprisePasswd_text.setBounds(210, 20, 80, 20);
		login_button.setBounds(300, 20, 80, 20);
		logout_button.setBounds(390, 20, 80, 20);
		active_button.setBounds(480, 20, 90, 20);
		messageContent_label = new JLabel("内容");
		messageContent_text = new JTextArea(40,9);
		messageContent_label.setBounds(5, 20, 30, 20);
		messageContent_text.setBounds(40, 20, 300, 60);
		send_button.setBounds(350, 20, 120, 60);
		
		backMessage_label = new JLabel("返回消息");
		backMessage_text = new JTextArea(80,9);
		backMessage_text.setEditable(false);
		backMessage_text.setLineWrap(true);
		
		scoll = new JScrollPane(backMessage_text);
		backMessage_label.setBounds(5, 20, 60, 20);
		scoll.setBounds(70, 20, 470, 100);
		clear_button = new JButton("清除");
		clear_button.setBounds(70, 130,80, 20);
		
		JPanel operatePanel = new JPanel();
		operatePanel.setLayout(null);
		operatePanel.setBorder(BorderFactory.createTitledBorder("短信操作"));
		operatePanel.add(messageContent_label);
		operatePanel.add(messageContent_text);
		operatePanel.add(send_button);
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null);
		loginPanel.setBorder(BorderFactory.createTitledBorder("企业登录"));
		
		JPanel statuPanel = new JPanel();
		statuPanel.setLayout(null);
		statuPanel.setBorder(BorderFactory.createTitledBorder("网关返回消息"));
		
		loginPanel.add(enterpriseId_label);
		loginPanel.add(enterpriseId_text);
		loginPanel.add(enterprisePasswd_label);
		loginPanel.add(enterprisePasswd_text);
		loginPanel.add(login_button);
		loginPanel.add(logout_button);
		loginPanel.add(active_button);
		
		statuPanel.add(backMessage_label);
		statuPanel.add(scoll);
		statuPanel.add(clear_button);
		
		loginPanel.setBounds(5, 5, 580, 60);
		operatePanel.setBounds(5, 70, 580, 220);
		statuPanel.setBounds(5, 300, 580, 160);
		setLayout(null);
		add(loginPanel);
		add(operatePanel);
		add(statuPanel);
		login_button.addActionListener(this);
		logout_button.addActionListener(this);
		active_button.addActionListener(this);
		clear_button.addActionListener(this);
		send_button.addActionListener(this);
		
		try {
			localAddress = InetAddress.getByName("127.0.0.1");
			serverAddress = InetAddress.getByName("127.0.0.1");
			socket = new Socket(serverAddress, REMOTE_PORT, localAddress,
					LOCAL_PORT);
		} catch (Exception e) {
			socket = null ;
			System.out.println("CMPP Connection Refused");
		}
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == login_button) {
			String enterpriseId = enterpriseId_text.getText();
			String enterprisePasswd = enterprisePasswd_text.getText();
			int version = 1;
			if (!enterpriseId.equals("") && !enterprisePasswd.equals("")) {
				loginToOpenfire(enterpriseId,enterprisePasswd);
				CMPPConnectMessage request = new CMPPConnectMessage(
						enterpriseId, version, enterprisePasswd, new Date());
				write(request);
				
			}
		}else if(ae.getSource()==clear_button){
			backMessage_text.setText("");
		}else if(ae.getSource()==send_button){
			String[] destTerminalId = {"\u76EE\u7684\u7528\u6237\u624B\u673A\u53F7\u7801"};
			CMPP30SubmitMessage request = new CMPP30SubmitMessage(
					1, 1, 0, 88, "MO_Test", 0, "8989899", 1, 0, 0, 15,
					"wisdom", "01", "999", null, null, "86137",
					destTerminalId, 0, messageContent_text.getText().getBytes(), "linkid=0123456789012");
			write(request);
		}else if(ae.getSource()==logout_button){
			//CMPPActiveMessage request = new CMPPActiveMessage();
			CMPPTerminateMessage request = new CMPPTerminateMessage();
			write(request);
		}else if(ae.getSource()==active_button){
			CMPPActiveMessage request = new CMPPActiveMessage();
			write(request);
		}
	}
	public void loginToOpenfire(String userName,String password){
		String serverName = "localhost";
		ConnectionConfiguration config = new ConnectionConfiguration(serverName);
		config.setReconnectionAllowed(true);
        config.setRosterLoadedAtLogin(true);
        config.setSendPresence(false);
        connection = new XMPPConnection(config);
        try {
			connection.connect();
			connection.login(userName, password, "spark");
		} catch (XMPPException e) {
			e.printStackTrace();
		}
        
	}
	public void write(CMPPMessage request){
		try {
			OutputStream out = socket.getOutputStream();
			out.write(request.getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public XMPPConnection getXMPPConnection(){
		return connection ;
	}
	public JTextArea getBackMessageTextArea(){
		
		return backMessage_text;
	}
	public Socket getSocket(){
		return socket;
	}

}
