package com.example.ven.vaccination.vm;

import java.util.List;

public class VaccinationClientResponseVM {
	 public List<CenterClientResponseVM> centers;

	public List<CenterClientResponseVM> getCenters() {
		return centers;
	}

	public void setCenters(List<CenterClientResponseVM> centers) {
		this.centers = centers;
	}
}
