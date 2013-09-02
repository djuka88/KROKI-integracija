package kroki.app.generators.django;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import kroki.profil.subsystem.BussinesSubsystem;

public class ViewsFileGenerator {

	@SuppressWarnings("rawtypes")
	public void generateViews(String filePath,BussinesSubsystem proj){
		
		String projectRoot=filePath+File.separator+proj.getLabel();
		String krokiRoot=new File("").getAbsolutePath();
		
		String templateDir=krokiRoot + File.separator+"src/kroki/app/generators/templates/django";
		
		Map model = new TreeMap();
		TemplateFileGenerator.generateFile(templateDir, "views.ftl", projectRoot+File.separator+"st_forms", "views.py", model);
	}
}
