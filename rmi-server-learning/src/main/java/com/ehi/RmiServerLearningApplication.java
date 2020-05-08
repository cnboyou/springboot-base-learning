package com.ehi;

import com.ehi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.rmi.RemoteException;

@SpringBootApplication
public class RmiServerLearningApplication {

    @Autowired
    private IUserService userService;

    //@Autowired
    //private IPermissionService permissionService;

    @Bean
    public RmiServiceExporter rmiServiceExporter(){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("userService");
        rmiServiceExporter.setService(userService);
        rmiServiceExporter.setServiceInterface(IUserService.class);
        rmiServiceExporter.setRegistryPort(2002);// 默认为1099，注意占用问题
        try {
            rmiServiceExporter.afterPropertiesSet();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return rmiServiceExporter;
    }

    public static void main(String[] args) {
        SpringApplication.run(RmiServerLearningApplication.class, args);
    }

}
