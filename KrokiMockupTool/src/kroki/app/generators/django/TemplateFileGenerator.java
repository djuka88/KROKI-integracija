package kroki.app.generators.django;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TemplateFileGenerator {

	@SuppressWarnings("rawtypes")
	public static void generateFile(String templateDir, String templateName, String outFilePath, String outFileName, Map model){
		
		Configuration cfg = new Configuration();
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		FileTemplateLoader templateLoader;
		
		try {
			templateLoader = new FileTemplateLoader(new File(templateDir));
			cfg.setTemplateLoader(templateLoader);
			Template tpl = cfg.getTemplate(templateName);
			
			File fout=new File(outFilePath+File.separator+outFileName);
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fout));
			
			tpl.process(model, writer);
			writer.close();	//obavezno
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}
