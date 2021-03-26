package src/test/resources/converters/toice/TestClassToIceConverter.java;
import java.util.function.Function;
import java2slice.testinlib.TestClass;
import java2slice.java2slice.testinlib.TestClassIce;

public class TestClassToIceConverter implements Function<TestClass,TestClassIce>  {

	public TestClassIce apply(TestClass in) {
		TestClassIce out = new TestClassIce();
		out.name =  in.getName();
		return out;
	}
}
