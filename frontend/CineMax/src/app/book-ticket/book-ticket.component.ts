import { Component, OnInit } from '@angular/core';
import { SeatService } from '../_services/seat.service';
import { TicketService } from '../_services/ticket.service';
import { UserAuthService } from '../_services/user-auth.service';

@Component({
  selector: 'app-book-ticket',
  templateUrl: './book-ticket.component.html',
  styleUrls: ['./book-ticket.component.css']
})
export class BookTicketComponent implements OnInit {

  public projection: any;
  public avgRate: any;
  public seats: any;
  public tickets: any;
  public selectedSeats: any[] = [];
  public msg: string = "Before booking, you must choose seats!";
  public modalActive: boolean = false;
  public createdTicket: any;
  public price: number = 0;

  constructor(
    public seatService: SeatService,
    public ticketService: TicketService,
    private userAuthService: UserAuthService
    ) 
  { }

  ngOnInit(): void {
    this.projection = JSON.parse(localStorage.getItem("projectionForBooking") || '{}');
    this.avgRate = this.projection.movie.averageRate;
    this.seats = this.getSeats();
    this.tickets = this.getTickets();
  }

  getSeats() {
    this.seatService.getSeats(this.projection.hall.id).subscribe(resp => {
      this.seats = resp;
    });
  }

  getTickets() {
    this.ticketService.getTickets(this.projection.id).subscribe(resp => {
      this.tickets = resp;
    });
  }

  getSeatClass(s: any) {
    for (let t of this.tickets) {
      for (let s1 of t.seats) {
        if (s.id === s1.id) {

          return 'btn-danger';
        }
      }
    }
    if (s.selected) {
      return 'btn-warning';
    }
    return 'btn-success';
  }

  onSelectSeat(s: any) {
    if (!s.selected) {
      s.selected = true;
      this.selectedSeats.push(s);
      this.price = this.projection.price * this.selectedSeats.length;
    } else {
      s.selected = false;
      this.selectedSeats.splice(this.selectedSeats.indexOf(s), 1);
      this.price = this.projection.price * this.selectedSeats.length;
    }
  }

  isReservedSeat(s: any) {
    for (let t of this.tickets) {
      for (let s1 of t.seats) {
        if (s.id === s1.id) {
          return true;
        }
      }
    }
    return false;
  }

  bookTicket() {
    let price = this.projection.price * this.selectedSeats.length;
    let userName = this.userAuthService.getUserName();
    let selectedSeats1: any[] = [];
    this.selectedSeats.forEach((s, index) => {
      selectedSeats1[index] = {
        id: s.id,
        number: s.number
      }
    });
    this.ticketService.bookTicket(price, userName, selectedSeats1, this.projection)
      .subscribe(resp => {
        this.tickets.push(resp);
        this.createdTicket = resp;
        this.selectedSeats = [];
        this.modalActive = true;
      });
  }

  public isLoggedIn() {
    return this.userAuthService.isLoggedIn();
  }

}
