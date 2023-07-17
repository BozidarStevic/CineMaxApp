import { Component, OnInit } from '@angular/core';
import { CinemaService } from '../_services/cinema.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  public forDisplay: string;

  constructor(public cinemaService: CinemaService) {
    this.forDisplay = "movies";
  }

  ngOnInit(): void {
  }

  showAdminMoviesComponent() {
    this.forDisplay = "movies";
  }

  showAdminProjectionsComponent() {
    this.forDisplay = "projections";
  }

  showAdminTicketsComponent() {
    this.forDisplay = "tickets";
  }

  showAdminUsersComponent() {
    this.forDisplay = "users";
  }



}
