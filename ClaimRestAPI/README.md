Technologies used:
Java 11 JRE
Spring Boot 2.5.0
DB used is MySQL Server
IDE used is Eclipse 2021-3
DB prerequisites:
Schema and the user with the name spring_boot_claim need to exist  
	Tables will be auto-generated using spring.jpa.hibernate.ddl-auto=update
	From some reason option spring.jpa.hibernate.ddl-auto=create did not want to create schema so moved to update with unfortunately this prerequisite
	Alternative was to put to none, and use schema.sql and data.sql, but then it would not use auto-generation which is not flexible
Starting up the application:
	1. Import the project into the Eclipse workspace
	2. Right click on the class ClaimRestApiApplication.java -> Run as Spring Boot Application
Testing the application:
Option 1:
	Here have created a class  ClaimRestApiApplicationClientTest.java which can be ran as Java application.
	Again, from the Eclipse workspace right click on ClaimRestApiApplicationClientTest.java -> Run as Java Application
	This class contains a main method which calls one business scenario and triggers the REST API endpoints in following order:
		1. Calls REST API for creating and storing one Car Part named Bumper - Prints last part of uri on the console having ID
		2. Calls REST API for creating and storing one Car Part named Engine - Prints last part of uri on the console having ID
		3. Calls REST API for creating and storing one Claim which would have Claim Items for Bumper and Engine - Prints last part of uri on the console having ID
		4. Calls REST API for reading created Claim - Prints claim on the console
		5. Calls REST API for reading the created Claim detals as per GET specification from Application Requirements spec - Prints claim details on the console 	
		
Example log file after run of the test:

13:31:35.307 [main] DEBUG org.springframework.web.client.RestTemplate - HTTP POST http://localhost:8080/carParts
13:31:39.493 [main] DEBUG org.springframework.web.client.RestTemplate - Writing [com.claim.app.entity.CarPart@29ca3d04] as "application/json"
13:31:40.745 [main] DEBUG org.springframework.web.client.RestTemplate - Response 201 CREATED
/carParts/1
13:31:46.165 [main] DEBUG org.springframework.web.client.RestTemplate - HTTP POST http://localhost:8080/carParts
13:31:46.172 [main] DEBUG org.springframework.web.client.RestTemplate - Writing [com.claim.app.entity.CarPart@4b3ed2f0] as "application/json"
13:31:46.334 [main] DEBUG org.springframework.web.client.RestTemplate - Response 201 CREATED
/carParts/2
13:31:47.902 [main] DEBUG org.springframework.web.client.RestTemplate - HTTP POST http://localhost:8080/claims
13:31:48.149 [main] DEBUG org.springframework.web.client.RestTemplate - Writing [ClaimId: null, ContractNumber: 111, CompanyNumber: 222, Claim Items: ClaimItemId: null, CarPartId: 1, MaterialCost: 300, WageCost: 200, ClaimId: nullClaimItemId: null, CarPartId: 2, MaterialCost: 800, WageCost: 200, ClaimId: null] as "application/json"
13:31:49.094 [main] DEBUG org.springframework.web.client.RestTemplate - Response 201 CREATED
/claims/3
13:31:51.285 [main] DEBUG org.springframework.web.client.RestTemplate - HTTP GET http://localhost:8080/claims/3
13:31:53.322 [main] DEBUG org.springframework.web.client.RestTemplate - Accept=[application/json, application/*+json]
13:31:53.439 [main] DEBUG org.springframework.web.client.RestTemplate - Response 200 OK
13:31:53.596 [main] DEBUG org.springframework.web.client.RestTemplate - Reading to [com.claim.app.entity.Claim]
ClaimId: 3, ContractNumber: 111, CompanyNumber: 222, Claim Items: ClaimItemId: 4, CarPartId: 1, MaterialCost: 300.00, WageCost: 200.00, ClaimId: 3ClaimItemId: 5, CarPartId: 2, MaterialCost: 800.00, WageCost: 200.00, ClaimId: 3
13:31:54.167 [main] DEBUG org.springframework.web.client.RestTemplate - HTTP GET http://localhost:8080/claims/details/3
13:31:54.200 [main] DEBUG org.springframework.web.client.RestTemplate - Accept=[application/json, application/*+json]
13:31:54.242 [main] DEBUG org.springframework.web.client.RestTemplate - Response 200 OK
13:31:54.244 [main] DEBUG org.springframework.web.client.RestTemplate - Reading to [com.claim.app.dto.ClaimInfo]
ClaimNumber: 3, ContractNumber: 111, Total: 1500.00, brokenParts: carPartName: Bumper, cost: 500.00carPartName: Engine, cost: 1000.00
		
Option 2:
From browser GET methods: 
localhost:8080/claims - lists all claims
localhost:8080/claims/{id} - where id is the id of existing claim
localhost:8080/claims/details/{id} - lists the claim details in the specified method as per Application Requirements 
localhost:8080/carParts - lists all carParts
localhost:8080/carParts/{id} - where id is the id of existing carPart 
