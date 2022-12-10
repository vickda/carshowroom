package model;


public class CarInventoryData {

	private String ID;
	private String carYear;
	private String carMake;
	private String carPrice;
	private String Status;
	
	CarInventoryData(String id, String carYear, String carMake, String carPrice,String Status){
		
		this.ID = new String(id);
		this.carYear = new String(carYear);
		this.carMake = new String(carMake);
		this.carPrice = new String(carPrice);
		this.Status = new String(Status);
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getCarYear() {
		return carYear;
	}

	public void setCarYear(String carYear) {
		this.carYear = carYear;
	}

	public String getCarMake() {
		return carMake;
	}

	public void setCarMake(String carMake) {
		this.carMake = carMake;
	}

	public String getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(String carPrice) {
		this.carPrice = carPrice;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	
}
