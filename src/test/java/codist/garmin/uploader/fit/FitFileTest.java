package codist.garmin.uploader.fit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import codist.garmin.uploader.model.FitFile;
import codist.garmin.uploader.model.FitStatus;

public class FitFileTest {
	
	@Test
	public void testStatusChanges() {
		
		assertFalse(createFitFile(FitStatus.FILE_ON_DISK).statusChangeAllowed(createFitFile(FitStatus.UPLOADED_TO_STRAVA)));
		assertTrue(createFitFile(FitStatus.FILE_ON_DISK).statusChangeAllowed(createFitFile(FitStatus.MARKED_FOR_UPLOAD_TO_STRAVA)));
	}
	
	private FitFile createFitFile(final FitStatus fitStatus) {
		FitFile fitFile = new FitFile();
		fitFile.setStatus(fitStatus);
		return fitFile;
	}

}
