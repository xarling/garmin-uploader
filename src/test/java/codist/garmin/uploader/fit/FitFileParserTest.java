package codist.garmin.uploader.fit;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

public class FitFileParserTest {

	@Test
	public void testParseFile() throws IOException, URISyntaxException {
		File file = new File(FitFileParserTest.class.getResource("/fitfiles/63542097220000000061.fit").toURI());
		
		new FitFileParser().parseFile(file);
			
	}
}
