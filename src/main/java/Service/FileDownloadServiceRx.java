package Service;

import com.google.protobuf.ByteString;
import io.reactivex.Single;
import proto.DataChunk;
import proto.FileDownloadRequest;
import proto.RxFileDownloadGrpc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileDownloadServiceRx extends RxFileDownloadGrpc.FileDownloadImplBase
{
    @Override
    public Single<DataChunk> downloadDEX(Single<FileDownloadRequest> request)
    {
        String filePath = "D:\\Java\\IDEA Projects\\ServerGRPC\\src\\main\\file\\base64.dex";

        return getDataChunkSingle(filePath);
    }

    @Override
    public Single<DataChunk> downloadSO(Single<FileDownloadRequest> request)
    {
        String filePath = "D:\\Java\\IDEA Projects\\ServerGRPC\\src\\main\\file\\libnative-lib.so";

        return getDataChunkSingle(filePath);
    }

    private Single<DataChunk> getDataChunkSingle(String filePath)
    {
        File file = new File(filePath);
        System.out.println(file.exists() ? "Существует" : "Не существует");

        try
        {
            byte[] filesArray = Files.readAllBytes(Paths.get(filePath));
            System.out.println("Size is " + filesArray.length/1024 + " kB");
            return Single.just(DataChunk.newBuilder().setData(ByteString.copyFrom(filesArray)).build());
        } catch (IOException e)
        {
            System.out.println("Exception: " + e.getClass().getCanonicalName());
            e.printStackTrace();
        }
        return Single.just(DataChunk.newBuilder().setData(ByteString.copyFrom(new byte[0])).build());
    }
}
