package com.javatechie.service;

import com.javatechie.entity.UserInfo;
import com.javatechie.exception.UserException;

public interface UserService{

  UserInfo findByUserId(Integer userId) throws UserException;

  UserInfo findUserProfileByJWT(String jwt) throws UserException;
}
