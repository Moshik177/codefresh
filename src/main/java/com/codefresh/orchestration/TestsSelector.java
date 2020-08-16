package com.codefresh.orchestration;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.testng.annotations.Test;

import static java.util.stream.Collectors.groupingBy;

public class TestsSelector {

    private final static Reflections reflections=
            new Reflections("com.codefresh.tests", new MethodAnnotationsScanner());
    private final static Class<org.testng.annotations.Test> classOfTest = Test.class;
    private final static Stream<Method> getAllTestMethods=
           reflections.getMethodsAnnotatedWith(classOfTest)
             .stream().filter(x -> x.getAnnotation(classOfTest).enabled());

    public static List<Method> getTestsToRunFromTestsNames(List<String> testsNames ) {
        return getAllTestMethods.filter(method -> {
                    String testName = method.getName();
                    return testsNames.contains(testName);
                }).collect(Collectors.toList());
    }

    public static Map<String, List<String>> getTestClassToMethods(ArrayList<Method> methodsNames) {

        return methodsNames
                .stream()
                .collect(groupingBy(method -> method.getDeclaringClass().getCanonicalName()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().stream().map(Method::getName).collect(Collectors.toList())));
    }
}