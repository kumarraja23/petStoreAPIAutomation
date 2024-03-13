package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import endPoints.UserEndpoint;
import io.restassured.response.Response;
import payloads.User;

public class TestCasesForUser {
	User userObj;
	Faker faker;
	
	@BeforeClass
	public void dataSetUp() {
		faker = new Faker();
		userObj = new User();
		
		userObj.setId(faker.idNumber().hashCode());
		userObj.setUsername(faker.name().username());
		userObj.setFirstName(faker.name().firstName());
		userObj.setLastName(faker.name().lastName());
		userObj.setEmail(faker.internet().safeEmailAddress());
		userObj.setPassword(faker.internet().password());
		userObj.setPhone(faker.phoneNumber().cellPhone());
		
	}
	
	@Test
	public void userCreationTest() {
		Response response = UserEndpoint.createNewUser(userObj);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 1)
	public void getUserDetailsTest() {
		Response response = UserEndpoint.getUserDetails(userObj.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().get("username").toString(),userObj.getUsername()) ;
	}
	
	@Test(priority = 2)
	public void updateUserDetailsTest() {
		userObj.setFirstName(faker.name().firstName());
		userObj.setLastName(faker.name().lastName());
		
		Response response = UserEndpoint.updateUserDetails(userObj.getUsername(), userObj);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response responseAfterUpdations = UserEndpoint.getUserDetails(userObj.getUsername());
		responseAfterUpdations.then().log().all();
		Assert.assertEquals(responseAfterUpdations.getStatusCode(), 200);

	}

}
