package demo30;

import com.huawei.insa2.comm.cmpp.message.CMPPCancelMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPCancelRepMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPQueryMessage;
import com.huawei.insa2.comm.cmpp.message.CMPPQueryRepMessage;
import com.huawei.insa2.comm.cmpp30.message.CMPP30DeliverMessage;
import com.huawei.insa2.comm.cmpp30.message.CMPP30SubmitMessage;
import com.huawei.insa2.comm.cmpp30.message.CMPP30SubmitRepMessage;
import com.huawei.insa2.util.Args;
import com.huawei.insa2.util.Cfg;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class Demo30 extends JFrame implements DemoConst {
	private static final long serialVersionUID = 4987236858180644456L;

	class MoniterThread extends Thread {

		public void run() {
			String connState = null;
			try {
				do {
					connState = smp.getConnState();
					showStatisticData();
					if (connState == null)
						connState = "\u7CFB\u7EDF\u8FD0\u884C\u6B63\u5E38";
					myProxyState.setText(connState);
					Thread.sleep(100L);
				} while (true);
			} catch (Exception e) {
				showStateMsg("\u72B6\u6001\u76D1\u63A7\u7EBF\u7A0B\u51FA\u73B0\u5F02\u5E38\u9000\u51FA");
				e.printStackTrace();
				return;
			}
		}

		public MoniterThread() {
			setDaemon(true);
		}
	}

	private static Args args;
	private MySMProxy30 smp;
	private boolean loginSmProxy;
	int sendMsgSum;
	int sendSuccessMsgSum;
	int recvDeliverMsgSum;
	int mt_tlmsg;
	int mt_tlusr;
	int mt_scs;
	int mt_wt;
	int mt_fl;
	int mo_scs;
	int mo_wt;
	int mo_fl;
	int cmppSubmitFrom;
	int cmppSubmitTo;
	int calledIndex;
	String serviceId;
	String feeTerminalId;
	String msgSrc;
	Date valid_Time;
	Date at_Time;
	String srcTerminalId;
	String destTerminalId[];
	byte msgContent[];
	String destTerminalPhone;
	SimpleDateFormat sdf;
	JLabel jLabel2;
	JTextField service_Id;
	JLabel jLabel3;
	JTextField fee_Terminal_Id;
	JLabel jLabel4;
	JTextField msg_src;
	JLabel jLabel5;
	JTextField src_Terminal_Id;
	JLabel jLabel6;
	JTextField dest_Terminal_Id;
	JLabel jLabel7;
	JTextField msg_Content;
	JButton SendButton;
	JLabel jLabel8;
	JTextField ThreadNum;
	JLabel jLabel9;
	JTextField threadRunInterval;
	JLabel jLabel10;
	JTextField QueryDate;
	JLabel jLabel11;
	JTextField QueryType;
	JLabel jLabel12;
	JTextField QueryCode;
	JToggleButton QueryButton;
	JLabel jLabel13;
	JTextField CancelMsgId;
	JButton CancelButton;
	JLabel jLabel14;
	JLabel jLabel15;
	JTextField SendMsgSum;
	JLabel jLabel16;
	JTextField SuccessSendSum;
	JLabel jLabel17;
	JTextField RecvMsgSum;
	JLabel jLabel21;
	JTextField threadSleepInterval;
	JLabel jLabel22;
	JTextField myProxyState;
	JLabel jLabel23;
	JTextField loginName;
	JLabel jLabel24;
	JTextField loginPass;
	JButton loginButton;
	JScrollPane jScrollPane1;
	JTextArea allRecvContent;
	JLabel jLabel1;
	JLabel jLabel18;
	JTextField calledFrom;
	JTextField calledTo;

	public Demo30() {
		smp = null;
		loginSmProxy = false;
		sendMsgSum = 0;
		sendSuccessMsgSum = 0;
		recvDeliverMsgSum = 0;
		mt_tlmsg = 0;
		mt_tlusr = 0;
		mt_scs = 0;
		mt_wt = 0;
		mt_fl = 0;
		mo_scs = 0;
		mo_wt = 0;
		mo_fl = 0;
		cmppSubmitFrom = 0;
		cmppSubmitTo = 0;
		calledIndex = 0;
		serviceId = null;
		feeTerminalId = null;
		msgSrc = null;
		valid_Time = null;
		at_Time = null;
		srcTerminalId = null;
		destTerminalId = new String[1];
		msgContent = null;
		destTerminalPhone = null;
		sdf = new SimpleDateFormat("HH:mm:ss.sss ");
		jLabel2 = new JLabel();
		service_Id = new JTextField();
		jLabel3 = new JLabel();
		fee_Terminal_Id = new JTextField();
		jLabel4 = new JLabel();
		msg_src = new JTextField();
		jLabel5 = new JLabel();
		src_Terminal_Id = new JTextField();
		jLabel6 = new JLabel();
		dest_Terminal_Id = new JTextField();
		jLabel7 = new JLabel();
		msg_Content = new JTextField();
		SendButton = new JButton();
		jLabel8 = new JLabel();
		ThreadNum = new JTextField();
		jLabel9 = new JLabel();
		threadRunInterval = new JTextField();
		jLabel10 = new JLabel();
		QueryDate = new JTextField();
		jLabel11 = new JLabel();
		QueryType = new JTextField();
		jLabel12 = new JLabel();
		QueryCode = new JTextField();
		QueryButton = new JToggleButton();
		jLabel13 = new JLabel();
		CancelMsgId = new JTextField();
		CancelButton = new JButton();
		jLabel14 = new JLabel();
		jLabel15 = new JLabel();
		SendMsgSum = new JTextField();
		jLabel16 = new JLabel();
		SuccessSendSum = new JTextField();
		jLabel17 = new JLabel();
		RecvMsgSum = new JTextField();
		jLabel21 = new JLabel();
		threadSleepInterval = new JTextField();
		jLabel22 = new JLabel();
		myProxyState = new JTextField();
		jLabel23 = new JLabel();
		loginName = new JTextField();
		jLabel24 = new JLabel();
		loginPass = new JTextField();
		loginButton = new JButton();
		jScrollPane1 = new JScrollPane();
		allRecvContent = new JTextArea();
		jLabel1 = new JLabel();
		jLabel18 = new JLabel();
		calledFrom = new JTextField();
		calledTo = new JTextField();
		try {
			jbInit();
			setSize(new Dimension(600, 450));
			setVisible(true);
			Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment()
					.getScreenDevices()[0].getDefaultConfiguration()
					.getBounds();
			setLocation((int) (r.getWidth() - (double) 600) / 2, (int) (r
					.getHeight() - (double) 450) / 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Task() {
		sendMsgSum++;
		try {
			ProcessSubmitRep(smp.send(getSubmitMsg(calledIndex)));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void StartSendThread(int threadNum, int timeLong, int sleepInterval) {
		if (smp == null)
			return;
		for (int i = 0; i < threadNum; i++)
			(new SendReqThread30("test", this, timeLong, sleepInterval))
					.start();

	}

	public static void main(String a[]) throws Exception {
		new Demo30();
		args = (new Cfg("app.xml", false)).getArgs("CMPPConnect");
	}

	private void jbInit() throws Exception {
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(204, 230, 210));
		setDefaultCloseOperation(3);
		setForeground(Color.black);
		setResizable(false);
		setTitle("\u77ED\u6D88\u606F\u7F51\u5173\u6D4B\u8BD5\u7A0B\u5E8F");
		jLabel2.setText("\u4E1A\u52A1\u7C7B\u578B");
		jLabel2.setBounds(new Rectangle(22, 61, 57, 22));
		service_Id.setBorder(BorderFactory.createLineBorder(Color.black));
		service_Id.setToolTipText("");
		service_Id.setText("good news");
		service_Id.setBounds(new Rectangle(99, 61, 131, 22));
		jLabel3.setText("\u8BB0\u8D39\u53F7\u7801");
		jLabel3.setBounds(new Rectangle(242, 61, 57, 22));
		fee_Terminal_Id.setBorder(BorderFactory.createLineBorder(Color.black));
		fee_Terminal_Id.setText("8989899");
		fee_Terminal_Id.setBounds(new Rectangle(305, 61, 131, 22));
		jLabel4.setText("\u6D88\u606F\u6765\u6E90");
		jLabel4.setBounds(new Rectangle(450, 61, 58, 22));
		msg_src.setBorder(BorderFactory.createLineBorder(Color.black));
		msg_src.setText("huawei");
		msg_src.setBounds(new Rectangle(506, 61, 75, 22));
		jLabel5.setText("\u4E3B\u53EB\u5730\u5740");
		jLabel5.setBounds(new Rectangle(22, 94, 59, 22));
		src_Terminal_Id.setBorder(BorderFactory.createLineBorder(Color.black));
		src_Terminal_Id
				.setToolTipText("\u6E90\u7EC8\u7AEFMSISDN\u53F7\u7801, \u5373\u6B64\u77ED\u6D88\u606F\u7684\u4E3B\u53EB\u5730\u5740");
		src_Terminal_Id.setText("86138");
		src_Terminal_Id.setBounds(new Rectangle(99, 94, 131, 22));
		jLabel6.setText("\u88AB\u53EB\u5730\u5740");
		jLabel6.setBounds(new Rectangle(242, 94, 58, 22));
		dest_Terminal_Id.setBorder(BorderFactory.createLineBorder(Color.black));
		dest_Terminal_Id
				.setToolTipText("\u76EE\u7684\u7528\u6237\u624B\u673A\u53F7\u7801");
		dest_Terminal_Id.setBounds(new Rectangle(305, 94, 70, 24));
		jLabel7.setText("\u77ED\u6D88\u606F\u5185\u5BB9");
		jLabel7.setBounds(new Rectangle(22, 124, 69, 22));
		msg_Content.setBorder(BorderFactory.createLineBorder(Color.black));
		msg_Content
				.setToolTipText("\u8F93\u5165\u53D1\u9001\u7684\u77ED\u6D88\u606F\u5185\u5BB9");
		msg_Content.setText("this is a test");
		msg_Content.setBounds(new Rectangle(99, 124, 483, 22));
		SendButton.setBorder(BorderFactory.createEtchedBorder());
		SendButton.setText("\u53D1\u9001\u6D4B\u8BD5\u8BF7\u6C42");
		SendButton.setBounds(new Rectangle(492, 161, 91, 22));
		SendButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				SendButton_actionPerformed(e);
			}

		});
		jLabel8.setText("\u542F\u52A8\u7684\u7EBF\u7A0B\u6570");
		jLabel8.setBounds(new Rectangle(22, 161, 75, 22));
		ThreadNum.setBorder(BorderFactory.createLineBorder(Color.black));
		ThreadNum
				.setToolTipText("\u542F\u52A8\u591A\u4E2A\u7EBF\u7A0B\u6D4B\u8BD5SmProxy\u63D0\u4F9B\u7684API");
		ThreadNum.setText("0");
		ThreadNum.setBounds(new Rectangle(99, 161, 76, 22));
		jLabel9.setText("\u6267\u884C\u65F6\u957F");
		jLabel9.setBounds(new Rectangle(180, 161, 53, 22));
		threadRunInterval
				.setBorder(BorderFactory.createLineBorder(Color.black));
		threadRunInterval
				.setToolTipText("\u8C03\u7528\u7EBF\u7A0B\u6267\u884C\u53D1\u9001\u8BF7\u6C42\u7684\u65F6\u95F4\u957F\u5EA6");
		threadRunInterval.setText("0");
		threadRunInterval.setBounds(new Rectangle(237, 161, 76, 22));
		jLabel10.setText("\u67E5\u8BE2\u65E5\u671F");
		jLabel10.setBounds(new Rectangle(22, 190, 65, 22));
		QueryDate.setBorder(BorderFactory.createLineBorder(Color.black));
		QueryDate
				.setToolTipText("\u67E5\u8BE2\u6307\u5B9A\u65E5\u671F\u7684\u77ED\u6D88\u606F\u4FE1\u606F");
		QueryDate.setText("20011210");
		QueryDate.setBounds(new Rectangle(99, 190, 76, 22));
		jLabel11.setText("\u67E5\u8BE2\u7C7B\u522B");
		jLabel11.setBounds(new Rectangle(180, 190, 57, 22));
		QueryType.setBorder(BorderFactory.createLineBorder(Color.black));
		QueryType
				.setToolTipText("0:\u67E5\u8BE2\u603B\u6570,1:\u6309\u7167\u4E1A\u52A1\u4EE3\u7801\u67E5\u8BE2");
		QueryType.setText("0");
		QueryType.setBounds(new Rectangle(238, 190, 76, 22));
		jLabel12.setText("\u4E1A\u52A1\u7C7B\u578B");
		jLabel12.setBounds(new Rectangle(320, 190, 54, 22));
		QueryCode.setBorder(BorderFactory.createLineBorder(Color.black));
		QueryCode.setToolTipText("");
		QueryCode.setText("good news");
		QueryCode.setBounds(new Rectangle(377, 190, 76, 22));
		QueryButton.setBorder(BorderFactory.createEtchedBorder());
		QueryButton.setText("\u53D1\u9001\u67E5\u8BE2\u8BF7\u6C42");
		QueryButton.setBounds(new Rectangle(492, 190, 91, 22));
		QueryButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				QueryButton_actionPerformed(e);
			}

		});
		jLabel13.setToolTipText("");
		jLabel13.setText("\u6D88\u606F\u6807\u8BC6");
		jLabel13.setBounds(new Rectangle(22, 221, 64, 22));
		CancelMsgId.setBorder(BorderFactory.createLineBorder(Color.black));
		CancelMsgId
				.setToolTipText("\u53D1\u9001\u77ED\u6D88\u606F\u8BF7\u6C42\u7684\u65F6\u5019\u6307\u5B9A\u7684\u6D88\u606FID");
		CancelMsgId.setBounds(new Rectangle(99, 221, 131, 22));
		CancelButton.setBorder(BorderFactory.createEtchedBorder());
		CancelButton.setToolTipText("");
		CancelButton.setText("\u53D1\u9001\u53D6\u6D88\u8BF7\u6C42");
		CancelButton.setBounds(new Rectangle(299, 221, 111, 22));
		CancelButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CancelButton_actionPerformed(e);
			}

		});
		jLabel14.setFont(new Font("Dialog", 0, 14));
		jLabel14.setBorder(BorderFactory.createEtchedBorder());
		jLabel14.setText("\u7EDF\u8BA1\u6570\u636E");
		jLabel14.setBounds(new Rectangle(63, 262, 61, 22));
		jLabel15.setText("\u53D1\u9001\u6D88\u606F\u603B\u6570");
		jLabel15.setBounds(new Rectangle(24, 293, 81, 22));
		SendMsgSum.setBorder(BorderFactory.createEtchedBorder());
		SendMsgSum.setEditable(false);
		SendMsgSum.setBounds(new Rectangle(113, 293, 74, 22));
		jLabel16.setToolTipText("");
		jLabel16.setText("\u6210\u529F\u53D1\u9001\u6D88\u606F\u6570");
		jLabel16.setBounds(new Rectangle(24, 320, 90, 22));
		SuccessSendSum.setBorder(BorderFactory.createEtchedBorder());
		SuccessSendSum.setEditable(false);
		SuccessSendSum.setBounds(new Rectangle(113, 320, 74, 22));
		jLabel17.setText("\u63A5\u6536\u6D88\u606F\u603B\u6570");
		jLabel17.setBounds(new Rectangle(24, 347, 85, 22));
		RecvMsgSum.setBorder(BorderFactory.createEtchedBorder());
		RecvMsgSum
				.setToolTipText("Smc\u4E0B\u53D1\u7684\u6D88\u606F\u7684\u603B\u7D20");
		RecvMsgSum.setEditable(false);
		RecvMsgSum.setBounds(new Rectangle(113, 348, 74, 22));
		jLabel21.setToolTipText("");
		jLabel21.setText("\u7761\u7720\u65F6\u957F");
		jLabel21.setBounds(new Rectangle(320, 161, 56, 22));
		threadSleepInterval.setBorder(BorderFactory
				.createLineBorder(Color.black));
		threadSleepInterval.setText("0");
		threadSleepInterval.setBounds(new Rectangle(377, 161, 76, 22));
		jLabel22.setBorder(BorderFactory.createEtchedBorder());
		jLabel22.setText("\u8FD0\u884C\u72B6\u6001 ");
		jLabel22.setBounds(new Rectangle(26, 381, 58, 22));
		myProxyState.setBorder(BorderFactory.createEtchedBorder());
		myProxyState.setEditable(false);
		myProxyState.setBounds(new Rectangle(101, 382, 484, 22));
		jLabel23.setToolTipText("");
		jLabel23.setText("\u767B\u5F55\u5E10\u53F7");
		jLabel23.setBounds(new Rectangle(23, 21, 66, 22));
		loginName.setBorder(BorderFactory.createLineBorder(Color.black));
		loginName.setText("901234");
		loginName.setBounds(new Rectangle(100, 21, 131, 22));
		jLabel24.setText("\u767B\u5F55\u5BC6\u7801");
		jLabel24.setBounds(new Rectangle(243, 21, 60, 22));
		loginPass.setBorder(BorderFactory.createLineBorder(Color.black));
		loginPass.setBounds(new Rectangle(306, 21, 131, 22));
		loginPass.setText("123456");
		loginButton.setBorder(BorderFactory.createEtchedBorder());
		loginButton.setText("\u767B\u5F55\u7CFB\u7EDF");
		loginButton.setBounds(new Rectangle(512, 21, 70, 22));
		loginButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				loginButton_actionPerformed(e);
			}

		});
		jScrollPane1.setHorizontalScrollBarPolicy(31);
		jScrollPane1.setVerticalScrollBarPolicy(22);
		jScrollPane1.setBounds(new Rectangle(200, 253, 385, 122));
		allRecvContent.setLineWrap(true);
		jLabel1.setText("From");
		jLabel1.setBounds(new Rectangle(395, 95, 37, 20));
		jLabel18.setText("To");
		jLabel18.setBounds(new Rectangle(516, 94, 27, 19));
		calledFrom.setBorder(BorderFactory.createLineBorder(Color.black));
		calledFrom.setText("0");
		calledFrom.setBounds(new Rectangle(441, 93, 63, 22));
		calledTo.setBorder(BorderFactory.createLineBorder(Color.black));
		calledTo.setText("0");
		calledTo.setBounds(new Rectangle(547, 92, 62, 25));
		getContentPane().add(jLabel23, null);
		getContentPane().add(loginName, null);
		getContentPane().add(jLabel24, null);
		getContentPane().add(loginPass, null);
		getContentPane().add(service_Id, null);
		getContentPane().add(jLabel2, null);
		getContentPane().add(jLabel3, null);
		getContentPane().add(fee_Terminal_Id, null);
		getContentPane().add(msg_src, null);
		getContentPane().add(ThreadNum, null);
		getContentPane().add(jLabel8, null);
		getContentPane().add(jLabel9, null);
		getContentPane().add(threadSleepInterval, null);
		getContentPane().add(threadRunInterval, null);
		getContentPane().add(jLabel21, null);
		getContentPane().add(QueryDate, null);
		getContentPane().add(jLabel10, null);
		getContentPane().add(jLabel11, null);
		getContentPane().add(QueryCode, null);
		getContentPane().add(QueryType, null);
		getContentPane().add(jLabel12, null);
		getContentPane().add(CancelMsgId, null);
		getContentPane().add(CancelButton, null);
		getContentPane().add(jLabel13, null);
		getContentPane().add(jLabel14, null);
		getContentPane().add(jScrollPane1, null);
		jScrollPane1.getViewport().add(allRecvContent, null);
		getContentPane().add(RecvMsgSum, null);
		getContentPane().add(jLabel15, null);
		getContentPane().add(jLabel16, null);
		getContentPane().add(SuccessSendSum, null);
		getContentPane().add(SendMsgSum, null);
		getContentPane().add(jLabel17, null);
		getContentPane().add(myProxyState, null);
		getContentPane().add(jLabel22, null);
		getContentPane().add(dest_Terminal_Id, null);
		getContentPane().add(src_Terminal_Id, null);
		getContentPane().add(jLabel6, null);
		getContentPane().add(jLabel5, null);
		getContentPane().add(msg_Content, null);
		getContentPane().add(jLabel7, null);
		getContentPane().add(SendButton, null);
		getContentPane().add(QueryButton, null);
		getContentPane().add(jLabel4, null);
		getContentPane().add(loginButton, null);
		getContentPane().add(jLabel1, null);
		getContentPane().add(calledFrom, null);
		getContentPane().add(jLabel18, null);
		getContentPane().add(calledTo, null);
	}

	void SendButton_actionPerformed(ActionEvent e) {
		int threadNum = 0;
		int runInterval = 0;
		int sleepInterval = 0;
		if (!loginSmProxy) {
			showStateMsg("\u7CFB\u7EDF\u6CA1\u6709\u6210\u529F\u767B\u5F55");
			return;
		}
		try {
			threadNum = Integer.parseInt(ThreadNum.getText().trim());
			runInterval = Integer.parseInt(threadRunInterval.getText().trim());
			sleepInterval = Integer.parseInt(threadSleepInterval.getText()
					.trim());
			cmppSubmitFrom = Integer.parseInt(calledFrom.getText().trim());
			cmppSubmitTo = Integer.parseInt(calledTo.getText().trim());
			calledIndex = cmppSubmitFrom;
			serviceId = service_Id.getText().trim();
			feeTerminalId = fee_Terminal_Id.getText().trim();
			msgSrc = msg_src.getText().trim();
			srcTerminalId = src_Terminal_Id.getText().trim();
			destTerminalPhone = dest_Terminal_Id.getText().trim();
			msgContent = msg_Content.getText().trim().getBytes();
		} catch (Exception ex) {
			threadNum = 0;
			runInterval = 0;
		}
		if (threadNum > 0 && runInterval > 0)
			StartSendThread(threadNum, runInterval, sleepInterval);
		else
			try {
				sendMsgSum++;
				ProcessSubmitRep(smp.send(getSubmitMsg(0)));
			} catch (IllegalArgumentException illegalargumentexception) {
			} catch (Exception ex) {
				ex.printStackTrace();
			}
	}

	void QueryButton_actionPerformed(ActionEvent e) {
		if (!loginSmProxy) {
			showStateMsg("\u7CFB\u7EDF\u6CA1\u6709\u6210\u529F\u767B\u5F55");
			return;
		}
		if (smp != null)
			try {
				ProcessQueryRep(smp.send(getQueryMsg()));
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
				showStateMsg("Query\u77ED\u6D88\u606F\u8BF7\u6C42\u51FA\u73B0\u5F02\u5E38,\u53EF\u80FD\u662F\u683C\u5F0F\u9519\u8BEF");
			} catch (Exception ex) {
				ex.printStackTrace();
				showStateMsg("Query\u77ED\u6D88\u606F\u8BF7\u6C42\u51FA\u73B0\u5F02\u5E38");
			}
	}

	void CancelButton_actionPerformed(ActionEvent e) {
		if (!loginSmProxy) {
			showStateMsg("\u7CFB\u7EDF\u6CA1\u6709\u6210\u529F\u767B\u5F55");
			return;
		}
		if (smp != null)
			try {
				ProcessCancelRep(smp.send(getCancelMsg()));
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
				showStateMsg("\u53D6\u6D88\u77ED\u6D88\u606F\u547D\u4EE4\u7684\u8F93\u5165\u53C2\u6570\u4E0D\u5408\u6CD5");
			} catch (Exception ex) {
				ex.printStackTrace();
				showStateMsg("\u53D6\u6D88\u77ED\u6D88\u606F\u547D\u4EE4\u7684\u5904\u7406\u51FA\u73B0\u5F02\u5E38");
			}
	}

	void ExitButton_actionPerformed(ActionEvent e) {
		if (!loginSmProxy) {
			showStateMsg("\u7CFB\u7EDF\u6CA1\u6709\u6210\u529F\u767B\u5F55");
			return;
		}
		if (smp != null)
			try {
				smp.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				showStateMsg("\u4E0EISMG\u65AD\u8FDE\u51FA\u73B0\u5F02\u5E38");
			}
	}

	private CMPP30SubmitMessage getSubmitMsg(int index) {
		if (calledIndex < cmppSubmitTo)
			calledIndex++;
		else
			calledIndex = cmppSubmitFrom;
		destTerminalId[0] = String.valueOf(destTerminalPhone)
				+ String.valueOf(calledIndex);
		try {
			CMPP30SubmitMessage cmpp30submitmessage = new CMPP30SubmitMessage(
					1, 1, 1, 88, serviceId, 0, feeTerminalId, 1, 0, 0, 15,
					msgSrc, "01", "999", valid_Time, at_Time, srcTerminalId,
					destTerminalId, 0, msgContent, "linkid=0123456789012");
			return cmpp30submitmessage;
		} catch (IllegalArgumentException e) {
			showStateMsg("\u63D0\u4EA4\u77ED\u6D88\u606F\u8BF7\u6C42\u7684\u8F93\u5165\u53C2\u6570\u4E0D\u5408\u6CD5");
			e.printStackTrace();
			CMPP30SubmitMessage cmpp30submitmessage1 = null;
			return cmpp30submitmessage1;
		} catch (Exception e) {
			showStateMsg("\u63D0\u4EA4\u77ED\u6D88\u606F\u8BF7\u6C42\u5904\u7406\u5F02\u5E38");
			e.printStackTrace();
			CMPP30SubmitMessage cmpp30submitmessage2 = null;
			return cmpp30submitmessage2;
		}
	}

	private CMPPQueryMessage getQueryMsg() {
		String strDate = QueryDate.getText().trim();
		if (strDate.length() != 8) {
			showStateMsg("\u67E5\u8BE2\u65E5\u671F\u8F93\u5165\u53C2\u6570\u4E0D\u5408\u6CD5");
			return null;
		}
		try {
			Date queryDate = java.sql.Date.valueOf(String.valueOf(String
					.valueOf((new StringBuffer(String.valueOf(String
							.valueOf(strDate.substring(0, 4))))).append("-")
							.append(strDate.substring(4, 6)).append("-")
							.append(strDate.substring(6, 8)))));
			int queryType = Integer.parseInt(QueryType.getText().trim());
			String queryCode = QueryCode.getText().trim();
			CMPPQueryMessage cmppquerymessage2 = new CMPPQueryMessage(
					queryDate, queryType, queryCode, "");
			return cmppquerymessage2;
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			showStateMsg("\u67E5\u8BE2\u53C2\u6570\u8F93\u5165\u53C2\u6570\u4E0D\u5408\u6CD5");
			CMPPQueryMessage cmppquerymessage = null;
			return cmppquerymessage;
		} catch (Exception e) {
			e.printStackTrace();
		}
		CMPPQueryMessage cmppquerymessage1 = null;
		return cmppquerymessage1;
	}

	private CMPPCancelMessage getCancelMsg() {
		try {
			byte msg_Id[] = CancelMsgId.getText().trim().getBytes();
			CMPPCancelMessage cmppcancelmessage = new CMPPCancelMessage(msg_Id);
			return cmppcancelmessage;
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			showStateMsg("\u53D6\u6D88\u77ED\u6D88\u606F\u53C2\u6570\u8F93\u5165\u53C2\u6570\u4E0D\u5408\u6CD5");
			CMPPCancelMessage cmppcancelmessage1 = null;
			return cmppcancelmessage1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		CMPPCancelMessage cmppcancelmessage2 = null;
		return cmppcancelmessage2;
	}

	private void ProcessSubmitRep(CMPPMessage msg) {
		CMPP30SubmitRepMessage repMsg = (CMPP30SubmitRepMessage) msg;
		if (repMsg != null && repMsg.getResult() == 0)
			sendSuccessMsgSum++;
	}

	private void ProcessQueryRep(CMPPMessage msg) {
		CMPPQueryRepMessage queryRep = (CMPPQueryRepMessage) msg;
		mt_tlmsg = queryRep.getMtTlmsg();
		mt_tlusr = queryRep.getMtTlusr();
		mt_scs = queryRep.getMtScs();
		mt_wt = queryRep.getMtWt();
		mt_fl = queryRep.getMtFl();
		mo_scs = queryRep.getMoScs();
		mo_wt = queryRep.getMoWt();
		mo_fl = queryRep.getMoFl();
		showStateMsg(String.valueOf(String.valueOf((new StringBuffer(
				"mt_tlmsg:")).append(mt_tlmsg).append(",mt_tlusr:").append(
				mt_tlusr).append(",mt_scs:").append(mt_scs).append(",mt_wt")
				.append(mt_wt).append(",mt_fl:").append(mt_fl).append(
						",mo_scs:").append(mo_scs))));
	}

	private boolean ProcessCancelRep(CMPPMessage msg) {
		CMPPCancelRepMessage cancelRep = (CMPPCancelRepMessage) msg;
		if (cancelRep.getSuccessId() == 0) {
			showStateMsg("\u53D6\u6D88\u64CD\u4F5C\u6210\u529F");
			return true;
		} else {
			showStateMsg("\u53D6\u6D88\u64CD\u4F5C\u5931\u8D25");
			return false;
		}
	}

	public void ProcessRecvDeliverMsg(CMPPMessage msg) {
		CMPP30DeliverMessage deliverMsg = (CMPP30DeliverMessage) msg;
		if (deliverMsg.getRegisteredDeliver() == 0)
			try {
				if (deliverMsg.getMsgFmt() == 8)
					showStateMsg(String
							.valueOf(String
									.valueOf((new StringBuffer(
											"\u63A5\u6536\u6D88\u606F: \u4E3B\u53EB\u53F7\u7801="))
											.append(
													deliverMsg
															.getSrcterminalId())
											.append(";\u5185\u5BB9=").append(
													new String(deliverMsg
															.getMsgContent(),
															"UTF-16BE")))));
				else
					showStateMsg(String
							.valueOf(String
									.valueOf((new StringBuffer(
											"\u63A5\u6536\u6D88\u606F: \u4E3B\u53EB\u53F7\u7801="))
											.append(
													deliverMsg
															.getSrcterminalId())
											.append(";\u5185\u5BB9=")
											.append(
													new String(deliverMsg
															.getMsgContent()))
											.append(";destterm=")
											.append(
													new String(deliverMsg
															.getDestnationId()))
											.append(";serviceid=")
											.append(
													new String(deliverMsg
															.getServiceId()))
											.append(";tppid=")
											.append(deliverMsg.getTpPid())
											.append(";tpudhi=")
											.append(deliverMsg.getTpUdhi())
											.append(";msgfmt")
											.append(deliverMsg.getMsgFmt())
											.append(";srctermid=")
											.append(
													new String(deliverMsg
															.getSrcterminalId()))
											.append(";deliver=")
											.append(
													deliverMsg
															.getRegisteredDeliver())
											.append(";msgcontent=").append(
													new String(deliverMsg
															.getMsgContent()))
											.append(";LinkID=").append(
													new String(deliverMsg
															.getLinkID())))));
			} catch (Exception e) {
				e.printStackTrace();
			}
		else
			showStateMsg(String
					.valueOf(String
							.valueOf((new StringBuffer(
									"\u6536\u5230\u72B6\u6001\u62A5\u544A\u6D88\u606F\uFF1A stat="))
									.append(new String(deliverMsg.getStat()))
									.append("dest_termID=").append(
											new String(deliverMsg
													.getDestTerminalId()))
									.append(";destterm=").append(
											new String(deliverMsg
													.getDestnationId()))
									.append(";serviceid=").append(
											new String(deliverMsg
													.getServiceId())).append(
											";tppid=").append(
											deliverMsg.getTpPid()).append(
											";tpudhi=").append(
											deliverMsg.getTpUdhi()).append(
											";msgfmt").append(
											deliverMsg.getMsgFmt()).append(
											";srctermid=").append(
											new String(deliverMsg
													.getSrcterminalId()))
									.append(";deliver=").append(
											deliverMsg.getRegisteredDeliver())
									.append(";LinkID=").append(
											new String(deliverMsg.getLinkID())))));
		recvDeliverMsgSum++;
	}

	public void Terminate() {
		showStateMsg("SMC\u4E0B\u53D1\u7EC8\u65AD\u6D88\u606F");
		loginSmProxy = false;
		smp = null;
	}

	private void showStateMsg(String str) {
		if (str == null || str == "")
			return;
		allRecvContent.insert(String.valueOf(String.valueOf((new StringBuffer(
				String.valueOf(String.valueOf(sdf.format(new Date())))))
				.append(str).append("\n"))), 0);
		if (allRecvContent.getText().length() > 2048)
			allRecvContent.setText(allRecvContent.getText().substring(0, 1024));
		allRecvContent.setCaretPosition(0);
	}

	private void showStatisticData() {
		SendMsgSum.setText((new Integer(sendMsgSum)).toString());
		SuccessSendSum.setText((new Integer(sendSuccessMsgSum)).toString());
		RecvMsgSum.setText((new Integer(recvDeliverMsgSum)).toString());
	}

	void loginButton_actionPerformed(ActionEvent e) {
		if (loginSmProxy) {
			showStateMsg("\u7CFB\u7EDF\u5DF2\u7ECF\u521D\u59CB\u5316");
			return;
		}
		try {
			args.set("source-addr", loginName.getText().trim());
			args.set("shared-secret", loginPass.getText().trim());
			showStateMsg("\u7CFB\u7EDF\u6B63\u5728\u521D\u59CB\u5316");
			smp = new MySMProxy30(this, args);
			showStateMsg("\u7CFB\u7EDF\u521D\u59CB\u5316\u6210\u529F");
			loginSmProxy = true;
			(new MoniterThread()).start();
		} catch (Exception ex) {
			showStateMsg("\u7CFB\u7EDF\u521D\u59CB\u5316\u5931\u8D25");
			myProxyState.setText(ex.getMessage());
			ex.printStackTrace();
			loginSmProxy = false;
		}
	}

}
