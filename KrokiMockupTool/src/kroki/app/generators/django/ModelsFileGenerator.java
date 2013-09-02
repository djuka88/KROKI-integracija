package kroki.app.generators.django;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import kroki.app.generators.utils.EJBClass;
import kroki.profil.subsystem.BussinesSubsystem;

public class ModelsFileGenerator {

	public void generateModels(String filePath,BussinesSubsystem proj,ArrayList<EJBClass> classes){
		
		String projectRoot=filePath+File.separator+proj.getLabel();
		String krokiRoot=new File("").getAbsolutePath();
		
		String templateDir=krokiRoot + File.separator+"src/kroki/app/generators/templates/django";
		
		//promena vrednosti promenljive 
		
		Map<String,Object> model = new TreeMap<String,Object>();
		model.put("st_forms", classes);
		TemplateFileGenerator.generateFile(templateDir, "models.ftl", projectRoot+File.separator+"st_forms", "models.py", model);
	}
}
