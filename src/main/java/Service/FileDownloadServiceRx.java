package Service;

import io.reactivex.Flowable;
import io.reactivex.Single;
import proto.DataChunk;
import proto.FileDownloadRequst;
import proto.RxFileDownloadGrpc;

public class FileDownloadServiceRx extends RxFileDownloadGrpc.FileDownloadImplBase
{
    @Override
    public Flowable<DataChunk> download(Single<FileDownloadRequst> request)
    {
        return super.download(request);
    }
}
