package kroki.app.generators.django;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import kroki.profil.subsystem.BussinesSubsystem;

public class DirectoryTreeGenerator {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void generateDirectories(String filePath,BussinesSubsystem proj){
		
		String projectRoot=filePath+File.separator+proj.getLabel();
		String krokiRoot=new File("").getAbsolutePath();
		
		String templateDir=krokiRoot + File.separator+"src/kroki/app/generators/templates/django";
		
		
		//create container for project, same name as project
		boolean success=(new File(projectRoot).mkdir());
		
		if(success){
			
			//create manage.py
			Map model = new TreeMap();
			model.put("project_ccname", proj.name());
			TemplateFileGenerator.generateFile(templateDir, "manage.ftl", projectRoot, "manage.py", model);
			
			//create project folder
			success=(new File(projectRoot+File.separator+proj.name()).mkdir());
			
			//create st_forms folder in root folder
			success=(new File(projectRoot+File.separator+"st_forms").mkdir());
			
			//create templates folder in st_forms folder
			success=(new File(projectRoot+File.separator+"st_forms/templates").mkdir());
			
			//create static folder in st_forms folder
			success=(new File(projectRoot+File.separator+"st_forms/static").mkdir());
			
			//create st_forms folder in templates folder
			success=(new File(projectRoot+File.separator+"st_forms/templates/st_forms").mkdir());
			
			//create st_forms folder in static folder
			success=(new File(projectRoot+File.separator+"st_forms/static/st_forms").mkdir());
			
			//create __init__.py file in st_forms folder
			File init =new File(projectRoot+File.separator+"st_forms/__init__.py");
			if(!init.exists())
				try {
					init.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			//create __init__.py file in project folder
			init =new File(projectRoot+File.separator+proj.name()+File.separator+"__init__.py");
			if(!init.exists())
				try {
					init.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			//create templates folder in project folder
			success=(new File(projectRoot+File.separator+proj.name()+File.separator+"templates")).mkdir();
			
			//create wsgi.py from templates
			model=new TreeMap();
			model.put("project_label", proj.getLabel());
			model.put("project_ccname", proj.name());
			TemplateFileGenerator.generateFile(templateDir, "wsgi.ftl", projectRoot+File.separator+proj.name(), "wsgi.py", model);
		}
	}
	
	/*private void copyFile(File sourceFile, File destinationFile){
		
		if(!destinationFile.exists()){
			try {
				destinationFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		FileChannel source=null;
		FileChannel destination=null;
		
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destinationFile).getChannel();
			destination.transferFrom(source, 0, source.size());
			
			if(source!=null)
				source.close();
			
			if(destination!=null)
				destination.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

}
