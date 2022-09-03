package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.domain.security.Role;
import com.brihaspathee.zeus.exception.*;
import com.brihaspathee.zeus.security.model.RoleDto;
import com.brihaspathee.zeus.security.model.RoleList;
import com.brihaspathee.zeus.security.model.UserDto;
import com.brihaspathee.zeus.security.model.UserList;
import com.brihaspathee.zeus.service.interfaces.RoleService;
import com.brihaspathee.zeus.test.BuildTestData;
import com.brihaspathee.zeus.test.TestClass;
import com.brihaspathee.zeus.util.TestSecurityUtil;
import com.brihaspathee.zeus.web.request.TestRoleRequest;
import com.brihaspathee.zeus.web.request.TestUserRequest;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 24, August 2022
 * Time: 6:48 AM
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
class RoleAPIImplTest {

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
     * Role service objects. The methods of this object will be mocked
     */
    @MockBean
    RoleService roleService;

    /**
     * The object mapper instance to parse the test data file
     */
    @Autowired
    ObjectMapper objectMapper;

    /**
     * The JSON file that contains the test data
     */
    @Value("classpath:com/brihaspathee/zeus/web/resource/impl/RoleAPIImplTest.json")
    Resource resourceFile;

    /**
     * The test class with the role request
     */
    private TestClass<TestRoleRequest> roleRequestTestClass;

    /**
     * The test info object that contains some basic details of the test
     */
    private TestInfo testInfo;

    /**
     * The instance of the class that helps to build the data
     */
    @Autowired
    private BuildTestData<TestRoleRequest> buildTestData;

    /**
     * The list of test role requests
     */
    private List<TestRoleRequest> requests = new ArrayList<>();

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
        roleRequestTestClass = objectMapper.readValue(resourceFile.getFile(), new TypeReference<TestClass<TestRoleRequest>>() {});
        this.testInfo = testInfo;
        log.info("Test Method name:{}", testInfo.getTestMethod().get().getName());
        this.requests = buildTestData.buildData(testInfo.getTestMethod().get().getName(), this.roleRequestTestClass);
    }

    /**
     * Method to test get all roles end point
     * @param repetitionInfo
     * @throws Exception
     */
    @RepeatedTest(5)
    void testGetAllRoles(RepetitionInfo repetitionInfo) throws Exception {
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the user request for the repetition
        TestRoleRequest testRoleRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateGetRoles(testRoleRequest);
    }

    /**
     * Method to test get all role by id end point
     * @param repetitionInfo
     * @throws Exception
     */
    @RepeatedTest(6)
    void testGetRoleById(RepetitionInfo repetitionInfo) throws Exception {
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the user request for the repetition
        TestRoleRequest testRoleRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateGetRoles(testRoleRequest);
    }

    /**
     * Method to test get all roles by role name endpoint
     * @param repetitionInfo
     * @throws Exception
     */
    @RepeatedTest(6)
    void testGetRoleByRoleName(RepetitionInfo repetitionInfo) throws Exception {
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the user request for the repetition
        TestRoleRequest testRoleRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateGetRoles(testRoleRequest);
    }

    /**
     * Method to test create role endpoint
     * @param repetitionInfo
     */
    @RepeatedTest(5)
    void testCreateRole(RepetitionInfo repetitionInfo) throws Exception {
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the user request for the repetition
        TestRoleRequest testRoleRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateCreateRole(testRoleRequest);
    }

    /**
     * Method to test update role endpoint
     * @param repetitionInfo
     */
    @RepeatedTest(5)
    void testUpdateRole(RepetitionInfo repetitionInfo) throws Exception {
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the user request for the repetition
        TestRoleRequest testRoleRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateUpdateRole(testRoleRequest);
    }

    /**
     * The method to validate the role that was updated
     * @param testRoleRequest
     */
    private void validateUpdateRole(TestRoleRequest testRoleRequest) throws Exception {
        log.info("Test role request:{}", testRoleRequest);
        // Get the expected role list from the test data
        RoleList expectedRoleList = testRoleRequest.getExpectedRoleList();
        // Get user which will be used for authenticating with the service
        UserDto loggedInUser = testRoleRequest.getLoggedInUser();
        // The role to be updated
        RoleDto inputRole = testRoleRequest.getRoleDtoRequest();
        String inputRoleAsString = objectMapper.writeValueAsString(inputRole);
        // URI to be called
        String uri = "/api/v1/zeus/security/role/"+testRoleRequest.getRoleDtoRequest().getRoleId();

        if(testRoleRequest.isAuthException()){
            // This piece of code will be executed if the expected exception is an
            // authentication or authorization exception

            if(testRoleRequest.getHttpStatusCode().equals("401")){
                // Expecting an unauthorized exception
                mockMvc.perform(put(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputRoleAsString))
                        .andExpect(status().isUnauthorized())
                        .andReturn();
            }else{
                // Expecting a forbidden operation exception
                mockMvc.perform(put(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputRoleAsString))
                        .andExpect(status().isForbidden())
                        .andReturn();
            }
        }else {
            // This piece of code will be executed if no authentication/authorization exception is expected

            if(!testRoleRequest.isExceptionExpected()){
                RoleDto expectedRoleDto = expectedRoleList.getRoleDtos().get(0);
                doReturn(expectedRoleDto).when(roleService).saveRole(any());
                MvcResult mvcResult = mockMvc.perform(put(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputRoleAsString))
                        .andExpect(status().isCreated())
                        .andReturn();
                String apiResponseString = mvcResult.getResponse().getContentAsString();
                ZeusApiResponse zeusApiResponse = objectMapper.readValue(apiResponseString, ZeusApiResponse.class);
                // get the updated role
                RoleDto roleDto =
                        objectMapper.convertValue(zeusApiResponse.getResponse(), RoleDto.class);
                log.info("Returned Role:{}", roleDto);
                expectedRoleDto.setRoleId(roleDto.getRoleId());
                TestSecurityUtil.assertRoleDetails(expectedRoleDto, roleDto);
            }else {
                doThrow(new AuthorityNotFoundException("One or more authorities not found")).when(roleService).saveRole(any());
                MvcResult mvcResult = mockMvc.perform(put(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputRoleAsString))
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
                assertEquals(testRoleRequest.getExceptionCode(), apiException.getExceptionCode());
                assertEquals(testRoleRequest.getExceptionMessage(), apiException.getExceptionMessage());
            }
        }
    }


    /**
     * The method to validate the user that is being created
     * @param testRoleRequest
     */
    private void validateCreateRole(TestRoleRequest testRoleRequest) throws Exception {
        log.info("Test role request:{}", testRoleRequest);
        // Get the expected role list from the test data
        RoleDto expectedRoleDto = testRoleRequest.getExpectedRoleDto();
        // Get role which will be used for authenticating with the service
        UserDto loggedInUser = testRoleRequest.getLoggedInUser();
        // The role to be created
        RoleDto inputRole = testRoleRequest.getRoleDtoRequest();
        String inputRoleAsString = objectMapper.writeValueAsString(inputRole);
        // The URI to be called
        String uri = "/api/v1/zeus/security/role";
        if(testRoleRequest.isAuthException()){
            // This piece of code will be executed if the expected exception is an
            if(testRoleRequest.getHttpStatusCode().equals("401")){
                // Expecting an unauthorized exception
                mockMvc.perform(post(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputRoleAsString))
                        .andExpect(status().isUnauthorized())
                        .andReturn();
            }else{
                // Expecting a forbidden operation exception
                mockMvc.perform(post(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputRoleAsString))
                        .andExpect(status().isForbidden())
                        .andReturn();
            }
        }else {
            if(!testRoleRequest.isExceptionExpected()){
                // This piece of code will be executed if no exception is expected
                doReturn(expectedRoleDto).when(roleService).saveRole(any());
                MvcResult mvcResult = mockMvc.perform(post(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputRoleAsString))
                        .andExpect(status().isCreated())
                        .andReturn();
                String apiResponseString = mvcResult.getResponse().getContentAsString();
                ZeusApiResponse zeusApiResponse = objectMapper.readValue(apiResponseString, ZeusApiResponse.class);
                // get the created role
                RoleDto roleDto =
                        objectMapper.convertValue(zeusApiResponse.getResponse(), RoleDto.class);
                log.info("Returned Role:{}", roleDto);
                expectedRoleDto.setRoleId(roleDto.getRoleId());
                TestSecurityUtil.assertRoleDetails(expectedRoleDto, roleDto);
            }else {
                // This piece of code will be executed if any exception other than authentication/authorization exception
                // is expected
                doThrow(new AuthorityNotFoundException("One or more authorities not found")).when(roleService).saveRole(any());
                MvcResult mvcResult = mockMvc.perform(post(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputRoleAsString))
                        .andExpect(status().isBadRequest())
                        .andReturn();
                String apiResponseString = mvcResult.getResponse().getContentAsString();
                ZeusApiResponse zeusApiResponse = objectMapper.readValue(apiResponseString, ZeusApiResponse.class);
                // get the api exception list
                ApiExceptionList apiExceptionList = objectMapper.convertValue(zeusApiResponse.getResponse(), ApiExceptionList.class);
                ApiException apiException = apiExceptionList.getExceptions().get(0);
                log.info("Actual Exception Code:{}", apiException.getExceptionCode());
                log.info("Actual Exception Message:{}", apiException.getExceptionMessage());
                assertEquals(testRoleRequest.getExceptionCode(), apiException.getExceptionCode());
                assertEquals(testRoleRequest.getExceptionMessage(), apiException.getExceptionMessage());
            }
        }
    }

    /**
     * Makes the appropriate call and validates the data returned for getAllRoles
     * @param testRoleRequest
     */
    private void validateGetRoles(TestRoleRequest testRoleRequest) throws Exception {
        log.info("Test role request:{}", testRoleRequest);
        // Get the expected rple list from the test data
        RoleList expectedRoleList = testRoleRequest.getExpectedRoleList();
        // Get user which will be used for authenticating with the service
        UserDto loggedInUser = testRoleRequest.getLoggedInUser();
        // The URI string will be populated based on the test case
        String uri = null;
        if(testRoleRequest.getRoleDtoRequest() == null){
            uri = "/api/v1/zeus/security/role";
            doReturn(expectedRoleList).when(roleService).getAllRoles();
        } else if (testRoleRequest.getRoleDtoRequest().getRoleId() != null){
            UUID roleId = testRoleRequest.getRoleDtoRequest().getRoleId();
            // The URI if role by role id are to be fetched
            uri = "/api/v1/zeus/security/role/role-id/"+roleId;
            // Expect role not found exception if role with role id is not found
            if(testRoleRequest.isExceptionExpected() && !testRoleRequest.isAuthException()){
                doThrow(new RoleNotFoundException("Role with role id " + roleId + " not found"))
                        .when(roleService)
                        .getRoleById(any());
            }else{
                doReturn(expectedRoleList).when(roleService).getRoleById(any());
            }
        }else {
            String roleName = testRoleRequest.getRoleDtoRequest().getRoleName();
            // The URI if role by role name are to fetched
            uri = "/api/v1/zeus/security/role/role-name/"+roleName;
            // Expect role not found exception if user with role name is not found
            if(testRoleRequest.isExceptionExpected() && !testRoleRequest.isAuthException()){
                doThrow(new RoleNotFoundException("Role with role name " + roleName + " not found"))
                        .when(roleService)
                        .getRoleByRoleName(any());
            }else{
                doReturn(expectedRoleList).when(roleService).getRoleByRoleName(any());
            }
        }
        if(testRoleRequest.isAuthException()){
            // This piece of code will be executed if the expected exception is an
            // authentication or authorization exception
            // Determine which auth exception is to be expected
            if(testRoleRequest.getHttpStatusCode().equals("401")){
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
        }else {
            if(!testRoleRequest.isExceptionExpected()){
                log.info("URI:{}", uri);
                MvcResult mvcResult = mockMvc.perform(get(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
                String apiResponseString = mvcResult.getResponse().getContentAsString();
                ZeusApiResponse zeusApiResponse = objectMapper.readValue(apiResponseString, ZeusApiResponse.class);
                // get the list of roles
                RoleList actualRoleList =
                        objectMapper.convertValue(zeusApiResponse.getResponse(), RoleList.class);
                assertEquals(expectedRoleList.getRoleDtos().size(), actualRoleList.getRoleDtos().size());
                TestSecurityUtil.assertRoleList(expectedRoleList.getRoleDtos(), actualRoleList.getRoleDtos());
            }else{
                log.info("URI:{}", uri);
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
                assertEquals(testRoleRequest.getExceptionCode(), apiException.getExceptionCode());
                assertEquals(testRoleRequest.getExceptionMessage(), apiException.getExceptionMessage());
            }

        }
    }
}