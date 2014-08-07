package codist.garmin.uploader.stravaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import codist.garmin.uploader.activity.Activity;
import codist.garmin.uploader.activity.ActivityService;

/**
 * Used to get access to Strava for a User. Strava will use this URL as a callback on a login to Strava request
 * 
 * @author Xander Arling
 *
 */
@Controller
@RequestMapping("/api/token_exchange")
public class TokenExchangeController {

	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(method=RequestMethod.GET)
    public @ResponseBody Iterable<Activity> tokenExchange() {
		
		
        return activityService.findAll();
    }
	
}
