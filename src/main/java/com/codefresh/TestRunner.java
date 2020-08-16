package com.codefresh;

import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import java.util.*;

public class TestRunner {

    private final TestNG testNG;
    private final String testFolder;

    public TestRunner(String testFolder, List<Class<? extends ITestNGListener>> listener) {
        testNG = new TestNG();
        testNG.setPreserveOrder(true);
        testNG.setOutputDirectory(testFolder + "/testNG");
        testNG.setListenerClasses(listener);
        testNG.setVerbose(0);
        this.testFolder = testFolder;
    }

    public void runTests(Map<String, List<String>> testClassToMethods) {

        XmlSuite suite = buildTestNGSuiteXml(testClassToMethods);
        testNG.setXmlSuites(Collections.singletonList(suite));
        testNG.setUseDefaultListeners(true);
        testNG.run();
        System.exit(testNG.getStatus());
    }

    private XmlSuite buildTestNGSuiteXml(Map<String, List<String>> testClassToMethods) {

            XmlSuite suite = new XmlSuite();
            suite.setName("Tests Suite");
            testClassToMethods.forEach((className, testsNames) ->
                    testsNames.forEach(testName -> {
                        XmlTest xmlTest = new XmlTest(suite);
                        xmlTest.addParameter("testFolder", testFolder);
                        xmlTest.setName(testName);
                        XmlClass xmlClass = new XmlClass(className);
                        xmlClass.setIncludedMethods(Collections.singletonList(new XmlInclude(testName)));
                        xmlTest.setXmlClasses(Collections.singletonList(xmlClass));
                    }));
            return suite;
    }
}