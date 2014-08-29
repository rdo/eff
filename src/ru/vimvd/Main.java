package ru.vimvd;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.thoughtworks.xstream.XStream;

public class Main {

	

	private JFrame frame;
	
	private JLabel lblNewLabel;
	

	/**
	 * Launch the application.
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace(new PrintWriter(new File("log.txt")));
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws FileNotFoundException 
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setTitle("ОЦЕНКА ЭФФЕКТИВНОСТИ ИНФОРМАЦИОННЫХ ПРОЦЕССОВ В УСЛОВИЯХ ПРОТИВОДЕЙСТВИЯ УТЕЧКЕ ИНФОРМАЦИИ");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel buttonPanel = new JPanel();
		frame.getContentPane().add(buttonPanel, BorderLayout.WEST);
		buttonPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel stagePanel = new JPanel();
		frame.getContentPane().add(stagePanel, BorderLayout.NORTH);
		
		/*JButton button3 = new JButton("Эффективность");
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				InfoDialog dialog = new InfoDialog(frame, DataHolder.getInstance().calculateMain());
				
			}
		});
		stagePanel.add(button3);*/
		
		JButton level2 = new JButton("Уровень 2");
		level2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MultiTableDialog dialog = new MultiTableDialog(frame, "Уровень 2", 600, 300);
				dialog.addTabs(DataHolder.getInstance().getLevel2());
				
			}
		});
		stagePanel.add(level2);
		
		JButton level1 = new JButton("Уровень 1");
		level1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MultiTableDialog dialog = new MultiTableDialog(frame, "Уровень 1", 600, 200);
				dialog.addTabs(DataHolder.getInstance().getLevel1());
			}
		});
		stagePanel.add(level1);
		
		JButton effectivness = new JButton("Эффективность");
		effectivness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaticTableModel model=DataHolder.getInstance().getLevel0();
				SingleTableDialog dialog = new SingleTableDialog(frame, "Эффективность", 600, 300, model);
			}
		});
		stagePanel.add(effectivness);
		
		JButton koeff1 = new JButton("Коэффициент влияния");
		koeff1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MultiTableDialog dialog = new MultiTableDialog(frame, "Коэффициент влияния", 800, 600);
				dialog.addTabs(DataHolder.getInstance().regressionModel());
				
			}
		});
		stagePanel.add(koeff1);
		
		JButton koeff2 = new JButton("Коэффициент согласованности");
		koeff2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MultiTableDialog dialog = new MultiTableDialog(frame, "Коэффициент согласования", 800, 600);
				dialog.addTabs(DataHolder.getInstance().correlationModel());
			}
		});
		stagePanel.add(koeff2);
		
		
		List<TableDTO> allTables = null;
		XStream stream = new XStream();
		File f = new File("config.xml");
		try {
			allTables=(List<TableDTO>) stream.fromXML(f);
			DataHolder.getInstance().setRawData(allTables);
			//stream.toXML(allTables, new FileOutputStream(f));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(TableDTO table: allTables){
			UpdatableTableLinkedButton btn1 = new UpdatableTableLinkedButton(table, frame);
			buttonPanel.add(btn1);
		}
		
		
		
		
		
		//System.out.println(frame.getContentPane().countComponents());
		//frame.getContentPane().remove(1);
		
	}
	
	
	

	
	
	

}
