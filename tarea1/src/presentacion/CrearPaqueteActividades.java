package presentacion;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.InternalFrameEvent;

import com.toedter.calendar.JDateChooser;

import excepciones.EntidadRepetidaException;
import logica.Fabrica;
import logica.interfaces.IControladorActividades;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;

public class CrearPaqueteActividades extends JInternalFrame {
	
	Fabrica fabrica = Fabrica.getInstance();
	private IControladorActividades controlAct = fabrica.getIControladorActividades();

	
	 private JTextField textFieldNombre;
	 private JTextField textFieldValidez;
	 private JTextField textFieldDescuento;
	 private JTextArea textAreaDescripcion;
	 private JDateChooser dateChooser;
	 private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
	   	setVisible(false);
	   	limpiarFormulario();
	 }


	/**
	 * Create the frame.
	 */
	public CrearPaqueteActividades() {
		setClosable(true);
		setMaximizable(true);
	    Toolkit myScreen = Toolkit.getDefaultToolkit();
        setSize((myScreen.getScreenSize().width)*2/5,(myScreen.getScreenSize().height)*2/5);
        setLocation(myScreen.getScreenSize().width/12,myScreen.getScreenSize().height/12);	

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {}
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {}
			@Override
			public void internalFrameDeactivated(InternalFrameEvent arg0) {}
			@Override
			public void internalFrameDeiconified(InternalFrameEvent arg0) {}
			@Override
			public void internalFrameIconified(InternalFrameEvent arg0) {}
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {}
		});
        
        
        setTitle("Crear paquete de actividades turísticas");
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{165, 332, 0, 0};
        gridBagLayout.rowHeights = new int[]{20, 25, 60, 25, 25, 25, 25, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        JLabel lblNombre = new JLabel("Nombre");
        GridBagConstraints gbclblNombre = new GridBagConstraints();
        gbclblNombre.insets = new Insets(0, 0, 5, 5);
        gbclblNombre.anchor = GridBagConstraints.EAST;
        gbclblNombre.gridx = 0;
        gbclblNombre.gridy = 1;
        getContentPane().add(lblNombre, gbclblNombre);
        
        textFieldNombre = new JTextField();
        GridBagConstraints gbctextFieldNombre = new GridBagConstraints();
        gbctextFieldNombre.insets = new Insets(0, 0, 5, 5);
        gbctextFieldNombre.fill = GridBagConstraints.BOTH;
        gbctextFieldNombre.gridx = 1;
        gbctextFieldNombre.gridy = 1;
        getContentPane().add(textFieldNombre, gbctextFieldNombre);
        textFieldNombre.setColumns(10);
        
        JLabel lblDescripcin = new JLabel("Descripción");
        GridBagConstraints gbclblDescripcin = new GridBagConstraints();
        gbclblDescripcin.anchor = GridBagConstraints.EAST;
        gbclblDescripcin.insets = new Insets(0, 0, 5, 5);
        gbclblDescripcin.gridx = 0;
        gbclblDescripcin.gridy = 2;
        getContentPane().add(lblDescripcin, gbclblDescripcin);
        
        textAreaDescripcion = new JTextArea();
        textAreaDescripcion.setLineWrap(true);
        textAreaDescripcion.setWrapStyleWord(true);

		textAreaDescripcion.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY), new EmptyBorder(5, 5, 5, 5)));
        GridBagConstraints gbctextAreaDescripcion = new GridBagConstraints();
        gbctextAreaDescripcion.insets = new Insets(0, 0, 5, 5);
        gbctextAreaDescripcion.fill = GridBagConstraints.BOTH;
        gbctextAreaDescripcion.gridx = 1;
        gbctextAreaDescripcion.gridy = 2;
        getContentPane().add(textAreaDescripcion, gbctextAreaDescripcion);
        
        JLabel lblPerodoDeValidez = new JLabel("Período de validez");
        GridBagConstraints gbclblPerodoDeValidez = new GridBagConstraints();
        gbclblPerodoDeValidez.anchor = GridBagConstraints.EAST;
        gbclblPerodoDeValidez.insets = new Insets(0, 0, 5, 5);
        gbclblPerodoDeValidez.gridx = 0;
        gbclblPerodoDeValidez.gridy = 3;
        getContentPane().add(lblPerodoDeValidez, gbclblPerodoDeValidez);
        
        textFieldValidez = new JTextField();
        GridBagConstraints gbctextFieldValidez = new GridBagConstraints();
        gbctextFieldValidez.insets = new Insets(0, 0, 5, 5);
        gbctextFieldValidez.fill = GridBagConstraints.BOTH;
        gbctextFieldValidez.gridx = 1;
        gbctextFieldValidez.gridy = 3;
        getContentPane().add(textFieldValidez, gbctextFieldValidez);
        textFieldValidez.setColumns(10);
        
        JLabel lblDescuento = new JLabel("Descuento");
        GridBagConstraints gbclblDescuento = new GridBagConstraints();
        gbclblDescuento.anchor = GridBagConstraints.EAST;
        gbclblDescuento.insets = new Insets(0, 0, 5, 5);
        gbclblDescuento.gridx = 0;
        gbclblDescuento.gridy = 4;
        getContentPane().add(lblDescuento, gbclblDescuento);
        
        textFieldDescuento = new JTextField();
        GridBagConstraints gbctextFieldDescuento = new GridBagConstraints();
        gbctextFieldDescuento.insets = new Insets(0, 0, 5, 5);
        gbctextFieldDescuento.fill = GridBagConstraints.BOTH;
        gbctextFieldDescuento.gridx = 1;
        gbctextFieldDescuento.gridy = 4;
        getContentPane().add(textFieldDescuento, gbctextFieldDescuento);
        textFieldDescuento.setColumns(10);
        
        JLabel lblFechaDeAlta = new JLabel("Fecha de alta");
        GridBagConstraints gbclblFechaDeAlta = new GridBagConstraints();
        gbclblFechaDeAlta.anchor = GridBagConstraints.EAST;
        gbclblFechaDeAlta.insets = new Insets(0, 0, 5, 5);
        gbclblFechaDeAlta.gridx = 0;
        gbclblFechaDeAlta.gridy = 5;
        getContentPane().add(lblFechaDeAlta, gbclblFechaDeAlta);
        
        dateChooser = new JDateChooser();
        GridBagConstraints gbcdateChooser = new GridBagConstraints();
        gbcdateChooser.insets = new Insets(0, 0, 5, 5);
        gbcdateChooser.fill = GridBagConstraints.BOTH;
        gbcdateChooser.gridx = 1;
        gbcdateChooser.gridy = 5;
        getContentPane().add(dateChooser, gbcdateChooser);
        
        JPanel panel = new JPanel();
        GridBagConstraints gbcpanel = new GridBagConstraints();
        gbcpanel.anchor = GridBagConstraints.EAST;
        gbcpanel.insets = new Insets(0, 0, 5, 5);
        gbcpanel.fill = GridBagConstraints.VERTICAL;
        gbcpanel.gridx = 1;
        gbcpanel.gridy = 6;
        getContentPane().add(panel, gbcpanel);
        GridBagLayout gblpanel = new GridBagLayout();
        gblpanel.columnWidths = new int[]{0, 0, 0, 0};
        gblpanel.rowHeights = new int[]{0, 0};
        gblpanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gblpanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel.setLayout(gblpanel);
        
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                crearPaqueteActividadesActionPerformed(arg0);
            }
        });
        GridBagConstraints gbcbtnAceptar = new GridBagConstraints();
        gbcbtnAceptar.insets = new Insets(0, 0, 0, 5);
        gbcbtnAceptar.gridx = 0;
        gbcbtnAceptar.gridy = 0;
        panel.add(btnAceptar, gbcbtnAceptar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		limpiarFormulario();
       	     	setVisible(false);
        	}
        });
        GridBagConstraints gbcbtnCancelar = new GridBagConstraints();
        gbcbtnCancelar.anchor = GridBagConstraints.EAST;
        gbcbtnCancelar.gridx = 2;
        gbcbtnCancelar.gridy = 0;
        panel.add(btnCancelar, gbcbtnCancelar);

	}
	
	protected void crearPaqueteActividadesActionPerformed(ActionEvent arg0) {
		String nombre = this.textFieldNombre.getText().trim();
		String descripcion = this.textAreaDescripcion.getText().trim();
		String validez = this.textFieldValidez.getText().trim();
		String descuento = this.textFieldDescuento.getText().trim();
		java.util.Date alta = this.dateChooser.getDate();
		
		if (nombre.isEmpty() || descripcion.isEmpty() || validez.isEmpty() || descuento.isEmpty() || alta == null) {
        	JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Crear Paquete de actividades",
                    JOptionPane.ERROR_MESSAGE);
        } else {
        	try {
        		LocalDate fechaAlta = alta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
               	int val = Integer.parseInt(validez);            		
                try {
                    float desc = Float.parseFloat(descuento); 
                    if (desc < 0) {
                    	JOptionPane.showMessageDialog(this, "El descuento debe ser positivo", "Crear Paquete de actividades",
                                  JOptionPane.ERROR_MESSAGE);
                    	textFieldDescuento.setText("");
                    } else if (val < 0) {
                    	JOptionPane.showMessageDialog(this, "La validez debe ser positiva", "Crear Paquete de actividades",
                                JOptionPane.ERROR_MESSAGE);
                    	textFieldValidez.setText("");
                    } else {
                    	controlAct.crearPaquete(nombre, descripcion, val, desc, fechaAlta);
                       	//Llamar a la funcion que crea la salida
                       	//controlSal.CrearSalida(nombre, cant, fechaAlta, salida, fechaSalida, hora,act);
                        JOptionPane.showMessageDialog(this, "Se ha registrado correctamente el paquete", "Crear Paquete de Actividades",
                                    JOptionPane.INFORMATION_MESSAGE);
                        textFieldNombre.setText("");
                        textAreaDescripcion.setText("");
                        textFieldValidez.setText("");
                        textFieldDescuento.setText("");
                    	dateChooser.setDate(null);
                        setVisible(false);
                    }
                    	
    			} catch (NumberFormatException e) {
    	                JOptionPane.showMessageDialog(this, "El descuento debe ser un numero real", "Crear Paquete Actividades", JOptionPane.ERROR_MESSAGE);
    	                textFieldDescuento.setText("");				
    	        }
			} catch (NumberFormatException e) {
	                JOptionPane.showMessageDialog(this, "El periodo de validez debe ser un numero entero", "rear Paquete Actividades", JOptionPane.ERROR_MESSAGE);
	                textFieldValidez.setText("");				
	        }	
        	catch (EntidadRepetidaException ex) {
        		JOptionPane.showMessageDialog(this, ex.getMessage(), "Crear Paquete Actividades", JOptionPane.ERROR_MESSAGE);
        	}
        }
	}
	
	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textFieldValidez.setText("");
		textFieldDescuento.setText("");
		textAreaDescripcion.setText("");
	    dateChooser.setDate(null);		
	}

}
