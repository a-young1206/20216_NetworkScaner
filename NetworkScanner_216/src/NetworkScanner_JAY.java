/*import java.awt.BorderLayout;
import java.io.File;
import java.net.InetAddress;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class NetworkScanner_JAY extends JFrame{
	
	String titles[] = new String[] {
			"IP", "Ping", "TTL", "Hostname", "Ports [4+]"};
	
	public NetworkScanner_JAY() {
		super("IP Scanner");
		setSize(800, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//File pwd = new File(".");
		//Object[][] stats = getFileStats(pwd);
		
		JTable jTable = new JTable(stats, titles);
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		jTable.setColumnSelectionAllowed(true);
		
		JScrollPane jScrollPane = new JScrollPane(jTable);
		getContentPane().add(jScrollPane, BorderLayout.CENTER);
	}
	
		Object[][] results = new Object[][titles.length];
		
		for(int i=0;i<255;i++) {			
			try {
				InetAddress address = InetAddress.getByName("192.168.0."+i);
				boolean reachable = address.isReachable(200);
				results[i][0] = address+"";
				results[i][1] = "";
				results[i][2] = "";
				results[i][3] = address.getHostName();
				results[i][4] = "";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return results;
	}

	public static void main(String[] args) {
		NetworkScanner_JAY tableFeature = new NetworkScanner_JAY();
		tableFeature.setVisible(true);
	}

}*/


