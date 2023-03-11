package presentacion;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.ZoneId;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.InternalFrameEvent;

import com.toedter.calendar.JDateChooser;

import excepciones.DepartamentoNoExisteException;
import excepciones.InscripcionRepetidaException;
import excepciones.NoExisteEntidadException;
import excepciones.NoHayCapacidadException;
import excepciones.SalidaNoExisteException;
import excepciones.UsuarioNoExisteException;
import logica.Fabrica;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataDepartamento;
import logica.datatypes.DataSalidaTuristica;
import logica.datatypes.DataTurista;
import logica.interfaces.IControladorDepartamentos;
import logica.interfaces.IControladorSalidas;
import logica.interfaces.IControladorUsuario;

public class InscripcionSalidaTuristica extends JInternalFrame {
	// private JTextField textFieldFechaAlta;
	private JDateChooser dateChooser;
	private JComboBox comboBoxDepartamentos;
	private JLabel lblActividad;
	private JComboBox comboBoxActividad;
	private JLabel lblSalidasVigentes;
	private JComboBox comboBoxSalidasVigentes;
	private JTextPane textPane;
	private JComboBox comboBoxTuristas;
	private JLabel lblTurista;
	private JLabel lblCantidadDeTuristas;
	private JSpinner spinnerCantidadTuristas;
	private JButton btnCancelar;
	private JButton btnRealizarInscripcin;
	private IControladorDepartamentos controladorDepartamentos;
	private IControladorSalidas controladorSalidas;
	private IControladorUsuario controladorUsuario;
	private DataActividadTuristica[] actividades;
	private List<DataSalidaTuristica> salidas;

	private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
		limpiarFormulario();
		setVisible(false);
	}

	Fabrica fabrica = Fabrica.getInstance();

	// private JButton btnCancelar;

	/**
	 * Create the frame.
	 */
	public InscripcionSalidaTuristica() {

		this.controladorSalidas = fabrica.getIControladorSalidas();
		this.controladorUsuario = fabrica.getIControladorUsuario();
		this.controladorDepartamentos = fabrica.getIControladorDepartamentos();

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
		setTitle("Inscripción a salida turística");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(myScreen.getScreenSize().width / 2, myScreen.getScreenSize().height / 2);
		setLocation(myScreen.getScreenSize().width / 12, myScreen.getScreenSize().height / 40);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 170, 190, 190, 40, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0 };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblA = new JLabel("Departamento");
		GridBagConstraints gbclblA = new GridBagConstraints();
		gbclblA.anchor = GridBagConstraints.EAST;
		gbclblA.insets = new Insets(0, 0, 5, 5);
		gbclblA.gridx = 0;
		gbclblA.gridy = 1;
		getContentPane().add(lblA, gbclblA);

		comboBoxDepartamentos = new JComboBox();
		GridBagConstraints gbccomboBoxDepartamentos = new GridBagConstraints();
		gbccomboBoxDepartamentos.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxDepartamentos.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxDepartamentos.gridx = 1;
		gbccomboBoxDepartamentos.gridy = 1;
		getContentPane().add(comboBoxDepartamentos, gbccomboBoxDepartamentos);
		comboBoxDepartamentos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (comboBoxDepartamentos.getSelectedItem() != null && comboBoxDepartamentos.getSelectedItem() != " ") {

					String departamento = (String) comboBoxDepartamentos.getSelectedItem();
					comboBoxActividad.removeAllItems();
					try {
						actividades = controladorSalidas.obtenerActividadesDepartamento(departamento);
						for (DataActividadTuristica dataActividadTuristica : actividades) {
							comboBoxActividad.addItem(dataActividadTuristica);
						}
					} catch (DepartamentoNoExisteException e1) {
						JOptionPane.showMessageDialog(textPane, e1.getMessage(), "Registrar Actividad",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		lblActividad = new JLabel("Actividad");
		GridBagConstraints gbclblActividad = new GridBagConstraints();
		gbclblActividad.anchor = GridBagConstraints.EAST;
		gbclblActividad.insets = new Insets(0, 0, 5, 5);
		gbclblActividad.gridx = 0;
		gbclblActividad.gridy = 2;
		getContentPane().add(lblActividad, gbclblActividad);

		comboBoxActividad = new JComboBox();
		comboBoxActividad.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int indexActividad = comboBoxActividad.getSelectedIndex();
				if (indexActividad == -1) {
					return;
				}
				comboBoxSalidasVigentes.removeAllItems();
				try {
					salidas = controladorSalidas.getSalidasVigentes(actividades[indexActividad].getNombre());
					for (DataSalidaTuristica salidaTuristica : salidas) {
						// comboBoxSalidasVigentes.addItem(new ItemTexto(salidaTuristica.toString()));
						comboBoxSalidasVigentes.addItem(salidaTuristica);
					}
				} catch (SalidaNoExisteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		GridBagConstraints gbccomboBoxActividad = new GridBagConstraints();
		gbccomboBoxActividad.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxActividad.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxActividad.gridx = 1;
		gbccomboBoxActividad.gridy = 2;
		getContentPane().add(comboBoxActividad, gbccomboBoxActividad);

		lblSalidasVigentes = new JLabel("Salidas vigentes");
		GridBagConstraints gbclblSalidasVigentes = new GridBagConstraints();
		gbclblSalidasVigentes.anchor = GridBagConstraints.EAST;
		gbclblSalidasVigentes.insets = new Insets(0, 0, 5, 5);
		gbclblSalidasVigentes.gridx = 0;
		gbclblSalidasVigentes.gridy = 3;
		getContentPane().add(lblSalidasVigentes, gbclblSalidasVigentes);

		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(UIManager.getColor("Button.background"));
		textPane.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY), new EmptyBorder(5, 5, 5, 5)));

		comboBoxSalidasVigentes = new JComboBox();
		comboBoxSalidasVigentes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				DataSalidaTuristica salidaSeleccionada = (DataSalidaTuristica) comboBoxSalidasVigentes
						.getSelectedItem();
				if (salidaSeleccionada == null) {
					return;
				}
				textPane.setText(salidaSeleccionada.toString());
			}
		});

		GridBagConstraints gbccomboBoxSalidasVigentes = new GridBagConstraints();
		gbccomboBoxSalidasVigentes.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxSalidasVigentes.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxSalidasVigentes.gridx = 1;
		gbccomboBoxSalidasVigentes.gridy = 3;
		getContentPane().add(comboBoxSalidasVigentes, gbccomboBoxSalidasVigentes);

		GridBagConstraints gbctextPane = new GridBagConstraints();
		gbctextPane.gridheight = 3;
		gbctextPane.gridwidth = 2;
		gbctextPane.insets = new Insets(0, 0, 5, 5);
		gbctextPane.fill = GridBagConstraints.BOTH;
		gbctextPane.gridx = 1;
		gbctextPane.gridy = 4;
		getContentPane().add(textPane, gbctextPane);

		lblTurista = new JLabel("Turista");
		GridBagConstraints gbclblTurista = new GridBagConstraints();
		gbclblTurista.insets = new Insets(0, 0, 5, 5);
		gbclblTurista.anchor = GridBagConstraints.EAST;
		gbclblTurista.gridx = 0;
		gbclblTurista.gridy = 7;
		getContentPane().add(lblTurista, gbclblTurista);

		comboBoxTuristas = new JComboBox();
		GridBagConstraints gbccomboBoxTuristas = new GridBagConstraints();
		gbccomboBoxTuristas.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxTuristas.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxTuristas.gridx = 1;
		gbccomboBoxTuristas.gridy = 7;
		getContentPane().add(comboBoxTuristas, gbccomboBoxTuristas);

		JLabel lblG = new JLabel("Fecha de inscripción");
		GridBagConstraints gbclblG = new GridBagConstraints();
		gbclblG.anchor = GridBagConstraints.EAST;
		gbclblG.insets = new Insets(0, 0, 5, 5);
		gbclblG.gridx = 0;
		gbclblG.gridy = 8;
		getContentPane().add(lblG, gbclblG);

		dateChooser = new JDateChooser();
		GridBagConstraints gbcdateChooser = new GridBagConstraints();
		gbcdateChooser.insets = new Insets(0, 0, 5, 5);
		gbcdateChooser.fill = GridBagConstraints.BOTH;
		gbcdateChooser.gridx = 1;
		gbcdateChooser.gridy = 8;
		getContentPane().add(dateChooser, gbcdateChooser);

		lblCantidadDeTuristas = new JLabel("Cantidad de turistas");
		GridBagConstraints gbclblCantidadDeTuristas = new GridBagConstraints();
		gbclblCantidadDeTuristas.anchor = GridBagConstraints.EAST;
		gbclblCantidadDeTuristas.insets = new Insets(0, 0, 5, 5);
		gbclblCantidadDeTuristas.gridx = 0;
		gbclblCantidadDeTuristas.gridy = 9;
		getContentPane().add(lblCantidadDeTuristas, gbclblCantidadDeTuristas);

		SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, (Comparable) null, 1);
		spinnerCantidadTuristas = new JSpinner(spinnerModel);
		GridBagConstraints gbcspinnerCantidadTuristas = new GridBagConstraints();
		gbcspinnerCantidadTuristas.insets = new Insets(0, 0, 5, 5);
		gbcspinnerCantidadTuristas.gridx = 1;
		gbcspinnerCantidadTuristas.gridy = 9;
		getContentPane().add(spinnerCantidadTuristas, gbcspinnerCantidadTuristas);

		btnRealizarInscripcin = new JButton("Realizar inscripción");
		btnRealizarInscripcin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					controladorSalidas.inscribirTuristaSalida(
							((DataTurista) comboBoxTuristas.getSelectedItem()).getNickname(),
							((DataSalidaTuristica) comboBoxSalidasVigentes.getSelectedItem()).getNombre(),
							(int) spinnerCantidadTuristas.getValue(),
							dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
					JOptionPane.showMessageDialog(btnRealizarInscripcin, "La inscripción se ha registrado con éxito",
							"Inscripción a Salida", JOptionPane.INFORMATION_MESSAGE);
					limpiarFormulario();
					setVisible(false);
				} catch (NoExisteEntidadException e1) {
					JOptionPane.showMessageDialog(comboBoxActividad, e1.getMessage(), "Inscripción a Salida",
							JOptionPane.ERROR_MESSAGE);
				} catch (InscripcionRepetidaException e1) {
					JOptionPane.showMessageDialog(comboBoxActividad, e1.getMessage(), "Inscripción a Salida",
							JOptionPane.ERROR_MESSAGE);
				} catch (NoHayCapacidadException e1) {
					JOptionPane.showMessageDialog(comboBoxActividad, e1.getMessage(), "Inscripción a Salida",
							JOptionPane.ERROR_MESSAGE);
				} catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(comboBoxActividad, "No pueden haber campos vacíos",
							"Inscripción a Salida", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		GridBagConstraints gbcbtnRealizarInscripcin = new GridBagConstraints();
		gbcbtnRealizarInscripcin.insets = new Insets(0, 0, 5, 5);
		gbcbtnRealizarInscripcin.gridx = 1;
		gbcbtnRealizarInscripcin.gridy = 10;
		getContentPane().add(btnRealizarInscripcin, gbcbtnRealizarInscripcin);

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
		gbcbtnCancelar.gridy = 10;
		getContentPane().add(btnCancelar, gbcbtnCancelar);

	}

	private void limpiarFormulario() {
		spinnerCantidadTuristas.setValue(1);
		dateChooser.getDateEditor().setDate(null);
		textPane.setText("");
		comboBoxActividad.removeAllItems();
		comboBoxDepartamentos.removeAllItems();
		comboBoxSalidasVigentes.removeAllItems();
		comboBoxTuristas.removeAllItems();
		comboBoxActividad.setSelectedIndex(-1);
		comboBoxDepartamentos.setSelectedIndex(-1);
		comboBoxSalidasVigentes.setSelectedIndex(-1);
		comboBoxTuristas.setSelectedIndex(-1);
	}

	public void hacerVisible() {
		try {
			comboBoxDepartamentos.removeAllItems();
			DataDepartamento[] dptos = controladorDepartamentos.obtenerDepartamentos();
			comboBoxDepartamentos.addItem(" ");
			for (int i = 0; i < dptos.length; i++) {
				comboBoxDepartamentos.addItem(dptos[i].getNombre());
			}
			comboBoxTuristas.addItem(" ");
			for (DataTurista dataTurista : controladorUsuario.getTuristas()) {
				comboBoxTuristas.addItem(dataTurista);
			}
			comboBoxDepartamentos.setSelectedIndex(-1);
			comboBoxActividad.setSelectedIndex(-1);
			comboBoxTuristas.setSelectedIndex(-1);
		} catch (DepartamentoNoExisteException e1) {
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
		}
		setVisible(true);

	}

}
