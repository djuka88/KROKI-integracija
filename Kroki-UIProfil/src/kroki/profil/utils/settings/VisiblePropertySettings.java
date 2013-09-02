/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kroki.profil.utils.settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import kroki.intl.Intl;
import kroki.profil.ComponentType;
import kroki.profil.VisibleElement;
import kroki.profil.property.VisibleProperty;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Vladan Marsenic (vladan.marsenic@gmail.com)
 */
public class VisiblePropertySettings extends VisibleElementSettings {

	protected JLabel typelbl;
    protected JLabel columnLabelLb;
    protected JLabel displayFormatLb;
    protected JLabel valuesLb;
    protected JLabel representativeLb;
    protected JLabel mandatoryLb;
    protected JLabel autoGoLb;
    protected JLabel disabledLb;
    protected JLabel defaultValueLb;
    protected JComboBox<String> typeCb;
    protected JTextField columnLabelTf;
    protected JTextField displayFormatTf;
    protected JScrollPane valuesSp;
    protected JTextArea valuesTa;
    protected JTextField defaultValueTf;
    protected JCheckBox representativeCb;
    protected JCheckBox mandatoryCb;
    protected JCheckBox disabledCb;
    protected JCheckBox autoGoCb;

    public VisiblePropertySettings(SettingsCreator settingsCreator) {
        super(settingsCreator);
        initComponents();
        layoutComponents();
        addActions();
    }

    private void initComponents() {
    	typelbl = new JLabel("Data type");
        columnLabelLb = new JLabel(Intl.getValue("visibleProperty.columnLabel"));
        displayFormatLb = new JLabel(Intl.getValue("visibleProperty.displayFormat"));
        valuesLb = new JLabel(Intl.getValue("visibleProperty.values"));
        representativeLb = new JLabel(Intl.getValue("visibleProperty.representative"));
        mandatoryLb = new JLabel(Intl.getValue("visibleProperty.mandatory"));
        autoGoLb = new JLabel(Intl.getValue("visibleProperty.autoGo"));
        disabledLb = new JLabel(Intl.getValue("visibleProperty.disabled"));
        defaultValueLb = new JLabel(Intl.getValue("visibleProperty.defaultValue"));
        
        String[] types = { "String", "Integer", "Long", "BigDecimal", "Date" };
        typeCb = new JComboBox<String>(types);
        typeCb.setSelectedIndex(0);
        typeCb.setEnabled(false);
        
        columnLabelTf = new JTextField(30);
        displayFormatTf = new JTextField(30);
        valuesTa = new JTextArea(5, 30);
        valuesTa.setFont(this.getFont());
        valuesSp = new JScrollPane(valuesTa);
        valuesSp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        valuesSp.setMinimumSize(valuesTa.getPreferredScrollableViewportSize());
        defaultValueTf = new JTextField(30);
        representativeCb = new JCheckBox();
        mandatoryCb = new JCheckBox();
        autoGoCb = new JCheckBox();
        disabledCb = new JCheckBox();
    }

    private void layoutComponents() {
        JPanel intermediate = null;
        if (panelMap.containsKey("group.INTERMEDIATE")) {
            intermediate = panelMap.get("group.INTERMEDIATE");
        } else {
            intermediate = new JPanel();
            intermediate.setLayout(new MigLayout("wrap 2,hidemode 3", "[right, shrink][fill, 200]"));
            panelMap.put("group.INTERMEDIATE", intermediate);
            addTab(Intl.getValue("group.INTERMEDIATE"), intermediate);
        }
        intermediate.add(typelbl);
        intermediate.add(typeCb);
        intermediate.add(columnLabelLb);
        intermediate.add(columnLabelTf);
        intermediate.add(displayFormatLb);
        intermediate.add(displayFormatTf);
        intermediate.add(valuesLb);
        intermediate.add(valuesSp);
        intermediate.add(mandatoryLb);
        intermediate.add(mandatoryCb);
        intermediate.add(representativeLb);
        intermediate.add(representativeCb);
        intermediate.add(autoGoLb);
        intermediate.add(autoGoCb);
        intermediate.add(disabledLb);
        intermediate.add(disabledCb);
        intermediate.add(defaultValueLb);
        intermediate.add(defaultValueTf);
        //intermediate.doLayout();
    }

    private void addActions() {
    	
    	VisiblePropertySettings.this.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				updateComponents();
			}
		});
    	
    	typeCb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VisibleProperty visibleProperty = (VisibleProperty) visibleElement;
				if(typeCb.isEnabled()) {
					if(typeCb.getSelectedItem() != null) {
						visibleProperty.setDataType(typeCb.getSelectedItem().toString());
					}else{
						visibleProperty.setDataType("String");
					}
				}
			}
		});
    	
        columnLabelTf.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                contentChanged(e);
            }

            public void removeUpdate(DocumentEvent e) {
                contentChanged(e);
            }

            public void changedUpdate(DocumentEvent e) {
                //nista se ne desava
            }

            private void contentChanged(DocumentEvent e) {
                Document doc = e.getDocument();
                String text = "";
                try {
                    text = doc.getText(0, doc.getLength());
                } catch (BadLocationException ex) {
                }
                VisibleProperty visibleProperty = (VisibleProperty) visibleElement;
                visibleProperty.setColumnLabel(text);
                updatePreformed();
            }
        });

        displayFormatTf.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                contentChanged(e);
            }

            public void removeUpdate(DocumentEvent e) {
                contentChanged(e);
            }

            public void changedUpdate(DocumentEvent e) {
                //nista se ne desava
            }

            private void contentChanged(DocumentEvent e) {
                Document doc = e.getDocument();
                String text = "";
                try {
                    text = doc.getText(0, doc.getLength());
                } catch (BadLocationException ex) {
                }
                VisibleProperty visibleProperty = (VisibleProperty) visibleElement;
                visibleProperty.setDisplayFormat(text);
                updatePreformed();
            }
        });

        valuesTa.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				VisibleProperty prop = (VisibleProperty) visibleElement;
				String[] values = valuesTa.getText().split("\\n");
				String enumeration = "";
				int size=values.length;
				
				//dodao Milan Djukic
				//ovde je bio bug... kada se obrise lista vrednosti (znaci prazna je)
				//program automatski dodaje novi red u listu...
				//ovo je zbog toga sto split funkcija kreira jedan novi string uvek
				//pa zbog toga size nikad nije nula
				if(values.length==1 && values[0].equals(""))
					size=0;
				
				for(int i=0; i<size; i++) {
					enumeration +=values[i] + ";";
				}
				prop.setEnumeration(enumeration);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
			}
		});
        
        defaultValueTf.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                contentChanged(e);
            }

            public void removeUpdate(DocumentEvent e) {
                contentChanged(e);
            }

            public void changedUpdate(DocumentEvent e) {
                //nista se ne desava
            }

            private void contentChanged(DocumentEvent e) {
                Document doc = e.getDocument();
                String text = "";
                try {
                    text = doc.getText(0, doc.getLength());
                } catch (BadLocationException ex) {
                }
                VisibleProperty visibleProperty = (VisibleProperty) visibleElement;
                visibleProperty.setDefaultValue(text);
            }
        });

        mandatoryCb.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox checkBox = (JCheckBox) e.getSource();
				boolean value = checkBox.isSelected();
				VisibleProperty visibleProperty = (VisibleProperty) visibleElement;
				if(value) {
					visibleProperty.setLower(1);
				}else {
					visibleProperty.setLower(0);
				}
				updatePreformed();
			}
		});
        
        representativeCb.addActionListener(new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                JCheckBox checkBox = (JCheckBox) e.getSource();
                boolean value = checkBox.isSelected();
                VisibleProperty visibleProperty = (VisibleProperty) visibleElement;
                visibleProperty.setRepresentative(value);
                updatePreformed();
            }
        });

        autoGoCb.addActionListener(new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                JCheckBox checkBox = (JCheckBox) e.getSource();
                boolean value = checkBox.isSelected();
                VisibleProperty visibleProperty = (VisibleProperty) visibleElement;
                visibleProperty.setAutoGo(value);
                updatePreformed();
            }
        });

        disabledCb.addActionListener(new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                JCheckBox checkBox = (JCheckBox) e.getSource();
                boolean value = checkBox.isSelected();
                VisibleProperty visibleProperty = (VisibleProperty) visibleElement;
                visibleProperty.setDisabled(value);
                updatePreformed();
            }
        });
    }

    @Override
    public void updateComponents() {
        super.updateComponents();
        VisibleProperty visibleProperty = (VisibleProperty) visibleElement;
        if(visibleProperty.getComponentType() == ComponentType.TEXT_FIELD) {
        	typeCb.setEnabled(true);
        	typeCb.setSelectedItem(visibleProperty.getDataType());
        }
        columnLabelTf.setText(visibleProperty.getColumnLabel());
        displayFormatTf.setText(visibleProperty.getDisplayFormat());
        if(visibleProperty.getEnumeration() != null) {
        	valuesTa.setText("");
            String[] vals = visibleProperty.getEnumeration().split(";");
            
            //dodao Milan Djukic
            //isto objasnjenje kao u redu 215
            int size=vals.length;
            if(vals.length==1 && vals[0].equals(""))
            	size=0;
            
            for(int i=0; i<size; i++) {
            	valuesTa.append(vals[i] + "\n");
            }
        }
        defaultValueTf.setText(visibleProperty.getDefaultValue());
        mandatoryCb.setSelected(visibleProperty.lower() != 0);
        representativeCb.setSelected(visibleProperty.isRepresentative());
        autoGoCb.setSelected(visibleProperty.isAutoGo());
        disabledCb.setSelected(visibleProperty.isDisabled());
    }

    @Override
    public void updateSettings(VisibleElement visibleElement) {
        super.updateSettings(visibleElement);
        VisibleProperty visibleProperty = (VisibleProperty) visibleElement;
        columnLabelTf.setText(visibleProperty.getColumnLabel());
        //ako nije text field, ne treba podesavanje za tip
        if(visibleElement.getComponentType() != ComponentType.TEXT_FIELD) {
        	typelbl.setVisible(false);
        	typeCb.setVisible(false);
        }else {
        	typelbl.setVisible(true);
        	typeCb.setVisible(true);
        }
        //ako nije combobox, ne treba lista sa vrednostima
        if(visibleElement.getComponentType() != ComponentType.COMBO_BOX) {
        	valuesLb.setVisible(false);
        	valuesSp.setVisible(false);
        }else {
        	valuesLb.setVisible(true);
        	valuesSp.setVisible(true);
        }
        if(mandatoryCb.isSelected()) {
        	visibleProperty.setLower(1);
        }else {
        	visibleProperty.setLower(0);
        }
    }

    @Override
    public void updatePreformed() {
        super.updatePreformed();
    }
}
