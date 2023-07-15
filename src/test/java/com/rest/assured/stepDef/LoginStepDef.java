package com.rest.assured.stepDef;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class LoginStepDef {

    Response response;
    public HashMap<Object,Object> map=new HashMap<Object,Object>();
    @Given("^the valid endpoint to fetch users$")
    public void theValidEndpointToFetchUsers() {
        RestAssured.baseURI="https://reqres.in/";
        RestAssured.basePath="/api/users";
    }

    @When("^the request is send to server with page number as \"([^\"]*)\"$")
    public void theRequestIsSendToServerWithPageNumberAs(String pageNumber) {
       response=  given().
            queryParam("page",pageNumber)
                .when().get().then().contentType(ContentType.JSON).extract().response();

    }

    @Then("^validate the response of first user record having email as \"([^\"]*)\"$")
    public void validateTheResponseOfFirstUserRecordHavingEmailAs(String email) throws Throwable {
        String userEmail = response.path("data[0].email");
        Assert.assertEquals(email, userEmail);
    }

    @When("^the request is send to the server$")
    public void theRequestIsSendToTheServer() {
        response =given().
                contentType(ContentType.JSON).
                body(map).
                when().
                post().
                then().
                statusCode(201).
                contentType(ContentType.JSON).
                extract().
                response();

    }

    @Given("^the valid endpoint with payload to create user$")
    public void theValidEndpointWithPayloadToCreateUser() {

        RestAssured.baseURI="https://reqres.in/";
        RestAssured.basePath="/api/users";

        map.put("id",1);
        map.put("email", "akhilsharma@gmail.com");
        map.put("first_name","Akhil");
        map.put("last_name","Sharma");
        map.put("Avatar","https://reqres.in/img/faces/1-image.jpg");
    }

    @Then("^the new user must be created with name as \"([^\"]*)\"$")
    public void theNewUserMustBeCreatedWithNameAs(String username) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(201,actualStatusCode);
        String actualUsername=response.path("name");
        Assert.assertEquals(actualUsername,username);
            }

//    @When("^the request is send to the server for PUT operator \"([^\"]*)$")
//    public void theRequestIsSendToTheServerForPUTOperator(String User) {
//        response=given().contentType(ContentType.JSON).body(map).put("/users/{userId}", userId).then().statusCode(200).contentType(ContentType.JSON).extract().response();
//    }

    @Given("^the valid endpoint with payload to update user$")
    public void theValidEndpointWithPayloadToUpdateUser() {
        RestAssured.baseURI="https://reqres.in/";
        RestAssured.basePath="/api/users";
        map.put("first_name","Test");

    }

    @When("^the request is send to the server for PUT operator \"([^\"]*)\"$")
    public void theRequestIsSendToTheServerForPUTOperator(String userId) throws Throwable {
        response=given().contentType(ContentType.JSON).body(map).put("/users/{userId}", userId).then().statusCode(200).contentType(ContentType.JSON).extract().response();

    }

    @Then("^the new user must be created with name as \"([^\"]*)\", \"([^\"]*)\"$")
    public void theNewUserMustBeCreatedWithNameAs(String username, String expectedStatusCode) throws Throwable {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode,String.valueOf(actualStatusCode));
        String actualContentType=response.contentType();
        Assert.assertEquals(actualContentType,"application/json; charset=utf-8");
        String actualUserName= response.body().path("first_name");
        Assert.assertEquals(username,actualUserName);
    }
}
