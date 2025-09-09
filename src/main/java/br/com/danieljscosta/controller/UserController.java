package br.com.danieljscosta.controller;

import br.com.danieljscosta.entity.Users;
import br.com.danieljscosta.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GET
    @Path("/list")
    public Response findAllUsers(@QueryParam("page") @DefaultValue("0") Integer page,
                                 @QueryParam("pageSize") @DefaultValue("10") Integer pageSize){
        var users = userService.findAllUsers(page, pageSize);
        // Mesma coisa que List<Users> users = userService.findAllUsers(page, pageSize);
        return Response.ok(users).build();
        //var users = Users.findAll().page(page, size).list();
        //return Response.ok(Users.listAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") UUID userId){
        return Response.ok(userService.findById(userId)).build();
    }

    @POST
    @Path("/create")
    public Response createUser(Users user){
        return Response.ok(userService.createUserX(user)).build();
    }

    @PUT
    @Transactional
    @Path("/update/{id}")
    public Response updateUser(@PathParam("id") UUID userId, Users user){
        return Response.ok(userService.updateUser(userId, user)).build();
    }

    @DELETE
    @Transactional
    @Path("/delete/{id}")
    public Response deleteUser(@PathParam("id") UUID userId){
        //return Response.ok(userService.deleteUser(userId)).build();
        userService.deleteUser(userId);
        return Response.ok("User removed").build();
        //return Response.noContent().build();

    }
}
