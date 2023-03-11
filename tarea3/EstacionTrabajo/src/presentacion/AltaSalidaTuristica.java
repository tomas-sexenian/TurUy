package presentacion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;

import com.toedter.calendar.JDateChooser;

import excepciones.ActividadNoExisteException;
import excepciones.DepartamentoNoExisteException;
import excepciones.SalidaRepetidaException;
import logica.EstadoActividad;
import logica.Fabrica;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataDepartamento;
import logica.interfaces.IControladorDepartamentos;
import logica.interfaces.IControladorSalidas;
import lu.tudor.santec.jtimechooser.JTimeChooser;

public class AltaSalidaTuristica extends JInternalFrame {

	/**
	 * Launch the application.
	 */

	private Fabrica fabrica = Fabrica.getInstance();
	private IControladorSalidas controlSal = fabrica.getIControladorSalidas();

	private JTextField textFieldDepartamento;
	private JTextField textFieldNombre;
	private JTextField textFieldHora;
	private JTextField textFieldSalida;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser2;
	private JTextField textFieldCantidad;
	private JComboBox comboBoxActividades;
	private JComboBox comboBoxDepartamento;
	private DataActividadTuristica[] acts;
	private JButton btnAceptar;
	private JTimeChooser timeChooser;

	private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
		limpiarFormulario();
		setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public AltaSalidaTuristica() {

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
		setTitle("Alta de salida turistica");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(myScreen.getScreenSize().width / 2, myScreen.getScreenSize().height / 2);
		setLocation(myScreen.getScreenSize().width / 12, myScreen.getScreenSize().height / 12);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 200, 170, 170, 40, 0 };
		gridBagLayout.rowHeights = new int[] { 00, 30, 30, 30, 30, 30, 30, 30, 30, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblA = new JLabel("Departamento");
		GridBagConstraints gbclblA = new GridBagConstraints();
		gbclblA.anchor = GridBagConstraints.EAST;
		gbclblA.insets = new Insets(0, 0, 5, 5);
		gbclblA.gridx = 0;
		gbclblA.gridy = 1;
		getContentPane().add(lblA, gbclblA);

		comboBoxDepartamento = new JComboBox();

		comboBoxDepartamento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if (comboBoxDepartamento.getSelectedItem() != null && comboBoxDepartamento.getSelectedItem() != " ") {

					String departamento = (String) comboBoxDepartamento.getSelectedItem();
					try {
						comboBoxActividades.removeAllItems();
						acts = controlSal.obtenerActividadesDepartamento(departamento);
						for (int i = 0; i < acts.length; i++) {
							if (acts[i].getEstadoActividad() == EstadoActividad.CONFIRMADA) {
								comboBoxActividades.addItem(new ItemTexto(acts[i].getNombre()));
							}

						}
						comboBoxActividades.setSelectedIndex(-1);

					} catch (DepartamentoNoExisteException e1) {
						JOptionPane.showMessageDialog(textFieldDepartamento, e1.getMessage(), "Registrar Actividad",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		GridBagConstraints gbccomboBoxDepartamento = new GridBagConstraints();
		gbccomboBoxDepartamento.gridwidth = 2;
		gbccomboBoxDepartamento.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxDepartamento.fill = GridBagConstraints.BOTH;
		gbccomboBoxDepartamento.gridx = 1;
		gbccomboBoxDepartamento.gridy = 1;
		getContentPane().add(comboBoxDepartamento, gbccomboBoxDepartamento);

		JLabel lblact = new JLabel("Lista actividades");
		GridBagConstraints gbclblact = new GridBagConstraints();
		gbclblact.anchor = GridBagConstraints.EAST;
		gbclblact.insets = new Insets(0, 0, 5, 5);
		gbclblact.gridx = 0;
		gbclblact.gridy = 2;
		getContentPane().add(lblact, gbclblact);

		comboBoxActividades = new JComboBox();

		GridBagConstraints gbccomboBoxActividades = new GridBagConstraints();
		gbccomboBoxActividades.gridwidth = 2;
		gbccomboBoxActividades.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxActividades.fill = GridBagConstraints.BOTH;
		gbccomboBoxActividades.gridx = 1;
		gbccomboBoxActividades.gridy = 2;

		getContentPane().add(comboBoxActividades, gbccomboBoxActividades);

		JLabel lblB = new JLabel("Nombre");
		GridBagConstraints gbclblB = new GridBagConstraints();
		gbclblB.anchor = GridBagConstraints.EAST;
		gbclblB.insets = new Insets(0, 0, 5, 5);
		gbclblB.gridx = 0;
		gbclblB.gridy = 3;
		getContentPane().add(lblB, gbclblB);

		textFieldNombre = new JTextField();
		GridBagConstraints gbctextFieldN = new GridBagConstraints();
		gbctextFieldN.gridwidth = 2;
		gbctextFieldN.insets = new Insets(0, 0, 5, 5);
		gbctextFieldN.fill = GridBagConstraints.BOTH;
		gbctextFieldN.gridx = 1;
		gbctextFieldN.gridy = 3;
		getContentPane().add(textFieldNombre, gbctextFieldN);
		textFieldNombre.setColumns(10);

		JLabel lblG = new JLabel("Fecha de salida");
		GridBagConstraints gbclblG = new GridBagConstraints();
		gbclblG.anchor = GridBagConstraints.EAST;
		gbclblG.insets = new Insets(0, 0, 5, 5);
		gbclblG.gridx = 0;
		gbclblG.gridy = 4;
		getContentPane().add(lblG, gbclblG);

		dateChooser = new JDateChooser();
		GridBagConstraints gbcdateChooser = new GridBagConstraints();
		gbcdateChooser.insets = new Insets(0, 0, 5, 5);
		gbcdateChooser.fill = GridBagConstraints.BOTH;
		gbcdateChooser.gridx = 1;
		gbcdateChooser.gridy = 4;
		getContentPane().add(dateChooser, gbcdateChooser);

		JLabel lblE = new JLabel("Hora");
		GridBagConstraints gbclblE = new GridBagConstraints();
		gbclblE.anchor = GridBagConstraints.EAST;
		gbclblE.insets = new Insets(0, 0, 5, 5);
		gbclblE.gridx = 0;
		gbclblE.gridy = 5;
		getContentPane().add(lblE, gbclblE);

		timeChooser = new JTimeChooser();

		textFieldHora = new JTextField();
		GridBagConstraints gbctextFieldC = new GridBagConstraints();
		gbctextFieldC.gridwidth = 2;
		gbctextFieldC.insets = new Insets(0, 0, 5, 5);
		gbctextFieldC.fill = GridBagConstraints.BOTH;
		gbctextFieldC.gridx = 1;
		gbctextFieldC.gridy = 5;
		getContentPane().add(timeChooser, gbctextFieldC);
		textFieldHora.setColumns(10);

		JLabel lblF = new JLabel("Lugar de salida");
		GridBagConstraints gbclblF = new GridBagConstraints();
		gbclblF.anchor = GridBagConstraints.EAST;
		gbclblF.insets = new Insets(0, 0, 5, 5);
		gbclblF.gridx = 0;
		gbclblF.gridy = 6;
		getContentPane().add(lblF, gbclblF);

		textFieldSalida = new JTextField();
		GridBagConstraints gbctextFieldCi = new GridBagConstraints();
		gbctextFieldCi.gridwidth = 2;
		gbctextFieldCi.insets = new Insets(0, 0, 5, 5);
		gbctextFieldCi.fill = GridBagConstraints.BOTH;
		gbctextFieldCi.gridx = 1;
		gbctextFieldCi.gridy = 6;
		getContentPane().add(textFieldSalida, gbctextFieldCi);
		textFieldSalida.setColumns(10);

		JLabel lblI = new JLabel("Cant max de turistas");
		GridBagConstraints gbclblI = new GridBagConstraints();
		gbclblI.anchor = GridBagConstraints.EAST;
		gbclblI.insets = new Insets(0, 0, 5, 5);
		gbclblI.gridx = 0;
		gbclblI.gridy = 7;
		getContentPane().add(lblI, gbclblI);

		textFieldCantidad = new JTextField();
		GridBagConstraints gbctextFieldCa = new GridBagConstraints();
		gbctextFieldCa.gridwidth = 2;
		gbctextFieldCa.insets = new Insets(0, 0, 5, 5);
		gbctextFieldCa.fill = GridBagConstraints.BOTH;
		gbctextFieldCa.gridx = 1;
		gbctextFieldCa.gridy = 7;
		getContentPane().add(textFieldCantidad, gbctextFieldCa);
		textFieldCantidad.setColumns(10);

		JLabel lblH = new JLabel("Fecha de alta");
		GridBagConstraints gbclblH = new GridBagConstraints();
		gbclblH.anchor = GridBagConstraints.EAST;
		gbclblH.insets = new Insets(0, 0, 5, 5);
		gbclblH.gridx = 0;
		gbclblH.gridy = 8;
		getContentPane().add(lblH, gbclblH);

		dateChooser2 = new JDateChooser();
		GridBagConstraints gbcdateChooser2 = new GridBagConstraints();
		gbcdateChooser2.insets = new Insets(0, 0, 5, 5);
		gbcdateChooser2.fill = GridBagConstraints.BOTH;
		gbcdateChooser2.gridx = 1;
		gbcdateChooser2.gridy = 8;
		getContentPane().add(dateChooser2, gbcdateChooser2);

		// textFieldFechaAlta = new JTextField();
		// GridBagConstraints gbctextFieldF = new GridBagConstraints();
		// gbctextFieldF.gridwidth = 2;
		// gbctextFieldF.insets = new Insets(0, 0, 5, 5);
		// gbctextFieldF.fill = GridBagConstraints.HORIZONTAL;
		// gbctextFieldF.gridx = 1;
		// gbctextFieldF.gridy = 7;
		// getContentPane().add(textFieldFechaAlta, gbctextFieldF);
		// textFieldFechaAlta.setColumns(10);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				altaSalidaActionPerformed(arg0);
			}
		});

		GridBagConstraints gbcbtnAceptar = new GridBagConstraints();
		gbcbtnAceptar.insets = new Insets(0, 0, 0, 5);
		gbcbtnAceptar.gridx = 1;
		gbcbtnAceptar.gridy = 9;
		getContentPane().add(btnAceptar, gbcbtnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				setVisible(false);
			}

		});
		GridBagConstraints gbcbtnCancelar = new GridBagConstraints();
		gbcbtnCancelar.insets = new Insets(0, 0, 0, 5);
		gbcbtnCancelar.gridx = 2;
		gbcbtnCancelar.gridy = 9;
		getContentPane().add(btnCancelar, gbcbtnCancelar);

	}

	protected void refrescar() {
		Fabrica fabrica = Fabrica.getInstance();
		comboBoxDepartamento.removeAllItems();
		IControladorDepartamentos ctrlDpto = fabrica.getIControladorDepartamentos();
		try {
			comboBoxDepartamento.addItem(" ");
			DataDepartamento[] dptos = ctrlDpto.obtenerDepartamentos();
			for (int i = 0; i < dptos.length; i++) {
				comboBoxDepartamento.addItem(dptos[i].getNombre());
			}
		} catch (DepartamentoNoExisteException e1) {
			// empty block on purpose
		}
		comboBoxDepartamento.setSelectedIndex(-1);
		comboBoxActividades.setSelectedIndex(-1);
	}

	protected void altaSalidaActionPerformed(ActionEvent arg0) {
		// Obtengo datos de los controles Swing
		String nombre = this.textFieldNombre.getText().trim();
		Date feAlta = this.dateChooser2.getDate();
		Date feSalida = this.dateChooser.getDate();
		String hor = this.timeChooser.getFormatedTime();
		String salida = this.textFieldSalida.getText().trim();
		String cantidad = this.textFieldCantidad.getText().trim();
		String act = comboBoxActividades.getSelectedItem().toString();

		if (nombre.isEmpty() || hor.isEmpty() || salida.isEmpty() || cantidad.isEmpty() || feAlta == null
				|| feSalida == null) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Alta de salida turistica",
					JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				LocalDate fechaAlta = feAlta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate fechaSalida = feSalida.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				try {
					int cant = Integer.parseInt(cantidad);
					try {
						if (fechaSalida.isBefore(fechaAlta)) {
							JOptionPane.showMessageDialog(this,
									"La fecha de salida debe ser posterior a la fecha de alta",
									"Alta de salida turistica", JOptionPane.ERROR_MESSAGE);
						} else if (cant < 0) {
							JOptionPane.showMessageDialog(this, "La cantidad de turistas debe ser positiva",
									"Alta de salida turistica", JOptionPane.ERROR_MESSAGE);
							textFieldCantidad.setText("");
						} else {
							controlSal.crearSalida(nombre, cant, fechaAlta, salida, fechaSalida, hor, act);
							JOptionPane.showMessageDialog(this, "Se ha registrado correctamente la salida",
									"Alta de salida turistica", JOptionPane.INFORMATION_MESSAGE);
							limpiarFormulario();
							setVisible(false);
						}

						// Llamar a la funcion que crea la salida

					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(this, "La hora debe ser un numero", "Registrar Actividad",
								JOptionPane.ERROR_MESSAGE);
						textFieldHora.setText("");
					} catch (ActividadNoExisteException e) {
						JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Actividad",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(this, "La cantidad de turistas debe ser un numero",
							"Registrar Actividad", JOptionPane.ERROR_MESSAGE);
					textFieldCantidad.setText("");
				}

			} catch (SalidaRepetidaException ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Registrar Salida", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	private void limpiarFormulario() {

		textFieldNombre.setText("");
		textFieldHora.setText("");
		textFieldSalida.setText("");
		textFieldCantidad.setText("");
		comboBoxDepartamento.removeAll();
		comboBoxActividades.removeAllItems();
		comboBoxActividades.setSelectedIndex(-1);
		comboBoxDepartamento.setSelectedIndex(-1);
		dateChooser.setDate(null);
		dateChooser2.setDate(null);
	}
}
