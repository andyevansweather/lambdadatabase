package com.amazonaws.lambda.demo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<Object, String> {
    private AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    private DynamoDB dynamoDB = new DynamoDB(client);

    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);
        Table table = dynamoDB.getTable("DemoTable");

        Item item = new Item()
                            .withPrimaryKey("DataKey", "data")
                            .withString("hello", "hello");

        // Adding some awesome try catch-age
        try {
            table.putItem(item);
        }   catch (RuntimeException e) {
            context.getLogger().log("it happens");
            context.getLogger().log(e.getMessage());
        }

        // TODO: implement your handler
        return "Hello from Lambda!";
    }

}
