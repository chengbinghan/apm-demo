package com.hcb.asm.transform;


import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class ChangeVersionAdapter extends ClassVisitor {
    public ChangeVersionAdapter(ClassVisitor cv) {
        super(Opcodes.ASM4, cv);
    }

    @Override
    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces) {

        cv.visit(Opcodes.V1_5, access, name + "NEW_NAME", signature, superName, interfaces);
    }
}