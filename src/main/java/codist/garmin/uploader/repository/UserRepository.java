package codist.garmin.uploader.repository;

import org.springframework.data.repository.CrudRepository;

import codist.garmin.uploader.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findById(final Long id);
}
