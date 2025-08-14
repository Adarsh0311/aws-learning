package aws.util;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

public class SqsHelper {
    private final SqsClient sqsClient;

    public SqsHelper() {
        sqsClient = SqsClient.builder()
                .region(Region.of(System.getenv("AWS_REGION")))
                .build();
    }

    public void sendMessage(String queueUrl, String messageBody) {
        sqsClient.sendMessage(SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(messageBody)
                .build());
    }
}
