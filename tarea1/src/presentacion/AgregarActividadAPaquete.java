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
import excepciones.ActividadRepetidaException;
import excepciones.DepartamentoNoExisteException;
import excepciones.NoExisteEntidadException;
import excepciones.NoExistePaqueteException;
import excepciones.PaqueteCompradoException;
import logica.Fabrica;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataDepartamento;
import logica.datatypes.DataPaqueteActividades;
import logica.interfaces.IControladorActividades;
import logica.interfaces.IControladorDepartamentos;

public class AgregarActividadAPaquete extends JInternalFrame {

	Fabrica fabrica = Fabrica.getInstance();
	private IControladorActividades ica = fabrica.getIControladorActividades();

	private JComboBox comboBoxPaquetes;
	private JLabel lblActividad;
	private JComboBox comboBoxDepartamentos;
	private JLabel lblSalidasVigentes;
	private JComboBox comboBoxActividades;
	private JButton btnCancelar;
	private JButton btnAgregarActividad;

	private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
		limpiarFormulario();
		setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public AgregarActividadAPaquete() {
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
		setTitle("Agregar actividad turistica a paquete");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(640, 280);
		setLocation(myScreen.getScreenSize().width / 12, myScreen.getScreenSize().height / 12);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 170, 190, 190, 40, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 30, 30, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0 };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblA = new JLabel("Paquete");
		GridBagConstraints gbclblA = new GridBagConstraints();
		gbclblA.anchor = GridBagConstraints.EAST;
		gbclblA.insets = new Insets(0, 0, 5, 5);
		gbclblA.gridx = 0;
		gbclblA.gridy = 1;
		getContentPane().add(lblA, gbclblA);

		comboBoxPaquetes = new JComboBox();
		GridBagConstraints gbccomboBoxPaquetes = new GridBagConstraints();
		gbccomboBoxPaquetes.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxPaquetes.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxPaquetes.gridx = 1;
		gbccomboBoxPaquetes.gridy = 1;
		getContentPane().add(comboBoxPaquetes, gbccomboBoxPaquetes);

		lblActividad = new JLabel("Departamento");
		GridBagConstraints gbclblActividad = new GridBagConstraints();
		gbclblActividad.anchor = GridBagConstraints.EAST;
		gbclblActividad.insets = new Insets(0, 0, 5, 5);
		gbclblActividad.gridx = 0;
		gbclblActividad.gridy = 2;
		getContentPane().add(lblActividad, gbclblActividad);

		comboBoxDepartamentos = new JComboBox();
		comboBoxDepartamentos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (comboBoxDepartamentos.getSelectedItem() != null) {
					String departamento = comboBoxDepartamentos.getSelectedItem().toString();
					IControladorActividades ctrlAct = fabrica.getIControladorActividades();
					try {
						comboBoxActividades.removeAllItems();
						comboBoxActividades.addItem(" ");
						List<DataActividadTuristica> acts = ctrlAct.obtenerActividadesConfirmadas(departamento);
						for (int i = 0; i < acts.size(); i++) {
							comboBoxActividades.addItem(acts.get(i).getNombre());
						}
						// comboBoxActividad.setSelectedIndex(-1);
					} catch (ActividadNoExisteException e1) {
						JOptionPane.showMessageDialog(comboBoxActividades, e1.getMessage(), "Consultar Actividad",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		IControladorDepartamentos ctrlDpto = fabrica.getIControladorDepartamentos();
		try {
			DataDepartamento[] dptos = ctrlDpto.obtenerDepartamentos();
			for (int i = 0; i < dptos.length; i++) {
				comboBoxDepartamentos.addItem(dptos[i].getNombre());
			}
		} catch (DepartamentoNoExisteException e1) {
		}
		comboBoxDepartamentos.setSelectedIndex(-1);

		GridBagConstraints gbccomboBoxDepartamentos = new GridBagConstraints();
		gbccomboBoxDepartamentos.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxDepartamentos.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxDepartamentos.gridx = 1;
		gbccomboBoxDepartamentos.gridy = 2;
		getContentPane().add(comboBoxDepartamentos, gbccomboBoxDepartamentos);

		lblSalidasVigentes = new JLabel("Actividad");
		GridBagConstraints gbclblSalidasVigentes = new GridBagConstraints();
		gbclblSalidasVigentes.anchor = GridBagConstraints.EAST;
		gbclblSalidasVigentes.insets = new Insets(0, 0, 5, 5);
		gbclblSalidasVigentes.gridx = 0;
		gbclblSalidasVigentes.gridy = 3;
		getContentPane().add(lblSalidasVigentes, gbclblSalidasVigentes);

		comboBoxActividades = new JComboBox();
		GridBagConstraints gbccomboBoxActividades = new GridBagConstraints();
		gbccomboBoxActividades.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxActividades.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxActividades.gridx = 1;
		gbccomboBoxActividades.gridy = 3;
		getContentPane().add(comboBoxActividades, gbccomboBoxActividades);

		btnAgregarActividad = new JButton("Agregar actividad");
		btnAgregarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarActividadPaqueteActionPerformed(arg0);
			}
		});
		GridBagConstraints gbcbtnAgregarActividad = new GridBagConstraints();
		gbcbtnAgregarActividad.insets = new Insets(0, 0, 5, 5);
		gbcbtnAgregarActividad.gridx = 1;
		gbcbtnAgregarActividad.gridy = 6;
		getContentPane().add(btnAgregarActividad, gbcbtnAgregarActividad);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		GridBagConstraints gbcbtnCancelar = new GridBagConstraints();
		gbcbtnCancelar.insets = new Insets(0, 0, 5, 5);
		gbcbtnCancelar.gridx = 2;
		gbcbtnCancelar.gridy = 6;
		getContentPane().add(btnCancelar, gbcbtnCancelar);

	}

	protected void agregarActividadPaqueteActionPerformed(ActionEvent arg0) {
		if (comboBoxPaquetes.getSelectedItem() == null || comboBoxDepartamentos.getSelectedItem() == null
				|| comboBoxActividades.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Agregar Actividad a Paquete",
					JOptionPane.ERROR_MESSAGE);
		} else {
			String paquete = this.comboBoxPaquetes.getSelectedItem().toString();
			String actividad = this.comboBoxActividades.getSelectedItem().toString();
			try {
				ica.agregarActividadPaquete(paquete, actividad);
				JOptionPane.showMessageDialog(this, "Se ha agregado correctamente la actividad",
						"Agregar Actividad Paquete", JOptionPane.INFORMATION_MESSAGE);
			} catch (NoExistePaqueteException e) {
				JOptionPane.showMessageDialog(this, "No existe el paquete", "Agregar Actividad Paquete",
						JOptionPane.ERROR_MESSAGE);
			} catch (ActividadNoExisteException e1) {
				JOptionPane.showMessageDialog(this, "No existe la actividad", "Agregar Actividad Paquete",
						JOptionPane.ERROR_MESSAGE);
			} catch (ActividadRepetidaException e2) {
				JOptionPane.showMessageDialog(this, "Esa actividad ya fue agregada al paquete",
						"Agregar Actividad Paquete", JOptionPane.ERROR_MESSAGE);
			} catch (PaqueteCompradoException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Agregar Actividad Paquete",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	void recargar() {
		comboBoxPaquetes.removeAllItems();
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades iActividades = fabrica.getIControladorActividades();

		List<DataPaqueteActividades> paquetes;
		try {
			paquetes = iActividades.obtenerPaquetes();
			for (DataPaqueteActividades paq : paquetes) {
				if (!paq.getComprado()) {
					comboBoxPaquetes.addItem(paq.getNombre());
				}
			}
		} catch (NoExisteEntidadException e) {
			JOptionPane.showMessageDialog(this, "No hay paquetes en el sistema", "Consultar Paquete",
					JOptionPane.ERROR_MESSAGE);
		}
		comboBoxPaquetes.setSelectedIndex(-1);

		IControladorDepartamentos ctrlDpto = fabrica.getIControladorDepartamentos();
		try {
			DataDepartamento[] dptos = ctrlDpto.obtenerDepartamentos();
			for (int i = 0; i < dptos.length; i++) {
				comboBoxDepartamentos.addItem(dptos[i].getNombre());
			}
		} catch (DepartamentoNoExisteException e1) {
		}
		comboBoxDepartamentos.setSelectedIndex(-1);
		comboBoxActividades.setSelectedIndex(-1);
	}

	private void limpiarFormulario() {
		comboBoxDepartamentos.setSelectedIndex(-1);
		comboBoxActividades.setSelectedIndex(-1);
		comboBoxPaquetes.setSelectedIndex(-1);
	}

}
