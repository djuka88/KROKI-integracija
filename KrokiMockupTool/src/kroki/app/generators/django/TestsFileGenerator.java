package kroki.app.generators.django;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import kroki.profil.subsystem.BussinesSubsystem;

public class TestsFileGenerator {

	@SuppressWarnings("rawtypes")
	public void generateTests(String filePath,BussinesSubsystem proj){
		
		String projectRoot=filePath+File.separator+proj.getLabel();
		String krokiRoot=new File("").getAbsolutePath();
		
		String templateDir=krokiRoot + File.separator+"src/kroki/app/generators/templates/django";
		
		Map model = new TreeMap();
		TemplateFileGenerator.generateFile(templateDir, "tests.ftl", projectRoot+File.separator+"st_forms", "tests.py", model);
	}
}
