package models;

import java.util.List;

public class ParkingSpot extends BaseModel{
    private List<VehicleType> supportedVechicleType;
    private ParkingSpotStatus parkingSpotStatus;
    private int spotNumber;
    private ParkingFloor parkingFloor;

    public List<VehicleType> getSupportedVechicleType() {
        return supportedVechicleType;
    }

    public void setSupportedVechicleType(List<VehicleType> supportedVechicleType) {
        this.supportedVechicleType = supportedVechicleType;
    }

    public ParkingSpotStatus getParkingSpotStatus() {
        return parkingSpotStatus;
    }

    public void setParkingSpotStatus(ParkingSpotStatus parkingSpotStatus) {
        this.parkingSpotStatus = parkingSpotStatus;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }

    public void setParkingFloor(ParkingFloor parkingFloor) {
        this.parkingFloor = parkingFloor;
    }
}
