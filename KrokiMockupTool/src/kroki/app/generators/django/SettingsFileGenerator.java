package kroki.app.generators.django;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import kroki.app.generators.utils.EJBClass;
import kroki.profil.subsystem.BussinesSubsystem;
import kroki.profil.utils.DatabaseProps;

public class SettingsFileGenerator {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void generateSettingsFile(String filePath,BussinesSubsystem proj,ArrayList<EJBClass> classes){
		
		String projectRoot=filePath+File.separator+proj.getLabel();
		String krokiRoot=new File("").getAbsolutePath();
		
		String templateDir=krokiRoot + File.separator+"src/kroki/app/generators/templates/django";
		
		//podesi default vrednosti za bazu ako nije odabrana
		DatabaseProps dbprops=new DatabaseProps();
		if(proj.getDBConnectionProps().getProfile()==5){ //test profil
			dbprops.setProfile(1);
			dbprops.setHost("localhost");
			dbprops.setSchema("postgres");
			dbprops.setUsername("postgres");
			dbprops.setPassword("piramida");
			dbprops.setPort(5432);
			
			proj.setDBConnectionProps(dbprops);
		}
		
		Map model = new TreeMap();
		model.put("project_label",proj.getLabel());
		model.put("project_ccname",proj.name());
		model.put("db_props", proj.getDBConnectionProps());
		model.put("template_dir", projectRoot+File.separator+proj.name()+File.separator+"templates");
		model.put("st_forms", classes);
		
		TemplateFileGenerator.generateFile(templateDir, "settings.ftl", projectRoot+File.separator+proj.name(), "settings.py", model);
	}
}
