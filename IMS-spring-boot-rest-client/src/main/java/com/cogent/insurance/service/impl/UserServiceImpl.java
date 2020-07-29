package com.cogent.insurance.service.impl;

import com.cogent.insurance.entity.User;
import com.cogent.insurance.service.UserService;
import com.cogent.insurance.shared.Utils;
import com.cogent.insurance.shared.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean verifyEmailToken(String token) {

        boolean returnValue = false;

        //find user by token
        User user = userRepository.findUserByEmailVerificationToken(token);

        if (user != null) {
            boolean hasTokenExpired = Utils.hasTokenExpired(token);
            if(!hasTokenExpired) {
                user.setEmailVerificationToken(null);
                user.setEmailVerificationStatus(Boolean.TRUE);
                userRepository.save(user);
                returnValue = true;
            }
        }

        return returnValue;
    }
}
