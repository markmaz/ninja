package com.ninjarmm.services.impl;

import com.ninjarmm.dao.IUserDataService;
import com.ninjarmm.dao.jooq.tables.pojos.User;
import com.ninjarmm.model.PagedResults;
import com.ninjarmm.model.UserModel;
import com.ninjarmm.services.IMapperService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = UserService.class)
class UserServiceTest {
    @MockBean
    private IUserDataService dataService;

    @MockBean
    private IMapperService mapperService;


    @Test
    public void testGetOne(){
        UUID id = UUID.randomUUID();
        User user = new User().setId(id).setUsername("test");
        UserModel userModel = new UserModel().username("test").id(id);

        when(dataService.selectOne(id)).thenReturn(user);
        when(mapperService.toModel(user)).thenReturn(userModel);

        UserService service = new UserService(dataService, mapperService);
        var results = service.getOne(id);

        assertThat(results.getId()).isEqualTo(id);
        assertThat(results.getUsername()).isEqualTo("test");
    }

    @Test
    public void testGetMany(){
        UUID id = UUID.randomUUID();
        User user = new User().setId(id).setUsername("test");
        UserModel userModel = new UserModel().username("test").id(id);
        var page = 0;
        var size = 10;
        var paged = PagedResults.of(List.of(user), 1, page, size);
        var pagedModel = PagedResults.of(List.of(userModel), 1, page, size);

        when(dataService.selectMany(page, size)).thenReturn(paged);
        when(mapperService.toModel(paged, page, size, UserModel.class)).thenReturn(pagedModel);

        UserService service = new UserService(dataService, mapperService);
        var results = service.getMany(page, size);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getValues().get(0).getUsername()).isEqualTo("test");
        assertThat(results.getValues().get(0).getId()).isEqualTo(id);
    }

    @Test
    public void testDelete(){
        UUID id = UUID.randomUUID();

        UserService service = new UserService(dataService, mapperService);
        service.delete(id);

        verify(dataService, times(1)).delete(id);
    }

    @Test
    public void testUpdateUserNotNewUser(){
        UUID id = UUID.randomUUID();
        User user = new User().setId(id).setUsername("test");
        User aUser = new User().setUsername("mm");
        UserModel resource = new UserModel().username("test").id(id);
        UserModel auditUser = new UserModel().username("mm");

        when(mapperService.toDao(resource)).thenReturn(user);
        when(mapperService.toDao(auditUser)).thenReturn(aUser);
        when(dataService.update(user, aUser)).thenReturn(user);
        when(mapperService.toModel(user)).thenReturn(resource);

        UserService userService = new UserService(dataService, mapperService);
        var result = userService.update(resource, auditUser);

        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getUsername()).isEqualTo(resource.getUsername());
    }

    @Test
    public void testUpdateUserNewUser(){
        UUID id = UUID.randomUUID();
        User user = new User().setUsername("test");
        User aUser = new User().setUsername("mm");
        UserModel resource = new UserModel().username("test");
        UserModel auditUser = new UserModel().username("mm");

        User anotherUser = new User().setId(id).setUsername("test");

        when(mapperService.toDao(resource)).thenReturn(user);
        when(mapperService.toDao(auditUser)).thenReturn(aUser);
        when(dataService.insert(user, aUser)).thenReturn(anotherUser);
        when(mapperService.toModel(anotherUser)).thenReturn(resource.id(id));

        UserService userService = new UserService(dataService, mapperService);
        var result = userService.add(resource, auditUser);

        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getUsername()).isEqualTo(resource.getUsername());
    }

    @Test
    public void testAddNewUser(){
        UUID id = UUID.randomUUID();
        User user = new User().setId(id).setUsername("test");
        User aUser = new User().setUsername("mm");
        UserModel resource = new UserModel().username("test").id(id);
        UserModel auditUser = new UserModel().username("mm");

        when(mapperService.toDao(resource)).thenReturn(user);
        when(mapperService.toDao(auditUser)).thenReturn(aUser);
        when(dataService.insert(user, aUser)).thenReturn(user);
        when(mapperService.toModel(user)).thenReturn(resource);

        UserService userService = new UserService(dataService, mapperService);
        var result = userService.add(resource, auditUser);

        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getUsername()).isEqualTo(resource.getUsername());
    }

    @Test
    public void testFindByUsername(){
        UUID id = UUID.randomUUID();
        User user = new User().setId(id).setUsername("test");
        UserModel resource = new UserModel().username("test").id(id);

        when(dataService.findByUsername("test")).thenReturn(user);
        when(mapperService.toModel(user)).thenReturn(resource);

        UserService userService = new UserService(dataService, mapperService);
        var result = userService.findByUserName("test");

        assertThat(result.getUsername()).isEqualTo("test");
    }
}