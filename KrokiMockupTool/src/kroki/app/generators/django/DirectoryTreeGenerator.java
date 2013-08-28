package kroki.app.generators.django;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.TreeMap;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DirectoryTreeGenerator {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void generateDirectories(String filePath,String projectName){
		
		String projectRoot=filePath+File.separator+projectName;
		String krokiRoot=new File("").getAbsolutePath();
		
		//create container for project, same name as project
		boolean success=(new File(projectRoot).mkdir());
		
		if(success){
			
			//create file manage.py from templates
			Configuration cfg = new Configuration();
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			FileTemplateLoader templateLoader;
			try {
				templateLoader = new FileTemplateLoader(new File(krokiRoot + File.separator+"src/kroki/app/generators/templates/django"));
				cfg.setTemplateLoader(templateLoader);
				Template tpl = cfg.getTemplate("manage.ftl");
				
				File fout=new File(projectRoot+File.separator+"manage.py");
				OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fout));
				Map model = new TreeMap();
				model.put("project_name", projectName);
				
				tpl.process(model, writer);
				writer.close();	//obavezno
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			
			
			//create project folder
			success=(new File(projectRoot+File.separator+projectName).mkdir());
			
			//create __init__.py file in project
			File init =new File(projectRoot+File.separator+projectName+File.separator+"__init__.py");
			if(!init.exists())
				try {
					init.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			//create templates folder in project folder
			success=(new File(projectRoot+File.separator+projectName+File.separator+"templates")).mkdir();
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
