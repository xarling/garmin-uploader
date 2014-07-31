package codist.garmin.uploader.task;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import codist.garmin.uploader.filereader.FileReader;

@RunWith(MockitoJUnitRunner.class)
public class FileReaderTaskTest {
	
	private FileReaderTask fileReaderTask;
	
	@Mock
	private FileReader fileReader;
	
	@Before
	public void before() {
		fileReaderTask = new FileReaderTask();
		fileReaderTask.setFileReader(fileReader);
	}
	
	@Test
	public void testGetAvailableFitFilesWithNull() {
		when(fileReader.getFilesInDirectory()).thenReturn(null);
		
		
		fileReaderTask.getAvailableFitFiles();
		
		
	}

}
