package tricentisListeners;

	import org.testng.ITestContext;
	import org.testng.ITestListener;
	import org.testng.ITestResult;
	import frameWorkClasses.BasePage;
import frameWorkClasses.Utilities;

	public class listenerClass extends BasePage implements ITestListener{
		
		//instantiate Utilities class.
		Utilities uts = new Utilities();

		public void onTestStart(ITestResult result) {
			// TODO Auto-generated method stub
			ITestListener.super.onTestStart(result);
			
			//report to console when when a new test starts
			try {
				System.out.println("This is the beginning of a new test");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void onTestSuccess(ITestResult result) {
			// TODO Auto-generated method stub
			ITestListener.super.onTestSuccess(result);
			
			//take a screenshot when this method is triggered
			try {
				//System.out.println("test success");
				uts.TakeSnapShot("On test success" + uts.returnTime() + ".png");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void onTestFailure(ITestResult result) {
			// TODO Auto-generated method stub
			ITestListener.super.onTestFailure(result);
			
			//take a screenshot when this method is triggered
			try {
				//System.out.println("test failed");
				uts.TakeSnapShot("On test failure" + uts.returnTime() + ".png");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void onTestSkipped(ITestResult result) {
			// TODO Auto-generated method stub
			ITestListener.super.onTestSkipped(result);
			
			//report to console when a test is skipped
			try {
				System.out.println("This test has been skipped");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void onStart(ITestContext context) {
			// TODO Auto-generated method stub
			ITestListener.super.onStart(context);
			
			//report to console when our test run starts
			try {
				System.out.println("This is the beginning of our test suite");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void onFinish(ITestContext context) {
			// TODO Auto-generated method stub
			ITestListener.super.onFinish(context);
			
			//report to console when our test run finishes
			try {
				System.out.println("You have reached the end of the test suite");
			}catch (Exception e) {
				e.printStackTrace();
			}
			TearDown();
		}

	}
