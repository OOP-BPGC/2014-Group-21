package driver_file;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class NirmaanManager {

	private JFrame frmNirmaanActivityManager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NirmaanManager window = new NirmaanManager();
					window.frmNirmaanActivityManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NirmaanManager() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNirmaanActivityManager = new JFrame();
		frmNirmaanActivityManager.setTitle("Nirmaan Activity Manager");
		frmNirmaanActivityManager.setBounds(100, 100, 532, 420);
		frmNirmaanActivityManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
