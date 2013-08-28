package kroki.app.action.django;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import kroki.app.KrokiMockupToolApp;
import kroki.app.export.ProjectExporterDjango;
import kroki.app.utils.ImageResource;
import kroki.app.utils.StringResource;
import kroki.profil.subsystem.BussinesSubsystem;

public class ExportDjangoAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExportDjangoAction(){
		putValue(NAME, "Export django application");
		ImageIcon smallIcon = new ImageIcon(ImageResource.getImageResource("action.exportdjango.largeicon"));
		ImageIcon largeIcon = new ImageIcon(ImageResource.getImageResource("action.exportdjango.smallicon"));
		putValue(SMALL_ICON, smallIcon);
		putValue(LARGE_ICON_KEY, largeIcon);
		putValue(SHORT_DESCRIPTION, StringResource.getStringResource("action.exportdjango.description"));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//OVDE SAM STAO
		//find selected project from workspace
		BussinesSubsystem proj = null;
		try {
			String selectedNoded = KrokiMockupToolApp.getInstance().getKrokiMockupToolFrame().getTree().getSelectionPath().getLastPathComponent().toString();
			for(int j=0; j<KrokiMockupToolApp.getInstance().getWorkspace().getPackageCount(); j++) {
				BussinesSubsystem pack = (BussinesSubsystem)KrokiMockupToolApp.getInstance().getWorkspace().getPackageAt(j);
				if(pack.getLabel().equals(selectedNoded)) {
					proj = pack;
				}
			}

			JFileChooser jfc = new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int retValue = jfc.showSaveDialog(KrokiMockupToolApp.getInstance().getKrokiMockupToolFrame());
			if (retValue == JFileChooser.APPROVE_OPTION) {
				File file = jfc.getSelectedFile();
				//pass selected project and directory to exporter class
				ProjectExporterDjango exporter = new ProjectExporterDjango();
				exporter.export(file.getAbsolutePath(),proj, "OK");
			} else {
				System.out.println("Export canceled");
			}


		} catch (NullPointerException e2) {
			//if no project is selected, inform user to select one
			JOptionPane.showMessageDialog(KrokiMockupToolApp.getInstance().getKrokiMockupToolFrame(), "You must select a project from workspace!");
			e2.printStackTrace();
		}

	}

}
