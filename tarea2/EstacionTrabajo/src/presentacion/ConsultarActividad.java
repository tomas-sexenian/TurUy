package presentacion;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.InternalFrameEvent;

import excepciones.ActividadNoExisteException;
import excepciones.DepartamentoNoExisteException;
import excepciones.NoExisteEntidadException;
import excepciones.SalidaNoExisteException;
import logica.Fabrica;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataDepartamento;
import logica.datatypes.DataPaqueteActividades;
import logica.datatypes.DataSalidaTuristica;
import logica.interfaces.IControladorActividades;
import logica.interfaces.IControladorDepartamentos;
import logica.interfaces.IControladorSalidas;

@SuppressWarnings("serial")
public class ConsultarActividad extends JInternalFrame {

	// Controlador de usuarios que se utilizará para las acciones del JFrame
	private JTextField textFieldNombre;
	private JLabel lblSeleccionDpto;
	private JLabel lblNombre;
	private JButton btnCerrar;
	private JLabel lblInfoUsuario;
	private JComboBox comboBoxActividad;
	private JLabel lblEmail;
	private JTextField textFieldDpto;
	private JLabel lblTipo;
	private JLabel lblDescripcion;
	private JLabel lblLink;
	private JTextField textFieldDuracion;
	private JTextField textFieldFecha;
	private JComboBox comboBoxSalidas;
	private JComboBox comboBoxPaquetes;
	private JButton btnVerPaquetes;
	private JButton btnVerSalidas;
	private JLabel lblActividades;
	private JLabel lblSalidas;
	private JComboBox comboBoxDepartamento;
	private JLabel lblSeleccioneUnaActividad;
	private JLabel lblCiudad;
	private JLabel lblCosto;
	private JTextField textFieldCiudad;
	private JTextField textFieldCosto;
	private JTextArea textAreaDescripcion;
	private boolean desdeConsultarPaquetes = false;
	private boolean desdeConsultarUsuarios = false;
	private JLabel lblCategorias;
	private JScrollPane scrollPane;
	private JList listCategorias;
	private DefaultListModel demoList;

	public void setDesdeConsultarPaquetes(boolean bool) {
		this.desdeConsultarPaquetes = bool;
	}

	public void setDesdeConsultarUsuarios(boolean bool) {
		this.desdeConsultarUsuarios = bool;
	}

	private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
		FabricaInternalFrames fab = FabricaInternalFrames.getInstance();
		if (desdeConsultarPaquetes) {
			ConsultarPaqueteActividades consultarPaqueteActividades = fab.getConsultarPaqueteActividades();
			consultarPaqueteActividades.setVisible(true);
		} else if (desdeConsultarUsuarios) {
			ConsultarUsuario consultarUsuario = fab.getConsultarUsuario();
			consultarUsuario.setVisible(true);
		}
		limpiarFormulario();
		setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public ConsultarActividad() {
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
		setTitle("Consultar una Actividad");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(myScreen.getScreenSize().width / 2, (myScreen.getScreenSize().height) * 4 / 7);
		setLocation(myScreen.getScreenSize().width / 15, myScreen.getScreenSize().height / 50);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 44, 102, 129, 36, 117, 60, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 10, 20, 0, 20, 31, 25, 25, 25, 25, 50, 25, 80, 25, 25, 25, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		lblSeleccionDpto = new JLabel("Seleccione un departamento:");
		GridBagConstraints gbclblSeleccionDpto = new GridBagConstraints();
		gbclblSeleccionDpto.anchor = GridBagConstraints.WEST;
		gbclblSeleccionDpto.gridwidth = 2;
		gbclblSeleccionDpto.insets = new Insets(0, 0, 5, 5);
		gbclblSeleccionDpto.gridx = 1;
		gbclblSeleccionDpto.gridy = 1;
		getContentPane().add(lblSeleccionDpto, gbclblSeleccionDpto);

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
						comboBoxActividad.setSelectedIndex(-1);
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
		gbccomboBoxDepartamento.gridx = 2;
		gbccomboBoxDepartamento.gridy = 2;
		getContentPane().add(comboBoxDepartamento, gbccomboBoxDepartamento);

		lblSeleccioneUnaActividad = new JLabel("Seleccione una actividad:");
		GridBagConstraints gbclblSeleccioneUnaActividad = new GridBagConstraints();
		gbclblSeleccioneUnaActividad.anchor = GridBagConstraints.WEST;
		gbclblSeleccioneUnaActividad.gridwidth = 2;
		gbclblSeleccioneUnaActividad.insets = new Insets(0, 0, 5, 5);
		gbclblSeleccioneUnaActividad.gridx = 1;
		gbclblSeleccioneUnaActividad.gridy = 3;
		getContentPane().add(lblSeleccioneUnaActividad, gbclblSeleccioneUnaActividad);

		comboBoxActividad = new JComboBox();
		GridBagConstraints gbccomboBoxActividad = new GridBagConstraints();
		gbccomboBoxActividad.gridwidth = 3;
		gbccomboBoxActividad.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxActividad.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxActividad.gridx = 2;
		gbccomboBoxActividad.gridy = 4;
		getContentPane().add(comboBoxActividad, gbccomboBoxActividad);

		lblInfoUsuario = new JLabel("Información de la actividad");
		GridBagConstraints gbclblInfoUsuario = new GridBagConstraints();
		gbclblInfoUsuario.insets = new Insets(0, 0, 5, 5);
		gbclblInfoUsuario.gridwidth = 3;
		gbclblInfoUsuario.gridx = 2;
		gbclblInfoUsuario.gridy = 5;
		getContentPane().add(lblInfoUsuario, gbclblInfoUsuario);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblNombre = new GridBagConstraints();
		gbclblNombre.gridwidth = 2;
		gbclblNombre.fill = GridBagConstraints.HORIZONTAL;
		gbclblNombre.insets = new Insets(0, 0, 5, 5);
		gbclblNombre.gridx = 0;
		gbclblNombre.gridy = 6;
		getContentPane().add(lblNombre, gbclblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		GridBagConstraints gbctextFieldNombre = new GridBagConstraints();
		gbctextFieldNombre.gridwidth = 3;
		gbctextFieldNombre.fill = GridBagConstraints.BOTH;
		gbctextFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNombre.gridx = 2;
		gbctextFieldNombre.gridy = 6;
		getContentPane().add(textFieldNombre, gbctextFieldNombre);

		lblEmail = new JLabel("Departamento:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblEmail = new GridBagConstraints();
		gbclblEmail.gridwidth = 2;
		gbclblEmail.anchor = GridBagConstraints.EAST;
		gbclblEmail.insets = new Insets(0, 0, 5, 5);
		gbclblEmail.gridx = 0;
		gbclblEmail.gridy = 7;
		getContentPane().add(lblEmail, gbclblEmail);

		textFieldDpto = new JTextField();
		textFieldDpto.setEditable(false);
		GridBagConstraints gbctextFieldDpto = new GridBagConstraints();
		gbctextFieldDpto.insets = new Insets(0, 0, 5, 5);
		gbctextFieldDpto.fill = GridBagConstraints.BOTH;
		gbctextFieldDpto.gridx = 2;
		gbctextFieldDpto.gridy = 7;
		getContentPane().add(textFieldDpto, gbctextFieldDpto);
		textFieldDpto.setColumns(10);

		lblCiudad = new JLabel("Ciudad:");
		GridBagConstraints gbclblCiudad = new GridBagConstraints();
		gbclblCiudad.anchor = GridBagConstraints.EAST;
		gbclblCiudad.insets = new Insets(0, 0, 5, 5);
		gbclblCiudad.gridx = 3;
		gbclblCiudad.gridy = 7;
		getContentPane().add(lblCiudad, gbclblCiudad);

		textFieldCiudad = new JTextField();
		textFieldCiudad.setEditable(false);
		textFieldCiudad.setColumns(10);
		GridBagConstraints gbctextFieldCiudad = new GridBagConstraints();
		gbctextFieldCiudad.insets = new Insets(0, 0, 5, 5);
		gbctextFieldCiudad.fill = GridBagConstraints.BOTH;
		gbctextFieldCiudad.gridx = 4;
		gbctextFieldCiudad.gridy = 7;
		getContentPane().add(textFieldCiudad, gbctextFieldCiudad);

		lblTipo = new JLabel("Duracion:");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblTipo = new GridBagConstraints();
		gbclblTipo.gridwidth = 2;
		gbclblTipo.anchor = GridBagConstraints.EAST;
		gbclblTipo.insets = new Insets(0, 0, 5, 5);
		gbclblTipo.gridx = 0;
		gbclblTipo.gridy = 8;
		getContentPane().add(lblTipo, gbclblTipo);

		textFieldDuracion = new JTextField();
		textFieldDuracion.setEditable(false);
		GridBagConstraints gbctextFieldDuracion = new GridBagConstraints();
		gbctextFieldDuracion.insets = new Insets(0, 0, 5, 5);
		gbctextFieldDuracion.fill = GridBagConstraints.BOTH;
		gbctextFieldDuracion.gridx = 2;
		gbctextFieldDuracion.gridy = 8;
		getContentPane().add(textFieldDuracion, gbctextFieldDuracion);
		textFieldDuracion.setColumns(10);

		lblCosto = new JLabel("Costo:");
		GridBagConstraints gbclblCosto = new GridBagConstraints();
		gbclblCosto.anchor = GridBagConstraints.EAST;
		gbclblCosto.insets = new Insets(0, 0, 5, 5);
		gbclblCosto.gridx = 3;
		gbclblCosto.gridy = 8;
		getContentPane().add(lblCosto, gbclblCosto);

		textFieldCosto = new JTextField();
		textFieldCosto.setEditable(false);
		textFieldCosto.setColumns(10);
		GridBagConstraints gbctextFieldCosto = new GridBagConstraints();
		gbctextFieldCosto.insets = new Insets(0, 0, 5, 5);
		gbctextFieldCosto.fill = GridBagConstraints.BOTH;
		gbctextFieldCosto.gridx = 4;
		gbctextFieldCosto.gridy = 8;
		getContentPane().add(textFieldCosto, gbctextFieldCosto);

		lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblDescripcion = new GridBagConstraints();
		gbclblDescripcion.gridwidth = 2;
		gbclblDescripcion.anchor = GridBagConstraints.EAST;
		gbclblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbclblDescripcion.gridx = 0;
		gbclblDescripcion.gridy = 9;
		getContentPane().add(lblDescripcion, gbclblDescripcion);

		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setBackground(UIManager.getColor("Button.background"));
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setWrapStyleWord(true);
		textAreaDescripcion.setEditable(false);
		textAreaDescripcion
				.setBorder(new CompoundBorder(new LineBorder(new Color(192, 192, 192)), new EmptyBorder(5, 5, 5, 5)));

		GridBagConstraints gbctextAreaDescripcion = new GridBagConstraints();
		gbctextAreaDescripcion.gridwidth = 3;
		gbctextAreaDescripcion.insets = new Insets(0, 0, 5, 5);
		gbctextAreaDescripcion.fill = GridBagConstraints.BOTH;
		gbctextAreaDescripcion.gridx = 2;
		gbctextAreaDescripcion.gridy = 9;
		getContentPane().add(textAreaDescripcion, gbctextAreaDescripcion);

		lblLink = new JLabel("Fecha de alta:");
		lblLink.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblLink = new GridBagConstraints();
		gbclblLink.gridwidth = 2;
		gbclblLink.anchor = GridBagConstraints.EAST;
		gbclblLink.insets = new Insets(0, 0, 5, 5);
		gbclblLink.gridx = 0;
		gbclblLink.gridy = 10;
		getContentPane().add(lblLink, gbclblLink);

		textFieldFecha = new JTextField();
		textFieldFecha.setEditable(false);
		GridBagConstraints gbctextFieldFecha = new GridBagConstraints();
		gbctextFieldFecha.gridwidth = 3;
		gbctextFieldFecha.insets = new Insets(0, 0, 5, 5);
		gbctextFieldFecha.fill = GridBagConstraints.BOTH;
		gbctextFieldFecha.gridx = 2;
		gbctextFieldFecha.gridy = 10;
		getContentPane().add(textFieldFecha, gbctextFieldFecha);
		textFieldFecha.setColumns(10);

		lblCategorias = new JLabel("Categorias:");
		GridBagConstraints gbclblCategorias = new GridBagConstraints();
		gbclblCategorias.anchor = GridBagConstraints.EAST;
		gbclblCategorias.insets = new Insets(0, 0, 5, 5);
		gbclblCategorias.gridx = 1;
		gbclblCategorias.gridy = 11;
		getContentPane().add(lblCategorias, gbclblCategorias);

		scrollPane = new JScrollPane();
		GridBagConstraints gbcscrollPane = new GridBagConstraints();
		gbcscrollPane.insets = new Insets(0, 0, 5, 5);
		gbcscrollPane.fill = GridBagConstraints.BOTH;
		gbcscrollPane.gridx = 2;
		gbcscrollPane.gridy = 11;
		getContentPane().add(scrollPane, gbcscrollPane);

		demoList = new DefaultListModel();
		listCategorias = new JList(demoList);
		listCategorias.setBackground(new Color(238, 238, 236));
		scrollPane.setViewportView(listCategorias);

		lblActividades = new JLabel("Paquetes");
		GridBagConstraints gbclblActividades = new GridBagConstraints();
		gbclblActividades.gridwidth = 2;
		gbclblActividades.insets = new Insets(0, 0, 5, 5);
		gbclblActividades.anchor = GridBagConstraints.EAST;
		gbclblActividades.gridx = 0;
		gbclblActividades.gridy = 12;
		getContentPane().add(lblActividades, gbclblActividades);

		comboBoxPaquetes = new JComboBox();
		GridBagConstraints gbccomboBoxPaquetes = new GridBagConstraints();
		gbccomboBoxPaquetes.gridwidth = 3;
		gbccomboBoxPaquetes.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxPaquetes.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxPaquetes.gridx = 2;
		gbccomboBoxPaquetes.gridy = 12;
		getContentPane().add(comboBoxPaquetes, gbccomboBoxPaquetes);

		lblSalidas = new JLabel("Salidas");
		GridBagConstraints gbclblSalidas = new GridBagConstraints();
		gbclblSalidas.gridwidth = 2;
		gbclblSalidas.insets = new Insets(0, 0, 5, 5);
		gbclblSalidas.anchor = GridBagConstraints.EAST;
		gbclblSalidas.gridx = 0;
		gbclblSalidas.gridy = 13;
		getContentPane().add(lblSalidas, gbclblSalidas);

		comboBoxSalidas = new JComboBox();
		GridBagConstraints gbccomboBoxSalidas = new GridBagConstraints();
		gbccomboBoxSalidas.gridwidth = 3;
		gbccomboBoxSalidas.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxSalidas.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxSalidas.gridx = 2;
		gbccomboBoxSalidas.gridy = 13;
		getContentPane().add(comboBoxSalidas, gbccomboBoxSalidas);

		comboBoxActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (comboBoxActividad.getSelectedItem() != null && comboBoxActividad.getSelectedItem() != " ") {
					String act = comboBoxActividad.getSelectedItem().toString();
					IControladorActividades ctrlAct = fabrica.getIControladorActividades();
					try {
						DataActividadTuristica dataAct = ctrlAct.consultarActividad(act);
						textFieldNombre.setText(dataAct.getNombre());
						textFieldDpto.setText(dataAct.getDepartamento().getNombre());
						textFieldCiudad.setText(dataAct.getCiudad());
						textAreaDescripcion.setText(dataAct.getDescripcion());
						textFieldFecha.setText(dataAct.getFechaAlta().toString());
						textFieldDuracion.setText(Integer.toString(dataAct.getDuracion()));
						textFieldCosto.setText(Double.toString(dataAct.getCosto()));
						Map<String, DataSalidaTuristica> salidas = dataAct.getSalidas();
						comboBoxSalidas.removeAllItems();
						for (String k : salidas.keySet()) {
							comboBoxSalidas.addItem(k);
						}
						comboBoxSalidas.setSelectedIndex(-1);
						demoList.removeAllElements();
						for (String cat : dataAct.getCategorias()) {
							demoList.addElement(cat);
						}

					} catch (ActividadNoExisteException e1) {
						JOptionPane.showMessageDialog(comboBoxActividad, e1.getMessage(), "Consultar Actividad",
								JOptionPane.ERROR_MESSAGE);
					}
					try {
						List<DataPaqueteActividades> paqsActividades = ctrlAct.obtenerPaquetes(act);
						comboBoxPaquetes.removeAllItems();
						for (DataPaqueteActividades p : paqsActividades) {
							comboBoxPaquetes.addItem(p.getNombre());
						}
						comboBoxPaquetes.setSelectedIndex(-1);
					} catch (NoExisteEntidadException e2) {
						JOptionPane.showMessageDialog(comboBoxActividad, e2.getMessage(), "Consultar Actividad",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		btnVerPaquetes = new JButton("Ver");
		btnVerPaquetes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if (comboBoxPaquetes.getSelectedItem() != null) {
					String paquete = comboBoxPaquetes.getSelectedItem().toString();
					IControladorActividades ctrlActividades = fabrica.getIControladorActividades();
					DataPaqueteActividades dpaq;
					try {
						dpaq = ctrlActividades.consultarPaquete(paquete);
						FabricaInternalFrames fab = FabricaInternalFrames.getInstance();
						ConsultarPaqueteActividades ifrCPaq = fab.getConsultarPaqueteActividades();
						ifrCPaq.setDesdeConsultarActividad(true);
						ifrCPaq.fromConsultarActividad(dpaq);
						ifrCPaq.setVisible(true);
						setVisible(false);
					} catch (NoExisteEntidadException e) {
						JOptionPane.showMessageDialog(btnVerPaquetes, e.getMessage(), "Consultar Actividad",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});
		GridBagConstraints gbcbtnVerPaquetes = new GridBagConstraints();
		gbcbtnVerPaquetes.fill = GridBagConstraints.HORIZONTAL;
		gbcbtnVerPaquetes.insets = new Insets(0, 0, 5, 5);
		gbcbtnVerPaquetes.gridx = 5;
		gbcbtnVerPaquetes.gridy = 12;
		getContentPane().add(btnVerPaquetes, gbcbtnVerPaquetes);

		btnVerSalidas = new JButton("Ver");
		btnVerSalidas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (comboBoxSalidas.getSelectedItem() != null) {
					String salida = comboBoxSalidas.getSelectedItem().toString();
					IControladorSalidas controladorSalidas = fabrica.getIControladorSalidas();
					DataSalidaTuristica dsal;
					try {
						dsal = controladorSalidas.consultarSalida(salida);
						FabricaInternalFrames fab = FabricaInternalFrames.getInstance();
						ConsultarSalidas ifrcSal = fab.getConsultarSalidas();
						ifrcSal.recargar();
						ifrcSal.setDesdeConsultarActividad(true);
						ifrcSal.fromOtraVentana(dsal);
						ifrcSal.setVisible(true);
						setVisible(false);
					} catch (SalidaNoExisteException e1) {
						JOptionPane.showMessageDialog(btnVerSalidas, e1.getMessage(), "Consultar Actividad",
								JOptionPane.ERROR_MESSAGE);
					} catch (ActividadNoExisteException e1) {
						JOptionPane.showMessageDialog(btnVerSalidas, e1.getMessage(), "Consultar Actividad",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		GridBagConstraints gbcbtnVerSalidas = new GridBagConstraints();
		gbcbtnVerSalidas.fill = GridBagConstraints.HORIZONTAL;
		gbcbtnVerSalidas.insets = new Insets(0, 0, 5, 5);
		gbcbtnVerSalidas.gridx = 5;
		gbcbtnVerSalidas.gridy = 13;
		getContentPane().add(btnVerSalidas, gbcbtnVerSalidas);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				FabricaInternalFrames fab = FabricaInternalFrames.getInstance();
				if (desdeConsultarPaquetes) {
					ConsultarPaqueteActividades consultarPaqueteActividades = fab.getConsultarPaqueteActividades();
					consultarPaqueteActividades.setVisible(true);
				} else if (desdeConsultarUsuarios) {
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
		gbcbtnCerrar.gridx = 5;
		gbcbtnCerrar.gridy = 14;
		getContentPane().add(btnCerrar, gbcbtnCerrar);
	}

	// Permite borrar el contenido de un formulario antes de cerrarlo.
	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textFieldCiudad.setText("");
		textFieldDuracion.setText("");
		textFieldCosto.setText("");
		textAreaDescripcion.setText("");
		textFieldFecha.setText("");
		textFieldDpto.setText("");
		comboBoxActividad.removeAllItems();
		comboBoxDepartamento.removeAllItems();
		comboBoxPaquetes.removeAllItems();
		comboBoxSalidas.removeAllItems();
		desdeConsultarPaquetes = false;
		desdeConsultarUsuarios = false;
		comboBoxActividad.setSelectedIndex(-1);
		comboBoxDepartamento.setSelectedIndex(-1);
		comboBoxPaquetes.setSelectedIndex(-1);
		comboBoxSalidas.setSelectedIndex(-1);
		demoList.removeAllElements();
	}

	protected void recargar() {
		comboBoxDepartamento.removeAllItems();
		comboBoxActividad.removeAllItems();
		Fabrica fabrica = Fabrica.getInstance();
		IControladorDepartamentos ctrlDpto = fabrica.getIControladorDepartamentos();
		try {
			comboBoxDepartamento.addItem(" ");
			DataDepartamento[] dptos = ctrlDpto.obtenerDepartamentos();
			for (int i = 0; i < dptos.length; i++) {
				comboBoxDepartamento.addItem(dptos[i].getNombre());
			}
			comboBoxDepartamento.setSelectedIndex(-1);
			comboBoxActividad.setSelectedIndex(-1);
		} catch (DepartamentoNoExisteException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Consultar Actividad", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void fromOtraVentana(DataActividadTuristica dact) {
		comboBoxDepartamento.setSelectedItem(dact.getDepartamento().getNombre());
		comboBoxActividad.addItem(dact.getNombre());
		comboBoxActividad.setSelectedItem(dact.getNombre());
		textFieldDpto.setText(dact.getDepartamento().getNombre());
		textFieldNombre.setText(dact.getNombre());
		textFieldCiudad.setText(dact.getCiudad());
		textAreaDescripcion.setText(dact.getDescripcion());
		textFieldFecha.setText(dact.getFechaAlta().toString());
		textFieldDuracion.setText(Integer.toString(dact.getDuracion()));
		textFieldCosto.setText(Double.toString(dact.getCosto()));
		Map<String, DataSalidaTuristica> salidas = dact.getSalidas();
		comboBoxSalidas.removeAllItems();
		demoList.removeAllElements();
		for (String cat : dact.getCategorias()) {
			demoList.addElement(cat);
		}
		for (String k : salidas.keySet()) {
			comboBoxSalidas.addItem(k);
		}
		comboBoxSalidas.setSelectedIndex(-1);

		try {
			Fabrica fabrica = Fabrica.getInstance();
			IControladorActividades ctrlAct = fabrica.getIControladorActividades();
			List<DataPaqueteActividades> paqsActividades = ctrlAct.obtenerPaquetes(dact.getNombre());
			comboBoxPaquetes.removeAllItems();
			for (DataPaqueteActividades p : paqsActividades) {
				comboBoxPaquetes.addItem(p.getNombre());
			}
			comboBoxPaquetes.setSelectedIndex(-1);
		} catch (NoExisteEntidadException e2) {
			JOptionPane.showMessageDialog(this, e2.getMessage(), "Consultar Actividad", JOptionPane.ERROR_MESSAGE);
		}
	}
}
