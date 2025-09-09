package br.com.danieljscosta.service;

import br.com.danieljscosta.entity.Users;
import br.com.danieljscosta.exceptions.UserNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class UserService {
    public Users createUserX(Users users){
        Users.persist(users);
        return users;
    }

    public List<Users> findAllUsers(Integer page, Integer pageSize){
        return Users.findAll()
                .page(page, pageSize)
                .list();
    }

    public Users findById(UUID userId){
        return  (Users)  Users.findByIdOptional(userId)
                .orElseThrow(UserNotFoundException::new);
        //findByIdOptional retorna um Optional<PanacheEntityBase>. e não um Optional<Users>
        //Por isso preciso do casting pq o metodo espera um Users como retorno
    }

    public Users updateUser(UUID userId, Users user){
        var userToUpdate = findById(userId);
        userToUpdate.username = user.username;
        userToUpdate.email = user.email;
        userToUpdate.persist();
        //Users.persist(userToUpdate); //Outra forma de persistir
        return userToUpdate;
    }

    public void deleteUser(UUID userId){
        var userToDelete = findById(userId);
        userToDelete.delete();
        //Users.delete("id", userId); //Outra forma de deletar
    }
}
