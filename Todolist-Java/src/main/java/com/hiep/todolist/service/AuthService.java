package com.hiep.todolist.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.hiep.todolist.auth.JwtTokenHelper;
import com.hiep.todolist.dao.UserDAO;
import com.hiep.todolist.entity.User;
 
@Path("/auth")
public class AuthService {
 
    /**
     * Authenticating a user with their username/ password and issuing a token
     * 
     * @param username
     * @param password
     * @return JSON Web Token (JWT)
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("username") String username, @FormParam("password") String password) {
 
        // Authenticate the user using the credentials provided
        User user = UserDAO.getUser(username, password);
        if (user == null) {
            return Response.status(Response.Status.FORBIDDEN) // 403 Forbidden
                    .entity("Wrong username or password") // the response entity
                    .build();
        }
 
        // Issue a token for the user
        String token = JwtTokenHelper.createJWT(user);
 
        // Return the token on the response
        return Response.ok("{\"token\": \"" + token + "\"}").build();
    }
}