package util.visitor;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

// クラス名と，親クラスと，インターフェースを変更するサンプル

public class SampleVisitor3 extends ASTVisitor {
    public boolean visit(TypeDeclaration node) {
//         クラス名を変更する
//        setClassName(node, "HogeHoge");
//         親クラスを変更する
//        setSuperClass(node, "java.applet.Applet");
//         インターフェースを変更する
//        setSuperInterfaces(node, "java.io.Serializable", "PiyoPiyo");
        return super.visit(node);
    }
    
    private  void setMethodName(MethodDeclaration node, String simpleClassName) {
        AST ast = node.getAST();
        SimpleName simpleName = ast.newSimpleName(simpleClassName);
        node.setName(simpleName);
    }
    
    
    private void setClassName(TypeDeclaration node, String simpleClassName) {
        AST ast = node.getAST();
        SimpleName simpleName = ast.newSimpleName(simpleClassName);
        node.setName(simpleName);
    }
    
    private void setSuperClass(TypeDeclaration node, String superClassName) {
        AST ast = node.getAST();
        Name name = ast.newName(superClassName);
        Type superClassType = ast.newSimpleType(name);
        node.setSuperclassType(superClassType);
    }
   
    @SuppressWarnings("unchecked")
    private void setSuperInterfaces(TypeDeclaration node,
            String... newInterfaceNames) {
        AST ast = node.getAST();
        node.superInterfaceTypes().clear();
        for (String interfaceName : newInterfaceNames) {
            Name name = ast.newName(interfaceName);
            Type interfaceType = ast.newSimpleType(name);
            node.superInterfaceTypes().add(interfaceType);
        }
    }
    
    public boolean visit(MethodDeclaration node) {
        //node.delete();
    	if(node.getName().toString().equals("addObserver")){
    		setMethodName(node,"addObserver1");	
    	}
        return super.visit(node);
    }
}