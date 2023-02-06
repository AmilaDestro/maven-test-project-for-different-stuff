package soloviova.mila.api.tests;

import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import soloviova.mila.main.author.Author;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

/**
 * Contains some RestAssured tests
 */
@Slf4j
public class RestAssuredTests {

    private static final String HTTPBIN_ORG = "https://httpbin.org";
    private static final String AZURE_WEBSITES_NET = "https://fakerestapi.azurewebsites.net/api/v1";

    @Test
    public void restAssuredTestSample() {
        final String url = HTTPBIN_ORG + "/get";
        log.info("Sending GET request to {}", url);

        final var response = given()
                    .when()
                        .get(url)
                            .then()
                                .statusCode(200)
                                .contentType(ContentType.JSON)
                                .body("headers.Host", equalTo("httpbin.org"))
                                .body("url", equalTo(url));
        response.log().body(true);
    }

    @Test
    public void restAssuredTestSample2() {
        final String url = HTTPBIN_ORG + "/uuid";
        log.info("Sending GET request to {}", url);

        given()
                .when()
                        .get(url)
                                .then()
                                        .statusCode(200)
                                        .contentType(ContentType.JSON)
                                        .body("uuid", notNullValue())
                                        .body("uuid", isA(String.class))
                .log().body(true);
    }

    @Test
    public void testGetResponseWithMultipleItems() {
        final String endpoint = AZURE_WEBSITES_NET + "/Activities";
        log.info("Sending GET request to {}", endpoint);

        given()
                .when()
                        .get(endpoint)
                                .then()
                                        .statusCode(200)
                                        .contentType(ContentType.JSON)
                                        .body("size()", greaterThan(0))
                                        .body("id", everyItem(notNullValue()))
                                        .body("title", everyItem(notNullValue()))
                                        .body("id[0]", equalTo(1))
                                        .body("title[0]", equalTo("Activity 1"))
                .log().body(true);
    }

    @Test
    public void testGetResponseDeserialized() {
        final int id = 12;
        final String endpoint = String.format("%s/Authors/%s", AZURE_WEBSITES_NET, id);
        log.info("Sending GET request to {}", endpoint);

        final Author expected = Author.builder()
                                       .id(id)
                                       .firstName("First Name " + id)
                                       .lastName("Last Name " + id)
                                       .build();
        final var response  = given()
                                    .when()
                                          .get(endpoint)
                                                .as(Author.class);
        log.info("Response body: {}", response.toString());

        assertEquals(response, expected);
    }
}
