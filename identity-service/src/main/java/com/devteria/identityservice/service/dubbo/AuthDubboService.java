package com.devteria.identityservice.service.dubbo;

import com.devteria.identityservice.dto.request.IntrospectRequest;
import com.devteria.identityservice.dto.response.IntrospectResponse;

public interface AuthDubboService {
    IntrospectResponse introspectDubbo(IntrospectRequest request);
}
