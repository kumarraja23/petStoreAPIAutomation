package endPoints;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.User;

public class UserEndpoint {
	
	public static Response createNewUser(User userData) {
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(userData)
				.when()
					.post(Routes.userPost_url);
		return response;
	}
	
	public static Response getUserDetails(String username) {
		Response response = 
				given()
					.pathParam("username", username)
				.when()
					.get(Routes.userGet_url);
		return response;
	}
	
	public static Response updateUserDetails(String username, User userData) {
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.pathParam("username", username)
					.body(userData)
				.when()
					.put(Routes.userPut_url);
		return response;
	}
	
	public static Response deleteUser(String username) {
		Response response = 
				given()
					.pathParam("username", username)
				.when()
					.delete(Routes.userDelete_url);
		return response;
	}
	
}
