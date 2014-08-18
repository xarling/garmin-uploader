package codist.garmin.uploader.stravaclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import codist.garmin.uploader.model.User;
import codist.garmin.uploader.service.UserService;
import codist.garmin.uploader.stravaclient.model.AccessToken;

/**
 * Used to get access to Strava for a User. Strava will use this URL as a
 * callback on a login to Strava request
 * 
 * @author Xander Arling
 *
 */
@Controller
@RequestMapping("/api/token_exchange")
public class TokenExchangeController {

	private static final Logger logger = LoggerFactory.getLogger(TokenExchangeController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private StravaHttpClient client;

	@RequestMapping(method = RequestMethod.GET)
	public RedirectView tokenExchange(final @RequestParam("code") String code,
			final @RequestParam("state") String userId) {
		logger.info("We are going to exchange some tokens!");
		
		final AccessToken accessToken = client.getAccessToken(code);

		final User user = userService.findById(Long.parseLong(userId));
		if (user != null) {
			logger.info("User with id {} found ", userId);
			user.setAccessToken(accessToken.getAccessToken());
			userService.save(user);
		} else {
			logger.error("User with id{} not found", userId);
		}
		
		
		return new RedirectView("/", true);

	}

}
