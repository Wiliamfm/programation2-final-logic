package com.example.demo.filter;

import com.example.demo.persistance.services.UserAppService;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

@Provider
@Logged
public class SecurityFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

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
                String role = userAppService.validateUser(username, password);
                if(!(role.equals("error") || role.equals("not found") || role.equals("no match"))) {
                    requestContext.getHeaders().add("role", role);
                    userAppService.close();
                    return;
                } else {
                    userAppService.close();
                    requestContext.abortWith(Response
                            .status(Response.Status.UNAUTHORIZED)
                            .entity("Invalid credentials")
                            .build());
                }
            } else {
            }

        } catch (NullPointerException e) {
            requestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("Credentials not provided")
                    .build());
        }
    }
}
