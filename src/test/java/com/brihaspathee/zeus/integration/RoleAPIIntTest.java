package com.brihaspathee.zeus.integration;

import com.brihaspathee.zeus.domain.repository.RoleRepository;
import com.brihaspathee.zeus.domain.security.Role;
import com.brihaspathee.zeus.exception.ApiException;
import com.brihaspathee.zeus.exception.ApiExceptionList;
import com.brihaspathee.zeus.security.model.*;
import com.brihaspathee.zeus.test.BuildTestData;
import com.brihaspathee.zeus.test.TestClass;
import com.brihaspathee.zeus.web.request.TestRoleRequest;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 29, July 2022
 * Time: 4:46 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.integration
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleAPIIntTest {

    /**
     * Repository object to clean up data once the validation is completed
     */
    @Autowired
    private RoleRepository roleRepository;
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
    @Value("classpath:com/brihaspathee/zeus/integration/RoleAPIIntTest.json")
    Resource resourceFile;

    /**
     * The test class with the user request
     */
    private TestClass<TestRoleRequest> roleRequestTestClass;

    /**
     * The instance of the class that helps to build the data
     */
    @Autowired
    private BuildTestData<TestRoleRequest> buildTestData;

    /**
     * The list of test user requests
     */
    private List<TestRoleRequest> requests = new ArrayList<>();

    /**
     * The setup method is executed before each test method is executed
     * @param testInfo
     * @throws IOException
     */
    @BeforeEach
    void setUp(TestInfo testInfo) throws IOException {

        // Read the file information and convert to test class object
        roleRequestTestClass = objectMapper.readValue(resourceFile.getFile(), new TypeReference<TestClass<TestRoleRequest>>() {});

        // Build the test data for the test method that is to be executed
        this.requests = buildTestData.buildData(testInfo.getTestMethod().get().getName(), this.roleRequestTestClass);
    }

    /**
     * This method will test the getAllRoles method
     * @param repetitionInfo
     */
    @RepeatedTest(5)
    void testGetAllRoles(RepetitionInfo repetitionInfo){
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the role request for the repetition
        TestRoleRequest testRoleRequest = requests.get(repetitionInfo.getCurrentRepetition() - 1);
        validateGetRoles(testRoleRequest);
    }

    /**
     * This method tests the API Endpoint to get the role by role id
     * @param repetitionInfo
     */
    @RepeatedTest(6)
    void testGetRoleById(RepetitionInfo repetitionInfo){
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the role request for the repetition
        TestRoleRequest testRoleRequest = requests.get(repetitionInfo.getCurrentRepetition() - 1);
        validateGetRoles(testRoleRequest);
    }

    /**
     * This method tests the API Endpoint to get role by role name
     * @param repetitionInfo
     */
    @RepeatedTest(6)
    void testGetRoleByRoleName(RepetitionInfo repetitionInfo){
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the role request for the repetition
        TestRoleRequest testRoleRequest = requests.get(repetitionInfo.getCurrentRepetition() - 1);
        validateGetRoles(testRoleRequest);
    }

    /**
     * This method will test the create role end point
     * @param repetitionInfo
     */
    @RepeatedTest(5)
    void testCreateRole(RepetitionInfo repetitionInfo){
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the role request for the repetition
        TestRoleRequest testRoleRequest = requests.get(repetitionInfo.getCurrentRepetition() - 1);
        validateCreateRole(testRoleRequest);
    }

    /**
     * THis method will test the update role API endpoint
     * @param repetitionInfo
     */
    @RepeatedTest(5)
    void testUpdateRole(RepetitionInfo repetitionInfo){
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());

        // Retrieve the role request for the repetition
        TestRoleRequest testRoleRequest = requests.get(repetitionInfo.getCurrentRepetition() - 1);
        validateUpdateRole(testRoleRequest);
    }

    /**
     * This methods makes the appropriate call and validates the role that was created
     * @param testRoleRequest
     */
    private void validateCreateRole(TestRoleRequest testRoleRequest) {
        log.info("Test role request:{}", testRoleRequest);
        // Get the expected role list from the test data
        RoleDto expectedRoleDto = testRoleRequest.getExpectedRoleDto();
        // Get user which will be used for authenticating with the service
        UserDto loggedInUser = testRoleRequest.getLoggedInUser();
        // The role to be created
        RoleDto inputRole = testRoleRequest.getRoleDtoRequest();
        // Create the headers and the HttpEntity to set the role that is to be created
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RoleDto> httpEntity = new HttpEntity<>(inputRole, headers);

        // Call the API Endpoint to create the role
        ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                .postForEntity("/api/v1/zeus/security/role",httpEntity, ZeusApiResponse.class);
        if(!testRoleRequest.isExceptionExpected()){
            // This piece of code will be executed if no exception is expected

            // Get the response body from the response
            ZeusApiResponse actualResponse = responseEntity.getBody();
            // get the created role
            RoleDto roleDto =
                    objectMapper.convertValue(actualResponse.getResponse(), RoleDto.class);
            log.info("Returned Role:{}", roleDto);
            expectedRoleDto.setRoleId(roleDto.getRoleId());
            assertRoleDetails(expectedRoleDto, roleDto);
        }else{
            // This piece of code will be executed if an exception is expected
            if(testRoleRequest.isAuthException()){
                // This piece of code will be executed if the expected exception is an
                // authentication or authorization exception
                assertEquals(testRoleRequest.getExceptionMessage(), responseEntity.getStatusCode().toString());
            }
        }
    }

    /**
     * Invokes the update role endpoint and updates the role
     * @param testRoleRequest
     */
    private void validateUpdateRole(TestRoleRequest testRoleRequest){
        log.info("Test role request:{}", testRoleRequest);
        // Get the expected role list from the test data
        RoleList expectedRoleList = testRoleRequest.getExpectedRoleList();
        // Get user which will be used for authenticating with the service
        UserDto loggedInUser = testRoleRequest.getLoggedInUser();
        // The role to be update
        RoleDto inputRole = testRoleRequest.getRoleDtoRequest();
        // Create the headers and the HttpEntity to set the role that is to be created
        // Get the role entity that can be restored once the test is completed
        Role role = roleRepository.findById(inputRole.getRoleId()).orElseThrow();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RoleDto> httpEntity = new HttpEntity<>(inputRole, headers);

        // Call the API Endpoint to update the role
        testRestTemplate
                .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                .put("/api/v1/zeus/security/role/"+testRoleRequest.getRoleDtoRequest().getRoleId(),httpEntity, ZeusApiResponse.class);
        if(!testRoleRequest.isExceptionExpected()){
            // This piece of code will be executed if no exception is expected
            // Retrieve the user that was updated
            String uri = "/api/v1/zeus/security/role/role-id/" + testRoleRequest.getRoleDtoRequest().getRoleId();
            ResponseEntity<ZeusApiResponse> responseEntity  = testRestTemplate
                    .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                    .getForEntity(uri, ZeusApiResponse.class);
            // Get the response body from the response
            ZeusApiResponse actualResponse = responseEntity.getBody();
            // get the list of roles
            RoleList roleList =
                    objectMapper.convertValue(actualResponse.getResponse(), RoleList.class);
            log.info("Returned Role list:{}", roleList);
            // compare the size of the user list to check if they match
            assertEquals(expectedRoleList.getRoleDtos().size(), roleList.getRoleDtos().size());
            assertRoleList(expectedRoleList, roleList);
            // Restore the role to its old state
            roleRepository.save(role);
        }else{
            // This piece of code will be executed if an exception is expected
            if(testRoleRequest.isAuthException()){
                // This piece of code will be executed if the expected exception is an
                // authentication or authorization exception
                ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                        .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                        .getForEntity("/api/v1/zeus/security/role", ZeusApiResponse.class);
                assertEquals(testRoleRequest.getExceptionMessage(), responseEntity.getStatusCode().toString());
            }
        }
    }

    /**
     * Makes the appropriate call and validates the data returned for getAllRoles
     * @param testRoleRequest
     */
    private void validateGetRoles(TestRoleRequest testRoleRequest) {
        log.info("Test role request:{}", testRoleRequest);
        // Get the expected rple list from the test data
        RoleList expectedRoleList = testRoleRequest.getExpectedRoleList();
        // Get user which will be used for authenticating with the service
        UserDto loggedInUser = testRoleRequest.getLoggedInUser();
        if(testRoleRequest.isAuthException()){
            // This piece of code will be executed if the expected exception is an
            // authentication or authorization exception
            ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                    .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                    .getForEntity("/api/v1/zeus/security/role", ZeusApiResponse.class);
            assertEquals(testRoleRequest.getExceptionMessage(), responseEntity.getStatusCode().toString());
        }else {
            // This piece of code will be executed if no auth exception is expected
            // check if the request is to retrieve a specific role by role or role id or to retrieve all users
            log.info("Role dto request present:{}", testRoleRequest.getRoleDtoRequest());
            ResponseEntity<ZeusApiResponse> responseEntity = null;
            if (testRoleRequest.getRoleDtoRequest() == null){
                log.info("came inside where the role dto is null");
                // This means that we are trying to retrieve all roles
                responseEntity  = testRestTemplate
                        .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                        .getForEntity("/api/v1/zeus/security/role", ZeusApiResponse.class);
                // assertApiResponse(expectedRoleList, responseEntity);
            }else{
                if(testRoleRequest.getRoleDtoRequest().getRoleId() != null){
                    // This means that we are trying to retrieve role by id
                    UUID roleId = testRoleRequest.getRoleDtoRequest().getRoleId();
                    responseEntity  = testRestTemplate
                            .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                            .getForEntity("/api/v1/zeus/security/role/role-id/"+roleId, ZeusApiResponse.class);
                    // assertApiResponse(expectedRoleList, responseEntity);
                }else{
                    // This means that we are trying to retrieve role by name
                    String roleName = testRoleRequest.getRoleDtoRequest().getRoleName();
                    responseEntity  = testRestTemplate
                            .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                            .getForEntity("/api/v1/zeus/security/role/role-name/"+roleName, ZeusApiResponse.class);
                    // assertApiResponse(expectedRoleList, responseEntity);
                }
            }
            if(!testRoleRequest.isExceptionExpected()){
                assertApiResponse(expectedRoleList, responseEntity);
            }else{
                // This code is executed when there is an exception that is other than auth exception is expected
                // Get the response body from the response
                ZeusApiResponse actualResponse = responseEntity.getBody();
                // get the api exception list
                ApiExceptionList apiExceptionList = objectMapper.convertValue(actualResponse.getResponse(), ApiExceptionList.class);
                ApiException apiException = apiExceptionList.getExceptions().get(0);
                log.info("Actual Exception Code:{}", apiException.getExceptionCode());
                log.info("Actual Exception Message:{}", apiException.getExceptionMessage());
                assertEquals(testRoleRequest.getExceptionCode(), apiException.getExceptionCode());
                assertEquals(testRoleRequest.getExceptionMessage(), apiException.getExceptionMessage());
            }
        }
    }

    /**
     * Common method that asserts the API response received, if the api response is a role list
     * @param expectedRoleList
     * @param responseEntity
     */
    private void assertApiResponse(RoleList expectedRoleList, ResponseEntity<ZeusApiResponse> responseEntity) {
        // Get the response body from the response
        ZeusApiResponse actualResponse = responseEntity.getBody();
        // get the list of roles
        RoleList roleList =
                objectMapper.convertValue(actualResponse.getResponse(), RoleList.class);
        log.info("Returned Role list:{}", roleList);
        // compare the size of the user list to check if they match
        assertEquals(expectedRoleList.getRoleDtos().size(), roleList.getRoleDtos().size());
        assertRoleList(expectedRoleList, roleList);
    }

    /**
     * Check if all roles are present
     * @param expectedRoleList
     * @param actualRoleList
     */
    private void assertRoleList(RoleList expectedRoleList, RoleList actualRoleList){
        List<RoleDto> expectedRoleDtos = expectedRoleList.getRoleDtos();
        List<RoleDto> actualRoleDtos = actualRoleList.getRoleDtos();
        log.info("Ex Roles:{}", expectedRoleDtos);
        log.info("Ac Roles:{}", actualRoleDtos);
        expectedRoleDtos.stream().forEach( (expectedRoleDto -> {
            RoleDto actualRole = actualRoleDtos.stream().filter(
                            (actualRoleDto) -> {
                                return expectedRoleDto.getRoleId().equals(actualRoleDto.getRoleId());
                            })
                    .findFirst().orElse(RoleDto.builder()
                            .roleId(UUID.randomUUID())
                            .roleName("Random Role")
                            .build());
            assertRoleDetails(expectedRoleDto, actualRole);
        }));
    }

    /**
     * Compare the role details
     * @param expectedRoleDto
     * @param actualRole
     */
    private void assertRoleDetails(RoleDto expectedRoleDto, RoleDto actualRole){
        log.info("Expected Role:{}", expectedRoleDto);
        log.info("Actual User:{}", actualRole);
        assertEquals(expectedRoleDto.getRoleId(), actualRole.getRoleId());
        assertEquals(expectedRoleDto.getRoleName(), actualRole.getRoleName());
        assertAuthorityList(expectedRoleDto.getAuthorityDtos(), actualRole.getAuthorityDtos());
    }

    /**
     * Check if all authorities are present
     * @param expectedAuthorities
     * @param actualAuthorities
     */
    private void assertAuthorityList(List<AuthorityDto> expectedAuthorities, List<AuthorityDto> actualAuthorities){
        expectedAuthorities.stream().forEach( (expectedAuthorityDto -> {
            AuthorityDto actualAuthority = actualAuthorities.stream().filter(
                            (actualAuthorityDto) -> {
                                return expectedAuthorityDto.getAuthorityId().equals(actualAuthorityDto.getAuthorityId());
                            })
                    .findFirst().orElse(AuthorityDto.builder()
                            .authorityId(UUID.randomUUID())
                            .permission("Random Permission")
                            .build());
            assertAuthorityDetails(expectedAuthorityDto, actualAuthority);
        }));
    }

    /**
     * Compare the authority details
     * @param expectedAuthorityDto
     * @param actualAuthorityDto
     */
    private void assertAuthorityDetails(AuthorityDto expectedAuthorityDto, AuthorityDto actualAuthorityDto){
        assertEquals(expectedAuthorityDto.getAuthorityId(), actualAuthorityDto.getAuthorityId());
        log.info("Authority id:{}", expectedAuthorityDto.getAuthorityId());
        assertEquals(expectedAuthorityDto.getPermission(), actualAuthorityDto.getPermission());
    }
}
