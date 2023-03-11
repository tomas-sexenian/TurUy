package presentacion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameEvent;

import com.toedter.calendar.JDateChooser;

import excepciones.UsuarioNoExisteException;
import logica.Fabrica;
import logica.datatypes.DataProveedor;
import logica.datatypes.DataTurista;
import logica.interfaces.IControladorUsuario;

@SuppressWarnings({ "serial" })
public class ModificarDatosUsuario extends JInternalFrame {

	private IControladorUsuario controlUsr;

	private JComboBox comboBoxTurista;
	private JButton btnCerrar;
	private JLabel lblSeleccioneUnUsuario;
	private JButton btnSeleccionarTurista;
	private JButton btnConfirmar;
	private JLabel lblNickname;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblEmail;
	private JLabel lblNacimiento;
	private JLabel lblTipo;
	private JLabel lblNacionalidad;
	private JLabel lblDescripcion;
	private JLabel lblLink;
	private JTextField textFieldNickname;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldEmail;
	private JTextField textFieldTipo;
	private JTextField textFieldNacionalidad;
	private JTextField textFieldDescripcion;
	private JTextField textFieldLink;
	private JLabel lblTipoDeUsuario;
	private JComboBox comboBoxTipoUsuario;
	private JLabel lblSeleccioneUnProveedor;
	private JComboBox comboBoxProveedor;
	private JButton btnSeleccionarProveedor;

	private DataTurista[] turistas;
	private DataTurista turista;
	private DataProveedor[] proveedores;
	private DataProveedor proveedor;
	private JDateChooser dateChooser;

	private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
		limpiarFormulario();
		setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public ModificarDatosUsuario() {
		// Se inicializa con el controlador de usuarios

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
		setTitle("Modificar datos de usuario");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(myScreen.getScreenSize().width / 2, myScreen.getScreenSize().height / 2);
		setLocation(0, 0);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 172, 288, 105, 71, 0 };
		gridBagLayout.rowHeights = new int[] { 10, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 0, 0, 25, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		Fabrica fabrica = Fabrica.getInstance();
		controlUsr = fabrica.getIControladorUsuario();

		lblTipoDeUsuario = new JLabel("Tipo de usuario:");
		GridBagConstraints gbclblTipoDeUsuario = new GridBagConstraints();
		gbclblTipoDeUsuario.anchor = GridBagConstraints.EAST;
		gbclblTipoDeUsuario.insets = new Insets(0, 0, 5, 5);
		gbclblTipoDeUsuario.gridx = 0;
		gbclblTipoDeUsuario.gridy = 1;
		getContentPane().add(lblTipoDeUsuario, gbclblTipoDeUsuario);

		comboBoxTipoUsuario = new JComboBox();
		comboBoxTipoUsuario.addItem(new ItemTexto("Seleccione..."));
		comboBoxTipoUsuario.addItem(new ItemTexto("Proveedor"));
		comboBoxTipoUsuario.addItem(new ItemTexto("Turista"));
		comboBoxTipoUsuario.setSelectedIndex(0);
		comboBoxTipoUsuario.setMaximumRowCount(3);
		GridBagConstraints gbccomboBoxTipoUsuario = new GridBagConstraints();
		gbccomboBoxTipoUsuario.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxTipoUsuario.fill = GridBagConstraints.BOTH;
		gbccomboBoxTipoUsuario.gridx = 1;
		gbccomboBoxTipoUsuario.gridy = 1;
		getContentPane().add(comboBoxTipoUsuario, gbccomboBoxTipoUsuario);

		lblSeleccioneUnUsuario = new JLabel("Seleccione un turista:");
		lblSeleccioneUnUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblSeleccioneUnUsuario = new GridBagConstraints();
		gbclblSeleccioneUnUsuario.insets = new Insets(0, 0, 5, 5);
		gbclblSeleccioneUnUsuario.anchor = GridBagConstraints.EAST;
		gbclblSeleccioneUnUsuario.gridx = 0;
		gbclblSeleccioneUnUsuario.gridy = 2;
		lblSeleccioneUnUsuario.setVisible(false);
		getContentPane().add(lblSeleccioneUnUsuario, gbclblSeleccioneUnUsuario);

		// Un combo (JComboBox) muestra la lista de usuarios registrados en el sistema.
		// Es posible utilizar otros componentes gráficos, esta es sólo una opción.
		// Se ubica al centro del layout.
		comboBoxTurista = new JComboBox();
		GridBagConstraints gbccomboBoxTurista = new GridBagConstraints();
		gbccomboBoxTurista.fill = GridBagConstraints.BOTH;
		gbccomboBoxTurista.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxTurista.gridx = 1;
		gbccomboBoxTurista.gridy = 2;
		comboBoxTurista.setVisible(false);
		getContentPane().add(comboBoxTurista, gbccomboBoxTurista);

		// Un botón (JButton) con un evento asociado que permite limpiar la lista
		// de usuarios y cerrar la ventana (sólo la oculta).
		// Se ubica al sur del layout.
		btnCerrar = new JButton("Cancelar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				setVisible(false);
			}
		});

		btnSeleccionarTurista = new JButton("Seleccionar");
		btnSeleccionarTurista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					turista = controlUsr.verInfoTurista(comboBoxTurista.getSelectedItem().toString().split(" ", 2)[0]);
					textFieldNickname.setText(turista.getNickname());
					textFieldNombre.setText(turista.getNombre());
					textFieldApellido.setText(turista.getApellido());
					textFieldEmail.setText(turista.getEmail());
					dateChooser.setDate(Date.valueOf(turista.getNacimiento()));
					textFieldNacionalidad.setText(turista.getNacionalidad());

				} catch (UsuarioNoExisteException e3) {

				}

			}
		});
		GridBagConstraints gbcbtnSeleccionarTurista = new GridBagConstraints();
		gbcbtnSeleccionarTurista.insets = new Insets(0, 0, 5, 5);
		gbcbtnSeleccionarTurista.gridx = 2;
		gbcbtnSeleccionarTurista.gridy = 2;
		btnSeleccionarTurista.setVisible(false);
		getContentPane().add(btnSeleccionarTurista, gbcbtnSeleccionarTurista);

		lblSeleccioneUnProveedor = new JLabel("Seleccione un proveedor:");
		GridBagConstraints gbclblSeleccioneUnProveedor = new GridBagConstraints();
		gbclblSeleccioneUnProveedor.anchor = GridBagConstraints.EAST;
		gbclblSeleccioneUnProveedor.insets = new Insets(0, 0, 5, 5);
		gbclblSeleccioneUnProveedor.gridx = 0;
		gbclblSeleccioneUnProveedor.gridy = 3;
		lblSeleccioneUnProveedor.setVisible(false);
		getContentPane().add(lblSeleccioneUnProveedor, gbclblSeleccioneUnProveedor);

		comboBoxProveedor = new JComboBox();
		GridBagConstraints gbccomboBoxProveedor = new GridBagConstraints();
		gbccomboBoxProveedor.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxProveedor.fill = GridBagConstraints.BOTH;
		gbccomboBoxProveedor.gridx = 1;
		gbccomboBoxProveedor.gridy = 3;
		comboBoxProveedor.setVisible(false);
		getContentPane().add(comboBoxProveedor, gbccomboBoxProveedor);

		btnSeleccionarProveedor = new JButton("Seleccionar");
		GridBagConstraints gbcbtnSeleccionarProveedor = new GridBagConstraints();
		gbcbtnSeleccionarProveedor.insets = new Insets(0, 0, 5, 5);
		gbcbtnSeleccionarProveedor.gridx = 2;
		gbcbtnSeleccionarProveedor.gridy = 3;
		btnSeleccionarProveedor.setVisible(false);
		btnSeleccionarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					proveedor = controlUsr
							.verInfoProveedor(comboBoxProveedor.getSelectedItem().toString().split(" ", 2)[0]);
					textFieldNickname.setText(proveedor.getNickname());
					textFieldNombre.setText(proveedor.getNombre());
					textFieldApellido.setText(proveedor.getApellido());
					textFieldEmail.setText(proveedor.getEmail());
					dateChooser.setDate(Date.valueOf(proveedor.getNacimiento()));
					textFieldDescripcion.setText(proveedor.getDescripcion());
					textFieldLink.setText(proveedor.getLink());
				} catch (UsuarioNoExisteException e4) {

				}
			}
		});
		getContentPane().add(btnSeleccionarProveedor, gbcbtnSeleccionarProveedor);

		lblNickname = new JLabel("Nickname:");
		lblNickname.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblNickname = new GridBagConstraints();
		gbclblNickname.anchor = GridBagConstraints.EAST;
		gbclblNickname.insets = new Insets(0, 0, 5, 5);
		gbclblNickname.gridx = 0;
		gbclblNickname.gridy = 4;
		getContentPane().add(lblNickname, gbclblNickname);

		textFieldNickname = new JTextField();
		textFieldNickname.setEditable(false);
		GridBagConstraints gbctextFieldNickname = new GridBagConstraints();
		gbctextFieldNickname.gridwidth = 2;
		gbctextFieldNickname.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNickname.fill = GridBagConstraints.BOTH;
		gbctextFieldNickname.gridx = 1;
		gbctextFieldNickname.gridy = 4;
		getContentPane().add(textFieldNickname, gbctextFieldNickname);
		textFieldNickname.setColumns(10);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblNombre = new GridBagConstraints();
		gbclblNombre.anchor = GridBagConstraints.EAST;
		gbclblNombre.insets = new Insets(0, 0, 5, 5);
		gbclblNombre.gridx = 0;
		gbclblNombre.gridy = 5;
		getContentPane().add(lblNombre, gbclblNombre);

		textFieldNombre = new JTextField();
		GridBagConstraints gbctextFieldNombre = new GridBagConstraints();
		gbctextFieldNombre.gridwidth = 2;
		gbctextFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNombre.fill = GridBagConstraints.BOTH;
		gbctextFieldNombre.gridx = 1;
		gbctextFieldNombre.gridy = 5;
		getContentPane().add(textFieldNombre, gbctextFieldNombre);
		textFieldNombre.setColumns(10);

		lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblApellido = new GridBagConstraints();
		gbclblApellido.anchor = GridBagConstraints.EAST;
		gbclblApellido.insets = new Insets(0, 0, 5, 5);
		gbclblApellido.gridx = 0;
		gbclblApellido.gridy = 6;
		getContentPane().add(lblApellido, gbclblApellido);

		textFieldApellido = new JTextField();
		GridBagConstraints gbctextFieldApellido = new GridBagConstraints();
		gbctextFieldApellido.gridwidth = 2;
		gbctextFieldApellido.insets = new Insets(0, 0, 5, 5);
		gbctextFieldApellido.fill = GridBagConstraints.BOTH;
		gbctextFieldApellido.gridx = 1;
		gbctextFieldApellido.gridy = 6;
		getContentPane().add(textFieldApellido, gbctextFieldApellido);
		textFieldApellido.setColumns(10);

		lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblEmail = new GridBagConstraints();
		gbclblEmail.anchor = GridBagConstraints.EAST;
		gbclblEmail.insets = new Insets(0, 0, 5, 5);
		gbclblEmail.gridx = 0;
		gbclblEmail.gridy = 7;
		getContentPane().add(lblEmail, gbclblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		GridBagConstraints gbctextFieldEmail = new GridBagConstraints();
		gbctextFieldEmail.gridwidth = 2;
		gbctextFieldEmail.insets = new Insets(0, 0, 5, 5);
		gbctextFieldEmail.fill = GridBagConstraints.BOTH;
		gbctextFieldEmail.gridx = 1;
		gbctextFieldEmail.gridy = 7;
		getContentPane().add(textFieldEmail, gbctextFieldEmail);
		textFieldEmail.setColumns(10);

		lblNacimiento = new JLabel("Nacimiento:");
		lblNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblNacimiento = new GridBagConstraints();
		gbclblNacimiento.anchor = GridBagConstraints.EAST;
		gbclblNacimiento.insets = new Insets(0, 0, 5, 5);
		gbclblNacimiento.gridx = 0;
		gbclblNacimiento.gridy = 8;
		getContentPane().add(lblNacimiento, gbclblNacimiento);

		dateChooser = new JDateChooser();
		GridBagConstraints gbcdateChooser = new GridBagConstraints();
		gbcdateChooser.insets = new Insets(0, 0, 5, 5);
		gbcdateChooser.fill = GridBagConstraints.BOTH;
		gbcdateChooser.gridx = 1;
		gbcdateChooser.gridy = 8;
		getContentPane().add(dateChooser, gbcdateChooser);

		lblTipo = new JLabel("Tipo:");
		GridBagConstraints gbclblTipo = new GridBagConstraints();
		gbclblTipo.anchor = GridBagConstraints.EAST;
		gbclblTipo.insets = new Insets(0, 0, 5, 5);
		gbclblTipo.gridx = 0;
		gbclblTipo.gridy = 9;
		getContentPane().add(lblTipo, gbclblTipo);

		textFieldTipo = new JTextField();
		textFieldTipo.setEditable(false);
		GridBagConstraints gbctextFieldTipo = new GridBagConstraints();
		gbctextFieldTipo.gridwidth = 2;
		gbctextFieldTipo.insets = new Insets(0, 0, 5, 5);
		gbctextFieldTipo.fill = GridBagConstraints.BOTH;
		gbctextFieldTipo.gridx = 1;
		gbctextFieldTipo.gridy = 9;
		getContentPane().add(textFieldTipo, gbctextFieldTipo);
		textFieldTipo.setColumns(10);

		lblNacionalidad = new JLabel("Nacionalidad");
		GridBagConstraints gbclblNacionalidad = new GridBagConstraints();
		gbclblNacionalidad.anchor = GridBagConstraints.EAST;
		gbclblNacionalidad.insets = new Insets(0, 0, 5, 5);
		gbclblNacionalidad.gridx = 0;
		gbclblNacionalidad.gridy = 10;
		lblNacionalidad.setVisible(false);
		getContentPane().add(lblNacionalidad, gbclblNacionalidad);

		textFieldNacionalidad = new JTextField();
		GridBagConstraints gbctextFieldNacionalidad = new GridBagConstraints();
		gbctextFieldNacionalidad.gridwidth = 2;
		gbctextFieldNacionalidad.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNacionalidad.fill = GridBagConstraints.BOTH;
		gbctextFieldNacionalidad.gridx = 1;
		gbctextFieldNacionalidad.gridy = 10;
		textFieldNacionalidad.setVisible(false);
		getContentPane().add(textFieldNacionalidad, gbctextFieldNacionalidad);
		textFieldNacionalidad.setColumns(10);

		lblDescripcion = new JLabel("Descripcion:");
		GridBagConstraints gbclblDescripcion = new GridBagConstraints();
		gbclblDescripcion.anchor = GridBagConstraints.EAST;
		gbclblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbclblDescripcion.gridx = 0;
		gbclblDescripcion.gridy = 11;
		lblDescripcion.setVisible(false);
		getContentPane().add(lblDescripcion, gbclblDescripcion);

		textFieldDescripcion = new JTextField();
		GridBagConstraints gbctextFieldDescripcion = new GridBagConstraints();
		gbctextFieldDescripcion.gridwidth = 2;
		gbctextFieldDescripcion.insets = new Insets(0, 0, 5, 5);
		gbctextFieldDescripcion.fill = GridBagConstraints.BOTH;
		gbctextFieldDescripcion.gridx = 1;
		gbctextFieldDescripcion.gridy = 11;
		lblDescripcion.setVisible(false);
		getContentPane().add(textFieldDescripcion, gbctextFieldDescripcion);
		textFieldDescripcion.setColumns(10);

		lblLink = new JLabel("LInk:");
		GridBagConstraints gbclblLink = new GridBagConstraints();
		gbclblLink.anchor = GridBagConstraints.EAST;
		gbclblLink.insets = new Insets(0, 0, 5, 5);
		gbclblLink.gridx = 0;
		gbclblLink.gridy = 12;
		lblLink.setVisible(false);
		getContentPane().add(lblLink, gbclblLink);

		textFieldLink = new JTextField();
		GridBagConstraints gbctextFieldLink = new GridBagConstraints();
		gbctextFieldLink.gridwidth = 2;
		gbctextFieldLink.insets = new Insets(0, 0, 5, 5);
		gbctextFieldLink.fill = GridBagConstraints.BOTH;
		gbctextFieldLink.gridx = 1;
		gbctextFieldLink.gridy = 12;
		textFieldLink.setVisible(false);
		getContentPane().add(textFieldLink, gbctextFieldLink);
		textFieldLink.setColumns(10);

		GridBagConstraints gbcbtnCerrar = new GridBagConstraints();
		gbcbtnCerrar.fill = GridBagConstraints.VERTICAL;
		gbcbtnCerrar.insets = new Insets(0, 0, 0, 5);
		gbcbtnCerrar.gridx = 1;
		gbcbtnCerrar.gridy = 13;
		getContentPane().add(btnCerrar, gbcbtnCerrar);

		btnConfirmar = new JButton("Confirmar");
		GridBagConstraints gbcbtnConfirmar = new GridBagConstraints();
		gbcbtnConfirmar.fill = GridBagConstraints.BOTH;
		gbcbtnConfirmar.insets = new Insets(0, 0, 0, 5);
		gbcbtnConfirmar.gridx = 2;
		gbcbtnConfirmar.gridy = 13;
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkFormulario()) {

					LocalDate nacimiento = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault())
							.toLocalDate();
					if (textFieldTipo.getText().equals("Turista")) {
						try {
							controlUsr.modificarTurista(textFieldNickname.getText().trim(),
									textFieldNombre.getText().trim(), textFieldApellido.getText().trim(),
									textFieldEmail.getText().trim(), nacimiento,
									textFieldNacionalidad.getText().trim());
						} catch (UsuarioNoExisteException e5) {
							JOptionPane.showMessageDialog(btnConfirmar, e5.getMessage(), "Buscar usuario",
									JOptionPane.ERROR_MESSAGE);

						}

					} else if (textFieldTipo.getText().equals("Proveedor")) {
						try {
							controlUsr.modificarProveedor(textFieldNickname.getText().trim(),
									textFieldNombre.getText().trim(), textFieldApellido.getText().trim(),
									textFieldEmail.getText().trim(), nacimiento, textFieldDescripcion.getText().trim(),
									textFieldLink.getText().trim());
						} catch (UsuarioNoExisteException e6) {
							JOptionPane.showMessageDialog(btnConfirmar, e6.getMessage(), "Buscar usuario",
									JOptionPane.ERROR_MESSAGE);

						}
					}

					limpiarFormulario();
					setVisible(false);
					JOptionPane.showMessageDialog(btnConfirmar, "Los datos se han modificado con éxito",
							"Modificar datos Usuario", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		getContentPane().add(btnConfirmar, gbcbtnConfirmar);

		comboBoxTipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBoxTipoUsuario.getSelectedIndex() == 1) {
					// Es un proveedor
					try {
						comboBoxProveedor.removeAllItems();
						proveedores = controlUsr.getProveedores();
						for (int i = 0; i < proveedores.length; i++) {
							comboBoxProveedor.addItem(proveedores[i].toString());
						}
						comboBoxProveedor.setSelectedIndex(-1);
					} catch (UsuarioNoExisteException e1) {

					}
					lblSeleccioneUnUsuario.setVisible(false);
					comboBoxTurista.setVisible(false);
					btnSeleccionarTurista.setVisible(false);
					lblSeleccioneUnProveedor.setVisible(true);
					comboBoxProveedor.setVisible(true);
					btnSeleccionarProveedor.setVisible(true);
					textFieldTipo.setText("Proveedor");
					lblNacionalidad.setVisible(false);
					textFieldNacionalidad.setVisible(false);
					lblDescripcion.setVisible(true);
					textFieldDescripcion.setVisible(true);
					lblLink.setVisible(true);
					textFieldLink.setVisible(true);

				} else if (comboBoxTipoUsuario.getSelectedIndex() == 2) {
					// Es un turista

					try {
						comboBoxTurista.removeAllItems();
						turistas = controlUsr.getTuristas();
						for (int i = 0; i < turistas.length; i++) {
							comboBoxTurista.addItem(turistas[i].toString());
						}
						comboBoxTurista.setSelectedIndex(-1);
					} catch (UsuarioNoExisteException e1) {

					}
					lblSeleccioneUnUsuario.setVisible(true);
					comboBoxTurista.setVisible(true);
					btnSeleccionarTurista.setVisible(true);
					lblSeleccioneUnProveedor.setVisible(false);
					comboBoxProveedor.setVisible(false);
					btnSeleccionarProveedor.setVisible(false);
					textFieldTipo.setText("Turista");
					lblNacionalidad.setVisible(true);
					textFieldNacionalidad.setVisible(true);
					lblDescripcion.setVisible(false);
					textFieldDescripcion.setVisible(false);
					lblLink.setVisible(false);
					textFieldLink.setVisible(false);
				} else {
					lblSeleccioneUnUsuario.setVisible(false);
					comboBoxTurista.setVisible(false);
					btnSeleccionarTurista.setVisible(false);
					lblSeleccioneUnProveedor.setVisible(false);
					comboBoxProveedor.setVisible(false);
					btnSeleccionarProveedor.setVisible(false);
					textFieldTipo.setText("");
					lblNacionalidad.setVisible(false);
					textFieldNacionalidad.setVisible(false);
					lblDescripcion.setVisible(false);
					textFieldDescripcion.setVisible(false);
					lblLink.setVisible(false);
					textFieldLink.setVisible(false);
				}
			}
		});
	}

	private void limpiarFormulario() {
		comboBoxTipoUsuario.setSelectedIndex(0);
		textFieldNickname.setText("");
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldEmail.setText("");
		textFieldTipo.setText("");
		textFieldNacionalidad.setText("");
		textFieldDescripcion.setText("");
		textFieldLink.setText("");
		dateChooser.setDate(null);
	}

	private boolean checkFormulario() {
		if (textFieldNombre.getText() == "" || textFieldApellido.getText() == "") {
			JOptionPane.showMessageDialog(this, "Faltan datos basicos del usuario", "Registrar Usuario",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (comboBoxTipoUsuario.getSelectedIndex() == 1
				&& (textFieldDescripcion.getText() == "" || textFieldLink.getText() == "")) {
			JOptionPane.showMessageDialog(this, "Faltan datos basicos del proveedor", "Registrar Usuario",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (comboBoxTipoUsuario.getSelectedIndex() == 2 && textFieldNacionalidad.getText() == "") {
			JOptionPane.showMessageDialog(this, "Faltan datos basicos del turista", "Registrar Usuario",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (comboBoxTipoUsuario.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Debe indicar el tipo de usuario", "Registrar Usuario",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}
}
