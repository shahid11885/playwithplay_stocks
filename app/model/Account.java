package model;


import lombok.*;
import lombok.extern.java.Log;

@NoArgsConstructor(force = true)
@Getter
@Setter
@ToString
@Log
//@AllArgsConstructor

public class Account {
    String 	accountID;
    String 	accountName;
    double	balance;
    
    public Account(String accountID, String accountName, double balance) {
	this.accountID = accountID;
	this.accountName = accountName;
	this.balance = balance;
    }
}

