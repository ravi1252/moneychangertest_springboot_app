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
Procedure to bring the service UP and API docs is as follows     :
******************************************************************

1) Import the DB file to database, genting_money_changer

2) Start the spring boot app

3) Initially to load the exchange rates to the memory, please call below endpoint.
   Loading these into the memory as this is the assessment test and have to limit with the time.
   URL : http://localhost:8080/exchangerates
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
   
4) API to view the exchange rates incase of BUY
   URL : http://localhost:8080/exchangerates/USD?amt=10
   Type : GET
   Response : {
    "status": "success",
    "errors": null,
    "data": {
        "dispenseCurrencyType": "SGD",
        "amountToDispense": 13.392,
        "buyRate": 1.3392
    }
   }
   

5) API to view the exchange rates incase of BUY
	URL : http://localhost:8080/exchangerates/SGD?amt=1&returnCurrency=USD
	Type : GET
	Resposne : {
    "status": "success",
    "errors": null,
    "data": {
        "sellRate": 1.3574,
        "dispenseCurrencyType": "USD",
        "amountToDispense": 1.3574
    }
  }


6) Save the deal
	URL : http://localhost:8080/deals
	Type : POST
	Request Body : {
    "name": "ravi",
    "type": "SELL",
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
   
   
   
    
  

