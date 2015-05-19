package util.visitor;

import util.Print;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;



public class SampleVisitor1 extends ASTVisitor {
	
	public boolean visit(TypeDeclaration node) {
		Print.printTitle("���饹����");

		ITypeBinding typeBinding = node.resolveBinding();// Ԕ��������ITypeBinding���󥹥��󥹤�ʹ�ä�ȡ�ä�����
		ITypeBinding superClass = typeBinding.getSuperclass();// �H���饹��ȡ��
		ITypeBinding[] interfaces = typeBinding.getInterfaces();// ���󥿩`�ե��`����ȡ��
		String className = typeBinding.getBinaryName();// ���饹����ȡ��
		int modifiers = typeBinding.getModifiers();// "public static"�Ȥ����R�e��

		Print.printMessage("ClassName", className);
		Print.printModifiers("Modifiers", modifiers);
		Print.printMessage("SuperClass", superClass.getBinaryName());
		Print.printMessage("Interfaces", interfaces);
		return super.visit(node);
	}

	/**
	 * �ե��`������Ԥ�Ҋ�Ĥ���Ⱥ��Ф��᥽�å�
	 */
	public boolean visit(FieldDeclaration node) {
		Print.printTitle("�ե��`�������");
		Print.printModifiers("Modifiers", node.getModifiers());
		Print.printMessage("Type", node.getType().toString());

		List<?> fragments = node.fragments();
		for (Object frg : fragments) {
			if (frg instanceof VariableDeclarationFragment) {
				IVariableBinding variableBinding = ((VariableDeclarationFragment) frg)
						.resolveBinding();
				Print.printMessage("Name", variableBinding.getName());
			}
		}
		return super.visit(node);
	}

	/**
	 * �᥽�å����Ԥ�Ҋ�Ĥ���Ⱥ��Ф��᥽�å�
	 */
	public boolean visit(MethodDeclaration node) {
		Print.printTitle("�᥽�å�����");
		Print.printMessage("MethodName", node.getName()
				.getFullyQualifiedName());
		Print.printModifiers("Modifiers", node.getModifiers());
		Print.printMessage("ReturnType", node.getReturnType2() + "");
		Print.printMessage("Parameters", node.parameters().toString());
		return super.visit(node);
		
	}
}