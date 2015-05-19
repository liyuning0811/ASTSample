package util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jface.text.Document;

/**
 * �����Ф���ASTParser�ǒQ����Type�Ω`�ɤ����ɤ��륯�饹<br>
 * �ץ�ߥƥ��֤⤤����裡<br>
 * ���Ф⤤����裡<br>
 * �����ͥꥯ���⤤����裡<br>
 * 
 * @author satanabe1
 * 
 */
public class TypeBuilder {

	private Type type;

	/**
	 * 
	 * @param typeString
	 *            ���ɤ��������饹��������<br>
	 *            ex: new TypeBuilder("double")<br>
	 *            ex: new TypeBuilder("Hoge[][]")<br>
	 * 
	 */
	public TypeBuilder(final String typeString) {
		final List<Type> types = new ArrayList<Type>();
		// ";"��һ�ĤǤ⤢��Џ���
		if (typeString.contains(";")) {
			throw new IllegalArgumentException(typeString);
		}

		Document doc = new Document("class tmpclass { " + typeString
				+ " tmpfield;}\n");
		// ����ѥ��륪�ץ�����source��target����1.7��
		Map<String, String> comap = new HashMap<String, String>();
		comap.put(JavaCore.COMPILER_SOURCE, "1.7");
		comap.put(JavaCore.COMPILER_COMPLIANCE, "1.7");
		ASTParser astParser = ASTParser.newParser(AST.JLS3);
		astParser.setUnitName("tmpclass");
		astParser.setCompilerOptions(comap);
		astParser.setSource(doc.get().toCharArray());
		CompilationUnit unit = (CompilationUnit) astParser
				.createAST(new NullProgressMonitor());
		unit.accept(new ASTVisitor() {
			public boolean visit(FieldDeclaration node) {
				// public�Ȥ�static�Ȥ�����Ӌ������Ӥ��Ĥ��Ƥ��鏎��
				if (Modifier.NONE != node.getModifiers()) {
					throw new IllegalArgumentException(typeString);
				}
				types.add(node.getType());
				return super.visit(node);
			}
		});

		// IProblem��һ�ĤǤ⤢��Џ���
		if (unit.getProblems().length > 0) {
			throw new IllegalArgumentException(typeString);
		}
		type = types.get(0);
	}

	/**
	 * Type�Ω`�ɤ����ɤ���
	 * 
	 * @param target
	 *            Type�Ω`�ɤ�׷�Ӥ�����AST
	 * @return Type�Ω`��
	 */
	public Type build(AST target) {
		return (Type) ASTNode.copySubtree(target, type);
	}
}