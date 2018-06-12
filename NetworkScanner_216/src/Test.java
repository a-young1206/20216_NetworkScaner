import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.SoftBevelBorder;

public class Test extends JFrame {
	public Test() {
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
		JLabel readyLable = new JLabel("Ready");
		readyLable.setPreferredSize(new Dimension(320, 20));
		readyLable.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		JLabel displayLable = new JLabel("Display: All");
		displayLable.setPreferredSize(new Dimension(170, 20));
		displayLable.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		JLabel ThreadsLable = new JLabel("Threads: 0");
		ThreadsLable.setPreferredSize(new Dimension(170, 20));
		ThreadsLable.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		
		statusPanel.add(readyLable);
		statusPanel.add(displayLable);
		statusPanel.add(ThreadsLable);
		
		//status bar end
		
		//table begin
		
		String[] titles = {"IP","Ping","Hostname","TTL","Ports [0+]"};
		
		Object[][] stats = initTable();
		JTable jTable = new JTable(stats,titles);
		add(jTable, BorderLayout.CENTER);
		
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
		
		RangeStartLabel.setFont(myFont);
		RangeStartLabel.setPreferredSize(new Dimension(90,30));
		RangeEndLabel.setFont(myFont);
		RangeEndLabel.setPreferredSize(new Dimension(15,30));
		
		String set[]={"IP Range","Random","Text File"};  
		JComboBox comboBox = new JComboBox(set);

		toolbar1.add(RangeStartLabel);
		toolbar1.add(rangeStartTextField);
		toolbar1.add(RangeEndLabel);
		toolbar1.add(rangeEndTextField);
		toolbar1.add(comboBox);
		
		
		JLabel hostNameLabel = new JLabel("Hostname : ");
		JTextField hostNameTextField = new JTextField(10);
		JButton upButton = new JButton("IP¡è");
		JComboBox optionComboBox = new JComboBox();
		optionComboBox.addItem("/24");
		optionComboBox.addItem("/26");
		JButton startButton = new JButton("Start");
		
		hostNameLabel.setFont(myFont);
		hostNameTextField.setPreferredSize(new Dimension(90,30));
		upButton.setPreferredSize(new Dimension(50, 30));
		optionComboBox.setPreferredSize(new Dimension(90, 30));
		startButton.setPreferredSize(new Dimension(50, 30));
		
		toolbar2.add(hostNameLabel);
		toolbar2.add(hostNameTextField);
		toolbar2.add(upButton);
		toolbar2.add(optionComboBox);
		toolbar2.add(startButton);
		
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(toolbar1,BorderLayout.NORTH);
		pane.add(toolbar2,BorderLayout.SOUTH);
		
		add(pane, BorderLayout.NORTH);
		
		//toolbar end
		
		setSize(700,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public Object[][] initTable(){
		Object[][] result = new Object[254][5];
		return result;
	}

	public static void main(String[] args) {
		Test test = new Test();

	}

}