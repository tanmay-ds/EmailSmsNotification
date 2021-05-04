package com.example.ven.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.ven.vaccination.vm.CenterClientResponseVM;
import com.example.ven.vaccination.vm.ResponseVM;
import com.example.ven.vaccination.vm.SessionClientResponseVM;
import com.example.ven.vaccination.vm.VaccinationClientResponseVM;

@RestController
public class Vaccination {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=370201&date=";
	
	@GetMapping("/")
	public String hello(){
		return "Hello";
	}
	
	@GetMapping("/get")
	public List<ResponseVM> getslots(@RequestParam(value = "date")String date,@RequestParam(value = "minAge") Integer minAge) {
		
		VaccinationClientResponseVM vaccinationClientResponseVM = new VaccinationClientResponseVM();
		ResponseEntity<VaccinationClientResponseVM> apiResponse = restTemplate.getForEntity(url+date, VaccinationClientResponseVM.class);
		
		
		vaccinationClientResponseVM = apiResponse.getBody();
		List<ResponseVM> responseVMs = new ArrayList<>();  
		for ( CenterClientResponseVM center:vaccinationClientResponseVM.getCenters())  {
			
			for(SessionClientResponseVM session:center.getSessions()) {
				ResponseVM responseVM = null;
				if(session.getMin_age_limit()==minAge && session.getAvailable_capacity() > 0) {
				responseVM = new ResponseVM(center.getName(),center.getPincode(),center.getAddress(),session.getMin_age_limit(),session.getVaccine(),session.getAvailable_capacity(),session.getDate());	
				}
				
				if(!ObjectUtils.isEmpty(responseVM))
					responseVMs.add(responseVM);
				
			}
		}
		if(!ObjectUtils.isEmpty(responseVMs))
			return responseVMs;
		else
			return null;
	}
}
