package com.example.asm;

import static org.objectweb.asm.Opcodes.ASM9;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;
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
    private Set<String> dialogClick;
    private Set<String> viewClick;
    private Set<String> butterKnifeClick;
    private Set<String> permissionValue;
    private boolean shouldLog = false;

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
        shouldLog = name.contains("TestAnnoActivity");
        if (shouldLog) {
            System.out.println("=========="+ name + "==========");
            System.out.println("MyClassVisitor -> trigger <visit(version:"+version+", access:"+ access + ", name" + name + ", signature:" + signature + ", superName:" + superName +">");
        }
    }
//
//    @Override
//    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
//        if (shouldLog) {
//            System.out.println("MyClassVisitor -> trigger <visitField(descriptor:"+descriptor+", access:"+ access + ", name" + name + ", signature:" + signature + ", value:" + value +">");
//        }
//        FieldVisitor fv = super.visitField(access, name, descriptor, signature, value);
//        fv = new FieldVisitor(Opcodes.ASM9, fv) {
//            @Override
//            public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
//                if (shouldLog) {
//                    System.out.println("FieldVisitor -> trigger <visitAnnotation(descriptor:"+descriptor+ ", visible:" + visible +">");
//                }
//                return super.visitAnnotation(descriptor, visible);
//            }
//
//            @Override
//            public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
//                if (shouldLog) {
//                    System.out.println("FieldVisitor -> trigger <visitTypeAnnotation(typeRef:"+typeRef+ ", typePath:" + typePath +"descriptor:"+descriptor+">");
//                }
//                return super.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
//            }
//
//            @Override
//            public void visitAttribute(Attribute attribute) {
//                if (shouldLog) {
//                    System.out.println("FieldVisitor -> trigger <visitAttribute(attribute:"+attribute.type+">");
//                }
//                super.visitAttribute(attribute);
//            }
//
//            @Override
//            public void visitEnd() {
//                if (shouldLog) {
//                    System.out.println("FieldVisitor -> trigger <visitEnd>");
//                }
//                super.visitEnd();
//            }
//        };
//        return fv;
//    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (shouldLog) {
            System.out.println("MyClassVisitor -> trigger <visitMethod(access:" + access + ", name:" + name + ", signature:" + signature + ", descriptor:" + descriptor + ">");
        }
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        final boolean[] needCopy = new boolean[1];
        mv = new AdviceAdapter(Opcodes.ASM9, mv, access, name, descriptor) {

            @Override
            public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
                super.visitLocalVariable(name, descriptor, signature, start, end, index);
                if (shouldLog) {
                    System.out.println("AdviceAdapter -> trigger <visitLocalVariable(descriptor:"+descriptor+", access:"+ access + ", name: " + name + ", signature:" + signature + ", start:" + start+ ", end:" + end +">");
                }
            }

            @Override
            public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
                super.visitFieldInsn(opcode, owner, name, descriptor);
                if (shouldLog) {
                    System.out.println("AdviceAdapter -> trigger <visitFieldInsn(opcode:" + opcode+ ", owner:" + owner + ", descriptor:" + descriptor+ ", name:" + name  + ")>");
                }
            }

            @Override
            protected void onMethodEnter() {
//                super.onMethodEnter();
                if (shouldLog) {
                    System.out.println("AdviceAdapter -> trigger <visitMethod(access:" + access + ", name:" + name + ", signature:" + signature + ", descriptor:" + descriptor + ">");
                }
                if (viewClick!= null && viewClick.contains(name)) {
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitTypeInsn(CHECKCAST, "android/content/Context");
                    mv.visitLdcInsn("View Click Toast");
                    mv.visitTypeInsn(CHECKCAST, "java/lang/CharSequence");
                    mv.visitInsn(ICONST_0);
                    mv.visitMethodInsn(INVOKESTATIC, "android/widget/Toast", "makeText", "(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;", false);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "android/widget/Toast", "show", "()V", false);
                }
                if (dialogClick != null && dialogClick.contains(name)) {

                }
                if (butterKnifeClick != null && butterKnifeClick.contains(name)) {
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitTypeInsn(CHECKCAST, "android/content/Context");
                    mv.visitLdcInsn("Butter Knife Toast");
                    mv.visitTypeInsn(CHECKCAST, "java/lang/CharSequence");
                    mv.visitInsn(ICONST_0);
                    mv.visitMethodInsn(INVOKESTATIC, "android/widget/Toast", "makeText", "(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;", false);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "android/widget/Toast", "show", "()V", false);
                }
                if (interfaces.contains("android/view/View$OnClickListener") && (name + descriptor).equals("onClick(Landroid/view/View;)V")) {
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitTypeInsn(CHECKCAST, "android/content/Context");
                    mv.visitLdcInsn("Interface Toast");
                    mv.visitTypeInsn(CHECKCAST, "java/lang/CharSequence");
                    mv.visitInsn(ICONST_0);
                    mv.visitMethodInsn(INVOKESTATIC, "android/widget/Toast", "makeText", "(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;", false);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "android/widget/Toast", "show", "()V", false);
                }
            }

            @Override
            protected void onMethodExit(int opcode) {
                super.onMethodExit(opcode);
                if (shouldLog) {
                    System.out.println("AdviceAdapter -> trigger <onMethodExit>");
                }
            }

            @Override
            public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
                super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
                if (shouldLog) {
                    System.out.println("AdviceAdapter -> trigger <visitInvokeDynamicInsn(name: " + name + ", descriptor:" + descriptor + ">");
                }
                if (name.equals("onClick") && descriptor.contains("Landroid/view/View$OnClickListener;")) {
                    if (viewClick == null) {
                        viewClick = new HashSet<>();
                    }
                    if (bootstrapMethodArguments != null && bootstrapMethodArguments.length > 2) {
                        if (bootstrapMethodArguments[1] instanceof Handle) {
                            viewClick.add(((Handle) bootstrapMethodArguments[1]).getName());
                        }
                    }
                }
                if (name.equals("onClick") && descriptor.contains("Landroid/content/DialogInterface$OnClickListener;")) {
                    if (dialogClick == null) {
                        dialogClick = new HashSet<>();
                    }
                    if (bootstrapMethodArguments != null && bootstrapMethodArguments.length > 2) {
                        if (bootstrapMethodArguments[1] instanceof Handle) {
                            dialogClick.add(((Handle) bootstrapMethodArguments[1]).getName());
                        }
                    }
                }
            }

            @Override
            public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
                needCopy[0] = true;
                if (shouldLog) {
                    System.out.println("AdviceAdapter -> trigger <visitAnnotation(visible:" + visible + ", descriptor:" + descriptor + ">");
                }
                if (descriptor.equals("Lbutterknife/OnClick;")) {
                    if (butterKnifeClick == null) {
                        butterKnifeClick = new HashSet<>();
                    }
                    butterKnifeClick.add(name);
                }

                return new AnnotationVisitor(ASM9, super.visitAnnotation(descriptor, visible)) {
                    @Override
                    public void visit(String name1, Object value) {
//                        permissionValue.add((String) value);
                        super.visit(name1, value);
                    }
                };
            }

            @Override
            public void visitEnd() {
                if (shouldLog) {
                    System.out.println("AdviceAdapter -> trigger <visitEnd>");
                }
                super.visitEnd();
            }
        };
        return mv;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        if (shouldLog) {
            System.out.println("MyClassVisitor -> trigger <visitAnnotation(visible:" + visible + ", descriptor:" + descriptor + ">");
        }
        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
