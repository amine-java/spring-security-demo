/**
 * 
 */
package com.mbh.springsecexample.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mbh.springsecexample.models.User;

/**
 * UserRepository extends JpaRepository.
 * 
 * @author amineboufatah
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {

  /**
   * This method finds a user by its email
   * 
   * @param email: The user's email
   * @return Optional<User>
   */
  Optional<User> findByEmail(String email);

}
