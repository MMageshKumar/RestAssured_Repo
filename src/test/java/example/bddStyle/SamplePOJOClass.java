package example.bddStyle;


import io.restassured.RestAssured;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SamplePOJOClass {

    private String id;
    private String httpMethod;
    private String endpoint;
    private Map<String, String> headers;
    private String requestBody;
    private int expectedStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Map<String, String> getHeaders() {
        return new HashMap<>(headers);
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers != null ? new HashMap<>(headers) : new HashMap<>();
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public int getExpectedStatus() {
        return expectedStatus;
    }

    public void setExpectedStatus(int expectedStatus) {
        this.expectedStatus = expectedStatus;
    }

    @Override
    public String toString() {
        return "ApiTestPojo{" +
                "id='" + id + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", headers=" + headers +
                ", requestBody='" + requestBody + '\'' +
                ", expectedStatus=" + expectedStatus +
                '}';
    }

	}