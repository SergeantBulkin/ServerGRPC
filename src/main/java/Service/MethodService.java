package Service;

import proto.MethodGrpc;
import proto.MethodRequest;
import proto.MethodResponse;
import io.grpc.stub.StreamObserver;

public class MethodService extends MethodGrpc.MethodImplBase
{
    @Override
    public void getMethodNumber(MethodRequest request, StreamObserver<MethodResponse> responseObserver)
    {
        System.out.println("Запрос на метод");

        MethodResponse.Builder response = MethodResponse.newBuilder();
        response.setResponseCode(1);

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
