/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2slice.testserver;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class ServerMain extends Ice.Application {

    public static void main(String[] args) {
        ServerMain app = new ServerMain();
        int status = app.main("Server", args);
        System.exit(status);
    }

    @Override
    public int run(String[] args) {
        Ice.Communicator ic = communicator(); // Ice.Util.initialize(args);
        Ice.ObjectAdapter adapter
                = ic.createObjectAdapterWithEndpoints("SimpleTestClassConsumerAdapter", "default -p 10000");
        Ice.Object object = new SimpleTestClassConsumer();
        adapter.add(object, ic.stringToIdentity("SimpleTestClassConsumer"));
        adapter.activate();
        ic.waitForShutdown();
        return 0;
    }

}
