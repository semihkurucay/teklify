package com.semihkurucay.service.impl;

import com.semihkurucay.dto.DtoUser;
import com.semihkurucay.dto.DtoUserUpdate;
import com.semihkurucay.entity.User;
import com.semihkurucay.enums.ErrorType;
import com.semihkurucay.exception.BaseException;
import com.semihkurucay.exception.ErrorMessage;
import com.semihkurucay.mapper.UserMapper;
import com.semihkurucay.repository.UserRepository;
import com.semihkurucay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    private User getUserByUsername(String username) {
        return userRepository.findByLogin_username(username)
                .orElseThrow(() -> new BaseException(new ErrorMessage(null, ErrorType.USER_NOT_FOUND)));
    }

    @Override
    public DtoUser getUser(String username) {
        return userMapper.toDtoUser(getUserByUsername(username));
    }

    @Transactional
    @Override
    public DtoUser updateUser(String username, DtoUserUpdate dtoUserUpdate) {
        int age = Period.between(dtoUserUpdate.getBirthDay(), LocalDate.now()).getYears();
        if (age < 18){
            throw new BaseException(new ErrorMessage(null, ErrorType.USER_NOT_ELIGIBLE));
        }

        User user = userMapper.toUpdateEntityUser(dtoUserUpdate, getUserByUsername(username));

        if (dtoUserUpdate.getPassword() != null && !dtoUserUpdate.getPassword().isEmpty()){
            user.getLogin().setPassword(passwordEncoder.encode(dtoUserUpdate.getPassword()));
        }

        return userMapper.toDtoUser(userRepository.save(user));
    }
}
