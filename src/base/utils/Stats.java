package base.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 
 * Des stats (nb de fois morts, nb de fois gagner, perdu, ...)
 * @author Yoann CAPLAIN
 *
 */
@Deprecated
public class Stats {

	private static String fileLocation;
	private static Properties configurationFile;

	/**
	 * Initialize the configuration file with the given path.
	 * 
	 * @param fileLocation
	 *            The path of the file.
	 * @throws IOException
	 *             If the file can't be loaded.
	 */
	public static void init(String fileLocation) throws IOException {
		Stats.fileLocation = fileLocation;
		updateConfigFile();
	}

	/**
	 * Update the current stats settings.
	 * 
	 * @throws IOException
	 *             If the file can't be loaded.
	 */
	public static void updateConfigFile() throws IOException {
		InputStreamReader is = new InputStreamReader(new FileInputStream(fileLocation));
		configurationFile = new Properties();
		configurationFile.load(is);
		is.close();
	}

	/**
	 * Save the current set configuration to the configuration file.
	 * 
	 * @throws IOException
	 */
	public static void saveNewConfig() throws IOException {
		OutputStream os = new FileOutputStream(fileLocation);
		configurationFile.store(os, "");
		os.flush();
		os.close();
		updateConfigFile();
	}
	
	
}
