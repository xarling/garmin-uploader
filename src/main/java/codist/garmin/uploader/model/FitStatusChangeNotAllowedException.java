package codist.garmin.uploader.model;

public class FitStatusChangeNotAllowedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7218919550954441758L;

	public FitStatusChangeNotAllowedException() {
		
	}
	
	public FitStatusChangeNotAllowedException(final String msg) {
		super(msg);
	}

	public FitStatusChangeNotAllowedException(final String msg, final Throwable t) {
		super(msg, t);
	}

}
