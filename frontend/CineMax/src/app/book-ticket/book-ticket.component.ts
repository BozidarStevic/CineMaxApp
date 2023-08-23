import { Component, OnInit, OnDestroy, HostListener } from '@angular/core';
import { SeatService } from '../_services/seat.service';
import { TicketService } from '../_services/ticket.service';
import { UserAuthService } from '../_services/user-auth.service';
import { IMessage } from '@stomp/stompjs';
import { WebSocketService } from '../_services/web-socket.service';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-book-ticket',
  templateUrl: './book-ticket.component.html',
  styleUrls: ['./book-ticket.component.css']
})
export class BookTicketComponent implements OnInit, OnDestroy {
  
  public foreignSelectedSeatsIDs: number[] = [];
  private topicSubscription!: Subscription;

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
    private webSocketService: WebSocketService,
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
    this.getCurrentlySelectedSeats();

    //WebSocket connection
    this.topicSubscription = this.webSocketService.getMessages(this.projection.id).subscribe((message: IMessage) => {
      let seatId = JSON.parse(message.body || '{}').seatId;
      let isForeign = true;
      for (let s of this.selectedSeats) {
        if (seatId === s.id) {
          isForeign = false;
        }
      }
      if (isForeign) {
        if (!this.foreignSelectedSeatsIDs.includes(seatId)) {
          this.foreignSelectedSeatsIDs.push(seatId);
        }
        else {
          const indexToRemove = this.foreignSelectedSeatsIDs.indexOf(seatId);
          if (indexToRemove !== -1) {
            this.foreignSelectedSeatsIDs.splice(indexToRemove, 1);
          }
        }
      }
    });
  }

  ngOnDestroy() {
    // da bi se obrisali kod svih klijenata i na serveru (singleton)
    for (let s of this.selectedSeats) {
      this.sendSeatSelectedMessage(s.id);
    }
    this.topicSubscription.unsubscribe();
  }

  @HostListener('window:beforeunload', ['$event'])
  beforeunloadHandler(event: any) {
    // Ovde možete implementirati logiku koja će se izvršiti pre osvežavanja stranice
    // da bi se obrisali kod svih klijenata i na serveru (singleton)
    for (let s of this.selectedSeats) {
      this.sendSeatSelectedMessage(s.id);
    }
    this.topicSubscription.unsubscribe();
    
  }

  // WebSocket message
  sendSeatSelectedMessage(seatId: number): void {
    this.webSocketService.sendMessage(this.projection.id, seatId);
  }



  getCurrentlySelectedSeats() {
    this.seatService.getCurrentlySelectedSeats(this.projection.id).subscribe(resp => {
      if (resp !== null) {
        this.foreignSelectedSeatsIDs = resp;
      }
    });
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
    for (let s2 of this.foreignSelectedSeatsIDs) {
      if (s.id === s2) {
        return 'btn-danger';
      }
    }
    return 'btn-success';
  }

  onSelectSeat(s: any) {
    if (!s.selected) {
      s.selected = true;
      this.selectedSeats.push(s);
      this.price = this.projection.price * this.selectedSeats.length;
      this.sendSeatSelectedMessage(s.id);
    } else {
      s.selected = false;
      //dodato da bi se obrisalo pri websocket odgovoru
      this.foreignSelectedSeatsIDs.push(s.id);
      this.sendSeatSelectedMessage(s.id);
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
    for (let s2 of this.foreignSelectedSeatsIDs) {
      if (s.id === s2) {
        return true;
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
