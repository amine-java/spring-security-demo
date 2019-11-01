package com.mbh.springsecexample.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.mbh.springsecexample.dao.UserRepository;
import com.mbh.springsecexample.models.User;

/**
 * JpaUserDetailsService: class that implements UserDetailsService. It's a custom UserDetailsService
 * for loading the user object through a JPA Repository
 * 
 * @author amineboufatah
 *
 */
@Service
public class JpaUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  /***
   * 
   * This method loads the user using the JpaRepository: UserRepository If the user does not exist
   * then the method throws an Exception, else, it returns the User.
   * 
   * 
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // Find the user by providing the email
    Optional<User> user = userRepository.findByEmail(username);

    // if user is not found, throw an exception
    user.orElseThrow(() -> new UsernameNotFoundException("The user was not found"));

    // Else create a spring sec user and return it.
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority(user.get().getAuthority()));

    return new org.springframework.security.core.userdetails.User(user.get().getEmail(),
        user.get().getPassword(), authorities);
  }

}
