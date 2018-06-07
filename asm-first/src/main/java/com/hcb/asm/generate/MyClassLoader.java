package com.hcb.asm.generate;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MyClassLoader extends ClassLoader {
    String myLibPath = null;

    public MyClassLoader(String path) {
        if (path != null && !"".equals(path.trim())) {
            myLibPath = path;
        }

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String className = getClassName(name);

        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(myLibPath + "/" + className);
            bos = new ByteArrayOutputStream();
            int len = 0;

            while ((len = fis.read()) != -1) {
                bos.write(len);
            }

            byte[] classDataArr = bos.toByteArray();

           return defineClass(name, classDataArr, 0, classDataArr.length);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bos != null) {
                try{

                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return super.findClass(name);
    }

    private String getClassName(String name) {
        int index = name.lastIndexOf(".");
        if (index != -1) {
            return name.substring(index +1) + ".class";
        } else {
            return name + ".class";
        }


    }
}
