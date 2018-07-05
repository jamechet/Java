import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;


public class DSUnitTesting {

	/* 
	 * Display the methods that fail. 
	 */
	@Rule
	public TestRule classWatchman = new TestWatcher() {
		
		@Override
		protected void failed(Throwable e, Description desc) {
			System.out.println("\tFAIL: \t" + desc.getClassName() + ": " + desc.getMethodName());
		}
		
		@Override
		protected void succeeded(Description desc) {
			
			String className = desc.getClassName().replaceAll("Test", "");
			String methodName = desc.getMethodName().replaceAll("test","").replaceAll("Test","");
			
			String testID = className + ":" + methodName;
			System.out.print(String.format("   %-4s %-25s %-26s", "PASS", 
					className, methodName));
			System.out.print(" Mark: " + MarksMap.getInstance().getMark(testID));
			System.out.print("\n");
			
		}
	};
}
