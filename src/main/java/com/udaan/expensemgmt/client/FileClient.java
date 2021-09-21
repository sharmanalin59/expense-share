package com.udaan.expensemgmt.client;

import com.udaan.expensemgmt.server.integration.CommandFactory;

import java.io.BufferedReader;

/**
 * @author nalin.sharma on 07/09/21
 */
public class FileClient extends Client {
    public FileClient(BufferedReader br) {
        super(br);
    }
}
