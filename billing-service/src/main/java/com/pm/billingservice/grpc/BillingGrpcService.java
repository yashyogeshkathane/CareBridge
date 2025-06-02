package com.pm.billingservice.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import billing.BillingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase{
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest, StreamObserver<BillingResponse> responseObserver){
        log.info("createBillingAccount request received {}", billingRequest.toString());

        BillingResponse response =BillingResponse.newBuilder()
                .setAccountId("12345")
                .setStatus("ACTIVE")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void updateBillingAccount(billing.BillingRequest billingRequest, StreamObserver<BillingResponse> responseObserver) {
        log.info("updateBillingAccount request received {}", billingRequest.toString());

        // Your logic to update the billing account in DB (if you have a DB)
        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("12345") // Or whatever logic you want
                .setStatus("UPDATED")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


}
