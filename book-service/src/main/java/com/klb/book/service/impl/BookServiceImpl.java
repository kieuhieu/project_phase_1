package com.klb.book.service.impl;

import com.devteria.identityservice.dto.request.IntrospectRequest;
import com.devteria.identityservice.service.AuthenticationService;
import com.klb.book.entity.Book;
import com.klb.book.repository.BookRepository;
import com.klb.book.service.BookService;
import com.klb.book.utils.GetTokenUtils;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookServiceImpl implements BookService {

    @DubboReference
    AuthenticationService authenticationService;

    HttpServletRequest httpServletRequest;
    BookRepository bookRepository;

    @Override
    public List<Book> findAllBooks() {
        String token = GetTokenUtils.getTokenFromRequest(httpServletRequest);

        IntrospectRequest request = new IntrospectRequest();
        request.setToken(token);

        try {
            var response = authenticationService.introspect(request);
            log.info(response.toString());
            if (response.isValid()) {
                return bookRepository.findAll();
            } else {
                throw new SecurityException("Token is not valid.");
            }
        } catch (JOSEException | ParseException e) {
            throw new RuntimeException("Failed to introspect token", e);
        }
    }
}
