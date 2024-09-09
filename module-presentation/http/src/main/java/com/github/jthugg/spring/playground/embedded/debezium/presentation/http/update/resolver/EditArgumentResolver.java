package com.github.jthugg.spring.playground.embedded.debezium.presentation.http.update.resolver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoEdit;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoStatus;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoStatusUpdate;
import com.github.jthugg.spring.playground.embedded.debezium.presentation.http.exception.CannotResolveArgumentsException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EditArgumentResolver implements HandlerMethodArgumentResolver {

    public static final String TITLE_ATTRIBUTE = "title";
    public static final String CONTENT_ATTRIBUTE = "content";

    private final ObjectMapper objectMapper;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(TodoEdit.class);
    }

    @NonNull
    @Override
    public TodoEdit resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) throws Exception {
        try {
            HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
            String bodyString = servletRequest.getReader().lines().collect(Collectors.joining());
            JsonNode bodyJsonNode = objectMapper.readTree(bodyString);
            String resolvedTitleValue = bodyJsonNode.get(TITLE_ATTRIBUTE).asText();
            String resolvedContentValue = bodyJsonNode.get(CONTENT_ATTRIBUTE).asText();
            String pathInfo = servletRequest.getPathInfo();
            UriComponents uriComponents = UriComponentsBuilder.fromUriString(pathInfo).build();
            String todoIdStringValue = uriComponents.getPathSegments().getLast();
            long todoId = Long.parseLong(todoIdStringValue);
            return new TodoEdit(todoId, resolvedTitleValue, resolvedContentValue);
        } catch (NullPointerException // servletRequest is null
                 | IllegalArgumentException // there's no TodoStatus enum value or no path variable to parse
                 | JsonProcessingException exception) { // cannot resolve to json object
            // TODO: handle exception gracefully
            throw new CannotResolveArgumentsException(exception);
        }
    }

}
