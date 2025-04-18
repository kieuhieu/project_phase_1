package com.devteria.identityservice.service.dubbo.impl;

import com.devteria.identityservice.dto.request.IntrospectRequest;
import com.devteria.identityservice.dto.response.IntrospectResponse;
import com.devteria.identityservice.service.AuthenticationService;
import com.devteria.identityservice.service.dubbo.AuthDubboService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@DubboService(version = "1.0.0")
@RequiredArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthDubboServiceImpl implements AuthDubboService {

    AuthenticationService  authenticationService;

    @Override
    public IntrospectResponse introspectDubbo(IntrospectRequest request) {
        try {
            return authenticationService.introspect(request);
        } catch (Exception e) {
            return IntrospectResponse.builder().valid(false).build();
        }
    }
}
