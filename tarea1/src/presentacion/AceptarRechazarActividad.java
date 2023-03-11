package presentacion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;

import excepciones.ActividadNoExisteException;
import logica.EstadoActividad;
import logica.Fabrica;
import logica.datatypes.DataActividadTuristica;
import logica.interfaces.IControladorActividades;

public class AceptarRechazarActividad extends JInternalFrame {

	Fabrica fabrica = Fabrica.getInstance();
	private IControladorActividades ica = fabrica.getIControladorActividades();
	private JLabel lblSalidasVigentes;
	private JComboBox comboBoxActividades;
	private JButton btnCancelar;
	private JButton btnAceptarActividad;
	private JButton btnRechazarActividad;

	private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
		limpiarFormulario();
		setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public AceptarRechazarActividad() {
		setResizable(true);
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

		setClosable(true);
		setTitle("Aceptar o Rechazar Actividad");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(640, 280);
		setLocation(myScreen.getScreenSize().width / 12, myScreen.getScreenSize().height / 12);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 205, 190, 190, 190, 40, 0 };
		gridBagLayout.rowHeights = new int[] { 30, 30, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0 };
		getContentPane().setLayout(gridBagLayout);

		lblSalidasVigentes = new JLabel("Actividad");
		GridBagConstraints gbclblSalidasVigentes = new GridBagConstraints();
		gbclblSalidasVigentes.anchor = GridBagConstraints.EAST;
		gbclblSalidasVigentes.insets = new Insets(0, 0, 5, 5);
		gbclblSalidasVigentes.gridx = 0;
		gbclblSalidasVigentes.gridy = 2;
		getContentPane().add(lblSalidasVigentes, gbclblSalidasVigentes);

		comboBoxActividades = new JComboBox();
		GridBagConstraints gbccomboBoxActividades = new GridBagConstraints();
		gbccomboBoxActividades.gridwidth = 2;
		gbccomboBoxActividades.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxActividades.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxActividades.gridx = 1;
		gbccomboBoxActividades.gridy = 2;
		getContentPane().add(comboBoxActividades, gbccomboBoxActividades);

		btnAceptarActividad = new JButton("Aceptar actividad");
		btnAceptarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aceptarActividadActionPerformed(arg0);
			}
		});
		GridBagConstraints gbcbtnAceptarActividad = new GridBagConstraints();
		gbcbtnAceptarActividad.insets = new Insets(0, 0, 5, 5);
		gbcbtnAceptarActividad.gridx = 1;
		gbcbtnAceptarActividad.gridy = 4;
		getContentPane().add(btnAceptarActividad, gbcbtnAceptarActividad);

		btnCancelar = new JButton("Cerrar");
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				setVisible(false);
			}
		});

		btnRechazarActividad = new JButton("Rechazar actividad");
		btnRechazarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rechazarActividadActionPerformed(arg0);
			}
		});
		GridBagConstraints gbcbtnRechazarActividad = new GridBagConstraints();
		gbcbtnRechazarActividad.anchor = GridBagConstraints.WEST;
		gbcbtnRechazarActividad.insets = new Insets(0, 0, 5, 5);
		gbcbtnRechazarActividad.gridx = 2;
		gbcbtnRechazarActividad.gridy = 4;
		getContentPane().add(btnRechazarActividad, gbcbtnRechazarActividad);
		GridBagConstraints gbcbtnCancelar = new GridBagConstraints();
		gbcbtnCancelar.anchor = GridBagConstraints.WEST;
		gbcbtnCancelar.insets = new Insets(0, 0, 5, 5);
		gbcbtnCancelar.gridx = 3;
		gbcbtnCancelar.gridy = 4;
		getContentPane().add(btnCancelar, gbcbtnCancelar);

	}

	protected void rechazarActividadActionPerformed(ActionEvent arg0) {

		if (comboBoxActividades.getSelectedItem() == null || comboBoxActividades.getSelectedItem().equals(" ")) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Aceptar o Rechazar Actividad",
					JOptionPane.ERROR_MESSAGE);
		} else {
			String actividad = this.comboBoxActividades.getSelectedItem().toString();
			try {
				ica.rechazarActividad(actividad);
				JOptionPane.showMessageDialog(this, "Se ha rechazado correctamente la actividad",
						"Agregar Actividad Paquete", JOptionPane.INFORMATION_MESSAGE);
				recargar();
			} catch (ActividadNoExisteException e1) {
				JOptionPane.showMessageDialog(this, "No existe la actividad", "Aceptar o Rechazar Actividad",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	protected void aceptarActividadActionPerformed(ActionEvent arg0) {
		if (comboBoxActividades.getSelectedItem() == null || comboBoxActividades.getSelectedItem().equals(" ")) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Aceptar o Rechazar Actividad",
					JOptionPane.ERROR_MESSAGE);
		} else {
			String actividad = this.comboBoxActividades.getSelectedItem().toString();
			try {
				ica.aceptarActividad(actividad);
				JOptionPane.showMessageDialog(this, "Se ha aceptado correctamente la actividad",
						"Agregar Actividad Paquete", JOptionPane.INFORMATION_MESSAGE);
				recargar();
			} catch (ActividadNoExisteException e1) {
				JOptionPane.showMessageDialog(this, "No existe la actividad", "Aceptar o Rechazar Actividad",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	void recargar() {
		comboBoxActividades.removeAllItems();
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades iActividades = fabrica.getIControladorActividades();

		List<DataActividadTuristica> actividadTuristicas;
		try {
			actividadTuristicas = iActividades.obtenerActividades();
			for (DataActividadTuristica dataAct : actividadTuristicas) {
				if (dataAct.getEstadoActividad() == EstadoActividad.AGREGADA) {
					comboBoxActividades.addItem(dataAct.getNombre());
				}
			}
		} catch (ActividadNoExisteException e) {
			JOptionPane.showMessageDialog(this, "No hay paquetes en el sistema", "Consultar Paquete",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarFormulario() {
		comboBoxActividades.setSelectedIndex(-1);
	}

}
