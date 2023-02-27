import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class GetNotMyPostsTest {
    private final String token = "b2786883460d40ae4ced59d72333a084";

    private final String postsURL = "https://test-stand.gb.ru/api/posts";
    //private static String c;

    @Test
    void getAscNotMeTes() {
        String c;
        final String response1 = given()
                .header("X-Auth-Token", token)
                .queryParam("owner", "notMe")
                .queryParam("order", "ASC")
                .queryParam("page", "3")
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

        //for (int i = 0; i < idString.length; i++) {
        //System.out.println(idInt[i]);
        // }

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
    void getDescNotMeTes() {
        String c;
        final String response1 = given()
                .header("X-Auth-Token", token)
                .queryParam("owner", "notMe")
                .queryParam("order", "DESC")
                .queryParam("page", "3")
                .log()
                .all()
                .when()
                .get(postsURL)
                .prettyPeek()
                .then()
                .assertThat()
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

        //for (int i = 0; i < idString.length; i++) {
        //System.out.println(idInt[i]);
        // }

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
    void getAllNotMeTes() {
        String c;
        given()
                .header("X-Auth-Token", token)
                .queryParam("owner", "notMe")
                .queryParam("order", "ALL")
                .queryParam("page", "3")
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
    void getNullPageNotMeTest() {
        given()
                .header("X-Auth-Token", token)
                .queryParam("owner", "notMe")
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
    void getNotAuthNotMeTest() {
        given()
                .queryParam("owner", "notMe")
                .queryParam("order", "ASC")
                .queryParam("page", "3")
                .log()
                .all()
                .when()
                .get(postsURL)
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200);
    }

}





