**************************************************************
Details : This application exposes the apis for money changer
**************************************************************


******
Note :
****** 
- As, this is assessment test and have to limit with the amount of time to develop,
just gave the skeleton kind of project. Still the code can be enhanced
to add more appropriate loggings, more precise exception handling and others

- Can also build UI with React JS for this.



******************************************************************
Procedure to bring the service UP
******************************************************************

1) Import the DB file (/db/genting_money_changer.sql) to database with name "genting_money_changer"

2) Perform mvn clean install and Start the spring boot app

Below are endpoints exposed : 
3) To get the list of currencies and their exchanges rates for the customer to view
   URL : http://localhost:8080/currencies/exchangerates
   Type : GET
   Response : {
    "status": "success",
    "errors": null,
    "data": [
        {
            "id": 1,
            "currency": "USD",
            "buy": 1.3392,
            "sell": 1.3574
        },
        {
            "id": 2,
            "currency": "HKD",
            "buy": 0.1738,
            "sell": 0.1698
        }
    ]
   }
   
4) API to view the converted rate, amount to dispense and currency type - BUY request
   URL : http://localhost:8080/currencies/convert/USD?amount=1
   Type : GET
   Response : {
    "status": "success",
    "errors": null,
    "data": {
        "dispenseCurrencyType": "SGD",
        "amountToDispense": 1.3392,
        "buyRate": 1.3392
    }
  }
   

5) API to view the converted rate, amount to dispense and currency type -  SELL request
	URL : http://localhost:8080/currencies/convert/SGD?amount=1&toCurrency=USD
	Type : GET
	Response : {
    "status": "success",
    "errors": null,
    "data": {
        "sellRate": 1.3574,
        "dispenseCurrencyType": "USD",
        "amountToDispense": 0.7367025195226168
    }
  }


6) Save the deal
	URL : http://localhost:8080/currencies/transactions
	Type : POST
	Request Body : {
    "name": "ravi",
    "inputCurrency": "SGD",
    "amount": "12",
    "dispensed": "YES",
    "buyrate": "1.2",
    "sellrate": "0"
    }
    Response : {
    "status": "success",
    "errors": null,
    "data": {
        "id": 4,
        "name": "ravi",
        "type": "SELL",
        "amount": 12.0,
        "dispensed": "YES",
        "buyRate": null,
        "sellRate": null
    }
   }
   
   
   
    
  

