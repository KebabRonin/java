package org.example;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

import static java.lang.Math.random;
import static java.lang.reflect.Modifier.isStatic;

public class MyTestFramework {
    public static void main(String[] args) throws Exception {
        int passed = 0, failed = 0;

        File f = new File(args[0]);
        StringTokenizer st = new StringTokenizer(args[0], "/\\");
        System.out.println(args[0]);
        String className = "";
        try {
            while (true) {
                className = st.nextToken();
            }
        } catch (NoSuchElementException ignored) {}
        className = new StringTokenizer(className, ".").nextToken();
        URL[] cp = {f.toURI().toURL()};
        System.out.println(cp[0]);
        URLClassLoader urlcl = new URLClassLoader(cp);
        String packName = new Object(){}.getClass().getPackage().getName();
        Class myclass  = urlcl.loadClass(packName + "." + className);
methods:        for (Method m : myclass.getMethods()) {
            int mods = m.getModifiers();
            List<Object> objs = new ArrayList<>();

            Parameter[] para = m.getParameters();
            for(Parameter p : para) {
                if(p.getType().toString().equals("int")) {
                    objs.add((int) (random() * 2000) - 1000);
                } else if(p.getType().toString().equals("String")) {
                    //objs.add((String) (random() * 2000) - 1000);
                } else {
                    continue methods;
                }
            }

            if (m.isAnnotationPresent(Test.class) && isStatic(mods)) {
                try {
                    m.invoke(null, objs.toArray());
                    passed++;
                } catch (Throwable ex) {
                    System.out.printf("Test %s failed: %s %n",
                            m, ex.getCause());
                    failed++;
                }
            }
        }
        System.out.printf("Passed: %d, Failed %d%n", passed, failed);
        System.out.println((int) ((float)passed/(Math.max(1, passed + failed)) * 100) + "% tests passed");
    }
}