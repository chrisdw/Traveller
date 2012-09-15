package uk.org.downesward.traveller.language;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class Languages extends HashMap<String, Language> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2701402246896332132L;
	
	public Languages() {
		
	}
	
	/*
	 * Given a File object representing the root of a directory 
	 * holding multiple language configuration files, create
	 * a map of the data
	 */
	public Languages(File root) throws IOException {
		String[] files = root.list();
		InputStream lang;
		Language oLang;
		
		for (int i = 0; i < files.length; i++) {
			lang = new FileInputStream(new File(files[i]));
			oLang = new Language(lang);
			oLang.setLanguage(files[i].substring(0, files[i].length() - 4));
			this.put(oLang.getLanguage(), oLang);
		}
	}
}
