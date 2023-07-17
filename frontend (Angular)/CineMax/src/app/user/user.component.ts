import { Component, OnInit } from '@angular/core';
import { TicketService } from '../_services/ticket.service';
import { UserAuthService } from '../_services/user-auth.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  public tickets: any;

  constructor(
    private ticketService: TicketService,
    private userAuthService: UserAuthService
  ) { }

  ngOnInit(): void {
    this.getTickets();
  }

  getTickets() {
    let username = this.userAuthService.getUserName();
    this.ticketService.getTicketsForUser(username).subscribe(resp => {
        this.tickets = resp;
    });
  }

  deleteTicket(ticketId: any) {
    this.ticketService.deleteTicket(ticketId).subscribe(resp => {
      this.getTickets();
    });
  }

}
