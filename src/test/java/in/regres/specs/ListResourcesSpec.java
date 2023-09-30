package in.regres.specs;

import in.regres.config.ApiConfig;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;

import static in.regres.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class ListResourcesSpec {

    private static final ApiConfig CONFIG = ConfigFactory.create(ApiConfig.class);

    public static RequestSpecification listResourcesRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().method()
            .baseUri(CONFIG.baseApiUrl())
            .basePath("/api");

    public static ResponseSpecification listResourcesResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .build();
}
