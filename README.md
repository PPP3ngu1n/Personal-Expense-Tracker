# Personal Expense Tracker!

## Description:
A simple application that allows you to track your daily expenses and gaining insights into your spending habits.

## Features:
- Track Daily income and expenditures
- Data is stored Locally so only you can access it
- View all past transactions

## Installation:
Clone the Repository:
```bash
git clone https://github.com/PPP3ngu1n/Personal-Expense-Tracker.git
cd Personal-Expense-Tracker
```

## Test Cases:
**Test Case 1: (Add Income)**
- Preconditions: Balance.txt contains 0
- Steps:
  1. Launch the Program
  2. Select [1. Add]
  3. Enter amount: > 100
  4. Enter reason: > Weekly Allowance
- Expected Results:
  - Balance.txt updated to 100
  - receipt.txt contains a new line like:
  ``Date -- In -- £100 - Salary``
  - Balance is shown as £100 when selecting [3. Display]

**Test case 2: (Minus Expense):**
- Preconditions: Balance.txt currently is 100
- Steps:
  1. Select [2. Minus]
  2. Enter amount: > 40
  3. Enter reason: > Groceries
- Expected Result:
  - Balance.txt updated to 60.0
  - reciept.txt contains a new line like:
  ``Date -- Out -- £40 - Groceries``
  - Balance is shown as £60 when selecting [3. Display]

**Test case 3: (Viewing all logs)**
- Steps:
  1. Select [4. Previous Logs]
  2. Select [1. All]
- Expected results:
  - Console showing all entries from receipt.txt, both income and expenditures

**Test case 4: (Viewing Income Logs)**
- Steps:
  1. Select [4. Previous Logs]
  2. Select [2. Income]
- Expected results:
  - Console showing only lines containing In

**Test case 5: (Exiting Program)**
- Steps:
  1. Select [5. Exit]
- Expected result:
  - Terminal returns:
  ``Thank You for using our service!``
  - Application ends

## Use Examples:

Welcome to your account!

Please select an option:
1. Add
2. Minus
3. Display
4. Previous Logs
5. Exit
>2 

Enter amount to minus:

> 50

Enter Reason for Transaction:
> Groceries

Please select and option:
1. Add
2. Minus
3. Display
4. Previous Logs
5. Exit
> 3

Your current balance is:
Balance: £150.00

Please select an option:
1. Add
2. Minus
3. Display
4. Previous Logs
5. Exit
> 5

Thank you for using our service!