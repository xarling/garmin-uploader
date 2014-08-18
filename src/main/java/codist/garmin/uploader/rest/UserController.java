package codist.garmin.uploader.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import codist.garmin.uploader.model.User;
import codist.garmin.uploader.service.UserService;

@Controller
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
    public @ResponseBody Iterable<User> getUsers() {
        return userService.getAll();
    }
	
	@RequestMapping(method=RequestMethod.POST)
    public @ResponseBody User save(@RequestBody @Valid final User user) {
        return userService.save(user);
    } 
}
