package com.udaan.expensemgmt.client;

import com.udaan.expensemgmt.server.integration.CommandFactory;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author nalin.sharma on 07/09/21
 */
public abstract class Client {
    BufferedReader br;
    //CommandFactory commandFactory;

    /*public Client(BufferedReader bf, CommandFactory commandFactory) {
        this.br = bf;
        this.commandFactory = commandFactory;
    }*/

    public Client(BufferedReader br) {
        this.br = br;
    }

    public void createServerRequest() {

    }
}
