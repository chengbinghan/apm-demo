package com.hcb.asm.transformmethod;

import java.util.*;

import org.objectweb.asm.*;

public class CDump implements Opcodes {

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(V1_5, ACC_PUBLIC + ACC_SUPER, "com/hcb/asm/transformmethod/C", null, "java/lang/Object", null);

        {
            fv = cw.visitField(ACC_PUBLIC + ACC_STATIC, "timer", "J", null, null);
            fv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "m", "()V", null, new String[]{"java/lang/Exception"});
            mv.visitCode();
            mv.visitFieldInsn(GETSTATIC, "com/hcb/asm/transformmethod/C", "timer", "J");
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitInsn(LSUB);
            mv.visitFieldInsn(PUTSTATIC, "com/hcb/asm/transformmethod/C", "timer", "J");
            mv.visitLdcInsn(new Long(100L));
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "sleep", "(J)V", false);
            mv.visitFieldInsn(GETSTATIC, "com/hcb/asm/transformmethod/C", "timer", "J");
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitInsn(LADD);
            mv.visitFieldInsn(PUTSTATIC, "com/hcb/asm/transformmethod/C", "timer", "J");
            mv.visitInsn(RETURN);
            mv.visitMaxs(4, 1);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}
