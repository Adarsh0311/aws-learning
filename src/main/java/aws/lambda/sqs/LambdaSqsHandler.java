package aws.lambda.sqs;

import aws.util.SqsHelper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;

public class LambdaSqsHandler implements RequestHandler<SQSEvent, Void> {
    private final String successQueueUrl = System.getenv("AWS_SQS_SUCCESS_QUEUE_URL");
    private final String failureQueueUrl = System.getenv("AWS_SQS_FAILURE_QUEUE_URL");
    private final SqsHelper sqsHelper = new SqsHelper();

    @Override
    public Void handleRequest(SQSEvent event, Context context) {
        context.getLogger().log(String.format("Received SQSEvent: %s", event));

        try {
            event.getRecords().forEach(r -> {
                String messageBody = r.getBody();

                if (messageBody.toLowerCase().contains("success")) {
                    sqsHelper.sendMessage(successQueueUrl, messageBody);

                } else  {
                    sqsHelper.sendMessage(failureQueueUrl, messageBody);

                }
            });

        } catch (Exception e) {
            context.getLogger().log("Error in processing message. Message: " + e.getMessage());
            sqsHelper.sendMessage(failureQueueUrl, e.getMessage());
        }

        return null;
    }
}
