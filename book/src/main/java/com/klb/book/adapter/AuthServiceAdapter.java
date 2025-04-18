package com.klb.book.adapter;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AuthServiceAdapter {

//    @DubboReference()
//    AuthDubboService authDubboService;
//
//    public boolean isTokenValid(String token) {
//        IntrospectRequest request = new IntrospectRequest(token);
//        IntrospectResponse response = authDubboService.introspect(request);
//        return response != null && response.isValid();
//    }
}
