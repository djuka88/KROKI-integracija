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

public class SettingsFileGenerator {

	@SuppressWarnings({ "unused", "rawtypes" })
	public void generateSettingsFile(String filePath,String projectName){
		
		String projectRoot=filePath+File.separator+projectName;
		String krokiRoot=new File("").getAbsolutePath();
		
		Configuration cfg = new Configuration();
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		FileTemplateLoader templateLoader;
		
		try {
			templateLoader = new FileTemplateLoader(new File(krokiRoot + File.separator+"src/kroki/app/generators/templates/django"));
			cfg.setTemplateLoader(templateLoader);
			Template tpl = cfg.getTemplate("settings.ftl");
			
			File fout=new File(projectRoot+File.separator+projectName+File.separator+"settings.py");
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fout));
			Map model = new TreeMap();
			
			//ovde dodaj podatke
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
