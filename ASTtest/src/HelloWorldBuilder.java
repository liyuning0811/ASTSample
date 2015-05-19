import org.eclipse.jdt.core.dom.AST;  
import org.eclipse.jdt.core.dom.ASTParser;  
import org.eclipse.jdt.core.dom.Block;  
import org.eclipse.jdt.core.dom.CompilationUnit;  
import org.eclipse.jdt.core.dom.ExpressionStatement;  
import org.eclipse.jdt.core.dom.MethodDeclaration;  
import org.eclipse.jdt.core.dom.MethodInvocation;  
import org.eclipse.jdt.core.dom.Modifier;  
import org.eclipse.jdt.core.dom.QualifiedName;  
import org.eclipse.jdt.core.dom.SimpleName;  
import org.eclipse.jdt.core.dom.StringLiteral;  
import org.eclipse.jdt.core.dom.TypeDeclaration; 
public class HelloWorldBuilder {
	 
	public static void main(String[] args){  
	        build();  
	    }  
	private static void build() {  
	        ASTParser parser = ASTParser.newParser(AST.JLS3);  
	        parser.setSource("".toCharArray());  
	          
	        CompilationUnit comp = (CompilationUnit) parser.createAST(null);   
	        comp.recordModifications();  
	          
	        AST ast = comp.getAST();  
	          
	       
	        TypeDeclaration classDec = ast.newTypeDeclaration();  
	        classDec.setInterface(false);
	          
	        SimpleName className = ast.newSimpleName("HelloWorld");  
	        Modifier classModifier = ast.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD);  
	          
	        //������ڵ�  
	        classDec.setName(className);//����  
	        classDec.modifiers().add(classModifier);//��ɼ���  
	          
	        //����ڵ�����Ϊ���뵥Ԫ���ӽڵ�  
	        comp.types().add(classDec);       
	          
	        /* 
	        *public class HelloWorld { 
	        *  public HelloWorld(){ 
	        *     
	        *  } 
	        *} 
	        */  
	        MethodDeclaration methodDec = ast.newMethodDeclaration();  
	        methodDec.setConstructor(true);//����Ϊ���캯��  
	          
	        SimpleName methodName = ast.newSimpleName("HelloWorld");  
	        Modifier methodModifier = ast.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD);  
	        Block methodBody = ast.newBlock();  
	  
	        //���÷����ڵ�  
	        methodDec.setName(methodName);// ������  
	        methodDec.modifiers().add(methodModifier);//�����ɼ���  
	        methodDec.setBody(methodBody); //������  
	          
	        //�������ڵ�����Ϊ��ڵ���ӽڵ�  
	        classDec.bodyDeclarations().add(methodDec);  
	          
	        /* 
	        *public class HelloWorld { 
	        *  public HelloWorld(){ 
	        *    System.out.println("Hello World!"); 
	        *  } 
	        *} 
	        */  
	        MethodInvocation methodInv = ast.newMethodInvocation();  
	          
	        SimpleName nameSystem = ast.newSimpleName("System");  
	        SimpleName nameOut = ast.newSimpleName("out");  
	        SimpleName namePrintln = ast.newSimpleName("println");  
	          
	        //���ӡ�System���͡�out��  
	        //System.out  
	        QualifiedName nameSystemOut = ast.newQualifiedName(nameSystem, nameOut);  
	          
	        //���ӡ�System.out���͡�println����MethodInvocation�ڵ�  
	        //System.out.println()  
	        methodInv.setExpression(nameSystemOut);  
	        methodInv.setName(namePrintln);  
	          
	        //��Hello World!��  
	        StringLiteral sHelloworld = ast.newStringLiteral();  
	        sHelloworld.setEscapedValue("\"Hello World!\"");  
	          
	        //System.out.println(��Hello World!��)  
	        methodInv.arguments().add(sHelloworld);  
	          
	        //���������ýڵ�MethodInvocation����Ϊ����ʽ���ExpressionStatement���ӽڵ�  
	        //System.out.println(��Hello World!��);  
	        ExpressionStatement es = ast.newExpressionStatement(methodInv);  
	          
	        //������ʽ���ExpressionStatement����Ϊ�����ڵ�ĵ�  
	        methodBody.statements().add(es);  
	          
	        //End  
	        System.out.println(comp.toString());  
	    } 
}