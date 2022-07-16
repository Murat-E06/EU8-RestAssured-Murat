package com.cydeo.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpartanGetRequest {
    String baseUrl="http://3.87.219.59:8000";

    // Given Accept type app/json
    //When user go to base URL
    // And user send GET req. to api/spartans end point
    //Then response Content Type must be app/json


    @Test
    public void test1(){

        Response response= RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl+"/api/spartans");
        //
        System.out.println("response.statusCode() = " + response.statusCode());//should be 200

        //
        System.out.println("response.contentType() = " + response.contentType());//should be "application/json"

        //
        response.prettyPrint();

        //
        //
        Assertions.assertEquals(response.statusCode(),200);//this is testing part

        //
        Assertions.assertEquals(response.contentType(),"application/json");//also testing part
        //All of above test case %60-70 of API Testing said Cemal


    }



}
