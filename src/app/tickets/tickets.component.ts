import { SlicePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProjectionService } from '../_services/projection.service';
import { UserAuthService } from '../_services/user-auth.service';

@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.css']
})
export class TicketsComponent implements OnInit {

  public selectedMovie: any;
  public dates: any;
  public selectedDate: any;
  public projections: any;
  public avgRate: any;

  constructor(
    public projectionService: ProjectionService,
    private router: Router,
    private userAuthService: UserAuthService
  ) 
  {
    this.dates = this.generateDatesArray();
    this.selectedDate = new Date().toJSON().slice(0, 10);
  }

  ngOnInit(): void {
    this.selectedMovie = JSON.parse(localStorage.getItem("currentMovieDetails") || '{}');
    this.avgRate = this.selectedMovie.averageRate;
    this.onGetProjections(this.selectedDate);
  }

  generateDatesArray() {
    var arrayDates = [];
    for (var i = 0; i < 7; i++) {
      var currentDate = new Date();
      var tempDate = new Date();
      tempDate.setDate(currentDate.getDate() + i);
      arrayDates.push(tempDate.toJSON().slice(0, 10));
    }
    return arrayDates;
  }

  onGetProjections(date: any) {
    this.selectedDate = date;
    this.projectionService.getProjectionsByMovieDate(this.selectedMovie.id, this.selectedDate).subscribe(resp => {
      this.projections = resp;
    });
  }

  goToBooking(proj: any) {
    localStorage.setItem("projectionForBooking", JSON.stringify(proj));
    this.router.navigate(["/book-ticket"]);
  }

  isLoggedIn() {
    return this.userAuthService.isLoggedIn();
  }


}
