package com.semihkurucay.controller.impl;

import com.semihkurucay.controller.RestUserController;
import com.semihkurucay.controller.RootEntity;
import com.semihkurucay.dto.DtoUser;
import com.semihkurucay.dto.DtoUserUpdate;
import com.semihkurucay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class RestUserControllerImpl extends RestBaseController implements RestUserController {

    private final UserService userService;

    @Override
    public RootEntity<DtoUser> getMyProfile(Principal principal) {
        return ok(userService.getUser(principal.getName()));
    }

    @Override
    public RootEntity<DtoUser> updateMyProfile(Principal principal, DtoUserUpdate dtoUserUpdate) {
        return ok(userService.updateUser(principal.getName(), dtoUserUpdate));
    }
}
