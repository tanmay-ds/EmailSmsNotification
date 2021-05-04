package com.example.ven.vaccination.vm;

public class ResponseVM {
	private String name;
	private Integer pincode;
	private String address;
	private Integer minAge;
	private String vaccineName;
	private Integer numberOfVaccine;
	private String date;
	
	public ResponseVM(String name, Integer pincode, String address, Integer minAge, String vaccineName,
			Integer numberOfVaccine,String date) {
		super();
		this.name = name;
		this.pincode = pincode;
		this.address = address;
		this.minAge = minAge;
		this.vaccineName = vaccineName;
		this.numberOfVaccine = numberOfVaccine;
		this.date = date;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getMinAge() {
		return minAge;
	}
	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}
	public String getVaccineName() {
		return vaccineName;
	}
	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}
	public Integer getNumberOfVaccine() {
		return numberOfVaccine;
	}
	public void setNumberOfVaccine(Integer numberOfVaccine) {
		this.numberOfVaccine = numberOfVaccine;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return name+" "+pincode+" \n"+numberOfVaccine+" doses available for "+minAge+"+ group on "+date+"\n";
	
	}
	
	
	
}
