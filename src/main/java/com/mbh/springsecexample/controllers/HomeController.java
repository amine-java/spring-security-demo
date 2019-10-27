/**
 * 
 */
package com.mbh.springsecexample.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Home Api
 * 
 * @author amineboufatah
 *
 */
@RestController
public class HomeController {

  private static final String HTML =
      "<a href='user'>User EndPoint</a><br><a href='admin'>Admin EndPoint</a><br><a href='logout'>Log Out</a> ";

  /**
   * This endpoint can be called by both "users" and "admins"
   * 
   * @return html
   */
  @GetMapping("/user")
  public String welcomeUser() {
    return "<h1>Welcome User</h1>" + HTML;
  }

  /**
   * This endpoint can be called by "Admins" only
   * 
   * @return html
   */
  @GetMapping("/admin")
  public String welcomeAdmin() {
    return "<h1>Welcome Admin</h1>" + HTML;
  }

  /**
   * Authenticated users and no authenticated users should be able to call this endpoint.
   * 
   * @return
   */
  @GetMapping("/")
  public String welcome() {
    return "<h1>Welcome </h1>" + HTML;
  }

}
