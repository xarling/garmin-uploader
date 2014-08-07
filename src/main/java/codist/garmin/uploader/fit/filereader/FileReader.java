package codist.garmin.uploader.fit.filereader;

import java.io.File;
import java.util.List;

public interface FileReader {

	/**
	 * Get all the files in the directory that match the {garmin.fit.extension}
	 * 
	 * @return
	 */
	public abstract List<File> getFilesInDirectory();

}