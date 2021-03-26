/*
 * This software is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain.
 * 
 * This software is experimental. NIST assumes no responsibility whatsoever 
 * for its use by other parties, and makes no guarantees, expressed or 
 * implied, about its quality, reliability, or any other characteristic. 
 * We would appreciate acknowledgement if the software is used. 
 * This software can be redistributed and/or modified freely provided 
 * that any derivative works bear some notice that they are derived from it, 
 * and any modified versions bear some notice that they have been modified.
 * 
 *  See http://www.copyright.gov/title17/92chap1.html#105
 * 
 */
package java2slice;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class ClassInspector {

    private final ClassLoader loader;
    private Set<Class<?>> seqenceClassesNeeded = new HashSet<>();
    private Map<Class<?>, Map<String, ClassInfo>> propertiesMapMap = new HashMap<>();

    public Set<Class<?>> getSeqenceClassesNeeded() {
        return Collections.unmodifiableSet(seqenceClassesNeeded);
    }

    public ClassInspector(ClassLoader loader) {
        this.loader = loader;
    }

    private static String getterNameToPropertyName(String getterName) {
        if (getterName.startsWith("get")) {
            return getterName.substring(3, 4).toLowerCase() + getterName.substring(4);
        } else if (getterName.startsWith("is")) {
            return getterName.substring(2, 3).toLowerCase() + getterName.substring(3);
        } else {
            throw new IllegalArgumentException(getterName + " must start with get/is");
        }
    }

    private static String setterNameToPropertyName(String setterName) {
        if (setterName.startsWith("set")) {
            return setterName.substring(3, 4).toLowerCase() + setterName.substring(4);
        } else {
            throw new IllegalArgumentException(setterName + " must start with set");
        }
    }

    private ClassNotFoundException e = null;

    public class ClassInfo<T> {

        private final Class<T> propertyClass;
        private final ParameterizedType parType;
        private final List<Class<?>> genericParamClasses;

        private Class<?> loadClass(String s) {
            try {
                return loader.loadClass(s);
            } catch (ClassNotFoundException ex) {
                e = ex;
                Logger.getLogger(ClassInspector.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

        public ClassInfo(Class<T> clss, Type pt) {
            this.propertyClass = clss;
            this.parType = (pt != null && pt instanceof ParameterizedType)
                    ? (ParameterizedType) pt
                    : null;
            genericParamClasses = new ArrayList<>();
            if (null != parType) {
                for (Type t : parType.getActualTypeArguments()) {
                    String names[] = t.toString().split("[ ]+");
                    try {
                        String clssname = names[names.length - 1];
                        Class parClss = loader.loadClass(clssname);
                        genericParamClasses.add(parClss);
                    } //                parType.map(ParameterizedType::getActualTypeArguments)
                    //                    .map(Stream::of)
                    //                    .orElse(Stream.empty())
                    //                    .map(Type::getTypeName)
                    //                    .map(this::loadClass)
                    //                    .collect(Collectors.toList());
                    catch (ClassNotFoundException ex) {
                        System.err.println("clss=" + clss);
                        System.err.println("pt=" + pt);
                        System.err.println("names="+Arrays.toString(names));
                        Logger.getLogger(ClassInspector.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        public Class<T> getPropertyClass() {
            return propertyClass;
        }

        public ParameterizedType getParType() {
            return parType;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 11 * hash + Objects.hashCode(this.propertyClass);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ClassInfo<?> other = (ClassInfo<?>) obj;
            if (!Objects.equals(this.propertyClass, other.propertyClass)) {
                return false;
            }
            return true;
        }

        public List<Class<?>> getGenericParamClasses() {
            return genericParamClasses;
        }

    };

    @SuppressWarnings("unchecked")
    public Map<String, ClassInfo> getProperties(Class clss)  {
        Map<String, ClassInfo> map = null;
        try {
            map = propertiesMapMap.get(clss);
            if (null != map) {
                return map;
            }
            map = new HashMap<>();
            Method methods[] = clss.getDeclaredMethods();
            Map<String, Method> getters = new HashMap<>();
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                if (method.getParameterTypes() != null && method.getParameterTypes().length > 0) {
                    continue;
                }
                if (!Modifier.isPublic(method.getModifiers())) {
                    continue;
                }
                Class<?> returnClass = method.getReturnType();
                if (returnClass == null) {
                    continue;
                }
                if (returnClass.equals(Void.class)) {
                    continue;
                }
                if (returnClass.equals(void.class)) {
                    continue;
                }
                if (returnClass.equals(java.lang.Class.class)) {
                    continue;
                }
                if (!method.getName().startsWith("get") && !method.getName().startsWith("is")) {
                    continue;
                }
                String propName = getterNameToPropertyName(method.getName());
                if (propName.equals("class")) {
                    continue;
                }
                getters.put(propName, method);
            }
            
            Map<String, Method> setters = new HashMap<>();
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                if (method.getParameterTypes() == null || method.getParameterTypes().length != 1) {
                    continue;
                }
                if (!Modifier.isPublic(method.getModifiers())) {
                    continue;
                }
                Class<?> returnClass = method.getReturnType();
                if (returnClass != null && !returnClass.equals(Void.class) && !returnClass.equals(void.class)) {
                    continue;
                }
                if (!method.getName().startsWith("set")) {
                    continue;
                }
                String propName = setterNameToPropertyName(method.getName());
                setters.put(propName, method);
            }
//        Map<String, Method> setters = Stream.of(clss.getDeclaredMethods())
//                .filter((Method m) -> m.getParameterCount() == 1)
//                .filter((Method m) -> Modifier.isPublic(m.getModifiers()))
//                .filter((Method m) -> m.getName().startsWith("set"))
//                .collect(Collectors.toMap((Method m) -> setterNameToPropertyName(m.getName()),
//                        (m) -> m));
//        System.out.println("setters = " + setters);
            
            for (Entry<String, Method> entry : getters.entrySet()) {
                ClassInfo ci = new ClassInfo(entry.getValue().getReturnType(),
                        entry.getValue().getGenericReturnType());
                if (List.class.isAssignableFrom(entry.getValue().getReturnType())) {
                    
                    List<Class<?>> paramClasses = (List<Class<?>>) ci.getGenericParamClasses();
                    seqenceClassesNeeded.add((Class<?>) paramClasses.get(0));
                } else if (!setters.containsKey(entry.getKey())) {
                    continue;
                }
                
                map.put(entry.getKey(), ci);
            }
//        map = getters.entrySet().stream()
//                .filter((Entry<String, Method> e)
//                        -> List.class.isAssignableFrom(e.getValue().getReturnType())
//                        || Objects.equals(e.getValue(), setters.get(e.getKey())))
//                .collect(
//                        Collectors.toMap((Entry<String, Method> e) -> e.getKey(),
//                                (Entry<String, Method> e) -> {
//                                    ClassInfo ci = new ClassInfo(e.getValue().getReturnType(),
//                                            e.getValue().getGenericReturnType());
//                                    seqenceClassesNeeded.add((Class<?>) ci.getGenericParamClasses().get(0));
//                                    return ci;
//                                }));
        } catch (Throwable exception) {
            System.err.println("Can't get properties for class = "+clss);
            Logger.getLogger(ClassInspector.class.getName()).log(Level.SEVERE, null, exception);
//            throw new RuntimeException("Can't get properties for class = "+clss, e);
        }
        if (null != e) {
            System.err.println("Can't get properties for class = "+clss);
            Logger.getLogger(ClassInspector.class.getName()).log(Level.SEVERE, null, e);
//            throw new RuntimeException("Can't get properties for class = "+clss, e);
        }
        propertiesMapMap.put(clss, map);
        return map;
    }

}
