package presentacion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameEvent;

import excepciones.ActividadNoExisteException;
import excepciones.DepartamentoNoExisteException;
import excepciones.SalidaNoExisteException;
import logica.Fabrica;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataDepartamento;
import logica.datatypes.DataSalidaTuristica;
import logica.interfaces.IControladorActividades;
import logica.interfaces.IControladorDepartamentos;
import logica.interfaces.IControladorSalidas;
import logica.manejadores.ManejadorActividades;

@SuppressWarnings("serial")
public class ConsultarSalidas extends JInternalFrame {

	// Controlador de usuarios que se utilizará para las acciones del JFrame
	private JTextField textFieldNombre;
	private JLabel lblIngresoCI;
	private JLabel lblNombre;
	private JButton btnCerrar;
	private JLabel lblInfoUsuario;
	private JComboBox comboBoxActividad;
	private JLabel lblTipo;
	private JLabel lblDescripcion;
	private JLabel lblLink;
	private JTextField textFieldCantTuristas;
	private JTextField textFieldFechaSalida;
	private JTextField textFieldLugarSalida;
	private JComboBox comboBoxSalidas;
	private JComboBox comboBoxDepartamento;
	private JLabel lblSeleccioneUnaActividad;
	private JLabel lblCosto;
	private JTextField textFieldFechaAlta;
	private JLabel lblSeleccioneUnaSalida;
	private JLabel lblHoraSalida;
	private JTextField textFieldHoraSalida;
	private boolean desdeConsultarActividad = false;
	private boolean desdeConsultarUsuario = false;

	public void setDesdeConsultarActividad(boolean bool) {
		this.desdeConsultarActividad = bool;
	}

	public void setDesdeConsultarUsuario(boolean bool) {
		this.desdeConsultarUsuario = bool;
	}

	private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
		FabricaInternalFrames fab = FabricaInternalFrames.getInstance();
		if (desdeConsultarActividad) {
			ConsultarActividad consultarActividad = fab.getConsultarActividad();
			consultarActividad.setVisible(true);
		} else if (desdeConsultarUsuario) {
			ConsultarUsuario consultarUsuario = fab.getConsultarUsuario();
			consultarUsuario.setVisible(true);
		}
		limpiarFormulario();
		setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public ConsultarSalidas() {
		// Se inicializa con el controlador de usuarios
		Fabrica fabrica = Fabrica.getInstance();

		// Propiedades del JInternalFrame como dimensión, posición dentro del frame,
		// etc.
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
		setTitle("Consultar una Salida");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(myScreen.getScreenSize().width / 2, myScreen.getScreenSize().height / 2);
		// setLocation(myScreen.getScreenSize().width/15,myScreen.getScreenSize().height/15);
		setLocation(0, 0);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 140, 183, 74, 117, 60, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 30, 0, 30, 31, 0, 0, 34, 30, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		// Una etiqueta (JLabel) indicando que en el siguiente campo debe ingresarse
		// la cédula del usuario.
		lblIngresoCI = new JLabel("Seleccione un departamento:");
		GridBagConstraints gbclblIngresoCI = new GridBagConstraints();
		gbclblIngresoCI.anchor = GridBagConstraints.WEST;
		gbclblIngresoCI.gridwidth = 2;
		gbclblIngresoCI.insets = new Insets(0, 0, 5, 5);
		gbclblIngresoCI.gridx = 0;
		gbclblIngresoCI.gridy = 1;
		getContentPane().add(lblIngresoCI, gbclblIngresoCI);

		comboBoxDepartamento = new JComboBox();

		comboBoxDepartamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (comboBoxDepartamento.getSelectedItem() != null && comboBoxDepartamento.getSelectedItem() != " ") {
					String departamento = comboBoxDepartamento.getSelectedItem().toString();
					IControladorActividades ctrlAct = fabrica.getIControladorActividades();
					try {
						comboBoxActividad.removeAllItems();
						comboBoxActividad.addItem(" ");
						List<DataActividadTuristica> acts = ctrlAct.obtenerActividades(departamento);
						for (int i = 0; i < acts.size(); i++) {
							comboBoxActividad.addItem(acts.get(i).getNombre());
						}
						// comboBoxActividad.setSelectedIndex(-1);
					} catch (ActividadNoExisteException e1) {
						JOptionPane.showMessageDialog(comboBoxActividad, e1.getMessage(), "Consultar Actividad",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		GridBagConstraints gbccomboBoxDepartamento = new GridBagConstraints();
		gbccomboBoxDepartamento.gridwidth = 3;
		gbccomboBoxDepartamento.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxDepartamento.gridx = 1;
		gbccomboBoxDepartamento.gridy = 2;
		getContentPane().add(comboBoxDepartamento, gbccomboBoxDepartamento);

		comboBoxActividad = new JComboBox();
		comboBoxSalidas = new JComboBox();

		comboBoxActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (comboBoxActividad.getSelectedItem() != null && comboBoxActividad.getSelectedItem() != " ") {
					String act = comboBoxActividad.getSelectedItem().toString();
					IControladorActividades ctrlAct = fabrica.getIControladorActividades();
					try {
						DataActividadTuristica dataAct = ctrlAct.consultarActividad(act);
						Map<String, DataSalidaTuristica> salidas = dataAct.getSalidas();
						comboBoxSalidas.removeAllItems();
						comboBoxSalidas.addItem(" ");
						for (String k : salidas.keySet()) {
							comboBoxSalidas.addItem(k);
						}
						comboBoxSalidas.setSelectedIndex(-1);

					} catch (ActividadNoExisteException e1) {
						JOptionPane.showMessageDialog(comboBoxActividad, e1.getMessage(), "Consultar Actividad",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});

		comboBoxSalidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (comboBoxSalidas.getSelectedItem() != null && comboBoxSalidas.getSelectedItem() != " ") {
					String sal = comboBoxSalidas.getSelectedItem().toString();
					IControladorSalidas ctrlSalidas = fabrica.getIControladorSalidas();
					try {
						DataSalidaTuristica dataSal = ctrlSalidas.consultarSalida(sal);
						textFieldNombre.setText(dataSal.getNombre());
						textFieldCantTuristas.setText(Integer.toString(dataSal.getCantMaxTuristas()));
						textFieldFechaAlta.setText(dataSal.getFechaAlta().toString());
						textFieldFechaSalida.setText(dataSal.getFechaSalida().toString());
						textFieldHoraSalida.setText(dataSal.getHoraSalida());

					} catch (SalidaNoExisteException e1) {
						JOptionPane.showMessageDialog(comboBoxSalidas, e1.getMessage(), "Consultar Salida",
								JOptionPane.ERROR_MESSAGE);
					} catch (ActividadNoExisteException e1) {
						JOptionPane.showMessageDialog(comboBoxSalidas, e1.getMessage(), "Consultar Salida",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});

		lblSeleccioneUnaActividad = new JLabel("Seleccione una actividad:");
		GridBagConstraints gbclblSeleccioneUnaActividad = new GridBagConstraints();
		gbclblSeleccioneUnaActividad.anchor = GridBagConstraints.WEST;
		gbclblSeleccioneUnaActividad.gridwidth = 2;
		gbclblSeleccioneUnaActividad.insets = new Insets(0, 0, 5, 5);
		gbclblSeleccioneUnaActividad.gridx = 0;
		gbclblSeleccioneUnaActividad.gridy = 3;
		getContentPane().add(lblSeleccioneUnaActividad, gbclblSeleccioneUnaActividad);
		GridBagConstraints gbccomboBoxActividad = new GridBagConstraints();
		gbccomboBoxActividad.gridwidth = 3;
		gbccomboBoxActividad.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxActividad.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxActividad.gridx = 1;
		gbccomboBoxActividad.gridy = 4;
		getContentPane().add(comboBoxActividad, gbccomboBoxActividad);

		lblSeleccioneUnaSalida = new JLabel("Seleccione una salida:");
		GridBagConstraints gbclblSeleccioneUnaSalida = new GridBagConstraints();
		gbclblSeleccioneUnaSalida.anchor = GridBagConstraints.WEST;
		gbclblSeleccioneUnaSalida.gridwidth = 2;
		gbclblSeleccioneUnaSalida.insets = new Insets(0, 0, 5, 5);
		gbclblSeleccioneUnaSalida.gridx = 0;
		gbclblSeleccioneUnaSalida.gridy = 5;
		getContentPane().add(lblSeleccioneUnaSalida, gbclblSeleccioneUnaSalida);

		GridBagConstraints gbccomboBoxSalidas = new GridBagConstraints();
		gbccomboBoxSalidas.gridwidth = 3;
		gbccomboBoxSalidas.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxSalidas.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxSalidas.gridx = 1;
		gbccomboBoxSalidas.gridy = 6;
		getContentPane().add(comboBoxSalidas, gbccomboBoxSalidas);

		// Una etiqueta (JLabel) indicando que a continuación se verá la
		// información del usuarios buscado.
		lblInfoUsuario = new JLabel("Información de la salida");
		GridBagConstraints gbclblInfoUsuario = new GridBagConstraints();
		gbclblInfoUsuario.insets = new Insets(0, 0, 5, 5);
		gbclblInfoUsuario.gridwidth = 3;
		gbclblInfoUsuario.gridx = 1;
		gbclblInfoUsuario.gridy = 7;
		getContentPane().add(lblInfoUsuario, gbclblInfoUsuario);

		// Una etiqueta (JLabel) indicando que en el siguiente campo se verá
		// el nombre del usuario encontrado.
		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblNombre = new GridBagConstraints();
		gbclblNombre.fill = GridBagConstraints.HORIZONTAL;
		gbclblNombre.insets = new Insets(0, 0, 5, 5);
		gbclblNombre.gridx = 0;
		gbclblNombre.gridy = 8;
		getContentPane().add(lblNombre, gbclblNombre);

		// Una campo de texto (JTextField) para mostrar el nombre del usuario.
		// El campo se hace no editable para impedir que se modifique.
		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		GridBagConstraints gbctextFieldNombre = new GridBagConstraints();
		gbctextFieldNombre.gridwidth = 4;
		gbctextFieldNombre.fill = GridBagConstraints.BOTH;
		gbctextFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNombre.gridx = 1;
		gbctextFieldNombre.gridy = 8;
		getContentPane().add(textFieldNombre, gbctextFieldNombre);

		lblTipo = new JLabel("Cant max Turistas:");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblTipo = new GridBagConstraints();
		gbclblTipo.anchor = GridBagConstraints.EAST;
		gbclblTipo.insets = new Insets(0, 0, 5, 5);
		gbclblTipo.gridx = 0;
		gbclblTipo.gridy = 9;
		getContentPane().add(lblTipo, gbclblTipo);

		textFieldCantTuristas = new JTextField();
		textFieldCantTuristas.setEditable(false);
		GridBagConstraints gbctextFieldCantTuristas = new GridBagConstraints();
		gbctextFieldCantTuristas.insets = new Insets(0, 0, 5, 5);
		gbctextFieldCantTuristas.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldCantTuristas.gridx = 1;
		gbctextFieldCantTuristas.gridy = 9;
		getContentPane().add(textFieldCantTuristas, gbctextFieldCantTuristas);
		textFieldCantTuristas.setColumns(10);

		lblCosto = new JLabel("Fecha alta:");
		GridBagConstraints gbclblCosto = new GridBagConstraints();
		gbclblCosto.anchor = GridBagConstraints.EAST;
		gbclblCosto.insets = new Insets(0, 0, 5, 5);
		gbclblCosto.gridx = 2;
		gbclblCosto.gridy = 9;
		getContentPane().add(lblCosto, gbclblCosto);

		textFieldFechaAlta = new JTextField();
		textFieldFechaAlta.setEditable(false);
		textFieldFechaAlta.setColumns(10);
		GridBagConstraints gbctextFieldFechaAlta = new GridBagConstraints();
		gbctextFieldFechaAlta.gridwidth = 2;
		gbctextFieldFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbctextFieldFechaAlta.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldFechaAlta.gridx = 3;
		gbctextFieldFechaAlta.gridy = 9;
		getContentPane().add(textFieldFechaAlta, gbctextFieldFechaAlta);

		lblDescripcion = new JLabel("Fecha Salida:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblDescripcion = new GridBagConstraints();
		gbclblDescripcion.anchor = GridBagConstraints.EAST;
		gbclblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbclblDescripcion.gridx = 0;
		gbclblDescripcion.gridy = 10;
		getContentPane().add(lblDescripcion, gbclblDescripcion);

		textFieldFechaSalida = new JTextField();
		textFieldFechaSalida.setEditable(false);
		GridBagConstraints gbctextFieldFechaSalida = new GridBagConstraints();
		gbctextFieldFechaSalida.insets = new Insets(0, 0, 5, 5);
		gbctextFieldFechaSalida.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldFechaSalida.gridx = 1;
		gbctextFieldFechaSalida.gridy = 10;
		getContentPane().add(textFieldFechaSalida, gbctextFieldFechaSalida);
		textFieldFechaSalida.setColumns(10);

		lblHoraSalida = new JLabel("Hora Salida:");
		GridBagConstraints gbclblHoraSalida = new GridBagConstraints();
		gbclblHoraSalida.anchor = GridBagConstraints.EAST;
		gbclblHoraSalida.insets = new Insets(0, 0, 5, 5);
		gbclblHoraSalida.gridx = 2;
		gbclblHoraSalida.gridy = 10;
		getContentPane().add(lblHoraSalida, gbclblHoraSalida);

		textFieldHoraSalida = new JTextField();
		textFieldHoraSalida.setEditable(false);
		textFieldHoraSalida.setColumns(10);
		GridBagConstraints gbctextFieldHoraSalida = new GridBagConstraints();
		gbctextFieldHoraSalida.gridwidth = 2;
		gbctextFieldHoraSalida.insets = new Insets(0, 0, 5, 5);
		gbctextFieldHoraSalida.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldHoraSalida.gridx = 3;
		gbctextFieldHoraSalida.gridy = 10;
		getContentPane().add(textFieldHoraSalida, gbctextFieldHoraSalida);

		lblLink = new JLabel("Lugar Salida:");
		lblLink.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblLink = new GridBagConstraints();
		gbclblLink.anchor = GridBagConstraints.EAST;
		gbclblLink.insets = new Insets(0, 0, 5, 5);
		gbclblLink.gridx = 0;
		gbclblLink.gridy = 11;
		getContentPane().add(lblLink, gbclblLink);
		lblLink.setVisible(false);

		textFieldLugarSalida = new JTextField();
		textFieldLugarSalida.setEditable(false);
		GridBagConstraints gbctextFieldLugarSalida = new GridBagConstraints();
		gbctextFieldLugarSalida.gridwidth = 4;
		gbctextFieldLugarSalida.insets = new Insets(0, 0, 5, 5);
		gbctextFieldLugarSalida.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldLugarSalida.gridx = 1;
		gbctextFieldLugarSalida.gridy = 11;
		getContentPane().add(textFieldLugarSalida, gbctextFieldLugarSalida);
		textFieldLugarSalida.setColumns(10);
		textFieldLugarSalida.setVisible(false);

		// Un botón (JButton) con un evento asociado que permite cerrar el formulario
		// (solo ocultarlo).
		// Dado que antes de cerrar se limpia el formulario, se invoca un método
		// reutilizable para ello.
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				FabricaInternalFrames fab = FabricaInternalFrames.getInstance();
				if (desdeConsultarActividad) {
					ConsultarActividad consultarActividad = fab.getConsultarActividad();
					consultarActividad.setVisible(true);
				} else if (desdeConsultarUsuario) {
					ConsultarUsuario consultarUsuario = fab.getConsultarUsuario();
					consultarUsuario.setVisible(true);
				}
				limpiarFormulario();
				setVisible(false);
			}
		});

		GridBagConstraints gbcbtnCerrar = new GridBagConstraints();
		gbcbtnCerrar.insets = new Insets(0, 0, 0, 5);
		gbcbtnCerrar.fill = GridBagConstraints.BOTH;
		gbcbtnCerrar.gridx = 4;
		gbcbtnCerrar.gridy = 12;
		getContentPane().add(btnCerrar, gbcbtnCerrar);
	}

	// Permite borrar el contenido de un formulario antes de cerrarlo.
	// Recordar que las ventanas no se destruyen, sino que simplemente
	// se ocultan, por lo que conviene borrar la información para que
	// no aparezca al mostrarlas nuevamente.
	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textFieldHoraSalida.setText("");
		textFieldCantTuristas.setText("");
		textFieldFechaAlta.setText("");
		textFieldFechaSalida.setText("");
		textFieldLugarSalida.setText("");
		comboBoxActividad.removeAllItems();
		comboBoxSalidas.removeAllItems();
		comboBoxSalidas.setSelectedIndex(-1);
		comboBoxActividad.setSelectedIndex(-1);
		desdeConsultarActividad = false;
		desdeConsultarUsuario = false;
	}

	protected void recargar() {
		comboBoxDepartamento.removeAllItems();
		Fabrica fabrica = Fabrica.getInstance();
		IControladorDepartamentos ctrlDpto = fabrica.getIControladorDepartamentos();
		try {
			comboBoxDepartamento.addItem(" ");
			DataDepartamento[] dptos = ctrlDpto.obtenerDepartamentos();
			for (int i = 0; i < dptos.length; i++) {
				comboBoxDepartamento.addItem(dptos[i].getNombre());
			}
			comboBoxDepartamento.setSelectedIndex(-1);
		} catch (DepartamentoNoExisteException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Consultar Actividad", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void fromOtraVentana(DataSalidaTuristica salida) {
		ManejadorActividades manejadorActividades = ManejadorActividades.getinstance();
		String depto = manejadorActividades.obtenerActividad(salida.getActividad()).getDepartamento().getNombre();
		comboBoxDepartamento.setSelectedItem(depto);
		comboBoxActividad.addItem(salida.getActividad());
		comboBoxActividad.setSelectedItem(salida.getActividad());
		comboBoxSalidas.addItem(salida.getNombre());
		comboBoxSalidas.setSelectedItem(salida.getNombre());
		textFieldNombre.setText(salida.getNombre());
		textFieldCantTuristas.setText(Integer.toString(salida.getCantMaxTuristas()));
		textFieldFechaAlta.setText(salida.getFechaAlta().toString());
		textFieldFechaSalida.setText(salida.getFechaSalida().toString());
		textFieldHoraSalida.setText(salida.getHoraSalida());
	}

}
