package Service;

import io.reactivex.Flowable;
import io.reactivex.Single;
import proto.MethodRequest;
import proto.MethodResponse;
import proto.ResponseInt;
import proto.RxMethodGrpc;

import java.util.ArrayList;
import java.util.List;

public class MethodServiceRx extends RxMethodGrpc.MethodImplBase
{

    @Override
    public Single<MethodResponse> getMethodNumber(Single<MethodRequest> request)
    {
        System.out.println("Запрос на реактивный метод");

        return request.map(methodRequest -> MethodResponse.newBuilder().setResponseCode(4).build());
    }

    @Override
    public Flowable<ResponseInt> getCollectionInt(Single<MethodRequest> request)
    {
        System.out.println("Запрос на getCollectionInt");

        long startMili = System.currentTimeMillis();
        long startNano = System.nanoTime();

        List<Integer> collection = new ArrayList<>();
        //System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE);
        //System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
        for (int i = -10000; i < 10001; i++)
        {
            collection.add(i);
        }

        long endNano = System.nanoTime();
        long endMili = System.currentTimeMillis();

        System.out.println("Заняло nano " + (endNano-startNano) + " нс");
        System.out.println("Заняло mili " + (endMili-startMili) + " мс");

        System.out.println("Length = " + collection.size());

        return Flowable.just(collection).flatMap(Flowable::fromIterable).map(integer -> ResponseInt.newBuilder().setResponseInt(integer).build());
    }
}
