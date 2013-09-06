package kroki.app.generators.django;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import kroki.app.generators.utils.Menu;
import kroki.profil.subsystem.BussinesSubsystem;

public class HTMLFileGenerator {

	public void generateHTMLs(String filePath,BussinesSubsystem proj,ArrayList<Menu> menus){
		
		String projectRoot=filePath+File.separator+proj.getLabel();
		String krokiRoot=new File("").getAbsolutePath();
		
		String templateDir=krokiRoot + File.separator+"src/kroki/app/generators/templates/django/html";

		// kopiraj login.html i login.css u projekat
		copyFile(templateDir+File.separator+"login.css", projectRoot+File.separator+"st_forms/static/st_forms/login.css");
		copyFile(templateDir+File.separator+"login.html", projectRoot+File.separator+"st_forms/templates/st_forms/login.html");
		
		// kreiraj main.html
		copyFile(templateDir+File.separator+"treeView.css", projectRoot+File.separator+"st_forms/static/st_forms/treeView.css");
		copyFile(templateDir+File.separator+"icons.png", projectRoot+File.separator+"st_forms/static/st_forms/icons.png");
		copyFile(templateDir+File.separator+"main.css", projectRoot+File.separator+"st_forms/static/st_forms/main.css");
		copyFile(templateDir+File.separator+"first.png", projectRoot+File.separator+"st_forms/static/st_forms/first.png");
		copyFile(templateDir+File.separator+"last.png", projectRoot+File.separator+"st_forms/static/st_forms/last.png");
		copyFile(templateDir+File.separator+"prev.png", projectRoot+File.separator+"st_forms/static/st_forms/prev.png");
		copyFile(templateDir+File.separator+"next.png", projectRoot+File.separator+"st_forms/static/st_forms/next.png");
		
		Map<String,Object> model = new TreeMap<String,Object>();
		model.put("menus", menus);
		model.put("project", proj);
		TemplateFileGenerator.generateFile(templateDir, "main.ftl", projectRoot+File.separator+"st_forms/templates/st_forms/", "main.html", model);
	}

	private void copyFile(String sourceFilePath, String destFilePath){
		
		File sourceFile=new File(sourceFilePath);
		File destinationFile=new File(destFilePath);

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
	}
}
