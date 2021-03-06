/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kroki.profil.utils.settings;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import kroki.intl.Intl;
import kroki.profil.VisibleElement;
import kroki.profil.association.Zoom;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Vladan Marsenić (vladan.marsenic@gmail.com)
 */
public class ZoomSettings extends VisibleAssociationEndSettings {

    JLabel combozoomLb;
    JCheckBox combozoomCb;

    public ZoomSettings(SettingsCreator settingsCreator) {
        super(settingsCreator);
        initComponents();
        layoutComponents();
        addActions();
    }

    private void initComponents() {
        combozoomLb = new JLabel(Intl.getValue("zoom.combozoom"));
        combozoomCb = new JCheckBox();
        combozoomCb.setEnabled(false);
    }

    private void layoutComponents() {
        JPanel intermediate = null;
        if (panelMap.containsKey("group.INTERMEDIATE")) {
            intermediate = panelMap.get("group.INTERMEDIATE");
        } else {
            intermediate = new JPanel();
            intermediate.setLayout(new MigLayout("wrap 2,hidemode 3", "[right, shrink][fill, 200]"));
            panelMap.put("group.INTERMEDIATE", intermediate);
            this.addTab(Intl.getValue("group.INTERMEDIATE"), intermediate);
        }
        intermediate.add(combozoomLb);
        intermediate.add(combozoomCb);
    }

    @Override
    public void updateComponents() {
        super.updateComponents();
        Zoom zoom = (Zoom) visibleElement;
        combozoomCb.setSelected(zoom.isCombozoom());
    }

    private void addActions() {
        //nema akcija
    }

    @Override
    public void updateSettings(VisibleElement visibleElement) {
        super.updateSettings(visibleElement);
    }

    @Override
    public void updatePreformed() {
        super.updatePreformed();
    }
}
