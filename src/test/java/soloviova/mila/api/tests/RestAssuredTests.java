package soloviova.mila.api.tests;

import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers.*;

import io.restassured.parsing.Parser;
import org.junit.Test;

import static io.restassured.RestAssured.registerParser;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class RestAssuredTests {

    private static final String GENERAL_URL = "https://httpbin.org";

    @Test
    public void restAssuredTestSample() {
        final String url = GENERAL_URL + "/get";
        when()
                .get(url)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("url", equalTo(url));
    }

    @Test
    public void restAssuredTestSample2() {
        when()
                .get(GENERAL_URL + "/uuid")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("uuid", notNullValue())
                .and()
                .body("uuid", isA(String.class));
    }
}
