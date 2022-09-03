package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.exception.ApiException;
import com.brihaspathee.zeus.exception.ApiExceptionList;
import com.brihaspathee.zeus.exception.RoleNotFoundException;
import com.brihaspathee.zeus.exception.UserNotFoundException;
import com.brihaspathee.zeus.security.model.UserDto;
import com.brihaspathee.zeus.security.model.UserList;
import com.brihaspathee.zeus.service.interfaces.UserService;
import com.brihaspathee.zeus.test.BuildTestData;
import com.brihaspathee.zeus.test.TestClass;
import com.brihaspathee.zeus.util.TestSecurityUtil;
import com.brihaspathee.zeus.web.request.TestUserRequest;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 16, August 2022
 * Time: 7:30 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.impl
 * To change this template use File | Settings | File and Code Template
 */
@SpringBootTest
@Slf4j
/**
 * The @WebMvcTest annotation can be used when there is no security involved
 * Since the users has to be loaded from the DB, the context have to brought up
 * so we have to use @SprintBootTest annotation
 */
// @WebMvcTest(UserResource.class)
class UserAPIImplTest {

    /**
     * The web application context to bring up the security details
     */
    @Autowired
    WebApplicationContext wac;

    /**
     * Mock service to mock up methods
     */
    protected MockMvc mockMvc;

    /**
     * User service objects. The methods of this object will be mocked
     */
    @MockBean
    UserService userService;

    /**
     * The object mapper instance to parse the test data file
     */
    @Autowired
    ObjectMapper objectMapper;

    /**
     * password encoder to encode any passwords
     */
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * The JSON file that contains the test data
     */
    @Value("classpath:com/brihaspathee/zeus/web/resource/impl/UserAPIImplTest.json")
    Resource resourceFile;

    /**
     * The test class with the user request
     */
    private TestClass<TestUserRequest> userRequestTestClass;

    /**
     * The test info object that contains some basic details of the test
     */
    private TestInfo testInfo;

    /**
     * The instance of the class that helps to build the data
     */
    @Autowired
    private BuildTestData<TestUserRequest> buildTestData;

    /**
     * The list of test user requests
     */
    private List<TestUserRequest> requests = new ArrayList<>();

    /**
     * The setup method that executes before each of the test methods
     * @throws IOException
     */
    @BeforeEach
    void setUp(TestInfo testInfo) throws IOException {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
        userRequestTestClass = objectMapper.readValue(resourceFile.getFile(), new TypeReference<TestClass<TestUserRequest>>() {});
        this.testInfo = testInfo;
        log.info("Test Method name:{}", testInfo.getTestMethod().get().getName());
        this.requests = buildTestData.buildData(testInfo.getTestMethod().get().getName(), this.userRequestTestClass);
    }

    /**
     * This method will test the get all users method
     * @param repetitionInfo
     */
    @RepeatedTest(5)
    void testGetAllUsers(RepetitionInfo repetitionInfo) throws Exception {
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the user request for the repetition
        TestUserRequest testUserRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateGetUsers(testUserRequest);
    }

    /**
     * This method will test the get user by id method
     * @param repetitionInfo
     */
    @RepeatedTest(6)
    void testGetUserById(RepetitionInfo repetitionInfo) throws Exception {
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the user request for the repetition
        TestUserRequest testUserRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateGetUsers(testUserRequest);
    }

    /**
     * This method will test the get user by id method
     * @param repetitionInfo
     */
    @RepeatedTest(6)
    void testGetUserByUsername(RepetitionInfo repetitionInfo) throws Exception {
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the user request for the repetition
        TestUserRequest testUserRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateGetUsers(testUserRequest);
    }

    /**
     * This method will test the create user method
     * @param repetitionInfo
     * @throws Exception
     */
    @RepeatedTest(6)
    void testCreateUser(RepetitionInfo repetitionInfo) throws Exception {
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the user request for the repetition
        TestUserRequest testUserRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateCreateUser(testUserRequest);
    }

    @RepeatedTest(6)
    void testUpdateUser(RepetitionInfo repetitionInfo) throws Exception {
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the user request for the repetition
        TestUserRequest testUserRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateUpdateUser(testUserRequest);
    }

    /**
     * The method to validate the user that was updated
     * @param testUserRequest
     */
    private void validateUpdateUser(TestUserRequest testUserRequest) throws Exception {
        log.info("Test user request:{}", testUserRequest);
        // Get the expected user list from the test data
        UserList expectedUserList = testUserRequest.getExpectedUserList();
        // Get user which will be used for authenticating with the service
        UserDto loggedInUser = testUserRequest.getLoggedInUser();
        // The user to be updated
        UserDto inputUser = testUserRequest.getUserDtoRequest();
        String inputUserAsString = objectMapper.writeValueAsString(inputUser);
        // URI to be called
        String uri = "/api/v1/zeus/security/user/"+testUserRequest.getUserDtoRequest().getUserId();

        if(testUserRequest.isAuthException()){
            // This piece of code will be executed if the expected exception is an
            // authentication or authorization exception

            if(testUserRequest.getHttpStatusCode().equals("401")){
                // Expecting an unauthorized exception
                mockMvc.perform(put(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputUserAsString))
                        .andExpect(status().isUnauthorized())
                        .andReturn();
            }else{
                // Expecting a forbidden operation exception
                mockMvc.perform(put(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputUserAsString))
                        .andExpect(status().isForbidden())
                        .andReturn();
            }
        }else {
            // This piece of code will be executed if no authentication/authorization exception is expected

            if(!testUserRequest.isExceptionExpected()){
                UserDto expectedUserDto = expectedUserList.getUserDtos().get(0);
                doReturn(expectedUserDto).when(userService).saveUser(any());
                MvcResult mvcResult = mockMvc.perform(put(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputUserAsString))
                        .andExpect(status().isOk())
                        .andReturn();
                String apiResponseString = mvcResult.getResponse().getContentAsString();
                ZeusApiResponse zeusApiResponse = objectMapper.readValue(apiResponseString, ZeusApiResponse.class);
                // get the updated user
                UserDto userDto =
                        objectMapper.convertValue(zeusApiResponse.getResponse(), UserDto.class);
                log.info("Returned User:{}", userDto);
                expectedUserDto.setUserId(userDto.getUserId());
                TestSecurityUtil.assertUserDetails(expectedUserDto, userDto, false);
            }else {
                doThrow(new RoleNotFoundException("One or more roles is not present")).when(userService).saveUser(any(UserDto.class));
                MvcResult mvcResult = mockMvc.perform(put(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputUserAsString))
                        .andExpect(status().isBadRequest())
                        .andReturn();
                log.info("Api response:{}", mvcResult.getResponse().getContentAsString());
                String apiResponseString = mvcResult.getResponse().getContentAsString();
                ZeusApiResponse zeusApiResponse = objectMapper.readValue(apiResponseString, ZeusApiResponse.class);
                // get the api exception list
                ApiExceptionList apiExceptionList = objectMapper.convertValue(zeusApiResponse.getResponse(), ApiExceptionList.class);
                ApiException apiException = apiExceptionList.getExceptions().get(0);
                log.info("Actual Exception Code:{}", apiException.getExceptionCode());
                log.info("Actual Exception Message:{}", apiException.getExceptionMessage());
                assertEquals(testUserRequest.getExceptionCode(), apiException.getExceptionCode());
                assertEquals(testUserRequest.getExceptionMessage(), apiException.getExceptionMessage());
            }
        }
    }

    /**
     * The method to validate the user that is being created
     * @param testUserRequest
     */
    private void validateCreateUser(TestUserRequest testUserRequest) throws Exception {
        log.info("Test user request:{}", testUserRequest);
        // Get the expected user list from the test data
        UserDto expectedUserDto = testUserRequest.getExpectedUserDto();
        // Get user which will be used for authenticating with the service
        UserDto loggedInUser = testUserRequest.getLoggedInUser();
        // The user to be created
        UserDto inputUser = testUserRequest.getUserDtoRequest();
        String inputUserAsString = objectMapper.writeValueAsString(inputUser);
        // The URI to be called
        String uri = "/api/v1/zeus/security/user";
        if(testUserRequest.isAuthException()){
            // This piece of code will be executed if the expected exception is an
            if(testUserRequest.getHttpStatusCode().equals("401")){
                // Expecting an unauthorized exception
                mockMvc.perform(post(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputUserAsString))
                        .andExpect(status().isUnauthorized())
                        .andReturn();
            }else{
                // Expecting a forbidden operation exception
                mockMvc.perform(post(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputUserAsString))
                        .andExpect(status().isForbidden())
                        .andReturn();
            }
        }else {
            if(!testUserRequest.isExceptionExpected()){
                // This piece of code will be executed if no exception is expected
                doReturn(expectedUserDto).when(userService).saveUser(any());
                MvcResult mvcResult = mockMvc.perform(post(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputUserAsString))
                        .andExpect(status().isCreated())
                        .andReturn();
                String apiResponseString = mvcResult.getResponse().getContentAsString();
                ZeusApiResponse zeusApiResponse = objectMapper.readValue(apiResponseString, ZeusApiResponse.class);
                // get the created user
                UserDto userDto =
                        objectMapper.convertValue(zeusApiResponse.getResponse(), UserDto.class);
                log.info("Returned User:{}", userDto);
                expectedUserDto.setUserId(userDto.getUserId());
                TestSecurityUtil.assertUserDetails(expectedUserDto, userDto,false);
            }else {
                // This piece of code will be executed if any exception other than authentication/authorization exception
                // is expected
                doThrow(new RoleNotFoundException("One or more roles is not present")).when(userService).saveUser(any());
                MvcResult mvcResult = mockMvc.perform(post(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputUserAsString))
                        .andExpect(status().isBadRequest())
                        .andReturn();
                String apiResponseString = mvcResult.getResponse().getContentAsString();
                ZeusApiResponse zeusApiResponse = objectMapper.readValue(apiResponseString, ZeusApiResponse.class);
                // get the api exception list
                ApiExceptionList apiExceptionList = objectMapper.convertValue(zeusApiResponse.getResponse(), ApiExceptionList.class);
                ApiException apiException = apiExceptionList.getExceptions().get(0);
                log.info("Actual Exception Code:{}", apiException.getExceptionCode());
                log.info("Actual Exception Message:{}", apiException.getExceptionMessage());
                assertEquals(testUserRequest.getExceptionCode(), apiException.getExceptionCode());
                assertEquals(testUserRequest.getExceptionMessage(), apiException.getExceptionMessage());
            }
        }
    }

    /**
     * Makes the appropriate call and validates the data returned for getUsers
     * @param testUserRequest
     */
    private void validateGetUsers(TestUserRequest testUserRequest) throws Exception {
        // Get the expected user list
        UserList expectedUserList = testUserRequest.getExpectedUserList();
        // Get log in user to use as credentials
        UserDto loggedInUser = testUserRequest.getLoggedInUser();
        // The URI string will be populated based on the test case
        String uri = null;
        if(testUserRequest.getUserDtoRequest() == null){
            // the URI if all users are to fetched
            uri = "/api/v1/zeus/security/user";
            doReturn(expectedUserList).when(userService).getAllUsers();
        }else if(testUserRequest.getUserDtoRequest().getUserId() != null){
            UUID userId = testUserRequest.getUserDtoRequest().getUserId();
            // The URI if user by user id are to fetched
            uri = "/api/v1/zeus/security/user/user-id/"+userId;
            // Expect User not found exception if user with user id is not found
            if(testUserRequest.isExceptionExpected() && !testUserRequest.isAuthException()){
                doThrow(new UserNotFoundException("User with user id " + userId + " not found"))
                        .when(userService)
                        .getUserById(any());
            }else{
                doReturn(expectedUserList).when(userService).getUserById(any());
            }

        }else{
            String username = testUserRequest.getUserDtoRequest().getUsername();
            // The URI if the user by user name are to be fetched
            uri = "/api/v1/zeus/security/user/username/"+username;
            // Expect user not found exception if user with username is not found
            if(testUserRequest.isExceptionExpected() && !testUserRequest.isAuthException()){
                doThrow(new UserNotFoundException("User with username " + username + " not found"))
                        .when(userService)
                        .getUserByUsername(any());
            }else{
                doReturn(expectedUserList).when(userService).getUserByUsername(any());
            }
        }
        if(testUserRequest.isAuthException()){
            // Determine which auth exception is to be expected
            if(testUserRequest.getHttpStatusCode().equals("401")){
                mockMvc.perform(get(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isUnauthorized())
                        .andReturn();
            }else{
                mockMvc.perform(get(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isForbidden())
                        .andReturn();
            }

        }else{
            if(!testUserRequest.isExceptionExpected()){
                MvcResult mvcResult = mockMvc.perform(get(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
                String apiResponseString = mvcResult.getResponse().getContentAsString();
                ZeusApiResponse zeusApiResponse = objectMapper.readValue(apiResponseString, ZeusApiResponse.class);
                // get the list of users
                UserList actualUserList =
                        objectMapper.convertValue(zeusApiResponse.getResponse(), UserList.class);
                assertEquals(expectedUserList.getUserDtos().size(), actualUserList.getUserDtos().size());
                TestSecurityUtil.assertUserList(expectedUserList, actualUserList,false);
            }else{
                    MvcResult mvcResult = mockMvc.perform(get(uri)
                            .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                            .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isBadRequest())
                    .andReturn();
                String apiResponseString = mvcResult.getResponse().getContentAsString();
                ZeusApiResponse zeusApiResponse = objectMapper.readValue(apiResponseString, ZeusApiResponse.class);
                // get the api exception list
                ApiExceptionList apiExceptionList = objectMapper.convertValue(
                        zeusApiResponse.getResponse(),
                        ApiExceptionList.class);
                ApiException apiException = apiExceptionList.getExceptions().get(0);
                assertEquals(testUserRequest.getExceptionCode(), apiException.getExceptionCode());
                assertEquals(testUserRequest.getExceptionMessage(), apiException.getExceptionMessage());
            }

        }
    }


}