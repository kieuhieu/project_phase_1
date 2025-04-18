package com.klb.book.utils;

import jakarta.servlet.http.HttpServletRequest;

public class GetTokenUtils {

    public static String getTokenFromRequest(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return token;
    }


}
