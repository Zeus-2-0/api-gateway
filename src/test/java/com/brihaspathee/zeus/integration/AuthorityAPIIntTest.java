package com.brihaspathee.zeus.integration;

import com.brihaspathee.zeus.domain.repository.AuthorityRepository;
import com.brihaspathee.zeus.domain.security.Authority;
import com.brihaspathee.zeus.exception.ApiException;
import com.brihaspathee.zeus.exception.ApiExceptionList;
import com.brihaspathee.zeus.security.model.*;
import com.brihaspathee.zeus.test.BuildTestData;
import com.brihaspathee.zeus.test.TestClass;
import com.brihaspathee.zeus.test.TestData;
import com.brihaspathee.zeus.test.TestMethod;
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
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 29, July 2022
 * Time: 4:47 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.integration
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorityAPIIntTest {

    /**
     * Repository to clean up after the tests are completed.
     */
    @Autowired
    private AuthorityRepository authorityRepository;

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
    @Value("classpath:com/brihaspathee/zeus/integration/AuthorityAPIIntTest.json")
    Resource resourceFile;

    /**
     * The test class with the user request
     */
    private TestClass<TestAuthorityRequest> authorityRequestTestClass;

    /**
     * The instance of the class that helps to build the data
     */
    @Autowired
    private BuildTestData<TestAuthorityRequest> buildTestData;

    /**
     * The list of test user requests
     */
    private List<TestAuthorityRequest> requests = new ArrayList<>();

    /**
     * The setup method is executed before each test method is executed
     * @param testInfo
     * @throws IOException
     */
    @BeforeEach
    void setUp(TestInfo testInfo) throws IOException {

        // Read the file information and convert to test class object
        authorityRequestTestClass = objectMapper.readValue(resourceFile.getFile(), new TypeReference<TestClass<TestAuthorityRequest>>() {});

        // Build the test data for the test method that is to be executed
        this.requests = buildTestData.buildData(testInfo.getTestMethod().get().getName(),this.authorityRequestTestClass);
    }

    /**
     * This method tests the get all authorities api end point
     * @param repetitionInfo
     */
    @RepeatedTest(5)
    @Order(1)
    void testGetAllAuthorities(RepetitionInfo repetitionInfo){
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the authority request for the repetition
        TestAuthorityRequest testAuthorityRequest = requests.get(repetitionInfo.getCurrentRepetition() - 1);
        validateAuthorityRequest(testAuthorityRequest);

    }

    /**
     * This method test the get by authority name end point
     * @param repetitionInfo
     */
    @RepeatedTest(6)
    @Order(2)
    void testGetAuthorityByName(RepetitionInfo repetitionInfo){
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the authority request for the repetition
        TestAuthorityRequest testAuthorityRequest = requests.get(repetitionInfo.getCurrentRepetition() - 1);
        validateAuthorityRequest(testAuthorityRequest);
    }

    /**
     * This method test the get by authority id end point
     * @param repetitionInfo
     */
    @RepeatedTest(6)
    @Order(3)
    void testGetAuthorityById(RepetitionInfo repetitionInfo){
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the authority request for the repetition
        TestAuthorityRequest testAuthorityRequest = requests.get(repetitionInfo.getCurrentRepetition() - 1);
        validateAuthorityRequest(testAuthorityRequest);
    }

    /**
     * This method tests the create Authority api end point
     * @param repetitionInfo
     */
    @RepeatedTest(5)
    @Order(4)
    void testCreateAuthority(RepetitionInfo repetitionInfo){
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the authority request for the repetition
        TestAuthorityRequest testAuthorityRequest = requests.get(repetitionInfo.getCurrentRepetition() - 1);
        validateCreateRequest(testAuthorityRequest);
    }

    /**
     * This method test the update Authority API Endpoint
     * @param repetitionInfo
     */
    @RepeatedTest(5)
    @Order(5)
    void testUpdateAuthority(RepetitionInfo repetitionInfo){
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the authority request for the repetition
        TestAuthorityRequest testAuthorityRequest = requests.get(repetitionInfo.getCurrentRepetition() - 1);
        validateUpdateRequest(testAuthorityRequest);
    }

    /**
     * Invokes and validates the update authority request end point
     * @param testAuthorityRequest
     */
    private void validateUpdateRequest(TestAuthorityRequest testAuthorityRequest){
        log.info("Test authority request:{}", testAuthorityRequest);
        // Get the expected authority list from the test data
        AuthorityList expectedAuthorityList = testAuthorityRequest.getExpectedAuthorityList();
        // Get user which will be used for authenticating with the service
        UserDto loggedInUser = testAuthorityRequest.getLoggedInUser();
        // The authority to be update
        AuthorityDto inputAuthority = testAuthorityRequest.getAuthorityDtoRequest();
        UUID authorityId = inputAuthority.getAuthorityId();
        // Get the authority from DB using repository so that it can be used to restore once the test is completed
        Authority authority = authorityRepository.findById(authorityId).orElseThrow();
        inputAuthority.setAuthorityId(null);
        // Create the headers and the HttpEntity to set the role that is to be created
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AuthorityDto> httpEntity = new HttpEntity<>(inputAuthority, headers);
        // Call the API Endpoint to update the role
        testRestTemplate
                .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                .put("/api/v1/zeus/security/authority/"+authorityId,httpEntity, ZeusApiResponse.class);
        if(!testAuthorityRequest.isExceptionExpected()){
            // This piece of code will be executed if no exception is expected
            // Retrieve the user that was updated
            String uri = "/api/v1/zeus/security/authority/authority-id/" + authorityId;
            ResponseEntity<ZeusApiResponse> responseEntity  = testRestTemplate
                    .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                    .getForEntity(uri, ZeusApiResponse.class);
            assertApiResponse(expectedAuthorityList, responseEntity);
            // Once the test is completed restore the authority to old state
            authorityRepository.save(authority);
        }else{
            // This piece of code will be executed if an exception is expected
            if(testAuthorityRequest.isAuthException()){
                // This piece of code will be executed if the expected exception is an
                // authentication or authorization exception
                ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                        .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                        .getForEntity("/api/v1/zeus/security/authority", ZeusApiResponse.class);
                assertEquals(testAuthorityRequest.getExceptionMessage(), responseEntity.getStatusCode().toString());
            }
        }
    }

    /**
     * Invokes and validates the create authority API Endpoint
     * @param testAuthorityRequest
     */
    private void validateCreateRequest(TestAuthorityRequest testAuthorityRequest){
        log.info("Test authority request:{}", testAuthorityRequest);
        // Get the expected authority from the test data
        AuthorityDto expectedAuthorityDto = testAuthorityRequest.getExpectedAuthorityDto();
        // Get user which will be used for authenticating with the service
        UserDto loggedInUser = testAuthorityRequest.getLoggedInUser();
        // The authority to be created
        AuthorityDto inputAuthority = testAuthorityRequest.getAuthorityDtoRequest();
        // Create the headers and the HttpEntity to set the authority that is to be created
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AuthorityDto> httpEntity = new HttpEntity<>(inputAuthority, headers);

        // Call the API Endpoint to create the role
        ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                .postForEntity("/api/v1/zeus/security/authority",httpEntity, ZeusApiResponse.class);
        if(!testAuthorityRequest.isExceptionExpected()){
            // This piece of code will be executed if no exception is expected

            // Get the response body from the response
            ZeusApiResponse actualResponse = responseEntity.getBody();
            // get the created authority
            AuthorityDto authorityDto =
                    objectMapper.convertValue(actualResponse.getResponse(), AuthorityDto.class);
            log.info("Returned Authority:{}", authorityDto);
            expectedAuthorityDto.setAuthorityId(authorityDto.getAuthorityId());
            assertAuthorityDetails(expectedAuthorityDto, authorityDto);
        }else{
            // This piece of code will be executed if an exception is expected
            if(testAuthorityRequest.isAuthException()){
                // This piece of code will be executed if the expected exception is an
                // authentication or authorization exception
                assertEquals(testAuthorityRequest.getExceptionMessage(), responseEntity.getStatusCode().toString());
            }
        }
    }

    /**
     * Calls and validates the get all authorities API Endpoint
     * @param testAuthorityRequest
     */
    private void validateAuthorityRequest(TestAuthorityRequest testAuthorityRequest){
        log.info("Test authority request:{}", testAuthorityRequest);
        // Get the expected authority list from the test data
        AuthorityList expectedAuthorityList = testAuthorityRequest.getExpectedAuthorityList();
        // Get user which will be used for authenticating with the service
        UserDto loggedInUser = testAuthorityRequest.getLoggedInUser();
        if (testAuthorityRequest.isAuthException()){
            // This piece of code will be executed if the expected exception is an
            // authentication or authorization exception
            ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                    .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                    .getForEntity("/api/v1/zeus/security/authority", ZeusApiResponse.class);
            assertEquals(testAuthorityRequest.getExceptionMessage(), responseEntity.getStatusCode().toString());
        }else {
            // This piece of code will be executed if no exception is expected
            // check if the request is to retrieve a specific authority by permission or authority id or to retrieve all authorities
            log.info("Authority dto request present:{}", testAuthorityRequest.getAuthorityDtoRequest());
            ResponseEntity<ZeusApiResponse> responseEntity  = null;
            if (testAuthorityRequest.getAuthorityDtoRequest() == null){
                log.info("came inside where the authority dto is null");
                // This means that we are trying to retrieve all authorities
                responseEntity  = testRestTemplate
                        .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                        .getForEntity("/api/v1/zeus/security/authority", ZeusApiResponse.class);
            }else {
                if (testAuthorityRequest.getAuthorityDtoRequest().getAuthorityId() != null){
                    // This means that we are trying to retrieve authority by id
                    UUID authorityId = testAuthorityRequest.getAuthorityDtoRequest().getAuthorityId();
                    responseEntity  = testRestTemplate
                            .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                            .getForEntity("/api/v1/zeus/security/authority/authority-id/"+authorityId, ZeusApiResponse.class);
                }else{
                    // This means that we are trying to retrieve authority by name
                    String authorityName = testAuthorityRequest.getAuthorityDtoRequest().getPermission();
                    responseEntity  = testRestTemplate
                            .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                            .getForEntity("/api/v1/zeus/security/authority/authority-name/"+authorityName, ZeusApiResponse.class);

                }
            }
            if(!testAuthorityRequest.isExceptionExpected()){
                log.info("Inside here");
                assertApiResponse(expectedAuthorityList, responseEntity);
            }else {
                // Get the response body from the response
                ZeusApiResponse actualResponse = responseEntity.getBody();
                // get the api exception list
                ApiExceptionList apiExceptionList = objectMapper.convertValue(actualResponse.getResponse(), ApiExceptionList.class);
                ApiException apiException = apiExceptionList.getExceptions().get(0);
                log.info("Actual Exception Code:{}", apiException.getExceptionCode());
                log.info("Actual Exception Message:{}", apiException.getExceptionMessage());
                assertEquals(testAuthorityRequest.getExceptionCode(), apiException.getExceptionCode());
                assertEquals(testAuthorityRequest.getExceptionMessage(), apiException.getExceptionMessage());
            }

        }
    }

    /**
     * Common method that asserts the API response received, if the api response is an authority list
     * @param expectedAuthorityList
     * @param responseEntity
     */
    private void assertApiResponse(AuthorityList expectedAuthorityList, ResponseEntity<ZeusApiResponse> responseEntity) {
        // Get the response body from the response
        ZeusApiResponse actualResponse = responseEntity.getBody();
        // get the list of authorities
        AuthorityList authorityList =
                objectMapper.convertValue(actualResponse.getResponse(), AuthorityList.class);
        log.info("Returned Authority list:{}", authorityList);
        // compare the size of the user list to check if they match
        assertEquals(expectedAuthorityList.getAuthorityDtos().size(), authorityList.getAuthorityDtos().size());
        assertAuthorityList(expectedAuthorityList, authorityList);
    }

    /**
     * Check if all authorities are present
     * @param expectedAuthorityList
     * @param actualAuthorityList
     */
    private void assertAuthorityList(AuthorityList expectedAuthorityList, AuthorityList actualAuthorityList){
        List<AuthorityDto> expectedAuthorityDtos = expectedAuthorityList.getAuthorityDtos();
        List<AuthorityDto> actualAuthorityDtos = actualAuthorityList.getAuthorityDtos();
        log.info("Ex Authorities:{}", expectedAuthorityDtos);
        log.info("Ac Authorities:{}", actualAuthorityDtos);
        expectedAuthorityDtos.stream().forEach( (expectedAuthorityDto -> {
            AuthorityDto actualAuthority = actualAuthorityDtos.stream().filter(
                            (actualAuthorityDto) -> {
                                return expectedAuthorityDto.getAuthorityId().equals(actualAuthorityDto.getAuthorityId());
                            })
                    .findFirst().orElse(AuthorityDto.builder()
                            .authorityId(UUID.randomUUID())
                            .permission("Random Authority")
                            .build());
            assertAuthorityDetails(expectedAuthorityDto, actualAuthority);
        }));
    }

    /**
     * Compare the authority details
     * @param expectedAuthorityDto
     * @param actualAuthority
     */
    private void assertAuthorityDetails(AuthorityDto expectedAuthorityDto, AuthorityDto actualAuthority){
        log.info("Expected Authority:{}", expectedAuthorityDto);
        log.info("Actual Authority:{}", actualAuthority);
        assertEquals(expectedAuthorityDto.getAuthorityId(), actualAuthority.getAuthorityId());
        assertEquals(expectedAuthorityDto.getPermission(), actualAuthority.getPermission());
    }

}
