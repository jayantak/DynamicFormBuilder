package com.springapp.mvc;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;

public class Server {
    public static void main(String... args) throws LifecycleException, ServletException, IOException {
        String WAR_FILE_SOURCE = "./target/";
        String WAR_FILE_NAME = "DynamicFormBuilder.war";
        String TOMCAT_SOURCE_FOLDER = "./tomcat/";
        String WAR_FILE_PATH = WAR_FILE_SOURCE + WAR_FILE_NAME;

        File tomcatBaseDir = new File(TOMCAT_SOURCE_FOLDER);
        String tomcatBaseDirPath = tomcatBaseDir.getAbsolutePath();
        if (!tomcatBaseDir.exists()) {
            tomcatBaseDir.mkdir();
        }

        File warFileToDeploy = new File(WAR_FILE_PATH);

        Tomcat server = new Tomcat();
        server.setBaseDir(tomcatBaseDirPath);
        server.getHost().setAppBase(tomcatBaseDirPath);
        server.getHost().setAutoDeploy(true);
        server.getHost().setDeployOnStartup(true);

        server.addWebapp(server.getHost(), "", warFileToDeploy.getAbsolutePath());
        server.start();
        server.getServer().await();
    }
}