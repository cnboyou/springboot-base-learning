package com.ehi.xml;

public class Receiver {


    public void processHello(String msg) {
        System.out.println("helloReceiver  : " + msg);
    }


    public void processUser(String msg) {
        System.out.println("userReceiver  : " + msg);
    }


    public void processTopicMessage(String msg) {
        System.out.println("topicMessageReceiver  : " + msg);
    }


    public void processTopicMessages(String msg) {
        System.out.println("topicMessagesReceiver  : " + msg);
    }


    public void processFanoutA(String msg) {
        System.out.println("fanoutAReceiver  : " + msg);
    }


    public void processFanoutB(String msg) {
        System.out.println("fanoutBReceiver  : " + msg);
    }


    public void processFanoutC(String msg) {
        System.out.println("fanoutCReceiver  : " + msg);
    }


}

