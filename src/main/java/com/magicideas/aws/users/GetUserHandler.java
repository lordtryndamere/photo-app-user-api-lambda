package com.magicideas.aws.users;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magicideas.aws.users.dto.UserBodyDto;
import com.magicideas.aws.users.responses.UserResponse;


import java.util.Map;
import java.util.UUID;

public class GetUserHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        //Read PathParameters
        Map<String, String> pathParameters = event.getPathParameters();
//        pathParameters.get("example");
        //Query params
        Map<String, String> queryParams = event.getQueryStringParameters();
//        String id = queryParams.get("id");

        try {

            //Mapper Body
            String body = event.getBody();
            UserBodyDto bodyDto = objectMapper.readValue(body, UserBodyDto.class);
            bodyDto.setId(UUID.randomUUID().toString());
            UserResponse<UserBodyDto>  userResponse = new UserResponse<>();
            userResponse.setMessage("User created successfully");
            userResponse.setCode(201);
            userResponse.setData(bodyDto);

            String jsonResponse = objectMapper.writeValueAsString(userResponse);
            response.setStatusCode(201);
            response.setHeaders(Map.of("Content-Type", "application/json"));
            response.withBody(jsonResponse);
//            response.setBody("Establishment created successfully");

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setBody("Internal Server Error : " + e.getMessage());
        }

        return response;
    }
}