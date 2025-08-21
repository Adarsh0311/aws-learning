package aws.dynamodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

public class DynamoDbDemo {
    private static final Logger log = LoggerFactory.getLogger(DynamoDbDemo.class);
    static DynamoDbClient client = DynamoDBConnection.createClient();

    public static void main(String[] args) {
        createTable();

    }


    public static void createTable() {
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
        log.info(response.toString());
        log.info("Table created");
    }
}
