package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {

	public static ExtentReports getReportObject() {

		//report configuration
		String Path = System.getProperty("user.dir") + "\\reports\\result.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(Path);
		reporter.config().setReportName("Automation Resluts");
		reporter.config().setDocumentTitle("Test Results");

		//calling report
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Chandrahas");
		return extent;
	}

}
