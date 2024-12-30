package controllers;

import dtos.*;

import models.Ticket;
import services.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponseDto issueTicket(IssueTicketRequestDto requestDto) {
        IssueTicketResponseDto response = new IssueTicketResponseDto();
        Ticket ticket;
        try {
            ticket = ticketService.issueTicket(
                    requestDto.getVehicleType(),
                    requestDto.getVehicleNumber(),
                    requestDto.getVehicleOwnerName(),
                    requestDto.getGateId()
            );
        } catch (Exception e) {
            response.setResponseStatus(ResponseStatus.FAILURE);
            response.setFailureReason(e.getMessage());
            return response;
        }

        response.setResponseStatus(ResponseStatus.SUCCESS);
        response.setTicket(ticket);

        return response;
    }
}
