package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTree;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


import java.util.Set;

import excepciones.InstitucionException;
import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;

import logica.IcontroladorClase;



@SuppressWarnings("serial")
public class ConsultaDictadoClase extends JInternalFrame {
	

	
	/* Componentes graficas */
	// JLabel:
	private JLabel lblSeleccionInstitucion;
	private JLabel lblSeleccionActividad;
	private JLabel lblSeleccionClase;
	
	// JComboBox:
	private JComboBox<String> boxInstitucion;
	private JComboBox<String> boxActividad;
	private JComboBox<String> boxClase;
	private JTree treeCuponera;
	private JLabel lblInformacin;
    
	//Scrollllllll
	private JScrollPane scrollPane;
	
    /* Crear frame */
	public ConsultaDictadoClase() {

		
		// Propiedades del JInternalFrame:
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consulta de Dictado de Clase");
		setBounds(10, 40, 392, 418);
		
		// GridLayout:
		GridBagLayout gridBagLayout = new GridBagLayout();
	    gridBagLayout.columnWidths = new int[] { 30, 60, 60, 30, 30, 10 };
	    gridBagLayout.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30 };
	    gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
	    gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0 };
	    getContentPane().setLayout(gridBagLayout);
        
        // JLabels:
        lblSeleccionInstitucion = new JLabel("Seleccione Institucion:");
        lblSeleccionInstitucion.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblSeleccionInstitucion = new GridBagConstraints();
        gbc_lblSeleccionInstitucion.gridwidth = 2;
        gbc_lblSeleccionInstitucion.fill = GridBagConstraints.BOTH;
        gbc_lblSeleccionInstitucion.insets = new Insets(0, 0, 5, 5);
        gbc_lblSeleccionInstitucion.gridx = 1;
        gbc_lblSeleccionInstitucion.gridy = 0;
        getContentPane().add(lblSeleccionInstitucion, gbc_lblSeleccionInstitucion);
        
        lblSeleccionActividad = new JLabel("Seleccione Actividad Deportiva:");
        lblSeleccionActividad.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblSeleccionActividad = new GridBagConstraints();
        gbc_lblSeleccionActividad.gridwidth = 2;
        gbc_lblSeleccionActividad.fill = GridBagConstraints.BOTH;
        gbc_lblSeleccionActividad.insets = new Insets(0, 0, 5, 5);
        gbc_lblSeleccionActividad.gridx = 1;
        gbc_lblSeleccionActividad.gridy = 2;
        getContentPane().add(lblSeleccionActividad, gbc_lblSeleccionActividad);
        
        lblSeleccionClase = new JLabel("Seleccione Clase:");
        lblSeleccionClase.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblSeleccionClase = new GridBagConstraints();
        gbc_lblSeleccionClase.gridwidth = 2;
        gbc_lblSeleccionClase.fill = GridBagConstraints.BOTH;
        gbc_lblSeleccionClase.insets = new Insets(0, 0, 5, 5);
        gbc_lblSeleccionClase.gridx = 1;
        gbc_lblSeleccionClase.gridy = 4;
        getContentPane().add(lblSeleccionClase, gbc_lblSeleccionClase);
        
        // JComboBox:
        boxInstitucion = new JComboBox<>();
        boxInstitucion.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
        boxInstitucion.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		try {
	        		int selectIndex = boxInstitucion.getSelectedIndex();
	    			boxActividad.removeAllItems();
	    			DefaultComboBoxModel<String> modelActividad = new DefaultComboBoxModel<>();
	    			modelActividad.addElement("-");
	    			if (selectIndex > 0) {
	        			
	                    boxActividad.setEnabled(true);
	        		} else {
	        			boxActividad.setEnabled(false);
	        			treeCuponera.setModel(new DefaultTreeModel(
	        					new DefaultMutableTreeNode("root") {
	        						{
	        							//WindowBuilder BUG: se cambia a getContentPane.add() por algun motivo. Dejarlo solo como add();
	        							// Cada vez que se abre la ventan design hay que corregirlo xddd;
	        							add(new DefaultMutableTreeNode("Seleccione una clase para desplegar su info."));
	        						}
	        					}
	        				));
	        		}
	            	boxActividad.setModel(modelActividad);
        		} catch (Exception ignore) { }
        	}
        });
        GridBagConstraints gbc_boxInstitucion = new GridBagConstraints();
        gbc_boxInstitucion.gridwidth = 3;
        gbc_boxInstitucion.insets = new Insets(0, 0, 5, 0);
        gbc_boxInstitucion.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxInstitucion.gridx = 1;
        gbc_boxInstitucion.gridy = 1;
        getContentPane().add(boxInstitucion, gbc_boxInstitucion);
        
        boxActividad = new JComboBox<>();
        boxActividad.setEnabled(false);
        boxActividad.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		try {
	        		int selectIndex = boxActividad.getSelectedIndex();
	    			boxClase.removeAllItems();
	    			DefaultComboBoxModel<String> modelClase = new DefaultComboBoxModel<>();
	    			modelClase.addElement("-");
	    		
	    			boxClase.setModel(modelClase);
        		} catch (Exception ignore) { }
        	}
        });
        GridBagConstraints gbc_boxActividad = new GridBagConstraints();
        gbc_boxActividad.gridwidth = 3;
        gbc_boxActividad.insets = new Insets(0, 0, 5, 0);
        gbc_boxActividad.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxActividad.gridx = 1;
        gbc_boxActividad.gridy = 3;
        getContentPane().add(boxActividad, gbc_boxActividad);
        
        boxClase = new JComboBox<>();
        GridBagConstraints gbc_boxClase = new GridBagConstraints();
        gbc_boxClase.gridwidth = 3;
        gbc_boxClase.insets = new Insets(0, 0, 5, 0);
        gbc_boxClase.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxClase.gridx = 1;
        gbc_boxClase.gridy = 5;
        getContentPane().add(boxClase, gbc_boxClase);
        boxClase.setEnabled(false);
        boxClase.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		// Falta funcion
        	}
        });
        lblInformacin = new JLabel("Informaci\u00F3n:");
        lblInformacin.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblInformacin = new GridBagConstraints();
        gbc_lblInformacin.anchor = GridBagConstraints.WEST;
        gbc_lblInformacin.insets = new Insets(0, 0, 5, 5);
        gbc_lblInformacin.gridx = 1;
        gbc_lblInformacin.gridy = 6;
        getContentPane().add(lblInformacin, gbc_lblInformacin);
        
        scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 7;
		add(scrollPane, gbc_scrollPane);
        
        treeCuponera = new JTree();
        scrollPane.setViewportView(treeCuponera);
        treeCuponera.setRootVisible(false);
		treeCuponera.setRootVisible(false);
		treeCuponera.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("root") {
					{
						add(new DefaultMutableTreeNode("Seleccione una clase para desplegar su info."));
					}
				}
			));
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		treeCuponera.setBorder(BorderFactory.createCompoundBorder(border, 
			      BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        GridBagConstraints gbc_treeCuponera = new GridBagConstraints();
        gbc_treeCuponera.gridwidth = 3;
        gbc_treeCuponera.gridheight = 4;
        gbc_treeCuponera.fill = GridBagConstraints.BOTH;
        gbc_treeCuponera.gridx = 1;
        gbc_treeCuponera.gridy = 7;

	}


    

    
    // Limpia el JInternalFrame
 	public void clear() {
 		//textArea.setText("Aqui se mostrara la informacion de la Clase seleccionada.");
        boxInstitucion.removeAllItems();
        boxActividad.removeAllItems();
        boxActividad.setEnabled(false);
        boxClase.removeAllItems();
        boxClase.setEnabled(false);
    }


}
