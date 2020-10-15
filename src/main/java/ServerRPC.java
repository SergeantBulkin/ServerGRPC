import io.grpc.ServerBuilder;
import io.grpc.Server;

import java.io.IOException;

public class ServerRPC
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        Server server = ServerBuilder.forPort(9090)
                .build();
        server.start();

        System.out.println("Started at port " + server.getPort());

        server.awaitTermination();
    }
}
