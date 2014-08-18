package codist.garmin.uploader.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import codist.garmin.uploader.model.User;
import codist.garmin.uploader.repository.UserRepository;

@Transactional
@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public Iterable<User> getAll() {
		return userRepository.findAll();
	}

	public User findById(final Long id) {
		return userRepository.findById(id);
	}

	public User save(final User user) {
		logger.info("Saving user {}", user);

		return userRepository.save(user);
	}
}
