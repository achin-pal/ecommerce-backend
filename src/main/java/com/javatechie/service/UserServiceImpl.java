package com.javatechie.service;

import com.javatechie.entity.UserInfo;
import com.javatechie.exception.UserException;
import com.javatechie.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private JwtService jwtService;

    @Override
    public UserInfo findByUserId(Integer userId) throws UserException {
        Optional<UserInfo> userInfo = userInfoRepository.findById(userId);

        if(userInfo.isPresent()){
            return userInfo.get();
        }
        throw new UserException("User not found with id "+userId);
    }

    @Override
    public UserInfo findUserProfileByJWT(String jwt) throws UserException {

       String email=jwtService.extractUsername(jwt);

       Optional<UserInfo> userInfo = userInfoRepository.findByEmail(email);

        if(!userInfo.isPresent()){
            throw new UserException("User not found with email "+email);
        }

        return  userInfo.get();
    }
}
