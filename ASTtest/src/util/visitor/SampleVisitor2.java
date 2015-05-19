package util.visitor;

import util.Print;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;


public class SampleVisitor2 extends ASTVisitor {
	
//	public boolean visit(TypeDeclaration node) {
//		node.delete();
//		return super.visit(node);
//	}
	/**
     * ¥Õ¥£©`¥ë¥ÉÐûÑÔ¤òdelete!
     */
    public boolean visit(FieldDeclaration node) {
        node.delete();
        return super.visit(node);
    }
    /**
     * javadoc¤â¤Ä¤¤¤Ç¤Ëdelete!
     */
    public boolean visit(Javadoc node) {
        node.delete();
        return super.visit(node);
    }
    /**
     * ¥À¥áÑº¤·¤Ëimport¤âdelete!
     */
    public boolean visit(ImportDeclaration node) {
        node.delete();
        return super.visit(node);
    }
    /**
     * ¥á¥½¥Ã¥ÉÄÚ¤Î„IÀí¤òdelete!
     */
    public boolean visit(MethodDeclaration node) {
//        node.getBody().statements().clear();
        node.delete();
        return super.visit(node);
    }
}