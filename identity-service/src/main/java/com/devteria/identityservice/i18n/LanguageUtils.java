package com.devteria.identityservice.i18n;

import jakarta.servlet.http.HttpServletRequest;

public class LanguageUtils {
    public static String extractLang(HttpServletRequest request) {
        String lang = request.getHeader("Accept-Language");
        return (lang == null || lang.isEmpty()) ? "vi" : lang.split(",")[0];
    }
}
