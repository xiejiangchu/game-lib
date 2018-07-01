package com.isnowfox.io.serialize.tool;

import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.net.URL;

public class Config {
    public static final String FILE_SUFFIX = ".m";
    private static final String DATA_TOOL_CONFIG_XML = "msgToolConfig.xml";
    private static final XStream xstream = new XStream();

    public static final Config load() {
        File file = new File(DATA_TOOL_CONFIG_XML);
        if (file.exists()) {
            return (Config) xstream.fromXML(file);
        } else {
            Config c = new Config();

            URL u = Config.class.getResource("/msg");
            if (u != null) {
                c.setPath(u.getFile());
            } else {
                File f = new File("msgConfig");
                c.setPath("C:\\git\\majiang\\server\\project\\mj-data\\src\\main\\resources\\msg");
            }

            c.setJavaSrcPath("C:\\git\\game-lib\\project\\isnowfox-serialize\\src\\main\\resources\\test");
            c.setAsSrcPath("C:\\git\\game-lib\\project\\isnowfox-serialize\\src\\main\\resources\\test\\as");
            c.setJavaRootPackage("game.net.message");
            c.setJavaHandlerRootPackage("game.net.handler");
            c.setAsRootPackage("");
            c.setAsHandlerRootPackage("handler");
            c.setTypeSrcPath("C:\\git\\game-lib\\project\\isnowfox-serialize\\src\\main\\resources\\test\\ts");
            c.setTypeRootPackage("factory");
            c.setTypeHandlerRootPackage("handler");

            return c;
        }
    }

    private String path;
    private String javaSrcPath;
    private String asSrcPath;
    private String typeSrcPath;

    private String javaRootPackage;
    private String asRootPackage;
    private String typeRootPackage;

    private String javaHandlerRootPackage;
    private String asHandlerRootPackage;
    private String typeHandlerRootPackage;

    private String javaCharacterPackage;
    private String javaCharacterClassName;

    private String asDirName;
    private String javaDirName;
    private boolean isOverrideHandler = false;


    public void save() throws FileNotFoundException, IOException {
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(DATA_TOOL_CONFIG_XML))) {
            xstream.toXML(this, out);
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getJavaSrcPath() {
        return javaSrcPath;
    }

    public void setJavaSrcPath(String javaSrcPath) {
        this.javaSrcPath = javaSrcPath;
    }

    public String getAsSrcPath() {
        return asSrcPath;
    }

    public void setAsSrcPath(String asSrcPath) {
        this.asSrcPath = asSrcPath;
    }

    public String getJavaRootPackage() {
        return javaRootPackage;
    }

    public void setJavaRootPackage(String javaRootPackage) {
        this.javaRootPackage = javaRootPackage;
    }

    public String getAsRootPackage() {
        return asRootPackage;
    }

    public String getTypeRootPackage() {
        return typeRootPackage;
    }

    public void setAsRootPackage(String asRootPackage) {
        this.asRootPackage = asRootPackage;
    }

    public final String getJavaHandlerRootPackage() {
        return javaHandlerRootPackage;
    }

    public final void setJavaHandlerRootPackage(String javaHandlerRootPackage) {
        this.javaHandlerRootPackage = javaHandlerRootPackage;
    }

    public final String getAsHandlerRootPackage() {
        return asHandlerRootPackage;
    }

    public final void setAsHandlerRootPackage(String asHandlerRootPackage) {
        this.asHandlerRootPackage = asHandlerRootPackage;
    }

    public String getJavaCharacterPackage() {
        return javaCharacterPackage;
    }

    public void setJavaCharacterPackage(String javaCharacterPackage) {
        this.javaCharacterPackage = javaCharacterPackage;
    }

    public String getJavaCharacterClassName() {
        return javaCharacterClassName;
    }

    public void setJavaCharacterClassName(String javaCharacterClassName) {
        this.javaCharacterClassName = javaCharacterClassName;
    }

    public String getAsDirName() {
        return asDirName;
    }

    public void setAsDirName(String asDirName) {
        this.asDirName = asDirName;
    }

    public String getJavaDirName() {
        return javaDirName;
    }

    public void setJavaDirName(String javaDirName) {
        this.javaDirName = javaDirName;
    }

    public boolean isOverrideHandler() {
        return isOverrideHandler;
    }

    public String getTypeSrcPath() {
        return typeSrcPath;
    }

    public void setTypeSrcPath(String typeSrcPath) {
        this.typeSrcPath = typeSrcPath;
    }

    public void setTypeRootPackage(String typeRootPackage) {
        this.typeRootPackage = typeRootPackage;
    }

    public String getTypeHandlerRootPackage() {
        return typeHandlerRootPackage;
    }

    public void setTypeHandlerRootPackage(String typeHandlerRootPackage) {
        this.typeHandlerRootPackage = typeHandlerRootPackage;
    }

    public void setOverrideHandler(boolean overrideHandler) {
        isOverrideHandler = overrideHandler;
    }

    @Override
    public String toString() {
        return "Config [path=" + path + ", javaSrcPath=" + javaSrcPath
                + ", asSrcPath=" + asSrcPath + ", javaRootPackage="
                + javaRootPackage + ", asRootPackage=" + asRootPackage
                + ", javaHandlerRootPackage=" + javaHandlerRootPackage
                + ", asHandlerRootPackage=" + asHandlerRootPackage + "]";
    }
}
