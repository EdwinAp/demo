package com.usuarios.demo.RestController;

import com.google.gson.Gson;
import com.usuarios.demo.dto.UserDto;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createUserSuccesfull() throws Exception {
        UserDto userDtoCrud ;
        String object = "{\n" +
                "        \"firtsName\": \"Edwin\",\n" +
                "        \"lastName\": \"Galicia\",\n" +
                "        \"age\": 26,\n" +
                "        \"birthDate\": \"1996-10-23\",\n" +
                "        \"email\": \"edwin.galicia@correo.com\"\n" +
                "    }";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(object);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertTrue(mvcResult.getResponse().getStatus() == 201);
    }

    @Test
    void createUserFail() throws Exception {
        String object = "{\n" +
                "        \"age\": 26,\n" +
                "        \"birthDate\": \"1996-10-23\",\n" +
                "        \"email\": \"edwin.galicia@correo.com\"\n" +
                "    }";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(object);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertTrue(mvcResult.getResponse().getStatus() == 400);
    }

    @Test
    void getAllUserOrAnyUsers() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/all");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(mvcResult.getResponse().getStatus(), 200);
    }

    @Test
    void getUserWithIdUser() throws Exception {
        UserDto userDtoCrud ;
        String object = "{\n" +
                "        \"firtsName\": \"Apolo\",\n" +
                "        \"lastName\": \"Venegas\",\n" +
                "        \"age\": 26,\n" +
                "        \"birthDate\": \"1996-10-23\",\n" +
                "        \"email\": \"edwin.galicia@correo.com\"\n" +
                "    }";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(object);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        userDtoCrud = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), UserDto.class);
        requestBuilder = MockMvcRequestBuilders
                .get("/users/"+userDtoCrud.getIduser())
                .accept(MediaType.APPLICATION_JSON);
        mvcResult = mockMvc.perform(requestBuilder).andReturn();
        if (mvcResult.getResponse().getContentAsString().isEmpty()){
            Assertions.assertTrue(false);
        }
        Assertions.assertEquals(new Gson().fromJson(mvcResult.getResponse().getContentAsString(), UserDto.class).getIduser(), userDtoCrud.getIduser());
    }

    @Test
    void deleteUserById() throws Exception {
        UserDto userDtoCrud ;
        String object = "{\n" +
                "        \"firtsName\": \"Apolo\",\n" +
                "        \"lastName\": \"Venegas\",\n" +
                "        \"age\": 26,\n" +
                "        \"birthDate\": \"1996-10-23\",\n" +
                "        \"email\": \"edwin.galicia@correo.com\"\n" +
                "    }";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(object);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        userDtoCrud = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), UserDto.class);
        requestBuilder = MockMvcRequestBuilders
                .delete("/users/"+userDtoCrud.getIduser())
                .accept(MediaType.APPLICATION_JSON);
        mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(mvcResult.getResponse().getStatus(), 200);
    }


    @Test
    void updateUser() throws Exception {
        UserDto userDtoCrud ;
        String changeEmail = "pruebacambio@gmail.com";
        String object = "{\n" +
                "        \"firtsName\": \"Apolo\",\n" +
                "        \"lastName\": \"Venegas\",\n" +
                "        \"age\": 26,\n" +
                "        \"birthDate\": \"1996-10-23\",\n" +
                "        \"email\": \"edwin.galicia@correo.com\"\n" +
                "    }";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(object);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        userDtoCrud = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), UserDto.class);
        object = "{\n" +
                "        \"iduser\": \""+userDtoCrud.getIduser()+"\",\n" +
                "        \"firtsName\": \"Apolo\",\n" +
                "        \"lastName\": \"Venegas\",\n" +
                "        \"age\": 26,\n" +
                "        \"birthDate\": \"1996-10-23\",\n" +
                "        \"email\": \""+changeEmail+"\"\n" +
                "    }";
        requestBuilder = MockMvcRequestBuilders
                .put("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(object);
        mvcResult = mockMvc.perform(requestBuilder).andReturn();
        if (mvcResult.getResponse().getStatus() != 200){
            Assertions.assertTrue(false);
        }
        userDtoCrud = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), UserDto.class);
        Assertions.assertEquals(userDtoCrud.getEmail(), changeEmail);
    }

}