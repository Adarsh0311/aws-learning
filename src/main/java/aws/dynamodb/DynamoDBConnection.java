package aws.dynamodb;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

/*
This will connect using your AWS credentials from:
    ~/.aws/credentials file
    or environment variables
    or IAM role if running in AWS.
 */
public class DynamoDBConnection {

    public static DynamoDbClient createClient() {
        return DynamoDbClient.builder()
                .region(Region.of(System.getenv("AWS_REGION")))
                .build();
    }
}
