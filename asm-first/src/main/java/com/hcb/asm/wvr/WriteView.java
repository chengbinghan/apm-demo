package com.hcb.asm.wvr;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author ChengBing Han
 * @date 14:36  2018/5/29
 * @description 生成一个接口
 */
public class WriteView {


    public static void main(String[] args) throws IOException {

        ClassWriter cw = new ClassWriter(0);
        cw.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE,
                "com/hcb/asm/generate/Comparable1234", null, "java/lang/Object",
               null);
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "LESS", "I",
                null, new Integer(92222)).visitEnd();
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "EQUAL", "I",
                null, new Integer(520)).visitEnd();
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "GREATER", "I",
                null, new Integer(1314520)).visitEnd();
        cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo",
                "(Ljava/lang/Object;)I", null, null).visitEnd();
        cw.visitEnd();
        byte[] b = cw.toByteArray();


        /**
         * 会复制所有的方法，变量
         */
        // cv forwards all events to cw
        ClassVisitor cv = new ClassVisitor(Opcodes.ASM4, cw) {};
        ClassReader cr = new ClassReader(b);
        cr.accept(cv, 0);
        byte[] b2 = cw.toByteArray(); // b2 represents the same class as b1


        //将上述生成的byts 写入一个XX.class 文件中，再通过一个反编译软件就可以看到生成的类。
        File file = new File("Comparable1234.class");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(b2);
        fos.close();

    }
}
