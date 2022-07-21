package com.cydeo.utilities;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class HRTestBase {
    @BeforeAll
    public static void init(){

        //save baseurl inside this variable so that we dont need to type each http method
        baseURI = "http://3.87.219.59:1000/ords/hr";

        //get ip adress from configurationsReader

    }


}
