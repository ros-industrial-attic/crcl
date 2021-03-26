package converters.fromice;
import java.util.function.Function;
import orig.TestClass;
import java2slice.orig.TestClassIce;

public class TestClassFromIceConverter implements Function<TestClassIce,TestClass>  {

	public TestClass apply(TestClassIce in) {
		TestClass out = new TestClass();
		out.setName(in.name);
		return out;
	}
}
