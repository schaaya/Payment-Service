package com.paymentservice.paymentService.resources;

import com.paymentservice.paymentService.payloads.request.login.LoginRequest;
import com.paymentservice.paymentService.payloads.response.LoginResponse;
import com.paymentservice.paymentService.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/login")
public class AuthenticationResource {

  @Autowired
  private AuthenticationService authenticationService;

  //createNewUser

  //changePassword
  @GetMapping(path = "/getAPI")
  ResponseEntity<String> getAPI(@RequestBody final LoginRequest loginRequest) {
    return new ResponseEntity<>("success", HttpStatus.OK);
  }


  @PostMapping(path = "/")
  ResponseEntity<LoginResponse> login(@RequestBody final LoginRequest loginRequest) {
    return new ResponseEntity<>(authenticationService.login(loginRequest), HttpStatus.OK);
  }


  @PostMapping(path = "/logout/{sessionId}")
  void logout(@PathVariable final String sessionId) {
    authenticationService.logout(sessionId);
  }


}
