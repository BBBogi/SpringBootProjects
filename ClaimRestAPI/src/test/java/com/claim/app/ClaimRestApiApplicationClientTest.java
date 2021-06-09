package com.claim.app;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.claim.app.dto.ClaimInfo;
import com.claim.app.entity.CarPart;
import com.claim.app.entity.Claim;
import com.claim.app.entity.ClaimItem;


public class ClaimRestApiApplicationClientTest {
 
	
	public void getClaimInfoById(Long claimId) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/claims/details/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<ClaimInfo> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ClaimInfo.class, claimId);
        ClaimInfo claimInfo = responseEntity.getBody();
        System.out.println(claimInfo.toString());
    }
	
	public void getClaimById(Long claimId) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/claims/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Claim> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Claim.class, claimId);
        Claim claim = responseEntity.getBody();
        System.out.println(claim.toString());      
    }

    public Long addCarPartDemo(String carPartName) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/carParts";
	CarPart carPart = new CarPart(carPartName);
	HttpEntity<CarPart> requestEntity = new HttpEntity<CarPart>(carPart, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());
        String carPartId = (uri.getPath().substring(uri.getPath().lastIndexOf("/")+1, uri.getPath().length()));
        return Long.parseLong(carPartId);
    }
	
    public Long addClaimDemo(Long carPartId1,Long carPartId2) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/claims";
	Claim claim = new Claim(111, 222);
	ClaimItem claimItem1 = new ClaimItem(carPartId1, new BigDecimal(300),new BigDecimal(200), claim);
	ClaimItem claimItem2 = new ClaimItem(carPartId2, new BigDecimal(800),new BigDecimal(200), claim);
	List<ClaimItem> claimItems = new ArrayList<ClaimItem>();
	claimItems.add(claimItem1);
	claimItems.add(claimItem2);
	claim.setClaimItems(claimItems);
	HttpEntity<Claim> requestEntity = new HttpEntity<Claim>(claim, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());
        String claimId = (uri.getPath().substring(uri.getPath().lastIndexOf("/")+1, uri.getPath().length()));
        return Long.parseLong(claimId);
    }
    
    public static void main(String args[]) {
    	ClaimRestApiApplicationClientTest util =new ClaimRestApiApplicationClientTest();
    	//add two car parts
    	Long createdCarPartId1 = util.addCarPartDemo("Bumper");
    	Long createdCarPartId2 = util.addCarPartDemo("Engine");
    	//add Claim
    	Long createdClaimId = util.addClaimDemo(createdCarPartId1,createdCarPartId2);
    	//read claim and println
    	util.getClaimById(createdClaimId);
    	//read details and println
    	util.getClaimInfoById(createdClaimId);	
    }    
} 