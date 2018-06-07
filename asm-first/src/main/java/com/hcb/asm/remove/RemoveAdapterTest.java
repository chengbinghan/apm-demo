package com.hcb.asm.remove;

import com.hcb.asm.transform.ChangeVersionAdapter;
import org.objectweb.asm.ClassReader;
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
public class RemoveAdapterTest {


    public static void main(String[] args) throws IOException {

        ClassWriter cw = new ClassWriter(0);
        cw.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE,
                "com/hcb/asm/generate/ComparableRemove", null, "java/lang/Object",
                null);
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "LESS", "I",
                null, new Integer(32123)).visitEnd();
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "EQUAL", "I",
                null, new Integer(520)).visitEnd();
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "GREATER", "I",
                null, new Integer(1314520)).visitEnd();
        cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo",
                "(Ljava/lang/Object;)I", null, null).visitEnd();
        cw.visitEnd();
        byte[] b = cw.toByteArray();


        ClassReader cr = new ClassReader(b);
        ClassWriter cw2 = new ClassWriter(cr, 0);
        //动态修改类名
        RemoveMethodAdapter ca = new RemoveMethodAdapter(cw2,"compareTo","(Ljava/lang/Object;)I");
        cr.accept(ca, 0);
        byte[] b2 = cw2.toByteArray();


        //将上述生成的byts 写入一个XX.class 文件中，再通过一个反编译软件就可以看到生成的类。
        File file = new File("ComparableRemove.class");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(b2);
        fos.close();

    }
}
