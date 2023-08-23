import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class CinemaService {

  host = 'http://localhost:8080';
  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  constructor(private http: HttpClient) {
  }

  getCinemas(cityId: number) {
    return this.http.get(this.host + `/getCinemas?cityId=${cityId}`, {headers: this.requestHeader,});
  }

  updateCinema(cinema: any) {
    return this.http.get(this.host + `/updateCinema?cinemaId=${cinema.id}&cinemaName=${cinema.name}`);
  }

  deleteCinema(cinemaId: number) {
    return this.http.delete(this.host + `/deleteCinema?cinemaId=${cinemaId}`);
  }

  addCinema(cinemaName: string, cityId: number) {
    return this.http.get(this.host + `/addCinema?cinemaName=${cinemaName}&cityId=${cityId}`);
  }

}
