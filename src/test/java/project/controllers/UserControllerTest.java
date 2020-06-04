package project.controllers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import project.saloon.Saloon;
import project.schedule.Schedule;
import project.user.*;
import project.user.User;
import project.user.UserController;
import project.user.UserRepository;
import project.service.Service;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock( lenient = true)
    UserRepository R;

    @Mock( lenient = true)
    UserService S;

    @Mock( lenient = true)
    private Model model;


    @InjectMocks
    UserController controller;


    @Test
    public void getAllUsers() throws Exception {

        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");


        List<User> allUsers = singletonList(user);



        Mockito.when(S.getAllUser()).thenReturn(allUsers);


        assertEquals(controller.all(),allUsers);

    }


    @Test
    public void newUser() throws Exception {
        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");




        Mockito.when(R.save(user)).thenReturn(user);


        assertEquals(controller.newUser(user),user);

    }


    @Test
    public void getUser() throws Exception {

        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");


        Mockito.when(R.getUserById(user.getId())).thenReturn(java.util.Optional.of(user));


        assertEquals(controller.getUser(user.getId()),user);

    }

    @Test
    public void editUser() throws Exception {

        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");





        Mockito.when(R.getUserById(user.getId())).thenReturn(java.util.Optional.of(user));
        Mockito.when(R.save(user)).thenReturn(user);


        assertEquals(controller.editUser(user,user.getId()),user);

    }




}
