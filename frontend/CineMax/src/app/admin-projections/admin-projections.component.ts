import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CinemaService } from '../_services/cinema.service';
import { CityService } from '../_services/city.service';
import { HallService } from '../_services/hall.service';
import { MovieService } from '../_services/movie.service';
import { ProjectionService } from '../_services/projection.service';

@Component({
  selector: 'app-admin-projections',
  templateUrl: './admin-projections.component.html',
  styleUrls: ['./admin-projections.component.css']
})
export class AdminProjectionsComponent implements OnInit {

  public cities: any;
  public currentCity: any;
  public cityName = "Beograd";
  public cinemas: any;
  public currentCinema: any | undefined;
  public selectedDate: any;
  public projections: any;
  public showUpdateCityFlag: any;
  public cityForUpdate: any;
  public newCity: string = "";
  public newCinema: string = "";
  public showUpdateCinemaFlag: any;
  public cinemaForUpdate: any;
  public halls: any;
  public showAddModal: boolean = false;
  public projectionForUpdate: any;
  public citiesForSelect: any;
  public moviesForSelect: any;
  public cinemasForSelect: any;
  public hallsForSelect: any;
  public showUpdateHallFlag: any;
  public hallForUpdate: any;
  public newHallName: string = "";
  public newHallSeatsNum: number | null = null;

  constructor(
    public cinemaService: CinemaService,
    public cityService: CityService,
    public projectionService: ProjectionService,
    public hallService: HallService,
    public movieService: MovieService,
    private router: Router
    ) {

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
    this.selectedDate = new Date().toJSON().slice(0, 10);
    this.projectionForUpdate = {
      dateProjection: "",
      startTime: "",
      price: 0,
      hall: {
        id: 0,
        cinema: {
          id: 0,
          city: {
            id: 0
          }
        }
      },
      movie: {
        id: 0
      }
    }
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

  onGetProjections(date: any = this.selectedDate) {
    if (this.currentCinema.id === undefined) {
      this.projections = [];
    } else {
      this.projectionService.getProjections(this.currentCinema.id, this.selectedDate).subscribe(resp => {
        this.projections = resp;
      });
    }
  }

  showUpdateCity(city: any) {
    this.showUpdateCityFlag = city.id;
    this.cityForUpdate = JSON.parse(JSON.stringify(city));
  }

  deleteCity(cityId: number) {
    this.cityService.deleteCity(cityId).subscribe(resp => {
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
    });
  }

  closeUpdateCity() {
    this.showUpdateCityFlag = 0;
  }

  updateCity() {
    this.cityService.updateCity(this.cityForUpdate).subscribe(resp => {
      this.showUpdateCityFlag = 0;
      this.getCities();
      this.onGetProjections(this.selectedDate);
    });
  }

  addCity() {
    this.cityService.addCity(this.newCity).subscribe(resp => {
      this.getCities();
      this.newCity = "";
    });
  }

  addCinema() {
    this.cinemaService.addCinema(this.newCinema, this.currentCity.id).subscribe(resp => {
      this.onGetCinema(this.currentCity);
      this.newCinema = "";
    });
  }

  selectCinema(cinema: any) {
    this.currentCinema = cinema;
    this.onGetProjections(this.selectedDate);
  }

  showUpdateCinema(cinema: any) {
    this.showUpdateCinemaFlag = cinema.id;
    this.cinemaForUpdate = JSON.parse(JSON.stringify(cinema));
  }

  deleteCinema(cinemaId: number) {
    this.cinemaService.deleteCinema(cinemaId).subscribe(resp => {
      this.onGetCinema(this.currentCity);
    });
  }

  getHallsForCinema(cinemaId: number) {
    this.hallService.getHalls(cinemaId).subscribe(resp => {
      this.halls = resp;
    });
  }

  closeUpdateCinema() {
    this.showUpdateCinemaFlag = 0;
  }

  updateCinema() {
    this.cinemaService.updateCinema(this.cinemaForUpdate).subscribe(resp => {
      this.showUpdateCinemaFlag = 0;
      this.onGetCinema(this.currentCity);
    });
  }

  showAddProjection() {
    this.showAddModal = true;
    this.projectionForUpdate = {
      dateProjection: this.selectedDate,
      hall: {
        id: 0,
        name: "",
        cinema: JSON.parse(JSON.stringify(this.currentCinema))
      },
      movie: {
        id: 0
      }
    };

    this.loadCitiesForSelect();
    this.loadCinemasForSelect(this.currentCity.id);
    this.loadMoviesForSelect();
  }

  showUpdateProjection(projection: any) {
    this.showAddModal = false;
    this.resetModal();
    this.projectionForUpdate = JSON.parse(JSON.stringify(projection));

    this.loadCitiesForSelect();
    this.loadCinemasForSelect(this.projectionForUpdate.hall.cinema.city.id);
    this.loadMoviesForSelect();

    // this.showAddModal = false;
    // this.projectionForUpdate = JSON.parse(JSON.stringify(projection));
    // this.resetModal();
    // this.projectionForUpdate.hall.cinema.city.id = projection.hall.cinema.city.id;
    // this.projectionForUpdate.hall.cinema.id = projection.hall.cinema.id;
    // this.projectionForUpdate.hall.id = projection.hall.id;
    // this.projectionForUpdate.movie.id = projection.movie.id;
    // this.loadCitiesForSelect();
    // this.loadCinemasForSelect(this.projectionForUpdate.hall.cinema.city.id);
    // this.loadMoviesForSelect();
  }

  loadCitiesForSelect() {
    this.cityService.getCities().subscribe(resp => { 
      this.citiesForSelect = resp; 
    });
  }

  loadMoviesForSelect() {
    this.movieService.getAllMovies().subscribe(resp => {
      this.moviesForSelect = resp;
    });
  }

  changedSelectedDate() {
    this.onGetProjections(this.selectedDate);
  }

  

  resetModal() {
    this.citiesForSelect = undefined;
    this.cinemasForSelect = undefined;
    this.hallsForSelect = undefined;
    this.moviesForSelect = undefined;
    this.projectionForUpdate.hall.cinema.city.id = 0;
    this.projectionForUpdate.hall.cinema.id = 0;
    this.projectionForUpdate.hall.id = 0;
    this.projectionForUpdate.movie.id = 0;
  }

  loadCinemasForSelect(cityId: number, change: boolean = false) {
    this.cinemaService.getCinemas(cityId).subscribe(resp => {
      this.cinemasForSelect = resp;
      if (this.showAddModal===true && change===false) {
        this.loadHallsForSelect(this.projectionForUpdate.hall.cinema.id);
      } else if (change===true) {
        this.projectionForUpdate.hall.cinema.id = this.cinemasForSelect[0].id;
        this.loadHallsForSelect(this.cinemasForSelect[0].id);
      } else {
        this.loadHallsForSelect(this.projectionForUpdate.hall.cinema.id);
      }
    });
  }

  loadHallsForSelect(cinemaId: number, change: boolean = false) {
    this.hallService.getHalls(cinemaId).subscribe(resp => { 
      this.hallsForSelect = resp;
      if (this.showAddModal===true) {
        this.projectionForUpdate.hall.id = this.hallsForSelect[0].id;
      } else if (this.showAddModal===false && change===true) {
        this.projectionForUpdate.hall.id = this.hallsForSelect[0].id;
      } else {
        
      }

    });
  }

  deleteProjection(projectionId: number) {
    this.projectionService.deleteProjection(projectionId).subscribe(resp => {
      this.onGetProjections();
    });
  }

  addProjectionComplete(): boolean {
    if (this.projectionForUpdate.dateProjection != "" &&
      this.projectionForUpdate.startTime != "" &&
      this.projectionForUpdate.price > 0 &&
      this.projectionForUpdate.hall.cinema.city.id > 0 &&
      this.projectionForUpdate.hall.cinema.id > 0 &&
      this.projectionForUpdate.hall.id > 0 &&
      this.projectionForUpdate.movie.id > 0) {
      return true;
    } else {
      return false;
    }
  }

  updateProjection() {
    this.projectionService.updateProjection(this.projectionForUpdate).subscribe(resp => {
      this.onGetProjections();
      this.resetModal();
    });
  }

  addProjection() {
    this.projectionService.addProjection(this.projectionForUpdate).subscribe(resp => {
      this.onGetProjections();
      this.resetModal();
    });
  }

  showUpdateHall(hall: any) {
    this.showUpdateHallFlag = hall.id;
    this.hallForUpdate = JSON.parse(JSON.stringify(hall));
  }

  deleteHall(hallId: number) {
    this.hallService.deleteHall(hallId).subscribe(resp => {
      this.getHallsForCinema(this.currentCinema.id);
      this.onGetProjections();
    });
  }

  closeUpdateHall() {
    this.showUpdateHallFlag = 0;
  }

  updateHall() {
    this.hallService.updateHall(this.hallForUpdate).subscribe(resp => {
      this.showUpdateHallFlag = 0;
      this.getHallsForCinema(this.currentCinema.id);
      this.onGetProjections();
    });
  }

  addHall() {
    this.hallService.addHall(this.newHallName, this.currentCinema.id, this.newHallSeatsNum).subscribe(resp => {
      this.getHallsForCinema(this.currentCinema.id);
      this.newHallName = "";
      this.newHallSeatsNum = null;
    });
  }

  goToBooking(proj: any) {
    localStorage.setItem("projectionForBooking", JSON.stringify(proj));
    this.router.navigate(["/book-ticket"]);
  }

}
