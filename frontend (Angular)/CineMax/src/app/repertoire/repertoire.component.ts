import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CinemaService } from '../_services/cinema.service';
import { CityService } from '../_services/city.service';
import { ProjectionService } from '../_services/projection.service';
import { UserAuthService } from '../_services/user-auth.service';

@Component({
  selector: 'app-repertoire',
  templateUrl: './repertoire.component.html',
  styleUrls: ['./repertoire.component.css']
})
export class RepertoireComponent implements OnInit {

  public cities: any;
  public currentCity: any;
  public cityName = "Beograd";
  public cinemas: any;
  public currentCinema: any;
  public dates: any;
  public selectedDate: any;
  public projections: any;

  constructor(
    public cinemaService: CinemaService,
    public cityService: CityService,
    public projectionService: ProjectionService,
    private userAuthService: UserAuthService,
    private router: Router
  ) 
  {
    this.getCities();
    this.currentCity = {
      "id": 1,
      "name": "Beograd"
    };
    this.cinemas = this.onGetCinema(this.currentCity);
    this.currentCinema = {
      "id": 1,
      "name": "CINEMAX - DELTA CITY",
      "city": {
        "id": 1,
        "name": "Beograd"
      }
    };
    this.dates = this.generateDatesArray();
    this.selectedDate = new Date().toJSON().slice(0, 10);
  }

  ngOnInit(): void {

  }

  getCities() {
    this.cityService.getCities().subscribe(resp => {
      this.cities = resp;
    });
  }

  onGetCinema(city: any) {
    this.currentCity = city;
    this.cityName = city.name;
    this.cinemaService.getCinemas(city.id).subscribe(resp => {
      this.cinemas = resp;
      if (this.cinemas.length > 0) {
        this.currentCinema = this.cinemas[0];
      } else {
        this.currentCinema = {id:0};
      }
      this.onGetProjections(this.selectedDate);
    });
  }

  selectCinema(cinema: any) {
    this.currentCinema = cinema;
    this.dates = this.generateDatesArray();
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
    this.projectionService.getProjections(this.currentCinema.id, this.selectedDate).subscribe(resp => {
      this.projections = resp;
    });
  }

  gotoDetails(movie: any) {
    localStorage.setItem("currentMovieDetails", JSON.stringify(movie));
    this.router.navigate(["/movie-details"]);
  }

  goToBooking(proj: any) {
    localStorage.setItem("projectionForBooking", JSON.stringify(proj));
    this.router.navigate(["/book-ticket"]);
  }

  isLoggedIn() {
    return this.userAuthService.isLoggedIn();
  }

}
