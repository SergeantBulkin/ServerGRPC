package Service;

import io.reactivex.Single;
import proto.MethodRequest;
import proto.MethodResponse;
import proto.RxMethodGrpc;

public class MethodServiceRx extends RxMethodGrpc.MethodImplBase
{
    @Override
    public Single<MethodResponse> getMethodNumber(Single<MethodRequest> request)
    {
        return super.getMethodNumber(request);
    }
}
