package codist.garmin.uploader.util;

import org.springframework.data.domain.Sort;

/**
 * @author Xander Arling
 *
 */
public final class SortBuilder {
	public static final Sort sortBy(final Sort.Direction direction, final String name) {
		return new Sort(direction, name);
	}
	
	
}
