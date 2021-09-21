package com.udaan.expensemgmt;

import com.udaan.expensemgmt.client.Client;
import com.udaan.expensemgmt.client.ClientFactory;

import java.io.FileNotFoundException;

/**
 * @author nalin.sharma on 07/09/21
 */
public class Applicaton {
    public static void main(String[] args) {
        ClientFactory cf = new ClientFactory();
        Client client;
        try {
            client = cf.buildClient(args);
            client.createServerRequest();
        }catch (FileNotFoundException ex) {
            System.out.println("File not found");
            return;
        }

    }
}
