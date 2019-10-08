package com.resolute.CodeProvider.test;

import org.testng.Assert;
import org.testng.TestNGException;
import org.testng.annotations.Test;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.test.BTestNg;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.logging.Logger;

@NiagaraType

public class BClassloadingTest extends BTestNg {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.resolute.CodProvider.BClassloadingTest(2979906276)1.0$ @*/
/* Generated Tue Oct 08 12:35:26 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BClassloadingTest.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    private static Logger logger = Logger.getLogger("ClassloadingTest");

    @Test
    public void setClassPath(){
        String classpath;
        try{
             classpath = AccessController.doPrivileged(
                    (PrivilegedAction<String>)() -> {
                        String[] propArray = System.getProperty("java.class.path").split(";");

                        ArrayList<String> propList = new ArrayList(propArray.length + 1);
                        propList.add(
                                "C:\\Users\\Victor Smolinski\\Niagara4.7\\vykon\\CodeProvider\\CodeProvider\\CodeProvider-rt\\build\\libs\\CodeProvider-rt-1.0.jar"
                        );
                        String cp = String.join(";", propList);
                        System.setProperty("java.class.path", cp);
                        return cp;
                    });
        }catch(SecurityException se){
            classpath = se.getMessage();
            logger.severe(se.getMessage());
            se.printStackTrace();
        }
        Assert.assertEquals(System.getProperty("java.class.path"), classpath);
    }

    @Test
    public void createClassLoader_checkSecurityMgr(){
        try{
            AccessController.doPrivileged(
                    (PrivilegedAction<Void>)() -> {
                        if(System.getSecurityManager() != null){
                            SecurityManager secMgr = System.getSecurityManager();
                            secMgr.checkCreateClassLoader();
                        }else{
                            throw new TestNGException("System.getSecurityManager() returns null...!");
                        }
                        return null;
                    });
        }catch(SecurityException se){
            throw new TestNGException("Security manager classloader error...\n" +
                    se.getMessage());
        }
    }

    @Test
    public void creatClassLoader_checkAccessController(){
        try{
            AccessController.doPrivileged( (PrivilegedAction<Void>)() -> {
                AccessController.checkPermission(new RuntimePermission("createClassLoader"));
                return null;
            });
        }catch(SecurityException se){
            throw new TestNGException("AccessController classloader error...\n" +
                    se.getMessage());
        }
    }

    @Test
    public void setClassloader(){
        try{
            AccessController.doPrivileged( (PrivilegedAction<Void>)() -> {
                AccessController.checkPermission(new RuntimePermission("setContextClassLoader"));
                return null;
            });
        }catch(SecurityException se){
            throw new TestNGException("AccessController setContextClassLoader error...\n" +
                    se.getMessage());
        }
    }

    @Test
    public void accessClassInPackage_com_resolute_codeProvider(){
        try{
            AccessController.doPrivileged( (PrivilegedAction<Void>)() -> {
                AccessController.checkPermission(new RuntimePermission("accessClassInPackage.com.resolute.CodeProvider"));
                return null;
            });
        }catch(SecurityException se){
            throw new TestNGException("AccessController accessClassInPackage for com.resolute - error...\n" +
                    se.getMessage());
        }
    }

    @Test
    public void accessClassInPackage_someTridiumPackage(){
        try{
            AccessController.doPrivileged( (PrivilegedAction<Void>)() -> {
                AccessController.checkPermission(new RuntimePermission("accessClassInPackage.com.tridium.program"));
                return null;
            });
        }catch(SecurityException se){
            throw new TestNGException("AccessController accessClassInPackage for  com.tridium - error...\n" +
                    se.getMessage());
        }
    }

    @Test
    public void loadClass_from_URLClassloader_Instance(){
        String msg = AccessController.doPrivileged((PrivilegedAction<String>)()  -> {
            try {
                URL[] resourceURLs =
                        new URL[]{
                                new URL(
                                        "file://Users/Victor Smolinski/Niagara4.7/vykon/CodeProvider/CodeProvider/resolute-rt/build/libs/CodeProvider-rt-1.0.jar"),
                                new URL("file://CodeProvider/CodeProvider-rt/build/libs/CodeProvider-rt-1.0.jar"),
                                new URL("file://Users/Victor Smolinski/Niagara4.7/vykon/CodeProvider/CodeProvider/libs/CodeProvider-rt-1.0.jar"),
                                new URL("file://CodeProvider/CodeProvider/libs/CodeProvider-rt-1.0.jar")
                        };

                URLClassLoader loader = new URLClassLoader(resourceURLs);

                Class<?> patchClass = loader.loadClass("com.resolute.CodeProvider.BinPatch");

                Constructor<?> constructor = patchClass.getConstructor();
                Object obj = constructor.newInstance();

                Method getBinMsg  = patchClass.getMethod("getBinMessage");
                Method getBinVer = patchClass.getMethod("getBinVersion");

                String result = getBinMsg.invoke(obj) +"-" + getBinVer.invoke(obj) ;

                return result;
            } catch (MalformedURLException |
                    SecurityException       |
                    ClassNotFoundException  |
                    NoSuchMethodException   |
                    IllegalAccessException  |
                    InstantiationException  |
                    InvocationTargetException cnfe) {
                cnfe.printStackTrace();

                throw new TestNGException(cnfe.getMessage());
            }
        });
        Assert.assertEquals(msg, "BinPatch-1.0");
    }
}
