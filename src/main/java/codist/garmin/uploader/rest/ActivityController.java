package codist.garmin.uploader.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import codist.garmin.uploader.model.Activity;
import codist.garmin.uploader.service.ActivityService;



@Controller
@RequestMapping("/api/activity")
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(method=RequestMethod.GET)
    public @ResponseBody Iterable<Activity> getAll() {
        return activityService.findAll();
    }

}
