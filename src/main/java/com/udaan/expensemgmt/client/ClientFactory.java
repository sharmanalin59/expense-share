package com.udaan.expensemgmt.client;

import java.io.*;

/**
 * @author nalin.sharma on 07/09/21
 */
public class ClientFactory {
    public Client buildClient(String args []) throws FileNotFoundException {
       if(args != null && args.length > 0 && "file".equalsIgnoreCase(args[0])) {
           return new FileClient(new BufferedReader(new FileReader(new File(args[1]))));
       }
       return new CLIClient(new BufferedReader(new InputStreamReader(System.in)));
    }
}
