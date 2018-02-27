package DesigniteTests.smells.implementationSmells;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import Designite.SourceModel.SourceItemInfo;
import Designite.metrics.MethodMetrics;
import Designite.smells.ThresholdsDTO;
import Designite.smells.implementationSmells.ImplementationSmellDetector;

public class ImplementationSmellDetectorTest {
	
	private SourceItemInfo info = new SourceItemInfo("testProject", "testPackage", "testType", "testMethod");
	private ThresholdsDTO thresholds = new ThresholdsDTO();
	
	@Test
	public void testLongMethodWhenHappyPath() {
		MethodMetrics methodMetrics = mock(MethodMetrics.class);
		when(methodMetrics.getNumOfLines()).thenReturn(thresholds.getLongMethod() - 1);
		ImplementationSmellDetector detector = new ImplementationSmellDetector(methodMetrics, info);
		
		int expected = 0;
		int actual = detector.detectLongMethods().size();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testLongMethodWhenSmellIsDetected() {
		MethodMetrics methodMetrics = mock(MethodMetrics.class);
		when(methodMetrics.getNumOfLines()).thenReturn(thresholds.getLongMethod() + 1);
		ImplementationSmellDetector detector = new ImplementationSmellDetector(methodMetrics, info);
		
		int expected = 1;
		int actual = detector.detectLongMethods().size();
		
		assertEquals(expected, actual);
	}


}