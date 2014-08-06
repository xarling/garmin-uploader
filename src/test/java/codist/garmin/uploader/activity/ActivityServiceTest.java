package codist.garmin.uploader.activity;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ActivityServiceTest {

	private ActivityService activityService = new ActivityService();
	
	@Mock
	private ActivityRepository activityRepository;
	
	
	@Before
	public void before() {
		activityService.setActivityRepository(activityRepository);
	}
	
	@Test
	public void testSave() {
		Activity activity = new Activity();
		
		when(activityRepository.save(activity)).thenReturn(activity);
		activityService.save(activity);
		verify(activityRepository).save(activity);

	}
	
	@Test
	public void testSaveExisting() {
		Activity activity = new Activity();
		activity.setStravaId(1L);
		
		when(activityRepository.findByStravaId(1L)).thenReturn(activity);
		when(activityRepository.save(activity)).thenReturn(null);
		
		
		activityService.saveNotExistingActivity(activity);

		verify(activityRepository).findByStravaId(activity.getStravaId());
		
	}
	
	@Test
	public void testSaveNotExisting() {
		Activity activity = new Activity();
		activity.setStravaId(1L);
		
		when(activityRepository.findByStravaId(1L)).thenReturn(activity);
		
		
		activityService.saveNotExistingActivity(activity);

		verify(activityRepository).findByStravaId(activity.getStravaId());
		verify(activityRepository).save(activity);
		
	}
}
