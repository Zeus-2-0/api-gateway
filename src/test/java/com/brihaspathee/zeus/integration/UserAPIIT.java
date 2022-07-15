package com.brihaspathee.zeus.integration;

import com.brihaspathee.zeus.domain.repository.UserRepository;
import com.brihaspathee.zeus.test.TestClass;
import com.brihaspathee.zeus.test.TestData;
import com.brihaspathee.zeus.test.TestMethod;
import com.brihaspathee.zeus.web.model.UserDto;
import com.brihaspathee.zeus.web.model.UserList;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
public class UserAPIIT {

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
     * The user repository to perform any necessary clean up operations
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * The password encoder to encode the password of the users
     */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * The file that contains the test data
     */
    @Value("classpath:com/brihaspathee/zeus/integration/UserAPIIT.json")
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
        /**
         * Read the file information and convert to test class object
         */
        userRequestTestClass = objectMapper.readValue(resourceFile.getFile(), new TypeReference<TestClass<TestUserRequest>>() {});
        this.testInfo = testInfo;
        /**
         * Buid the test data for the test method that is to be executed
         */
        this.requests = buildTestData(testInfo.getTestMethod().get().getName());
    }

    /**
     * This method builds the test data that is needed for testing the method
     * @param methodName
     * @return
     */
    private List<TestUserRequest> buildTestData(String methodName){
        log.info("Test Class:{}", userRequestTestClass);
        /**
         * Get the test method that is matching the method name passed in as input
         */
        TestMethod<TestUserRequest> testMethod =
                userRequestTestClass.getTestMethods().stream()
                        .filter(userTestMethod -> userTestMethod.getTestMethodName().equals(methodName))
                        .findFirst()
                        .get();
        log.info("Test Method:{}", testMethod);
        /**
         * Get the test data for the method
         */
        List<TestData<TestUserRequest>> userTestData = testMethod.getTestData();
        /**
         * Create a list for all the requests and return the list populated with the requests for the method
         */
        List<TestUserRequest> requests = new ArrayList<>();
        requests.addAll(userTestData.stream().map(testData -> testData.getTestData()).collect(Collectors.toList()));
        return requests;
    }

    /**
     * This test method will be executed the number of times that is mentioned by
     * @RepeatedTest annotation
     *
     * This method tests the retrieval of users from the API
     * @param repetitionInfo
     */
    @RepeatedTest(1)
    void testGetUsers(RepetitionInfo repetitionInfo){

        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());
        /**
         * Retrieve the user request for the repetition
         */
        TestUserRequest testUserRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateGetUsers(testUserRequest);
    }

    /**
     * Makes the appropriate call and validates the data returned
     * @param testUserRequest
     */
    private void validateGetUsers(TestUserRequest testUserRequest) {

        /**
         * Get the expected user list from the test data
         */
        UserList expectedUserList = testUserRequest.getExpectedUserList();
        /**
         * Get user which will be used for authenticating with the service
         */
        UserDto loggedInUser = testUserRequest.getLoggedInUser();
        if(!testUserRequest.isExceptionExpected()){
            /**
             * This piece of code will be executed if no exception is expected
             */
            // check if the request is to retrieve a specific user by username of user id or to retrieve all users
            log.info("User dto request present:{}", testUserRequest.getUserDtoRequest());
            if(testUserRequest.getUserDtoRequest() == null){
                log.info("came inside");
                /**
                 * This means that we are trying to retrieve all users
                 */
                ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                        .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                        .getForEntity("/api/v1/zeus/security/user", ZeusApiResponse.class);
                /**
                 * Get the response body from the response
                 */
                ZeusApiResponse actualResponse = responseEntity.getBody();
                /**
                 * get the list of users
                 */
                UserList userList =
                        objectMapper.convertValue(actualResponse.getResponse(), UserList.class);
                log.info("Returned User list:{}", userList);
                /**
                 * compare the size of the user list to check if they match
                 */
                assertEquals(expectedUserList.getUserDtos().size(), userList.getUserDtos().size());
            }
        }else{
            /**
             * This piece of code will be executed if an exception is expected
             */
            if(testUserRequest.isAuthException()){
                /**
                 * This piece of code will be executed if the expected exception is an authentication
                 * or authorization exception
                 */
                ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                        .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                        .getForEntity("/api/v1/tp/user", ZeusApiResponse.class);
                assertEquals(testUserRequest.getExceptionMessage(), responseEntity.getStatusCode().toString());
            }
        }
    }
}
