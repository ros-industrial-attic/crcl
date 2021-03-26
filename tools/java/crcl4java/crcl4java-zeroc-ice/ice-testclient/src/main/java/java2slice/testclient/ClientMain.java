/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2slice.testclient;

import consumer.TestClassConsumerPrx;
import consumer.TestClassConsumerPrxHelper;
import java2slice.testinlib.TestClassIce;
import testinlib.TestClass;


/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class ClientMain extends Ice.Application {

    public static void main(String[] args) {
        ClientMain app = new ClientMain();
        int status = app.main("Client", args);
        System.exit(status);
    }

    @Override
    public int run(String[] args) {
        Ice.Communicator ic = communicator(); //Ice.Util.initialize(args);
        Ice.ObjectPrx base = ic.stringToProxy("SimpleTestClassConsumer:default -p 10000");
        TestClassConsumerPrx consumer = TestClassConsumerPrxHelper.checkedCast(base);
        if (consumer == null) {
            throw new Error("Invalid proxy");
        }
        TestClass orig_tc = new TestClass();
        orig_tc.setName("my name");
        TestClassIce test = converters.toice.ToIceConverters.toIce(orig_tc);
        consumer.accept(test);
        return 0;
    }

}
