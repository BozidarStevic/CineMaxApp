import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  host = 'http://localhost:8080';
  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  constructor(private http: HttpClient) {
  }

  public getTicketsForUser(username: string) {
    return this.http.get(this.host + `/getTicketsForUser?username=${username}`);
  }

  getTicketsFiltered(currentPage: number, sort: string, ownerFirstName: string, 
    ownerLastName: string, dateProjection: any = 0, selectedCinemaId: number = 0) {
    if (dateProjection === null || dateProjection === 0) {
      return this.http.get(this.host + 
        `/ticketsForAdmin?page=${currentPage}&sortDateProjection=${sort}&ownerFirstName=${ownerFirstName}&ownerLastName=${ownerLastName}&cinemaId=${selectedCinemaId}`);
    } else {
      return this.http.get(this.host + 
        `/ticketsForAdmin?page=${currentPage}&sortDateProjection=${sort}&ownerFirstName=${ownerFirstName}&ownerLastName=${ownerLastName}&cinemaId=${selectedCinemaId}&dateProjection=${dateProjection}`);
    }
  }

  deleteTicket(ticketId: number) {
    return this.http.delete(this.host + `/deleteTicket?ticketId=${ticketId}`);
  }

  getTickets(projectionId: number) {
    return this.http.get(this.host + `/getTickets?projectionId=${projectionId}`);
  }

  bookTicket(price: any, userName: string, selectedSeats: any, projection: any) {
    let sendObject = {
      price: price,
      userName: userName,
      selectedSeats: selectedSeats,
      projection: projection
    }
    return this.http.post(this.host + `/bookTicket`, sendObject);
  }

}
