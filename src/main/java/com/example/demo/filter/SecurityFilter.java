package com.example.demo.filter;

import com.example.demo.persistance.services.UserAppService;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

@Provider
@Logged
public class SecurityFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        if(isPreflightRequest(requestContext)){
            try {

                List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);

                if (authHeader.size() > 0) {

                    // Extracting credentials from header
                    String authToken = authHeader.get(0);
                    authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
                    String decodedString = new String(Base64.getDecoder().decode(authToken));
                    StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                    String username = tokenizer.nextToken();
                    String password = tokenizer.nextToken();

                    // Validating credentials
                    UserAppService userAppService= new UserAppService();
                    String role = userAppService.validateUser(username, password).getRole();
                    if(!(role.equals("error") || role.equals("not found") || role.equals("no match"))) {
                        requestContext.getHeaders().add("role", role);
                        userAppService.close();
                        return;
                    } else {
                        userAppService.close();
                        requestContext.abortWith(Response
                                .status(Response.Status.UNAUTHORIZED)
                                .header("Access-Control-Allow-Origin", "*")
                                .entity("Invalid credentials")
                                .build());
                    }
                } else {
                }

            } catch (NullPointerException e) {
                requestContext.abortWith(Response
                        .status(Response.Status.UNAUTHORIZED)
                        .header("Access-Control-Allow-Origin", "*")
                        .entity("Credentials not provided")
                        .build());
            }
        }
    }

    private static boolean isPreflightRequest(ContainerRequestContext request) {
        return request.getHeaderString("Origin") != null
                && request.getMethod().equalsIgnoreCase("OPTIONS");
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        if (requestContext.getHeaderString("Origin") == null) {
            return;
        }
        if (isPreflightRequest(requestContext)) {
            responseContext.getHeaders().add("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
            responseContext.getHeaders().add("Access-Control-Allow-Headers", "X-PINGOTHER, Content-Type");
            responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        }
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "X-PINGOTHER, Content-Type");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
    }
}
