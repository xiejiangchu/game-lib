package com.isnowfox.io.serialize.tool.model;

import com.isnowfox.io.serialize.tool.Config;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.Map;

public class Utils {
    private Map<String, Package> map;
    private Config config;

    public Utils(Map<String, Package> map, Config config) {
        this.map = map;
        this.config = config;
    }

    public String getJavaPack(Message m) {
        if (StringUtils.isEmpty(m.getPackageName())) {
            return config.getJavaRootPackage();
        } else {
            return config.getJavaRootPackage() + "." + m.getPackageName();
        }
    }

    public String getJavaHandlerRootPackage() {
        return config.getJavaHandlerRootPackage();
    }

    public String getAsPack(Message m) {
        if (StringUtils.isEmpty(m.getPackageName())) {
            return config.getAsRootPackage();
        } else {
            return config.getAsRootPackage() + "." + m.getPackageName();
        }
    }

    public String getTypePack(Message m) {
        return "message";
    }

    public String getTypeHandlerPack(Message m) {
        return config.getAsHandlerRootPackage();
    }

    public String getAsHandlerPack(Message m) {
        if (StringUtils.isEmpty(m.getPackageName())) {
            return config.getAsHandlerRootPackage();
        } else {
            return config.getAsHandlerRootPackage() + "." + m.getPackageName();
        }
    }

    public String getJavaHandlerPack(Message m) {
        if (StringUtils.isEmpty(m.getPackageName())) {
            return config.getJavaHandlerRootPackage();
        } else {
            return config.getJavaHandlerRootPackage() + "." + m.getPackageName();
        }
    }

    public String getJavaType(Attribute a) {
        if (a.getType() == AttributeType.OTHER) {
            if (a.isArray()) {
                return "java.util.ArrayList<" + getOtherType(a, 0) + ">";
            } else {
                return getOtherType(a, 0);
            }
        } else {
            return a.getJavaTypeString();
        }
    }

    public String getAsTypeName(Attribute a) {
        if (a.getType() == AttributeType.OTHER) {
            String name = getOtherType(a, 1);
            if (a.isArray()) {
                return "Vector.<" + name + ">";
            } else {
                return name;
            }
        } else {
            return a.getAsTypeString();
        }
    }

    public String getTypeTypeName(Attribute a) {
        if (a.getType() == AttributeType.OTHER) {
            String name = getOtherType(a, 2);
            if (a.isArray()) {
                return "Array<" + name + ">";
            } else {
                return name;
            }
        } else {
            return a.getTypeTypeString();
        }
    }

    public String getLayaTypeName(Attribute a) {
        if (a.getType() == AttributeType.OTHER) {
            String name = getOtherType(a, 1);
            if (a.isArray()) {
                return "Array";
            } else {
                return name;
            }
        } else {
            return a.getLayaTypeString();
        }
    }

    public String getAsTypeNoArray(Attribute a) {
        if (a.getType() == AttributeType.OTHER) {
            String name = getOtherType(a, 1);
            return name;
        } else {
            return a.getAsTypeString();
        }
    }

    public String getTypeTypeNoArray(Attribute a) {
        if (a.getType() == AttributeType.OTHER) {
            String name = getOtherType(a, 2);
            return name;
        } else {
            return a.getTypeTypeString();
        }
    }

//    0 java  1 as 1 type
    private String getOtherType(Attribute a, int isJava) {
        String name = a.getTypeName();
        String pack = getPack(name);
        name = getName(name);
        if (StringUtils.isEmpty(pack)) {
            //先找自己，然后找默认
            Package p = a.getMessage().getPack();
            Message m = p.get(name);
            if (m != null) {
                if (isJava==0) {
                    return getJavaPack(m) + "." + name;
                } else if(isJava==1){
                    return getAsPack(m) + "." + name;
                }else if(isJava==2){
                    return name;
                }
            } else {
                //找默认包的
                p = map.get("");
                if (p != null) {
                    m = p.get(name);
                    if (m != null) {
                        if (isJava==0) {
                            return getJavaPack(m) + "." + name;
                        } else if(isJava==1){
                            return getAsPack(m) + "." + name;
                        }else if(isJava==2){
                            return name;
                        }
                    }
                }
            }
//			if(p)
        } else {
            Package p = map.get(pack);
            if (p != null) {
                Message m = p.get(name);
                if (m != null) {
                    if (isJava==0) {
                        return getJavaPack(m) + "." + name;
                    } else if(isJava==1){
                        return getAsPack(m) + "." + name;
                    }else if(isJava==2){
                        return name;
                    }
                }
            }
        }
        return a.getTypeName();
    }

    private String getPack(String name) {
        int index = name.lastIndexOf('.');
        if (index > -1) {
            return name.substring(0, index);
        } else {
            return null;
        }
    }

    private String getName(String name) {
        int index = name.lastIndexOf('.');
        if (index > -1) {
            return name.substring(index + 1, name.length());
        } else {
            return name;
        }
    }

    public final static File packToPath(String path, String packname, String name, String suffix) {
        File f = new File(path, packname.replace(".", File.separator));
        f = new File(f, name + suffix);
        return f;
    }

    public final static String formatComment(String comment, String indent) {
        return comment.replace("\n", "\r\n" + indent + "* ");
    }

    public static final String toClassName(String str) {
        StringBuilder sb = new StringBuilder();
        boolean isUp = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (0 == i) {
                sb.append(Character.toUpperCase(c));
            } else if (c == '_') {
                isUp = true;
            } else if (isUp) {
                isUp = false;
                sb.append(Character.toUpperCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static final String toFieldName(String str) {
        StringBuilder sb = new StringBuilder();
        boolean isUp = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0) {
                c = Character.toLowerCase(c);
            }
            if (c == '_') {
                isUp = true;
            } else if (isUp) {
                isUp = false;
                sb.append(Character.toUpperCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public Config getConfig() {
        return config;
    }
}
