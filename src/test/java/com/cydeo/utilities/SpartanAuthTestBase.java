package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanAuthTestBase {
    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://44.195.19.167:7000";//bu githubdaki ip ama o da çalışmadı day8 de :7000 portu
        //kullanılıyor postman de benim ip çalışmadı burad da çalışmadı doğal olarak githubdakini aldım
        //ama değişmedi!

    }
}
