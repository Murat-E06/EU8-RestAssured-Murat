package com.cydeo.Day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiWithJsonPath extends HRTestBase {

    @DisplayName("GET request to Countries ")
    @Test
    public void test1() {

        Response response = get("/countries");

        //get second country name with JsonPath

        //to use JsonPath we assign response to JsonPath
        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country name
        List<String> allCountryIds = jsonPath.getList("items.country_id");
        System.out.println(allCountryIds);

        //get all country names where their region id is equal to 3
        List<Object> countryId3 = jsonPath.getList("items.findAll {it.region_id==3}.country_name");
        //{it.region_id==3}-->if stat.(it=each) country_name-->their value
        System.out.println(countryId3);
    }

    @DisplayName("GET requesto /employees with query param")
    @Test
    public void test2(){
        //we added limit query param to get 107 employees
        Response response = given().queryParam("limit", 107)
                .when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

        //get me all email of employees who is working as IT_PROG
        List<String> employeeITProgs = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        //id==\"IT_PROG\" sayı olmadığı için \ \ kullandık
        System.out.println(employeeITProgs);

        //get me first name of employees who is making more than 10000
        List<String> empNames = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println(empNames);

        //get the max salary first_name
        String kingFirstName = jsonPath.getString("items.max {it.salary}.first_name");
        String kingNameWithPathMethod = response.path("items.max {it.salary}.first_name");
        System.out.println("kingFirstName = " + kingFirstName);
        System.out.println("kingNameWithPathMethod = " + kingNameWithPathMethod);
    }

}
