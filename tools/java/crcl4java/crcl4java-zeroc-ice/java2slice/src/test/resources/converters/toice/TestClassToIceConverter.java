package converters.toice;
import java.util.function.Function;
import orig.TestClass;
import java2slice.orig.TestClassIce;

public class TestClassToIceConverter implements Function<TestClass,TestClassIce>  {

	public TestClassIce apply(TestClass in) {
		TestClassIce out = new TestClassIce();
		out.name =  in.getName();
		return out;
	}
}
