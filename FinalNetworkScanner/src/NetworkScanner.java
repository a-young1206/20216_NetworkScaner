import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

public class NetworkScanner extends JFrame {
	static DefaultTableModel jTable;
	private JButton startButton;
	private JLabel readyLable;
	private String myIp = null;
	public NetworkScanner() {
		setTitle("Ping");
		
		//menu begin
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu scanMenu = new JMenu("Scan");
		JMenu GotoMenu = new JMenu("Go to");
		JMenu CommandsMenu = new JMenu("Commands");
		JMenu FavoritesMenu = new JMenu("Favorites");
		JMenu ToolsMenu = new JMenu("Tools");
		JMenu HelpMenu = new JMenu("Help");
		
		JMenuItem LoadFromFile = new JMenuItem("Load from file...");
		JMenuItem Exportall = new JMenuItem("Export all...");
		JMenuItem ExportSelection = new JMenuItem("Export selection...");
		JMenuItem Quit = new JMenuItem("Quit");
		
		scanMenu.add(LoadFromFile);
		scanMenu.add(Exportall);
		scanMenu.add(ExportSelection);
		scanMenu.addSeparator();
		scanMenu.add(Quit);
		
		
		JMenuItem NextAliveHost = new JMenuItem("Next alive host");
		JMenuItem NextOpenPort = new JMenuItem("Next Open Port");
		JMenuItem NextDeadHost = new JMenuItem("Next Dead Host");
		JMenuItem PreviousAliveHost = new JMenuItem("Previous Alive Host");
		JMenuItem PreviousOpenPort = new JMenuItem("Previous Open Port");
		JMenuItem PreviousDeadHost = new JMenuItem("Previous Dead Host");
		JMenuItem Find = new JMenuItem("Find...");
		
		GotoMenu.add(NextAliveHost);
		GotoMenu.add(NextOpenPort);
		GotoMenu.add(NextDeadHost);
		GotoMenu.addSeparator();
		GotoMenu.add(PreviousAliveHost);
		GotoMenu.add(PreviousDeadHost);
		GotoMenu.add(PreviousOpenPort);
		GotoMenu.addSeparator();
		GotoMenu.add(Find);
		
		
		JMenuItem ShowDetails = new JMenuItem("Show Details");
		JMenuItem RescanIP = new JMenuItem("Rescan IP");
		JMenuItem DeleteIP = new JMenuItem("Delete IP");
		JMenuItem CopyIP = new JMenuItem("Copy IP");
		JMenuItem CopyDetails = new JMenuItem("Copy details");
		JMenuItem Open = new JMenuItem("Open");
		
		CommandsMenu.add(ShowDetails);
		CommandsMenu.addSeparator();
		CommandsMenu.add(RescanIP);
		CommandsMenu.add(DeleteIP);
		CommandsMenu.addSeparator();
		CommandsMenu.add(CopyIP);
		CommandsMenu.add(CopyDetails);
		CommandsMenu.addSeparator();
		CommandsMenu.add(Open);
		
		
		JMenuItem AddCurrent = new JMenuItem("Add current...");
		JMenuItem ManageFavorites = new JMenuItem("Manage favorites...");
		
		FavoritesMenu.add(AddCurrent);
		FavoritesMenu.add(ManageFavorites);
		
		
		JMenuItem Preferences = new JMenuItem("Preferences...");
		JMenuItem Fetchers = new JMenuItem("Fetchers...");
		JMenuItem Selection = new JMenuItem("Selection");
		JMenuItem ScanStatistics = new JMenuItem("Scan statistics");
		
		ToolsMenu.add(Preferences);
		ToolsMenu.add(Fetchers);
		ToolsMenu.addSeparator();
		ToolsMenu.add(Selection);
		ToolsMenu.add(ScanStatistics);
		
		
		JMenuItem GettingStarted = new JMenuItem("Getting started");
		JMenuItem OfficialWebsite = new JMenuItem("Official Website");
		JMenuItem FAQ = new JMenuItem("FAQ");
		JMenuItem ReportAnIssue = new JMenuItem("Report an issue");
		JMenuItem Plugins = new JMenuItem("Plugins");
		JMenuItem CommandLineUsage = new JMenuItem("Command line usage");
		JMenuItem CheckForNewerVersion = new JMenuItem("Check for newer version...");
		JMenuItem About = new JMenuItem("About");
		
		HelpMenu.add(GettingStarted);
		HelpMenu.addSeparator();
		HelpMenu.add(OfficialWebsite);
		HelpMenu.add(FAQ);
		HelpMenu.add(ReportAnIssue);
		HelpMenu.add(Plugins);
		HelpMenu.addSeparator();
		HelpMenu.add(CommandLineUsage);
		HelpMenu.addSeparator();
		HelpMenu.add(CheckForNewerVersion);
		HelpMenu.addSeparator();
		HelpMenu.add(About);
		
			
		menuBar.add(scanMenu);
		menuBar.add(GotoMenu);
		menuBar.add(CommandsMenu);
		menuBar.add(FavoritesMenu);
		menuBar.add(ToolsMenu);
		menuBar.add(HelpMenu);
		
		Quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);	
			}
		});
		
		//menu end
		
		//status bar begin
		
		JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		add(statusPanel, BorderLayout.SOUTH);
		readyLable = new JLabel("Ready");
		readyLable.setPreferredSize(new Dimension(220, 20));
		readyLable.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		JLabel displayLable = new JLabel("Display: All");
		displayLable.setPreferredSize(new Dimension(100, 20));
		displayLable.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		JLabel ThreadsLable = new JLabel("Threads: 0");
		ThreadsLable.setPreferredSize(new Dimension(100, 20));
		ThreadsLable.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		JProgressBar loading = new JProgressBar();
	    loading.setMinimum(0);
	    loading.setMaximum(253);
	    loading.setPreferredSize(new Dimension(240, 20));
		loading.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		
		statusPanel.add(readyLable);
		statusPanel.add(displayLable);
		statusPanel.add(ThreadsLable);
		statusPanel.add(loading);
		
		
		//status bar end
		
		//table begin
		
		
		String[] titles = new String[] {
				"IP", "Ping", "Hostname", "TTL", "Port"
		};
		Object[][] stats = initTable();
		
		JTable jTable = new JTable(stats, titles);
		
		JScrollPane sp = new JScrollPane(jTable);
		add(sp, BorderLayout.CENTER);
		
		
		
		//table end
		
		//toolbar begin
		Font myFont= new Font("Serif", Font.BOLD, 16);
		JToolBar toolbar1=new JToolBar();
		toolbar1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JToolBar toolbar2=new JToolBar();
		toolbar2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel RangeStartLabel = new JLabel("IP Range : ");
		JTextField rangeStartTextField= new JTextField(10);
		JLabel RangeEndLabel = new JLabel("to");
		JTextField rangeEndTextField= new JTextField(10);
		String set[]={"IP Range","Random","Text File"}; 
		JComboBox comboBox = new JComboBox(set);
		
		ImageIcon setimage = new ImageIcon("./icon/set1.png");
	    JButton seticon = new JButton(setimage);
	    seticon.setBorderPainted(false);
	    seticon.setContentAreaFilled(false);
	    seticon.setOpaque(false);
		
		RangeStartLabel.setFont(myFont);
		RangeStartLabel.setPreferredSize(new Dimension(80,30));
		RangeEndLabel.setFont(myFont);
		RangeEndLabel.setPreferredSize(new Dimension(15,30));
		
		toolbar1.add(RangeStartLabel);
		toolbar1.add(rangeStartTextField);
		toolbar1.add(RangeEndLabel);
		toolbar1.add(rangeEndTextField);
		toolbar1.add(comboBox);
		toolbar1.add(seticon);
		
		
		JLabel hostNameLabel = new JLabel("Hostname : ");
		JTextField hostNameTextField = new JTextField(10);
        String hostname;
        try {
            InetAddress IP = InetAddress.getLocalHost();
        	myIp=IP.getHostAddress();
            hostname = IP.getHostName();
            hostNameTextField.setText(hostname);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String fixedIP =myIp.substring(0, myIp.lastIndexOf(".")+1);
		rangeStartTextField.setText(fixedIP + "1");
		rangeEndTextField.setText(fixedIP+"254");
		
        
		JButton upButton = new JButton("IP¡è");
		upButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rangeStartTextField.setText(myIp);
				rangeEndTextField.setText(myIp);
			}
		});
		
		String option[]= {"/26","/24","/16","255...192","255...128","255...0","255..0.0","255.0.0.0"};
		JComboBox optionComboBox = new JComboBox(option);
		startButton = new JButton("¢ºStart");
		startButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();

				if(b.getText().equals("¢ºStart")) {
					loading.setStringPainted(true);
					Pinging[] ping = new Pinging[254];
					//add
					for(int i = 0; i <= 253; i++) {
						ping[i] = new Pinging(fixedIP + (i+1));
						ping[i].start();
						loading.setValue(i);
					}
					for(int i = 0;i<=253;i++) {
						Object[] msg = ping[i].getMsg();
						stats[i][0] = msg[0];
						
						if(msg[1]!=null) {
							stats[i][1] = msg[1];
						}else {
							stats[i][1]="[n/a]";
						}
						if(msg[2]!=null) {
							stats[i][2] = msg[2];
						}else {
							stats[i][2]="[n/s]";
						}
						if(msg[3]!=null) {
							stats[i][3] = msg[3];
						}else {
							stats[i][3]="[n/s]";
						}
						loading.setValue(i);
						loading.repaint();
					}
					/*if(msg[1] != null || msg[2] != null || msg[3] == null) {
						portscan.
						scan value == null => stats[i][4] = "[n/s]"
						scan value != null => assign value stats[i][4] 
					}*/
					jTable.repaint();
					loading.setStringPainted(true);
					readyLable.setText("Ready");
					}
				else
					b.setText("¢ºStart");	
			}
		});
		
		ImageIcon menuimage = new ImageIcon("./icon/menuicon.png");
	    JButton menuicon = new JButton(menuimage);
	    menuicon.setBorderPainted(false);
	    menuicon.setContentAreaFilled(false);
	    menuicon.setOpaque(false);
		
		hostNameLabel.setFont(myFont);
		hostNameTextField.setPreferredSize(new Dimension(90,30));
		upButton.setPreferredSize(new Dimension(50, 30));
		optionComboBox.setPreferredSize(new Dimension(90, 30));
		startButton.setPreferredSize(new Dimension(70, 30));
		
		toolbar2.add(hostNameLabel);
		toolbar2.add(hostNameTextField);
		toolbar2.add(upButton);
		toolbar2.add(optionComboBox);
		toolbar2.add(startButton);
		toolbar2.add(menuicon);
		
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(toolbar1,BorderLayout.NORTH);
		pane.add(toolbar2,BorderLayout.SOUTH);
		
		add(pane, BorderLayout.NORTH);
		
		//toolbar end
		
		setSize(700,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}

	public Object[][] initTable() {
		Object[][] result = new Object[254][5];
		return result;
	}
	
	
	public static void main(String[] args) {
		NetworkScanner OL = new NetworkScanner();
	}

}