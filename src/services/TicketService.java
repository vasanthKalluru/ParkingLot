package services;

import exceptions.GateNotFoundException;
import models.Gate;
import models.Ticket;
import models.Vehicle;
import models.VehicleType;
import repos.GateRepository;
import repos.ParkingLotRepository;
import repos.TicketRepository;
import repos.VehicleRepository;
import strategies.SpotAssignmentStrategy;
import strategies.SpotAssignmentStrategyFactory;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository, VehicleRepository vehicleRepository, ParkingLotRepository parkingLotRepository, TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(VehicleType vehicleType,
                              String vehicleNumber,
                              String vehicleOwnerName,
                              Long gateId) throws GateNotFoundException {

        // create ticket object.
        // get gate info from DB
        // assign spot
        //return the ticket

        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());

        Optional<Gate> gateOptional = gateRepository.findGateById(gateId);
        if(!gateOptional.isEmpty()) {
            throw new GateNotFoundException("The gate with id " + gateId + " does not exist");
        }

        Gate gate = gateOptional.get();
        ticket.setGeneratedAt(gate);
        ticket.setGeneratedBy(gate.getOperator());

        //get the vehicle assign to the ticket if already present in the database.
        //else create the vehicle object, save the vehicle to the db
        Optional<Vehicle> optionalVehicle = vehicleRepository.getVehicleByNumber(vehicleNumber);
        Vehicle vehicle;
        if(!optionalVehicle.isEmpty()) {
            vehicle = new Vehicle();
            vehicle.setNumber(vehicleNumber);
            vehicle.setOwnerName(vehicleOwnerName);
            vehicle.setVehicleType(vehicleType);
            vehicleRepository.save(vehicle);
        } else {
            vehicle = optionalVehicle.get();
        }

        ticket.setVehicle(vehicle);

        SpotAssignmentStrategy spotAssignmentStrategy = SpotAssignmentStrategyFactory.getSpotAssignmentStrategy(
                parkingLotRepository.getParkingLotFromGate(gate)
                        .getSpotAssignmentStrategyType()
        );

        ticket.setParkingSpot(spotAssignmentStrategy.getSpot());

        ticket.setNumber("TICKET - "+ticket.getVehicle().getNumber()+ticket.getEntryTime());

        return ticketRepository.save(ticket);
    }
}
