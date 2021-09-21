package com.udaan.expensemgmt.client;

import com.udaan.expensemgmt.server.compute.Strategy;
import com.udaan.expensemgmt.server.endpoint.*;

import java.io.BufferedReader;

/**
 * @author nalin.sharma on 07/09/21
 */
public class CLIClient extends Client {
    ExpenseEndpoints expenseEndpoints = new ExpenseEndpoints();
    ExpenseShareEndpoints expenseShareEndpoints = new ExpenseShareEndpoints();
    UserExpenseShareEndpoints userExpenseShareEndpoints = new UserExpenseShareEndpoints();
    UserDebtStatusEndpoints userDebtStatusEndpoints = new UserDebtStatusEndpoints();
    UserEndPoints userEndPoints = new UserEndPoints();

    public CLIClient(BufferedReader br) {
        super(br);
    }

    public void createServerRequest() {
        while(true) {
            String line;
            try {
                System.out.println("1. add expense");
                System.out.println("2. get expense");
                System.out.println("3. get all expenses");

                System.out.println("4. add share expense");
                System.out.println("5. get share expense");
                System.out.println("6. get all share expenses");

                System.out.println("7. add user");
                System.out.println("8. get user");
                System.out.println("9. get all users");

                System.out.println("10. get all user expense Share");
                System.out.println("11. get pending user expense Share");
                System.out.println("12. get paid user expense Share");

                System.out.println("13. show two users settlement status");
                System.out.println("14. show pending expense shares based on debt on user by another user");
                System.out.println("15. show paid expense shares based on debt on user by another user");
                System.out.println("16. pay for expense share");

                String arr [];

                line = br.readLine();
                if("".equalsIgnoreCase(line)) {
                    break;
                }

                if(line.equals("1")) {
                    System.out.println("[total, email, currency, strategy(real, percent)]");
                    line = br.readLine();
                    arr = line.split(" ");
                    expenseEndpoints.createExpense(arr[0], arr[1], arr[2], Strategy.valueOf(arr[3]));
                }
                else if(line.equals("2")) {
                    line = br.readLine();
                    System.out.println(expenseEndpoints.getExpense(line));
                }
                else if(line.equals("3")) {
                    System.out.println(expenseEndpoints.getExpenseList());
                }
                else if(line.equals("4")) {
                    System.out.println("[email, totalShare, currency, expenseId]");
                    line = br.readLine();
                    arr = line.split(" ");
                    expenseShareEndpoints.createExpenseShare(arr[0], arr[1], arr[2], arr[3]);
                }
                else if(line.equals("5")) {
                    line = br.readLine();
                    System.out.println(expenseShareEndpoints.getExpenseShare(line));
                }
                else if(line.equals("6")) {
                    System.out.println(expenseShareEndpoints.getExpenseShareList());
                }

                else if(line.equals("7")) {
                    System.out.println("[email, name]");
                    line = br.readLine();
                    arr = line.split(" ");
                    userEndPoints.addUser(arr[1], arr[0]);
                }
                else if(line.equals("8")) {
                    line = br.readLine();
                    System.out.println(userEndPoints.getUser(line));
                }
                else if(line.equals("9")) {
                    System.out.println(userEndPoints.getUserList());
                }
                else if(line.equals("10")) {
                    System.out.println("[email]");
                    line = br.readLine();
                    System.out.println(userExpenseShareEndpoints.getAllUserExpenseShare(line));
                }
                else if(line.equals("11")) {
                    System.out.println("[email]");
                    line = br.readLine();
                    System.out.println(userExpenseShareEndpoints.getPendingUserExpenseShare(line));
                }
                else if(line.equals("12")) {
                    System.out.println("[email]");
                    line = br.readLine();
                    System.out.println(userExpenseShareEndpoints.getUserPaidExpenseShare(line));
                }
                else if(line.equals("13")) {
                    System.out.println("[compare email 1, email 2]");
                    line = br.readLine();
                    arr = line.split(" ");
                    System.out.println(userDebtStatusEndpoints.userOneInDebtBy(arr[0], arr[1]));
                }
                else if(line.equals("14")) {
                    System.out.println("[compare email 1, email 2]");
                    line = br.readLine();
                    arr = line.split(" ");
                    System.out.println(userDebtStatusEndpoints.showPendingExpenseShareForUserInDebtBy(arr[0], arr[1]));
                }
                else if(line.equals("15")) {
                    System.out.println("[compare email 1, email 2]");
                    line = br.readLine();
                    arr = line.split(" ");
                    System.out.println(userDebtStatusEndpoints.showAllExpenseShareForUserInDebtBy(arr[0], arr[1]));
                }
                else if(line.equals("16")) {
                    System.out.println("[compare email 1, email 2]");
                    line = br.readLine();
                    arr = line.split(" ");
                    System.out.println(userExpenseShareEndpoints.(arr[0], arr[1]));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }
}
