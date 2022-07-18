package com.cydeo.Day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1(){

        Response response= given().accept(ContentType.JSON)
                                .and().queryParam("q","{\"region_id\":2}")
                         .when()
                                 .get("/countries");

        assertEquals(200,response.statusCode());

        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        System.out.println("response.path(\"hashMore\") = " + response.path("hashMore"));

        String firstCountryId = response.path("items[0].country_id");//postman de İtems dan bir içerde
        //country id parent to sibling olayı araya . koyduk o yüzden
        System.out.println("firstCountryId = " + firstCountryId);

        //print second country name
        System.out.println("response.path(\"items[1].country_name\") = " + response.path("items[1].country_name"));
        //Brasil 2. ülke

        //print "http://52.207.61.129:1000/ords/hr/countries/CA"
        String thirdHref = response.path("items[2].links[0].href");//link bir array arrayin içindeki ilk href yani linki
        //çağırdık orada 2. element ama 1. link dolayısıyla link için index no:0 oluyor!!
        System.out.println("thirdHref = " + thirdHref);

        //get me all country names
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //assert all region id=2
        List<Integer>allRegionIDs=response.path("items.region_id");

        for (Integer regionID : allRegionIDs) {
            System.out.println("regionID = " + regionID);
            assertEquals(2,regionID);
        }
    }

        @DisplayName("GET request to /employees with Query Param")
        @Test
        public void test2() {
            Response response = given().accept(ContentType.JSON)
                    .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                    .when().get("/employees");

            assertEquals(200, response.statusCode());
            assertEquals("application/json", response.header("Content-Type"));
            assertTrue(response.body().asString().contains("IT_PROG"));

            //make sure we have only IT_PROG as a job_id
            List<String> allJobIDs = response.path("items.job_id");

            for (String jobID : allJobIDs) {
                System.out.println("jobID = " + jobID);
                assertEquals("IT_PROG", jobID);
            }
        }
}
