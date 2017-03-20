package DesigniteTests;

import static org.junit.Assert.*;

import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.Before;
import org.junit.Test;

import Designite.InputArgs;
import Designite.SourceModel.SM_Package;
import Designite.SourceModel.SM_Project;
import Designite.SourceModel.SM_SourceItem.AccessStates;
import Designite.SourceModel.SM_Type;
import Designite.SourceModel.TypeVisitor;

public class SM_TypeTest {
	//Set this path before executing tests
	private static String TESTS_PATH = "C:\\Users\\Alex\\workspace\\DesigniteJava\\tests\\TestFiles";
	private SM_Project project;
	private SM_Type type;
	
	@Before
	public void setUp() {
		project = new SM_Project(new InputArgs(TESTS_PATH + "\\testBatchFile.txt"));
	}
	
	@Test
	public void SM_Type_getName() {
		CompilationUnit unit = project.createCU(TESTS_PATH + "\\test_package\\TestClass.java");
		List<TypeDeclaration> listOfTypes = unit.types();
		
		type = new SM_Type(listOfTypes.get(0), unit);
		assertEquals(type.getName(), "TestClass");		
	}

	@Test
	public void SM_Type_checkDefaultAccess() {
		CompilationUnit unit = project.createCU(TESTS_PATH + "\\test_package\\DefaultClass.java");
		List<TypeDeclaration> listOfTypes = unit.types();
		
		type = new SM_Type(listOfTypes.get(0), unit);
		assertEquals(type.getAccessModifier(), AccessStates.DEFAULT);		
	}
	
	@Test
	public void SM_Type_check_getTypeDeclaration() {
		CompilationUnit unit = project.createCU(TESTS_PATH + "\\test_package\\DefaultClass.java");
		List<TypeDeclaration> listOfTypes = unit.types();

		type = new SM_Type(listOfTypes.get(0), unit);
		assertEquals(type.getTypeDeclaration(), listOfTypes.get(0));		
	}
	
	@Test
	public void SM_Type_check_isAbstract() {
		CompilationUnit unit = project.createCU(TESTS_PATH + "\\test_package\\AbstractClass.java");
		List<TypeDeclaration> listOfTypes = unit.types();

		type = new SM_Type(listOfTypes.get(0), unit);
		assertTrue(type.isAbstract());		
	}
	
	@Test
	public void SM_Type_check_isInterface() {
		CompilationUnit unit = project.createCU(TESTS_PATH + "\\test_package\\Interface.java");
		List<TypeDeclaration> listOfTypes = unit.types();

		type = new SM_Type(listOfTypes.get(0), unit);
		assertTrue(type.isInterface());		
	}
	
	@Test //too complicated for the moment 
	public void SM_Type_check_isNestedClass() {		
		SM_Project project = new SM_Project(new InputArgs(TESTS_PATH + "\\testBatchFile.txt"));
		project.parse();
		List<SM_Package> packageList = project.getPackageList();
		
		for (SM_Package pkg: packageList) {
			if (pkg.getName().equals("test_package")) {
				List<SM_Type> list = pkg.getTypeList();
				for (SM_Type type:list) {
					if (type.getName().equals("InnerClass")) 
						assertTrue(type.isNestedClass());
				}
			}
		}
	}
	
	@Test(expected = NullPointerException.class)
	public void SM_Type_nullTypeDeclaration() {
		CompilationUnit unit = project.createCU(TESTS_PATH + "\\test_package\\TestClass.java");
		type = new SM_Type(null, unit);	
	}
	
	@Test(expected = NullPointerException.class)
	public void SM_Type_nullCompilationUnit() {
		CompilationUnit unit = project.createCU(TESTS_PATH + "\\test_package\\TestClass.java");
		List<TypeDeclaration> listOfTypes = unit.types();
		
		type = new SM_Type(listOfTypes.get(0), null);	
	}
	
	@Test
	public void SM_Type_countFields() {
		project.parse();
		List<SM_Package> packageList = project.getPackageList();
		
		for (SM_Package pkg: packageList) {
			if (pkg.getName().equals("test_package")) {
				List<SM_Type> list = pkg.getTypeList();
				for (SM_Type type:list) {
					if (type.getName().equals("TestMethods")) 
						assertEquals(type.countFields(), 5);
				}
			}
		}
	}
	
	@Test
	public void SM_Type_countMethods() {
		SM_Project project = new SM_Project(new InputArgs(TESTS_PATH + "\\testBatchFile.txt"));
		project.parse();
		List<SM_Package> packageList = project.getPackageList();
		
		for (SM_Package pkg: packageList) {
			if (pkg.getName().equals("test_package")) {
				List<SM_Type> list = pkg.getTypeList();
				for (SM_Type type:list) {
					if (type.getName().equals("TestMethods")) 
						assertEquals(type.countMethods(), 5);
				}
			}
		}
	}
	
	@Test
	public void SM_Type_getParent() {
		project.parse();
		List<SM_Package> packageList = project.getPackageList();
		
		for (SM_Package pkg: packageList) {
			if (pkg.getName().equals("test_package")) {
				List<SM_Type> list = pkg.getTypeList();
				for (SM_Type type:list) {
					if (type.getName().equals("TestMethods")) 
						assertEquals(type.getParent().getName(), "test_package");
				}
			}
		}
	}
}
