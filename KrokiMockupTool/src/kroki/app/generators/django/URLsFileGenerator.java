package kroki.app.generators.django;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import kroki.profil.subsystem.BussinesSubsystem;

public class URLsFileGenerator {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void generateURLFile(String filePath,BussinesSubsystem proj){
		
		String projectRoot=filePath+File.separator+proj.getLabel();
		String krokiRoot=new File("").getAbsolutePath();
		
		String templateDir=krokiRoot + File.separator+"src/kroki/app/generators/templates/django";
		
		Map model = new TreeMap();
		model.put("project_ccname", proj.name());
		
		TemplateFileGenerator.generateFile(templateDir, "urls.ftl", projectRoot+File.separator+proj.name(), "urls.py", model);
	}
}
