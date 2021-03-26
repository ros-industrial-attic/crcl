/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package java2slice.testserver;

import Ice.Current;
import consumer._TestClassConsumerDisp;
import java2slice.testinlib.TestClassIce;
import testinlib.TestClass;


/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class SimpleTestClassConsumer extends  _TestClassConsumerDisp{

    @Override
    public void accept(TestClassIce t, Current __current) {
        TestClass orig_t = converters.fromice.FromIceConverters.fromIce(t);
         System.out.println("t.name = " + orig_t.getName());
    }


}
