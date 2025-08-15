# AWS Lambda - SQS Event Handler

A Lambda function that processes SQS messages and routes them to appropriate queues based on message content.

## 🎯 Function Overview

**Purpose**: Process SQS messages and route them to appropriate queues
- Messages containing "success" → Success Queue
- All other messages → Failure Queue

## 📋 Prerequisites

- AWS Console access
- Java 21
- IAM permissions for Lambda and SQS

## 🔧 Setup & Deployment (AWS Console)

### 1. Build the Project

```bash
mvn clean package
```
### 2. Create SQS Queues
- Success queue
- Failure queue
- Source queue

### 3. Create IAM Role
- AWSLambdaBasicExecutionRole
- AmazonSQSFullAccess

### 4. Create Lambda Function
- Runtime java 21
- Handler: aws.lambda.sqs.LambdaSqsHandler::handleRequest
- Upload JAR: Use the built JAR from step 1

### 5. Set Environment Variables
#### Lambda Console → Configuration → Environment Variables

- AWS_SQS_SUCCESS_QUEUE_URL: Your success queue URL
- AWS_SQS_FAILURE_QUEUE_URL: Your failure queue URL

### 6. Add SQS Trigger
Lambda Console → Configuration → Triggers → Add Trigger

### 7. Test
- AWS Console → SQS → Select source-queue → Send Message