package com.pm.patient_service.grpc;

import billing.BillingServiceGrpc;
import billing.BillingServiceGrpc.BillingServiceStub;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

public class BillingServiceGrpcClient {
    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

    public BillingServiceGrpcClient(
        @Value("${billing.service.address:localhost}") String serverAddress,
        @Value("${billing.service.address:localhost}") int serverPort
    ){
        
    }
}
