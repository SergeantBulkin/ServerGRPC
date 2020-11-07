package Service;

import proto.MethodGrpc;
import proto.MethodRequest;
import proto.MethodResponse;
import io.grpc.stub.StreamObserver;
import proto.ResponseInt;

import java.util.Arrays;

public class MethodService extends MethodGrpc.MethodImplBase
{
    @Override
    public void getMethodNumber(MethodRequest request, StreamObserver<MethodResponse> responseObserver)
    {
        System.out.println("Запрос на метод");

        MethodResponse response = MethodResponse.newBuilder().setResponseCode(1).build();

        System.out.println("Bytes - " + Arrays.toString(response.toByteArray()));

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getCollectionInt(MethodRequest request, StreamObserver<ResponseInt> responseObserver)
    {
        super.getCollectionInt(request, responseObserver);
    }
}
