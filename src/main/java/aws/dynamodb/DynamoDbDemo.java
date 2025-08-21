package aws.dynamodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DynamoDbDemo {
    private static final Logger log = LoggerFactory.getLogger(DynamoDbDemo.class);
    DynamoDbClient client = DynamoDBConnection.createClient();


    public static void main(String[] args) {
        DynamoDbDemo demo = new DynamoDbDemo();
        //demo.createTable();

        String userID = UUID.randomUUID().toString();
        demo.insertItem("Users", userID); //or you can pass 123 as userid for now

        demo.getItem("Users", userID);

    }


    public void createTable() {
         CreateTableRequest request = CreateTableRequest.builder()
                .tableName("Users")
                .keySchema(KeySchemaElement.builder()
                        .attributeName("userId")
                        .keyType(KeyType.HASH) //partition key
                        .build())
                .attributeDefinitions(AttributeDefinition.builder()
                        .attributeName("userId")
                        .attributeType(ScalarAttributeType.S) //string type
                        .build())
                .provisionedThroughput(ProvisionedThroughput.builder()
                        .readCapacityUnits(2L)
                        .writeCapacityUnits(2L)
                        .build())
                .build();

        CreateTableResponse response = client.createTable(request);
        log.info("Table created");
        log.info(response.toString());

    }


    public void insertItem(String tableName, String userId) {
        Map<String, AttributeValue> item = Map.of(
                "userId", AttributeValue.builder().s(userId).build(),
                "name", AttributeValue.builder().s("abc").build(),
                "age", AttributeValue.builder().n("123").build()
        );
        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        PutItemResponse response = client.putItem(request);
        log.info(response.toString());

    }

    public GetItemResponse getItem(String tableName, String userId) {
        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(Map.of("userId", AttributeValue.builder().s(userId).build()))
                .build();

        GetItemResponse response = client.getItem(request);
        log.info(response.toString());
        return response;
    }
}
