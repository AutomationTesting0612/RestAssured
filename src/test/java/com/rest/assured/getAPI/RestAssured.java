package com.rest.assured.getAPI;

import com.rest.assured.util.Utlility;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class RestAssured extends Utlility {

    @Test
    public void getApi() throws IOException {

        given(). //precondition
        when(). ///action perform---get
            get(prop.getProperty("url")).
        then().
            assertThat().
             statusCode(200).
                and().
                body("MRData.CircuitTable.Circuits.circuitId",hasSize(20)).
                and().
                contentType("application/json");
    }



}
