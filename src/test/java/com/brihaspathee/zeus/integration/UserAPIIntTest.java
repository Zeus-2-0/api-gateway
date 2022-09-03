package com.brihaspathee.zeus.integration;

import com.brihaspathee.zeus.domain.repository.UserRepository;
import com.brihaspathee.zeus.domain.security.User;
import com.brihaspathee.zeus.exception.ApiException;
import com.brihaspathee.zeus.exception.ApiExceptionList;
import com.brihaspathee.zeus.security.model.UserDto;
import com.brihaspathee.zeus.security.model.UserList;
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
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 14, July 2022
 * Time: 2:08 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.integration
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserAPIIntTest {

    /**
     * The repository object to clean up the data once test is completed
     */
    @Autowired
    private UserRepository userRepository;
    /**
     * Object mapper to read the file and convert it to an object
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Rest template to call the api endpoint
     */
    @Autowired
    private TestRestTemplate testRestTemplate;

    /**
     * The file that contains the test data
     */
    @Value("classpath:com/brihaspathee/zeus/integration/UserAPIIntTest.json")
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
     * The setup method is executed before each test method is executed
     * @param testInfo
     * @throws IOException
     */
    @BeforeEach
    void setUp(TestInfo testInfo) throws IOException {

        // Read the file information and convert to test class object
        userRequestTestClass = objectMapper.readValue(resourceFile.getFile(), new TypeReference<TestClass<TestUserRequest>>() {});
        this.testInfo = testInfo;

        // Build the test data for the test method that is to be executed
        // this.requests = buildTestData(testInfo.getTestMethod().get().getName());
        // this.requests = buildTestData(testInfo.getTestMethod().get().getName());
        this.requests = buildTestData.buildData(testInfo.getTestMethod().get().getName(), this.userRequestTestClass);
    }

    /**
     * This test method will be executed the number of times that is mentioned by
     * @RepeatedTest annotation
     *
     * This method tests the retrieval of users from the API
     * @param repetitionInfo
     */
    @RepeatedTest(5)
    void testGetUsers(RepetitionInfo repetitionInfo){

        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the user request for the repetition
        TestUserRequest testUserRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateGetUsers(testUserRequest);
    }

    /**
     * This method tests the retrieval of a user using username
     * @param repetitionInfo
     */
    @RepeatedTest(6)
    void testGetUserByUsername(RepetitionInfo repetitionInfo){
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the user request for the repetition
        TestUserRequest testUserRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateGetUsers(testUserRequest);
    }

    /**
     * This method tests the retrieval of a user using username
     * @param repetitionInfo
     */
    @RepeatedTest(6)
    void testGetUserByUserId(RepetitionInfo repetitionInfo){
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the user request for the repetition
        TestUserRequest testUserRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateGetUsers(testUserRequest);
    }

    /**
     * This method tests the creation of a user
     * @param repetitionInfo
     */
    @RepeatedTest(6)
    void testCreateUser(RepetitionInfo repetitionInfo){
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the user request for the repetition
        TestUserRequest testUserRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateCreateUser(testUserRequest);
    }

    /**
     * This method tests the update of a user
     * @param repetitionInfo
     */
    @RepeatedTest(5)
    void testUpdateUser(RepetitionInfo repetitionInfo){
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the user request for the repetition
        TestUserRequest testUserRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateUpdateUser(testUserRequest);
    }

    /**
     * The method to validate the user that is being created
     * @param testUserRequest
     */
    private void validateCreateUser(TestUserRequest testUserRequest) {
        log.info("Test user request:{}", testUserRequest);
        // Get the expected user list from the test data
        UserDto expectedUserDto = testUserRequest.getExpectedUserDto();
        // Get user which will be used for authenticating with the service
        UserDto loggedInUser = testUserRequest.getLoggedInUser();
        // The user to be created
        UserDto inputUser = testUserRequest.getUserDtoRequest();
        // Create the headers and the HttpEntity to set the user that is to be created
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(inputUser, headers);
        // check if the request is to retrieve a specific user by username of user id or to retrieve all users
        log.info("User dto request present:{}", testUserRequest.getUserDtoRequest());

        // Call the API Endpoint to create the user
        ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                .postForEntity("/api/v1/zeus/security/user",httpEntity, ZeusApiResponse.class);
        if(testUserRequest.isAuthException()){
            // This piece of code will be executed if the expected exception is an
            // authentication or authorization exception
            assertEquals(testUserRequest.getExceptionMessage(), responseEntity.getStatusCode().toString());
        }else {
            // Get the response body from the response
            ZeusApiResponse actualResponse = responseEntity.getBody();
            if(!testUserRequest.isExceptionExpected()){
                // This piece of code will be executed if no exception is expected

                // get the created user
                UserDto userDto =
                        objectMapper.convertValue(actualResponse.getResponse(), UserDto.class);
                log.info("Returned User:{}", userDto);
                expectedUserDto.setUserId(userDto.getUserId());
                TestSecurityUtil.assertUserDetails(expectedUserDto, userDto,true);
            }else {
                // This piece of code will be executed if any exception other than authentication/authorization exception
                // is expected

                // get the api exception list
                ApiExceptionList apiExceptionList = objectMapper.convertValue(actualResponse.getResponse(), ApiExceptionList.class);
                log.info("Api Exception List:{}", apiExceptionList);
                ApiException apiException = apiExceptionList.getExceptions().get(0);
                log.info("Actual Exception Code:{}", apiException.getExceptionCode());
                log.info("Actual Exception Message:{}", apiException.getExceptionMessage());
                assertEquals(testUserRequest.getExceptionCode(), apiException.getExceptionCode());
                assertEquals(testUserRequest.getExceptionMessage(), apiException.getExceptionMessage());
            }
        }
    }

    /**
     * The method to validate the user that was updated
     * @param testUserRequest
     */
    private void validateUpdateUser(TestUserRequest testUserRequest){
        log.info("Test user request:{}", testUserRequest);
        // Get the expected user list from the test data
        UserList expectedUserList = testUserRequest.getExpectedUserList();
        // Get user which will be used for authenticating with the service
        UserDto loggedInUser = testUserRequest.getLoggedInUser();
        // The user to be updated
        UserDto inputUser = testUserRequest.getUserDtoRequest();

        // Create the headers and the HttpEntity to set the user that is to be created
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(inputUser, headers);

        if(testUserRequest.isAuthException()){
            // This piece of code will be executed if the expected exception is an
            // authentication or authorization exception
            ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                    .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                    .getForEntity("/api/v1/zeus/security/user", ZeusApiResponse.class);

            assertEquals(testUserRequest.getExceptionMessage(), responseEntity.getStatusCode().toString());
        }else {
            // This piece of code will be executed if no authentication/authorization exception is expected

            if(!testUserRequest.isExceptionExpected()){
                // Get the user before the update so that it can be restored once the test is completed
                User user = userRepository.findById(inputUser.getUserId()).orElseThrow();
                // Make the API call to make the update
                testRestTemplate
                    .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                    .put("/api/v1/zeus/security/user/"+testUserRequest.getUserDtoRequest().getUserId(),httpEntity, ZeusApiResponse.class);
                 //Retrieve the user that was updated
                String uri = "/api/v1/zeus/security/user/user-id/" + testUserRequest.getUserDtoRequest().getUserId();
                ResponseEntity<ZeusApiResponse> responseEntity  = testRestTemplate
                    .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                    .getForEntity(uri, ZeusApiResponse.class);
                // Get the response body from the response
                ZeusApiResponse actualResponse = responseEntity.getBody();
                // get the list of users
                UserList userList =
                        objectMapper.convertValue(actualResponse.getResponse(), UserList.class);
                log.info("Returned User list:{}", userList);
                // compare the size of the user list to check if they match
                assertEquals(expectedUserList.getUserDtos().size(), userList.getUserDtos().size());
                TestSecurityUtil.assertUserList(expectedUserList, userList,true);
                // Revert the user back to their old state
                userRepository.save(user);
            }
        }
    }

    /**
     * Makes the appropriate call and validates the data returned for getUsers
     * @param testUserRequest
     */
    private void validateGetUsers(TestUserRequest testUserRequest) {
        log.info("Test user request:{}", testUserRequest);
        // Get the expected user list from the test data
        UserList expectedUserList = testUserRequest.getExpectedUserList();
        // Get user which will be used for authenticating with the service
        UserDto loggedInUser = testUserRequest.getLoggedInUser();
        if(testUserRequest.isAuthException()){
            // This piece of code will be executed if the expected exception is an
            // authentication or authorization exception
            ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                    .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                    .getForEntity("/api/v1/zeus/security/user", ZeusApiResponse.class);
            assertEquals(testUserRequest.getExceptionMessage(), responseEntity.getStatusCode().toString());
        } else {
            // This piece of code will be executed if no auth exception is expected
            // check if the request is to retrieve a specific user by username of user id or to retrieve all users
            log.info("User dto request present:{}", testUserRequest.getUserDtoRequest());
            ResponseEntity<ZeusApiResponse> responseEntity = null;
            if(testUserRequest.getUserDtoRequest() == null){
                log.info("came inside where the user do is null");
                // This means that we are trying to retrieve all users
                responseEntity  = testRestTemplate
                        .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                        .getForEntity("/api/v1/zeus/security/user", ZeusApiResponse.class);
                // assertApiResponse(expectedUserList, responseEntity);
            }else{
                log.info("came inside where the user do is not null");
                String uri = "";
                if(testUserRequest.getUserDtoRequest().getUserId() != null){
                    // Fetch the user using user id
                    uri = "/api/v1/zeus/security/user/user-id/" + testUserRequest.getUserDtoRequest().getUserId();
                }else if (testUserRequest.getUserDtoRequest().getUsername() !=null){
                    // Fetch the user using username
                    uri = "/api/v1/zeus/security/user/username/" + testUserRequest.getUserDtoRequest().getUsername();
                }
                responseEntity  = testRestTemplate
                        .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                        .getForEntity(uri, ZeusApiResponse.class);
                // assertApiResponse(expectedUserList, responseEntity);
            }
            if(!testUserRequest.isExceptionExpected()){
                assertApiResponse(expectedUserList, responseEntity);
            }else{
                // This code is executed when there is an exception that is other than auth exception is expected
                // Get the response body from the response
                ZeusApiResponse actualResponse = responseEntity.getBody();
                // get the api exception list
                ApiExceptionList apiExceptionList = objectMapper.convertValue(actualResponse.getResponse(), ApiExceptionList.class);
                ApiException apiException = apiExceptionList.getExceptions().get(0);
                assertEquals(testUserRequest.getExceptionCode(), apiException.getExceptionCode());
                assertEquals(testUserRequest.getExceptionMessage(), apiException.getExceptionMessage());
            }
        }
    }

    private void assertApiResponse(UserList expectedUserList, ResponseEntity<ZeusApiResponse> responseEntity) {
        // Get the response body from the response
        ZeusApiResponse actualResponse = responseEntity.getBody();
        // get the list of users
        UserList userList =
                objectMapper.convertValue(actualResponse.getResponse(), UserList.class);
        log.info("Returned User list:{}", userList);
        // compare the size of the user list to check if they match
        assertEquals(expectedUserList.getUserDtos().size(), userList.getUserDtos().size());
        TestSecurityUtil.assertUserList(expectedUserList, userList,true);
    }

}
