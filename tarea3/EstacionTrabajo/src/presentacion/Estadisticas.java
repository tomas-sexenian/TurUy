package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.InternalFrameEvent;

public class Estadisticas extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
		setVisible(false);
	}

	public Estadisticas(String[][] estadisticas) {
		setIconifiable(true);
		setMaximizable(true);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
			public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
				formInternalFrameClosing(evt);
			}

			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {
			}

			@Override
			public void internalFrameDeactivated(InternalFrameEvent arg0) {
			}

			@Override
			public void internalFrameDeiconified(InternalFrameEvent arg0) {
			}

			@Override
			public void internalFrameIconified(InternalFrameEvent arg0) {
			}

			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
			}
		});
		this.setSize(800, 214);
		setClosable(true);
		setTitle("Estadisticas");

		// headers for the table
		String[] columnNames = { "#", "Actividad / Salida Turística", "Proveedor Actividad", "Tipo",
				"Cantidad de visitas" };
		JTable table = new JTable(estadisticas, columnNames);
		table.setSize(750, 214);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);

		getContentPane().add(new JScrollPane(table));
		table.setEnabled(false);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
