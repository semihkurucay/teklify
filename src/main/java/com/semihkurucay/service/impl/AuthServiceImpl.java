package com.semihkurucay.service.impl;

import com.semihkurucay.dto.*;
import com.semihkurucay.entity.*;
import com.semihkurucay.enums.ErrorType;
import com.semihkurucay.exception.BaseException;
import com.semihkurucay.exception.ErrorMessage;
import com.semihkurucay.jwt.JwtService;
import com.semihkurucay.mapper.UserMapper;
import com.semihkurucay.repository.LoginRepository;
import com.semihkurucay.repository.RefreshTokenRepository;
import com.semihkurucay.repository.UserRepository;
import com.semihkurucay.repository.WalletRepository;
import com.semihkurucay.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
class AuthServiceImpl implements AuthService {

    private final LoginRepository loginRepository;
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final AuthenticationProvider authenticationProvider;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    private User findUserByUsername(String username){
        return userRepository.findByLogin_username(username)
                .orElseThrow(() -> new BaseException(new ErrorMessage(null, ErrorType.USER_NOT_FOUND)));
    }

    private String createRefreshToken(User user){
        refreshTokenRepository.deleteByUser_IdAndExpTimeBefore(user.getId(), LocalDateTime.now());

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setExpTime(LocalDateTime.now().plusHours(4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshTokenRepository.save(refreshToken);

        return refreshToken.getRefreshToken();
    }

    private String createAccessToken(CustomUserDetail customUserDetail){
        return jwtService.generateToken(customUserDetail);
    }

    private DtoLoginUser userConverterDto(User user){
        Wallet wallet = walletRepository.findByUser_Id(user.getId())
                .orElseThrow(() -> new BaseException(new ErrorMessage("Bakiye", ErrorType.NO_VALUE)));

        DtoLoginUser dtoUser = new DtoLoginUser();
        dtoUser.setId(user.getId());
        dtoUser.setFullname(user.getFirstName() + " " + user.getLastName());
        dtoUser.setBalance(wallet.getBalance());

        return dtoUser;
    }

    private DtoAuthLoginResponse createLoginResponse(User user, CustomUserDetail customUserDetail){
        DtoAuthLoginResponse response = new DtoAuthLoginResponse();
        response.setAccessToken(createAccessToken(customUserDetail));
        response.setRefreshToken(createRefreshToken(user));
        response.setUser(userConverterDto(user));
        return response;
    }

    @Transactional
    @Override
    public DtoAuthLoginResponse login(DtoAuthRequest request) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            Authentication authentication = authenticationProvider.authenticate(authenticationToken);

            if(authentication == null || authentication.getPrincipal() == null){
                throw new BaseException(new ErrorMessage(null, ErrorType.INCORRECT_CREDENTIALS));
            }

            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
            User user = findUserByUsername(customUserDetail.getUsername());

            return createLoginResponse(user, customUserDetail);
        }catch (BadCredentialsException e){
            throw new BaseException(new ErrorMessage(null, ErrorType.INCORRECT_CREDENTIALS));
        }
        catch (Exception e){
            throw new BaseException(new ErrorMessage(null, ErrorType.GENERAL_ERROR));
        }
    }

    @Transactional
    @Override
    public DtoAuthResponse refreshToken(DtoAuthRefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(request.getRefreshToken())
                .orElseThrow(() -> new BaseException(new ErrorMessage(null, ErrorType.NO_VALUE)));

        refreshTokenRepository.deleteByRefreshToken(refreshToken.getRefreshToken());

        if(refreshToken.getExpTime().isBefore(LocalDateTime.now())){
            throw new BaseException(new ErrorMessage(null, ErrorType.REFRESH_TOKEN_EXPIRED));
        }

        if(refreshToken.getUser() == null || refreshToken.getUser().getLogin() == null){
            throw new BaseException(new ErrorMessage(null, ErrorType.USER_NOT_FOUND));
        }

        User user = refreshToken.getUser();
        Login login = user.getLogin();

        return new DtoAuthResponse(
                createAccessToken(
                        new CustomUserDetail(login.getUsername(), login.getPassword())), createRefreshToken(user));
    }

    @Transactional
    @Override
    public DtoAuthLoginResponse register(DtoRegisterUser request) {
        if(loginRepository.existsByUsername((request.getLogin().getUsername()))){
            throw new BaseException(new ErrorMessage(null, ErrorType.REPETITIVE_RECORDING));
        }

        User user = userMapper.toEntityUser(request);

        int age = Period.between(request.getBirthDay(), LocalDate.now()).getYears();
        if(age < 18){
            throw new BaseException(new ErrorMessage(null, ErrorType.USER_NOT_ELIGIBLE));
        }

        user.getLogin().setPassword(
                passwordEncoder.encode(
                        user.getLogin().getPassword()));

        User newUser = userRepository.save(user);
        walletRepository.save(new Wallet(newUser));

        Login login = newUser.getLogin();

        return createLoginResponse(newUser, new CustomUserDetail(login.getUsername(), login.getPassword()));
    }
}
