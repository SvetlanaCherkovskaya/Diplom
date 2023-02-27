import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class GetMyPostsTest {

    private final String token = "b2786883460d40ae4ced59d72333a084";
    private final String postsURL = "https://test-stand.gb.ru/api/posts";

    @Test
    void getAscTest() {
        String c;
        final String response1 = given()
                .header("X-Auth-Token", token)
                //.queryParam("owner", "n")
                .queryParam("order", "ASC")
                .queryParam("page", "1")
                .log()
                .all()
                .when()
                .get(postsURL)
                .prettyPeek()
                .then().
                assertThat()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("data.id");

        // String response2 = response1.replaceAll(",", "");

        String[] idString = response1.split(", ");

        System.out.println(response1);

        int[] idInt = new int[idString.length];
        for (int i = 1; i < idString.length - 1; i++) {
            idInt[i] = Integer.parseInt(idString[i]);
        }

        int a = idInt[1];
        int b = idInt[2];
        if (a < b) {
            c = "true";
        } else {
            c = "false";
        }
        assertThat(c, equalTo("true"));
    }

    @Test
    void getDECSTest() {
        String c;
        final String response1 = given()
                .header("X-Auth-Token", token)
                //.queryParam("owner", "n")
                .queryParam("order", "DESC")
                .queryParam("page", "2")
                .log()
                .all()
                .when()
                .get(postsURL)
                .prettyPeek()
                .then().
                assertThat()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("data.id");

        // String response2 = response1.replaceAll(",", "");

        String[] idString = response1.split(", ");

        System.out.println(response1);

        int[] idInt = new int[idString.length];
        for (int i = 1; i < idString.length - 1; i++) {
            idInt[i] = Integer.parseInt(idString[i]);
        }

        int a = idInt[1];
        int b = idInt[2];
        if (a > b) {
            c = "true";
        } else {
            c = "false";
        }
        assertThat(c, equalTo("true"));
    }

    @Test
    void getNotAuthTest() {
        given()
                .queryParam("order", "ASC")
                .queryParam("page", "2")
                .log()
                .all()
                .when()
                .get(postsURL)
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void getNullPageTest() {
        given()
                .header("X-Auth-Token", token)
                .queryParam("order", "ASC")
                .queryParam("page", "500000")
                .log()
                .all()
                .when()
                .get(postsURL)
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .body("data.id[0]", nullValue())
                .body("data.authorId[0]", nullValue());
    }

    @Test
    void getNullPage0Test() {
        given()
                .header("X-Auth-Token", token)
                .queryParam("order", "ASC")
                .queryParam("page", "0")
                .log()
                .all()
                .when()
                .get(postsURL)
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .body("data.id[0]", nullValue())
                .body("data.authorId[0]", nullValue());
    }
}
