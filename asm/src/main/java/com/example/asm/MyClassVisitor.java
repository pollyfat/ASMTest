package com.example.asm;

import static org.objectweb.asm.Opcodes.ASM9;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: hmei
 * @date: 2021/10/28
 * @email: huangmei@haohaozhu.com
 */
public class MyClassVisitor extends ClassVisitor {
    private Set<String> dynamicClick;
    private Set<String> butterKnifeClick;

    public MyClassVisitor() {
        super(ASM9);
    }

    public MyClassVisitor(ClassVisitor classVisitor) {
        super(ASM9, classVisitor);
    }

    private List<String> interfaces;

    /**
     * @param version    表示jdk的版本
     * @param access     当前类的修饰符 （这个和ASM 和 java有些差异，比如public 在这里就是ACC_PUBLIC）
     * @param name       当前类名
     * @param signature  泛型信息
     * @param superName  当前类的父类
     * @param interfaces 当前类实现的接口列表
     */
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.interfaces = Arrays.asList(interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        mv = new AdviceAdapter(Opcodes.ASM7, mv, access, name, descriptor) {
            @Override
            protected void onMethodEnter() {
                super.onMethodEnter();
                if (dynamicClick != null && dynamicClick.contains(name)) {
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "getContext", "()Landroid/content/Context;", false);
                    mv.visitLdcInsn("Lambda Toast");
                    mv.visitTypeInsn(CHECKCAST, "java/lang/CharSequence");
                    mv.visitInsn(ICONST_0);
                    mv.visitMethodInsn(INVOKESTATIC, "android/widget/Toast", "makeText", "(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;", false);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "android/widget/Toast", "show", "()V", false);
                    mv.visitEnd();
                }

//                if (butterKnifeClick!= null && butterKnifeClick.contains(name)) {
//                    mv.visitVarInsn(ALOAD, 0);
//                    mv.visitTypeInsn(CHECKCAST, "android/content/Context");
//                    mv.visitLdcInsn("Butter Knife Toast");
//                    mv.visitTypeInsn(CHECKCAST, "java/lang/CharSequence");
//                    mv.visitInsn(ICONST_0);
//                    mv.visitMethodInsn(INVOKESTATIC, "android/widget/Toast", "makeText", "(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;", false);
//                    mv.visitMethodInsn(INVOKEVIRTUAL, "android/widget/Toast", "show", "()V", false);
//                }
//                if (interfaces.contains("android/view/View$OnClickListener") && (name + descriptor).equals("onClick(Landroid/view/View;)V")) {
//                    mv.visitVarInsn(ALOAD, 0);
//                    mv.visitTypeInsn(CHECKCAST, "android/content/Context");
//                    mv.visitLdcInsn("Interface Toast");
//                    mv.visitTypeInsn(CHECKCAST, "java/lang/CharSequence");
//                    mv.visitInsn(ICONST_0);
//                    mv.visitMethodInsn(INVOKESTATIC, "android/widget/Toast", "makeText", "(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;", false);
//                    mv.visitMethodInsn(INVOKEVIRTUAL, "android/widget/Toast", "show", "()V", false);
//
//                }
                }

            @Override
            protected void onMethodExit(int opcode) {
                super.onMethodExit(opcode);

            }

            @Override
            public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
                super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
                if (name.equals("onClick") && descriptor.contains("Landroid/view/View$OnClickListener;")) {
                    if (dynamicClick == null) {
                        dynamicClick = new HashSet<>();
                    }
                    if (bootstrapMethodArguments != null && bootstrapMethodArguments.length > 2) {
                        if (bootstrapMethodArguments[1] instanceof Handle) {
                            dynamicClick.add(((Handle) bootstrapMethodArguments[1]).getName());
                        }
                    }
                }
            }

//            @Override
//            public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
//                if (descriptor.equals("Lbutterknife/OnClick;")) {
//                    if (butterKnifeClick == null) {
//                        butterKnifeClick = new HashSet<>();
//                    }
//                    butterKnifeClick.add(name);
//                }
//                return super.visitAnnotation(descriptor, visible);
//            }
        };
        return mv;
    }
}
