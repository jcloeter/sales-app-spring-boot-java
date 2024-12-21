package com.example.vanillajava.service;

public abstract class AbstractCollection implements CollectionsServiceInterface {
    
    @Override
    public void run() {
        System.out.println("Abstract method here, howdy!");
    }

    public void roleCall(){
        System.out.println("ROOooooOOOOLE caaaAAAAllllLLL");
    }
    
}