package codist.garmin.uploader.fit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.garmin.fit.Decode;
import com.garmin.fit.FileIdMesg;
import com.garmin.fit.FileIdMesgListener;
import com.garmin.fit.FitRuntimeException;
import com.garmin.fit.MesgBroadcaster;
import com.garmin.fit.SessionMesg;
import com.garmin.fit.SessionMesgListener;

/**
 * @author Xander Arling
 *
 */
@Component
public class FitFileParser {

	private static final Logger logger = LoggerFactory.getLogger(FitFileParser.class);

	public FitFile parseFile(final java.io.File file) throws FileNotFoundException, IOException {
		Decode decode = new Decode();
		MesgBroadcaster mesgBroadcaster = new MesgBroadcaster(decode);

		FileIdListener fileListener = new FileIdListener();

		InputStream in = new FileInputStream(file);
		if (!Decode.checkIntegrity(in)) {
			throw new RuntimeException("FIT file integrity failed.");
		}
		in.close();

		mesgBroadcaster.addListener((FileIdMesgListener) fileListener);
		mesgBroadcaster.addListener((SessionMesgListener) fileListener);

		in = new FileInputStream(file);

		try {
			mesgBroadcaster.run(in);
		} catch (FitRuntimeException e) {
			System.err.print("Exception decoding file: ");
			System.err.println(e.getMessage());

			try {
				in.close();
			} catch (java.io.IOException f) {
				throw new RuntimeException(f);
			}

			return null;
		}

		in.close();

		FitFile fitFile = fileListener.getFitFile();
		fitFile.setName(file.getName());
		fitFile.setUploaded(false);
		return fitFile;
	}

	private class FileIdListener implements FileIdMesgListener, SessionMesgListener {

		private FitFile fitFile = new FitFile();

		@Override
		public void onMesg(FileIdMesg mesg) {
			fitFile.setProductId(Long.toString(mesg.getProduct()));
			fitFile.setSerialNumber(Long.toString(mesg.getSerialNumber()));
		}

		@Override
		public void onMesg(SessionMesg mesg) {
			logger.info(" time first loaded to computer {}", mesg.getTimestamp());

			fitFile.setAvgHeartBpm(mesg.getAvgHeartRate());
			fitFile.setAvgSpeedInMetersPerSecond(mesg.getAvgSpeed().doubleValue());
			fitFile.setTotalDistanceInMeters(mesg.getTotalDistance().doubleValue());
			fitFile.setTotalTimeInSeconds(mesg.getTotalTimerTime().doubleValue());
			fitFile.setSport(mesg.getSport().name());
			Instant instant = Instant.ofEpochMilli(mesg.getStartTime().getDate().getTime());
			LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

			fitFile.setStartTime(res);

			// logger.info("starttime {}", mesg.getStartTime());
			// logger.info(" Hartslag: {} bpm", mesg.getAvgHeartRate());
			// logger.info(" Gemiddelde snelheid: {} m/s", mesg.getAvgSpeed());
			// logger.info(" Gemiddelde snelheid: {} min/km", mesg.getAvgSpeed()
			// * 1.6666666666667);
			// logger.info(" Gemiddelde snelheid: {} km/h", mesg.getAvgSpeed() *
			// 3.6);
			// logger.info("afstand: {} m", mesg.getTotalDistance());
			// final Duration totalTimerTime =
			// Duration.ofSeconds(Math.round(mesg.getTotalTimerTime()));
			// logger.info("afstand: {} km", mesg.getTotalDistance() / 1000);
			// logger.info(" Total time : {} s", mesg.getTotalTimerTime());
			// logger.info(" Total time : {} minutes",
			// totalTimerTime.toString());
			// logger.info(" Sport: {}", mesg.getSport());
		}
		
		public FitFile getFitFile() {
			return fitFile;
		}

	}
}
