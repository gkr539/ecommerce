# ecommerce
1. Run docker-compose up (to set up postgres, kafka and to create topic).
2. Run discovery-server, gateway, config-server, order-servicee and payment-service microservices.
3. Kafka producer will publish messages to batchOrders topic and kafka consumer will listen to this topic.
4. For client-order request, following api end points are available.
        -> localhost:9000/order/createOrder/
        -> localhost:9000/order/cancelOrder/{id}/
        -> localhost:9000/order/updateOrder/{id}/
        -> localhost:9000/order/getOrderById/{id}/
5. For batch-order request, following api end points are available.
        -> localhost:9000/order/createBatchOrder/
        -> localhost:9000/order/updateBatchOrder/{id}/
6. Order-service calls payment-service to verify payment conifirmation.
7. For distributed tracing of requests, Zipkin and sleuth are used.
8. For centralized logging, ELK stack is used.
  
        
      
