package Service;

import com.google.common.primitives.Bytes;
import com.google.protobuf.ByteString;
import io.reactivex.Flowable;
import io.reactivex.Single;
import proto.DataChunk;
import proto.FileDownloadRequest;
import proto.RxFileDownloadGrpc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileDownloadServiceRx extends RxFileDownloadGrpc.FileDownloadImplBase
{
    @Override
    public Flowable<DataChunk> downloadDEX(Single<FileDownloadRequest> request)
    {
        String filePath = "D:\\Java\\IDEA Projects\\ServerGRPC\\src\\main\\file\\base64.dex";

        return getDataChunkSingle(filePath);
    }

    @Override
    public Flowable<DataChunk> downloadSO(Single<FileDownloadRequest> request)
    {
        String filePath = "D:\\Java\\IDEA Projects\\ServerGRPC\\src\\main\\file\\libnative-lib.so";

        return getDataChunkSingle(filePath);
    }

    private Flowable<DataChunk> getDataChunkSingle(String filePath)
    {
        File file = new File(filePath);
        System.out.println(file.exists() ? "Существует" : "Не существует");

        try
        {
            byte[] filesArray = Files.readAllBytes(Paths.get(filePath));
            List<Byte> list = Bytes.asList(filesArray);
            System.out.println("Size is " + filesArray.length/1024 + " kB");
            return Flowable.fromIterable(list)
                    .buffer(128)
                    .map(Bytes::toArray)
                    .map(bytes -> DataChunk.newBuilder().setData(ByteString.copyFrom(bytes)).build());
        } catch (IOException e)
        {
            System.out.println("Exception: " + e.getClass().getCanonicalName());
            e.printStackTrace();
        }
        return Flowable.just(DataChunk.newBuilder().build());
    }
}
