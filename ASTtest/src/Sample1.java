import util.Envs;
import util.SourceFile;
import util.visitor.*;

import java.io.File;
import java.io.PrintWriter;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class Sample1 {
	
	private static ASTVisitor visitor = new SampleVisitor1();
	
	public static void main(String[] args) throws Exception {
		
		SourceFile sourceFile = new SourceFile("C:/Users/Liyuning/Desktop/ASTtest/ASTtest/src/Subject.java");
		CompilationUnit unit;
        ASTParser astParser = ASTParser.newParser(AST.JLS4);
        astParser.setBindingsRecovery(true);
        astParser.setStatementsRecovery(true);
        astParser.setResolveBindings(true);
        astParser.setEnvironment(Envs.getClassPath(), Envs.getSourcePath(),null, true);
        astParser.setUnitName(sourceFile.getFilePath());
        astParser.setSource(sourceFile.getSourceCode().toCharArray());
        unit = (CompilationUnit) astParser.createAST(new NullProgressMonitor());
        unit.recordModifications();
        unit.accept(visitor);
       
	}
}
