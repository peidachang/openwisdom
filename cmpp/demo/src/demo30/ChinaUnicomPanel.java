package demo30;

import java.awt.Container;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.huawei.insa2.comm.cmpp.message.CMPPActiveMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPConnectMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPTerminateMessage;
import com.huawei.insa2.comm.cmpp30.message.CMPP30SubmitMessage;
import com.huawei.insa2.comm.sgip.message.SGIPBindMessage;
import com.huawei.insa2.comm.sgip.message.SGIPMessage;

public class ChinaUnicomPanel extends JPanel implements ActionListener {

	private JLabel loginName_label;
	private JTextField loginName_text;
	
	private JLabel loginPasswd_label;
	private JTextField loginPasswd_text;
	
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
	private static final int SERVER_PORT = 8801;
	private static final int LOCAL_PORT = 8891;
	
	public ChinaUnicomPanel(){
		super();
		init();
	}
	
	public void init(){
		loginName_label = new JLabel("用户");
		loginName_text = new JTextField(9);
		loginName_label.setBounds(5, 20, 55, 20);
		loginName_text.setBounds(65, 20, 80, 20);
		
		loginPasswd_label = new JLabel("密码");
		loginPasswd_text = new JTextField(9);
		login_button = new JButton("登录");
		logout_button = new JButton("注销");
		active_button = new JButton("连接测试");
		send_button = new JButton("发送到SMSC");
		loginPasswd_label.setBounds(150, 20, 55, 20);
		loginPasswd_text.setBounds(210, 20, 80, 20);
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
		loginPanel.setBorder(BorderFactory.createTitledBorder("登录"));
		
		JPanel statuPanel = new JPanel();
		statuPanel.setLayout(null);
		statuPanel.setBorder(BorderFactory.createTitledBorder("网关返回消息"));
		
		loginPanel.add(loginName_label);
		loginPanel.add(loginName_text);
		loginPanel.add(loginPasswd_label);
		loginPanel.add(loginPasswd_text);
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
			socket = new Socket(serverAddress, SERVER_PORT, localAddress,
					LOCAL_PORT);
			System.out.println("--"+socket);
		} catch (Exception e) {
			socket = null ;
			System.out.println("SGIP Connection Refused");
			
		}
	}
	
	public Socket getSocket(){
		return socket;
	}
	
	public JTextArea getBackMessageTextArea(){
		
		return backMessage_text ;
	}
	
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == login_button) {
			if(socket==null){
				
			}
			String loginName = loginName_text.getText();
			String loginPasswd = loginPasswd_text.getText();
			if (!loginName.equals("") && !loginPasswd.equals("")) {
				SGIPBindMessage request = new SGIPBindMessage(1,loginName,loginPasswd);
				write(request);
			}
		}else if(ae.getSource()==clear_button){
			backMessage_text.setText("");
		}else if(ae.getSource()==send_button){
			
		}else if(ae.getSource()==logout_button){
			
		}else if(ae.getSource()==active_button){
			
		}
	}
	public void write(SGIPMessage request){
		try {
			OutputStream out = socket.getOutputStream();
			out.write(request.getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
