package com.devteria.identityservice.i18n;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class TranslatorService {
    private final RestTemplate restTemplate;

    public String translate(String text, String targetLang) {
        Map<String, Object> body = Map.of(
                "q", text,
                "source", "en",
                "target", targetLang,
                "format", "text"
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            String endpoint = "http://localhost:5000/translate";
            var response = restTemplate.postForEntity(endpoint, request, Map.class);

            log.info("LibreTranslate response: {}", response.getBody());

            assert response.getBody() != null;
            return (String) response.getBody().get("translatedText");
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("HTTP error during translation: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            return text;
        } catch (ResourceAccessException e) {
            log.error("Resource access error (possible timeout or connection issue): {}", e.getMessage());
            return text;
        } catch (Exception e) {
            log.error("Unexpected error during translation: ", e);
            return text;
        }
    }
}

