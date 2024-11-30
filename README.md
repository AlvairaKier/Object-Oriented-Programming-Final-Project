# I. Brief Project Overview

KeepSafe: A money tracking system where it keeps a data where your money goes. This system allows the user to know what they do with their money, where their money go, spending their money properly appropriate with their recorded transactions separating their wallets for allowance and savings. 

# II. Explanation of how OOP principles were applied
##  Python Concepts
  - **Encapsulation**:
    - The fields in the Transaction class (`type`, `sourceAccount`, `targetAccount`, `etc.`) and `Account` class (`accountName`, `balance`) are private. 
     Access is controlled through methods.
Users interact with methods like `deposit()`, `withdraw()`, and `transferMoney()` instead of directly modifying balances.
      
  - **Polymorphism**:
    - Overriding (`@Override`) method was used for example: `toString()` in the `Transaction` class overrides the default `toString()` method to return a custom representation of transactions.
      
  - **Inheritance**:
    - The `KeepSafe` class extends the `Account` class, inheriting its properties (i.e., `accountName`, `balance`) and methods (`deposit()`, `withdraw()`). Additional behavior, like `transferMoney()`, is added in the child class.
      
  - **Abstraction**:
    - Methods like `deposit()`, `withdraw()`, and `transferMoney()` abstract complex operations such as balance validation and transaction creation. 
# III. SDG and Its Integration into the Project

  - **SDG 1: No Poverty**:
    - By helping users manage their finances effectively, the system reduces the risk of overspending and encourages savings. This can empower users to achieve financial stability and avoid falling into poverty. 
 
  - **SDG 8: Decent Work and Economic Growth**:
    - The app promotes financial literacy and responsible money management, which are critical to sustaining economic growth. Proper allocation of funds enables individuals to contribute effectively to their personal and professional goals. 
 
  - **SDG 12: Responsible production and Consumption**: 
    - The system emphasizes tracking and managing expenses responsibly. It encourages users to allocate their resources effectively and align spending with their priorities, promoting sustainable financial habits.  


# IV. Instructions for Running the Program

 1. First is to open the java file named `KeepSafe.java`.

2. Then run the `KeepSafeSystem.java` code in Visual Studio.
   
