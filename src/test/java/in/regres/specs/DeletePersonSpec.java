package in.regres.specs;

import in.regres.config.ApiConfig;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;

import static in.regres.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.STATUS;

public class DeletePersonSpec {

    private static final ApiConfig config = ConfigFactory.create(ApiConfig.class);

    public static RequestSpecification deletePersonRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().method()
            .baseUri(config.baseApiUrl())
            .basePath("/api");

    public static ResponseSpecification deletePersonResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(204)
            .build();
}
