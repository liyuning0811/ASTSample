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
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.TextEdit;

public class Sample3 {
	
	 private static ASTVisitor visitor = new SampleVisitor3();
	    public static void main(String[] arg) throws Exception {
	        
	    	SourceFile sourceFile = new SourceFile("C:/Users/Liyuning/Desktop/ASTtest/ASTtest/src/Subject.java");
	       
	    	CompilationUnit unit;
	        
	        ASTParser astParser = ASTParser.newParser(AST.JLS4);

	        astParser.setBindingsRecovery(true);
	        astParser.setStatementsRecovery(true);

	        astParser.setResolveBindings(true);

	        astParser.setEnvironment(Envs.getClassPath(), Envs.getSourcePath(),
	                null, true);

	        astParser.setUnitName(sourceFile.getFilePath());
	        astParser.setSource(sourceFile.getSourceCode().toCharArray());
	        unit = (CompilationUnit) astParser.createAST(new NullProgressMonitor());
	        unit.recordModifications();

	        unit.accept(visitor);
	  
	        String code = getCode(sourceFile.getSourceCode(), unit);
	        System.out.println(code);
	        
//	        PrintWriter out = new PrintWriter("change.java");
//	        out.write(code);
//	        out.close();
	    }
	   
	    private static String getCode(String code, CompilationUnit unit) {
	        org.eclipse.jface.text.IDocument eDoc = new Document(code);
	        TextEdit edit = unit.rewrite(eDoc, null);
	        try {
	            edit.apply(eDoc);
	            return eDoc.get();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return null;
	        }
	    }
}
