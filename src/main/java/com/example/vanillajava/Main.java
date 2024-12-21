package com.example.vanillajava;

import com.example.vanillajava.service.ListService;
import com.example.vanillajava.service.QueueService;
import com.example.vanillajava.service.SetService;

public class Main {

    SetService setService;
    ListService listService;
    QueueService queueService;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        Main main = new Main();
        main.run();
    }

    public Main(){
        System.out.println("MAIN constructor is running");
        this.setService = new SetService();
        this.listService = new ListService();
        this.queueService = new QueueService();
    }

    public void run(){
        System.out.println("Run() be runnin");
        this.queueService.run();
        // this.listService.run();
        // this.listService.roleCall();
    }
}