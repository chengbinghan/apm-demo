package com.hcb.agent.load;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;

import com.hcb.agent.load.ClassLoaderFactory.Repository;
import com.hcb.agent.load.ClassLoaderFactory.RepositoryType;

/**
 * @author ChengBing Han
 * @date 15:18  2018/6/15
 * @description
 */
public class Start {


    static boolean flag = true;


    public static void agentmain(String agentArgs, Instrumentation inst) throws Exception {
        System.out.println("agentmain===invoke=============================>");


    }

    public static void main(String[] args) throws Exception {

        final ClassLoader myLoader = createClassLoader("myLoader");
        final Class<?> aClass = myLoader.loadClass("com.hcb.agent.update.MyTransformer3");
        final Object o = aClass.newInstance();
        System.out.println(o);

    }

    private static ClassLoader createClassLoader(String name)
            throws Exception {

        List<Repository> repositories = new ArrayList<>();

        String[] repositoryPaths = {"C:\\kingdee\\study\\bytecode\\bytecode-workspace\\apm-demo\\transforms\\target\\transforms-1.jar"};

        for (String repository : repositoryPaths) {
            // Check for a JAR URL repository
            try {
                @SuppressWarnings("unused")
                URL url = new URL(repository);
                repositories.add(
                        new Repository(repository, RepositoryType.URL));
                continue;
            } catch (MalformedURLException e) {
                // Ignore
            }

            // Local repository
            if (repository.endsWith("*.jar")) {
                repository = repository.substring
                        (0, repository.length() - "*.jar".length());
                repositories.add(
                        new Repository(repository, RepositoryType.GLOB));
            } else if (repository.endsWith(".jar")) {
                repositories.add(
                        new Repository(repository, RepositoryType.JAR));
            } else {
                repositories.add(
                        new Repository(repository, RepositoryType.DIR));
            }
        }

        return ClassLoaderFactory.createClassLoader(repositories, null);
    }


}
