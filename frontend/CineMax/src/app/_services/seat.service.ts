import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SeatService {

  host = 'http://localhost:8080';
  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  constructor(private http: HttpClient) {
  }

  getSeats(hallId: number) {
    return this.http.get(this.host + `/getSeats?hallId=${hallId}`, {headers: this.requestHeader,});
  }

  getCurrentlySelectedSeats(projectionId: number) {
    return this.http.get<number[]>(this.host + `/getCurrentlySelectedSeats?projectionId=${projectionId}`);
  }

}
