import org.eclipse.jdt.core.dom.CompilationUnit;

public class DemoVisitorTest {
	static String Path="C:/Users/Liyuning/Desktop/ASTtest/ASTtest/src/Subject.java";
	 
	 public static void main(String[] args) {
		 
		        CompilationUnit comp = JdtAstUtil.getCompilationUnit(Path); 
		        DemoVisitor visitor = new DemoVisitor();  
		        comp.accept(visitor);  
		    
	 }

}
