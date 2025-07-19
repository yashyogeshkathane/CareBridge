package com.pm;

import com.amazonaws.services.ec2.model.Vpc;
import software.amazon.awscdk.*;

public class LocalStack extends Stack {
    private final Vpc vpc;
    public LocalStack(final App scope, final String id, final StackProps props) {
        super(scope,id,props);
        this.vpc = new Vpc();

    }

    private Vpc CreateVpc(){
        return Vpc.Builder.create(this,"PatientManagementVpc")
                .vpcName("PatientManagementVpc")
                .maxAzs(2).build();

    }

    public static void main(String[] args) {
        App app=new App(AppProps.builder().outdir("./cdk.out").build());

        StackProps props=StackProps.builder().synthesizer(new BootstraplessSynthesizer()).build();

        new LocalStack(app,"LocalStack",props);
        app.synth();
        System.out.println("App synth in progress....");
    }

}
