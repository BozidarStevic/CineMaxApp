import { Component, OnInit } from '@angular/core';
import { CinemaService } from '../_services/cinema.service';
import { CityService } from '../_services/city.service';
import { TicketService } from '../_services/ticket.service';

@Component({
  selector: 'app-admin-tickets',
  templateUrl: './admin-tickets.component.html',
  styleUrls: ['./admin-tickets.component.css']
})
export class AdminTicketsComponent implements OnInit {

  public response: any;
  public tickets: any;
  public sort: string;
  public pageCount: number;
  public currentPage: number;
  public ownerFirstName: string;
  public ownerLastName: string;
  public dateProjection: any;
  public cities: any;
  public cinemas: any;
  public selectedCityId: number = 0;
  public selectedCinemaId: number = 0;
  

  constructor(
    private cityService: CityService,
    private cinemaService: CinemaService,
    private ticketService: TicketService,
    ) 
  {
    this.pageCount = 0;
    this.sort = "desc";
    this.currentPage = 0;
    this.ownerFirstName = "";
    this.ownerLastName = "";
  }

  ngOnInit(): void {
    this.getTickets();
    this.loadCitiesForSelect();
  }

  loadCitiesForSelect() {
    this.cityService.getCities().subscribe(resp => { 
      this.cities = resp; 
    });
  }

  loadCinemasForSelect(cityId: number) {
    this.cinemaService.getCinemas(cityId).subscribe(resp => {
      this.cinemas = resp;
    });
  }

  getTickets() {
    this.ticketService
      .getTicketsFiltered(this.currentPage, this.sort, this.ownerFirstName, 
                  this.ownerLastName, this.dateProjection, this.selectedCinemaId)
      .subscribe(resp => {
        this.response = resp;
        this.tickets = this.response.content;
        this.pageCount = this.response.totalPages;
    });
  }

  filterTickets() {
    this.currentPage = 0;
    this.getTickets();
  }

  resetFilters() {
    this.currentPage = 0;
    this.sort = "desc";
    this.ownerFirstName = "";
    this.ownerLastName = "";
    this.selectedCinemaId = 0;
    this.dateProjection = null;
  
    this.getTickets();
  }

  changeSort() {
    switch (this.sort) {
      case "asc": {
        this.sort = "desc";
        break;
      }
      case "desc": {
        this.sort = "asc";
        break;
      }
    }
    this.getTickets();
  }

  decrementPage() {
    if (this.currentPage > 0) {
      this.currentPage = this.currentPage - 1;
      this.getTickets();
    }
  }

  incrementPage() {
    if (this.currentPage < this.pageCount - 1) {
      this.currentPage = this.currentPage + 1;
      this.getTickets();
    }
  }

  createRange(num: number) {
    return new Array(num);
  }

  goToPage(pageNum: number) {
    this.currentPage = pageNum;
    this.getTickets();
  }

  deleteTicket(ticketId: any) {
    this.ticketService.deleteTicket(ticketId).subscribe(resp => {
      this.getTickets();
    });
  }
}
