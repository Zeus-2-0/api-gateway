package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.exception.ApiException;
import com.brihaspathee.zeus.exception.ApiExceptionList;
import com.brihaspathee.zeus.exception.AuthorityNotFoundException;
import com.brihaspathee.zeus.exception.RoleNotFoundException;
import com.brihaspathee.zeus.security.model.*;
import com.brihaspathee.zeus.service.interfaces.AuthorityService;
import com.brihaspathee.zeus.service.interfaces.RoleService;
import com.brihaspathee.zeus.test.BuildTestData;
import com.brihaspathee.zeus.test.TestClass;
import com.brihaspathee.zeus.util.TestSecurityUtil;
import com.brihaspathee.zeus.web.request.TestAuthorityRequest;
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
 * Time: 7:02 AM
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
class AuthorityAPIImplTest {

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
     * Authority service objects. The methods of this object will be mocked
     */
    @MockBean
    AuthorityService authorityService;

    /**
     * The object mapper instance to parse the test data file
     */
    @Autowired
    ObjectMapper objectMapper;

    /**
     * The JSON file that contains the test data
     */
    @Value("classpath:com/brihaspathee/zeus/web/resource/impl/AuthorityAPIImplTest.json")
    Resource resourceFile;

    /**
     * The test class with the authority request
     */
    private TestClass<TestAuthorityRequest> authorityRequestTestClass;

    /**
     * The test info object that contains some basic details of the test
     */
    private TestInfo testInfo;

    /**
     * The instance of the class that helps to build the data
     */
    @Autowired
    private BuildTestData<TestAuthorityRequest> buildTestData;

    /**
     * The list of test authority requests
     */
    private List<TestAuthorityRequest> requests = new ArrayList<>();

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
        authorityRequestTestClass = objectMapper.readValue(resourceFile.getFile(), new TypeReference<TestClass<TestAuthorityRequest>>() {});
        this.testInfo = testInfo;
        log.info("Test Method name:{}", testInfo.getTestMethod().get().getName());
        this.requests = buildTestData.buildData(testInfo.getTestMethod().get().getName(), this.authorityRequestTestClass);
    }

    /**
     * Method to test get all authorities end point
     * @param repetitionInfo
     * @throws Exception
     */
    @RepeatedTest(5)
    void testGetAllAuthorities(RepetitionInfo repetitionInfo) throws Exception {
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the authority request for the repetition
        TestAuthorityRequest testAuthorityRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateGetAuthorities(testAuthorityRequest);
    }

    /**
     * Method to test get authority by id end point
     * @param repetitionInfo
     * @throws Exception
     */
    @RepeatedTest(6)
    void testGetAuthorityById(RepetitionInfo repetitionInfo) throws Exception {
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the authority request for the repetition
        TestAuthorityRequest testAuthorityRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateGetAuthorities(testAuthorityRequest);
    }

    /**
     * Method to test get authority by name end point
     * @param repetitionInfo
     * @throws Exception
     */
    @RepeatedTest(6)
    void testGetAuthorityByName(RepetitionInfo repetitionInfo) throws Exception {
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the authority request for the repetition
        TestAuthorityRequest testAuthorityRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateGetAuthorities(testAuthorityRequest);
    }

    /**
     * Method to test create authority end point
     * @param repetitionInfo
     */
    @RepeatedTest(5)
    void testCreateAuthority(RepetitionInfo repetitionInfo) throws Exception {
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the authority request for the repetition
        TestAuthorityRequest testAuthorityRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateCreateAuthority(testAuthorityRequest);
    }

    /**
     * Method to test update authority end point
     */
    @RepeatedTest(5)
    void testUpdateAuthority(RepetitionInfo repetitionInfo) throws Exception {
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the authority request for the repetition
        TestAuthorityRequest testAuthorityRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateUpdateAuthority(testAuthorityRequest);
    }

    /**
     * The method to validate the authority that was updated
     * @param testAuthorityRequest
     */
    private void validateUpdateAuthority(TestAuthorityRequest testAuthorityRequest) throws Exception {
        log.info("Test authority request:{}", testAuthorityRequest);
        // Get the expected authority list from the test data
        AuthorityList expectedAuthorityList = testAuthorityRequest.getExpectedAuthorityList();
        // Get user which will be used for authenticating with the service
        UserDto loggedInUser = testAuthorityRequest.getLoggedInUser();
        // The authority to be updated
        AuthorityDto inputAuthority = testAuthorityRequest.getAuthorityDtoRequest();
        String inputAuthorityAsString = objectMapper.writeValueAsString(inputAuthority);
        // URI to be called
        String uri = "/api/v1/zeus/security/authority/"+testAuthorityRequest.getAuthorityDtoRequest().getAuthorityId();

        if(testAuthorityRequest.isAuthException()){
            // This piece of code will be executed if the expected exception is an
            // authentication or authorization exception

            if(testAuthorityRequest.getHttpStatusCode().equals("401")){
                // Expecting an unauthorized exception
                mockMvc.perform(put(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputAuthorityAsString))
                        .andExpect(status().isUnauthorized())
                        .andReturn();
            }else{
                // Expecting a forbidden operation exception
                mockMvc.perform(put(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputAuthorityAsString))
                        .andExpect(status().isForbidden())
                        .andReturn();
            }
        }else {
            // This piece of code will be executed if no authentication/authorization exception is expected

            if(!testAuthorityRequest.isExceptionExpected()){
                AuthorityDto expectedAuthorityDto = expectedAuthorityList.getAuthorityDtos().get(0);
                doReturn(expectedAuthorityDto).when(authorityService).saveAuthority(any());
                MvcResult mvcResult = mockMvc.perform(put(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputAuthorityAsString))
                        .andExpect(status().isNoContent())
                        .andReturn();
                String apiResponseString = mvcResult.getResponse().getContentAsString();
                ZeusApiResponse zeusApiResponse = objectMapper.readValue(apiResponseString, ZeusApiResponse.class);
                // get the updated authority
                AuthorityDto authorityDto  =
                        objectMapper.convertValue(zeusApiResponse.getResponse(), AuthorityDto.class);
                log.info("Returned Authority:{}", authorityDto);
                expectedAuthorityDto.setAuthorityId(authorityDto.getAuthorityId());
                TestSecurityUtil.assertAuthorityDetails(expectedAuthorityDto, authorityDto);
            }else {

            }
        }
    }

    /**
     * The method to validate the authority that is being created
     * @param testAuthorityRequest
     */
    private void validateCreateAuthority(TestAuthorityRequest testAuthorityRequest) throws Exception {
        log.info("Test authority request:{}", testAuthorityRequest);
        // Get the expected authority list from the test data
        AuthorityDto expectedAuthorityDto = testAuthorityRequest.getExpectedAuthorityDto();
        // Get authority which will be used for authenticating with the service
        UserDto loggedInUser = testAuthorityRequest.getLoggedInUser();
        // The authority to be created
        AuthorityDto inputAuthority = testAuthorityRequest.getAuthorityDtoRequest();
        String inputAuthorityAsString = objectMapper.writeValueAsString(inputAuthority);
        // The URI to be called
        String uri = "/api/v1/zeus/security/authority";
        if(testAuthorityRequest.isAuthException()){
            // This piece of code will be executed if the expected exception is an
            if(testAuthorityRequest.getHttpStatusCode().equals("401")){
                // Expecting an unauthorized exception
                mockMvc.perform(post(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputAuthorityAsString))
                        .andExpect(status().isUnauthorized())
                        .andReturn();
            }else{
                // Expecting a forbidden operation exception
                mockMvc.perform(post(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputAuthorityAsString))
                        .andExpect(status().isForbidden())
                        .andReturn();
            }
        }else {
            if(!testAuthorityRequest.isExceptionExpected()){
                // This piece of code will be executed if no exception is expected
                doReturn(expectedAuthorityDto).when(authorityService).saveAuthority(any());
                MvcResult mvcResult = mockMvc.perform(post(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputAuthorityAsString))
                        .andExpect(status().isCreated())
                        .andReturn();
                String apiResponseString = mvcResult.getResponse().getContentAsString();
                ZeusApiResponse zeusApiResponse = objectMapper.readValue(apiResponseString, ZeusApiResponse.class);
                // get the created authority
                AuthorityDto authorityDto =
                        objectMapper.convertValue(zeusApiResponse.getResponse(), AuthorityDto.class);
                log.info("Returned Authority:{}", authorityDto);
                expectedAuthorityDto.setAuthorityId(authorityDto.getAuthorityId());
                TestSecurityUtil.assertAuthorityDetails(expectedAuthorityDto, authorityDto);
            }else {

            }
        }
    }

    /**
     * Makes the appropriate call and validates the data returned for getAllAuthorities
     * @param testAuthorityRequest
     */
    private void validateGetAuthorities(TestAuthorityRequest testAuthorityRequest) throws Exception {
        log.info("Test authority request:{}", testAuthorityRequest);
        // Get the expected authority list from the test data
        AuthorityList expectedAuthorityList = testAuthorityRequest.getExpectedAuthorityList();
        // Get user which will be used for authenticating with the service
        UserDto loggedInUser = testAuthorityRequest.getLoggedInUser();
        // The URI string will be populated based on the test case
        String uri = null;
        if(testAuthorityRequest.getAuthorityDtoRequest() == null){
            uri = "/api/v1/zeus/security/authority";
            doReturn(expectedAuthorityList).when(authorityService).getAllAuthorities();
        } else if (testAuthorityRequest.getAuthorityDtoRequest().getAuthorityId() != null){
            UUID authorityId = testAuthorityRequest.getAuthorityDtoRequest().getAuthorityId();
            // The URI if authority by authority id are to be fetched
            uri = "/api/v1/zeus/security/authority/authority-id/"+authorityId;
            // Expect authority not found exception if authority with authority id is not found
            if(testAuthorityRequest.isExceptionExpected() && !testAuthorityRequest.isAuthException()){
                doThrow(new AuthorityNotFoundException("Permission with authority id " + authorityId + " not found"))
                        .when(authorityService)
                        .getAuthorityById(any());
            }else{
                doReturn(expectedAuthorityList).when(authorityService).getAuthorityById(any());
            }
        }else {
            String authorityName = testAuthorityRequest.getAuthorityDtoRequest().getPermission();
            // The URI if authority by authority name are to fetched
            uri = "/api/v1/zeus/security/authority/authority-name/"+authorityName;
            // Expect authority not found exception if authority with authority name is not found
            if(testAuthorityRequest.isExceptionExpected() && !testAuthorityRequest.isAuthException()){
                doThrow(new AuthorityNotFoundException("Permission " + authorityName + " not found"))
                        .when(authorityService)
                        .getAuthorityByName(any());
            }else{
                doReturn(expectedAuthorityList).when(authorityService).getAuthorityByName(any());
            }
        }
        if(testAuthorityRequest.isAuthException()){
            // This piece of code will be executed if the expected exception is an
            // authentication or authorization exception
            // Determine which auth exception is to be expected
            if(testAuthorityRequest.getHttpStatusCode().equals("401")){
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
            if(!testAuthorityRequest.isExceptionExpected()){
                log.info("URI:{}", uri);
                MvcResult mvcResult = mockMvc.perform(get(uri)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
                String apiResponseString = mvcResult.getResponse().getContentAsString();
                ZeusApiResponse zeusApiResponse = objectMapper.readValue(apiResponseString, ZeusApiResponse.class);
                // get the list of authorities
                AuthorityList actualAuthorityList =
                        objectMapper.convertValue(zeusApiResponse.getResponse(), AuthorityList.class);
                assertEquals(expectedAuthorityList.getAuthorityDtos().size(), actualAuthorityList.getAuthorityDtos().size());
                TestSecurityUtil.assertAuthorityList(expectedAuthorityList, actualAuthorityList);
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
                assertEquals(testAuthorityRequest.getExceptionCode(), apiException.getExceptionCode());
                assertEquals(testAuthorityRequest.getExceptionMessage(), apiException.getExceptionMessage());
            }

        }
    }
}