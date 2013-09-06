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
		
		// generisi glavni urls.py fajl
		Map model = new TreeMap();
		model.put("project_ccname", proj.name());
		TemplateFileGenerator.generateFile(templateDir, "main_urls.ftl", projectRoot+File.separator+proj.name(), "urls.py", model);
		
		// generisi urls.py fajl u projektu
		model = new TreeMap();
		TemplateFileGenerator.generateFile(templateDir, "project_urls.ftl", projectRoot+File.separator+"st_forms", "urls.py", model);
	}
}
