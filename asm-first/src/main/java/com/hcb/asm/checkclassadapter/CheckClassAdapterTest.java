package com.hcb.asm.checkclassadapter;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.CheckClassAdapter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ChengBing Han
 * @date 14:36  2018/5/29
 * @description 生成一个接口, 接口输入到文件, 验证是否符合规范
 */
public class CheckClassAdapterTest {


    public static void main(String[] args) throws IOException {

        ClassWriter cw = new ClassWriter(0);


        File file1 = new File("bbbbbb.txt");
        file1.delete();
        if (!file1.exists()) {
            file1.createNewFile();
        }
        PrintWriter printWriter = new PrintWriter(file1);

        TraceClassVisitor tcv = new TraceClassVisitor(cw, printWriter);
        CheckClassAdapter cv = new CheckClassAdapter(tcv);


        //如果改变下面一些参数，那么都会不合规。会抛出异常。
        cv .visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE,
                "com/hcb/asm/generate1/Comparable1", null, "java/lang/Object",
                null);
        cv .visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "LESS", "I",
                null, new Integer(0)).visitEnd();
        cv.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "EQUAL", "I",
                null, new Integer(1)).visitEnd();
        cv.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "GREATER", "I",
                null, new Integer(2)).visitEnd();
        cv.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo",
                "(Ljava/lang/Object;)I", null, null).visitEnd();
        cv.visitEnd();

        byte[] b = null;
        b = cw.toByteArray();


        printWriter.close();


        //将上述生成的byts 写入一个XX.class 文件中，再通过一个反编译软件就可以看到生成的类。
        File file = new File("Comparable1.class");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(b);

        fos.close();

    }
}
