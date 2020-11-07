import Service.FileDownloadServiceRx;
import Service.MethodService;
import Service.MethodServiceRx;
import io.grpc.*;
import model.CollectionInt;

import java.io.IOException;

public class ServerRPC
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        Server server = ServerBuilder.forPort(9090)
                .addService(new MethodService())
                .addService(new MethodServiceRx())
                .addService(new FileDownloadServiceRx())
                .build();
        server.start();

        System.out.println("Started at port " + server.getPort() + "\n");

        server.awaitTermination();
    }
}
