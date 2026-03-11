
# Spring Boot AI Chat Service

## Features
- REST API to receive user messages
- Mocked LLM API integration
- Conversation history stored in H2 database
- Retry logic for AI failures
- Idempotency using requestId
- Logging support

## API

POST /api/chat

Example Request:

{
"userId": "user1",
"conversationId": "conv1",
"message": "Hello AI",
"requestId": "req-123"
}

Response:

{
"response": "AI Response to: Hello AI"
}

## Reliability

Retry mechanism implemented using Spring Retry.
If the AI service fails, it retries up to 3 times.

## Idempotency

Duplicate requests are prevented using unique requestId stored in DB.

## Run Application

mvn spring-boot:run

H2 Console:
http://localhost:8080/h2-console
