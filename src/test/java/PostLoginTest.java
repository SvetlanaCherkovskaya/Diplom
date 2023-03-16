import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class PostLoginTest {
    private final String token = "b2786883460d40ae4ced59d72333a084";
    private final String username = "Petr_";
    private final String password = "e56ab09a52";
    private final String loginURL = "https://test-stand.gb.ru/gateway/login";

    @Test
    void postLoginTest() {
        final String response = given()
                .formParam("username", username)
                .formParam("password", password)
                .log()
                .all()
                .when()
                .post(loginURL)
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .asString();
        assertThat(response, containsString("token"));
    }

    @Test
    void negativePostLoginTest() {
        final String response =
                given()
                        .formParam("username", "sv")
                        .formParam("password", "sv")
                        .log()
                        .all()
                        .when()
                        .post(loginURL)
                        .prettyPeek()
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .asString();
        assertThat(response, containsString("Неправильный логин. Может быть не менее 3 и не более 20 символов"));
    }

    @Test
    void negativePostLoginTest2() {
        final String response =
                given()
                        .formParam("username", username)
                        .formParam("password", "")
                        .log()
                        .all()
                        .when()
                        .post(loginURL)
                        .prettyPeek()
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .asString();
        assertThat(response, containsString("Неправильный логин. Может быть не менее 3 и не более 20 символов"));
    }

    public PostLoginTest() {
        super();
    }
}
