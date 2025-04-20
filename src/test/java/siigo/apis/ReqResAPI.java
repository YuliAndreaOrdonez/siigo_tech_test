package siigo.apis;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
import siigo.models.User;

import java.util.List;
import java.util.Objects;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;

public class ReqResAPI {


    private String baseURL;
    public void setBaseURL(String baseURL) {
        given().baseUri(baseURL).basePath("/users").contentType(ContentType.JSON);
        this.baseURL = baseURL;
    }

    @Step("User get all the users")
    public void getAllUsers() {
        when().get("").then().statusCode(200);
        List<User> users = SerenityRest.lastResponse().jsonPath().getList("data", User.class);

        assertThat(users).allMatch(user -> !user.getFirstName().isEmpty());
    }

    @Step("get user details")
    public void getUserDetails() {
        List<User> users = SerenityRest.lastResponse().jsonPath().getList("data", User.class);
        when().get("/"+users.get(0).getId()).then().statusCode(200);

        User userData = SerenityRest.lastResponse().jsonPath().getObject("data", User.class);
        assertThat(userData).matches(user -> !user.getFirstName().isEmpty() && Objects.equals(user.getId(), users.get(0).getId()));
    }


    public void changeUserName(String userName) {
        User userData = SerenityRest.lastResponse().jsonPath().getObject("data", User.class);
        userData.setFirstName(userName);
        ValidatableResponse response = given()
                .baseUri(this.baseURL)
                .basePath("/users")
                .body(userData)
                .pathParam("userId", userData.getId())
                .when()
                .put("/{userId}")
                .then()
                .statusCode(200);

        response.body("first_name", not(emptyString()));
    }

    public void deleteUser() {
        String id = SerenityRest.lastResponse().jsonPath().getString("id");
        given()
                .baseUri(this.baseURL)
                .basePath("/users").
                when()
                .delete("/"+id)
                .then().statusCode(204);
    }
}
