import com.hcb.asm.generate.Comparable123;
import com.hcb.asm.transform.ChangeVersionAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @author ChengBing Han
 * @date 16:44  2018/5/29
 * @description
 */
public class TestClazz {
    public static void main(String[] args) {
        System.out.println(Comparable123.EQUAL);
        System.out.println(com.hcb.asm.generate1.Comparable1.EQUAL);
    }


    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {
            public byte[] transform(ClassLoader l, String name, Class c,
                                    ProtectionDomain d, byte[] b)
                    throws IllegalClassFormatException {
                ClassReader cr = new ClassReader(b);
                ClassWriter cw = new ClassWriter(cr, 0);
                ClassVisitor cv = new ChangeVersionAdapter(cw);
                cr.accept(cv, 0);
                return cw.toByteArray();
            }
        });



    }
}
