import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HallService {

  host = 'http://localhost:8080';
  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  constructor(private http: HttpClient) {
  }

  getHalls(cinemaId: number) {
    return this.http.get(this.host + `/getHalls?cinemaId=${cinemaId}`, {headers: this.requestHeader,});
  }

  updateHall(hall: any) {
    return this.http.get(this.host + `/updateHall?hallId=${hall.id}&hallName=${hall.name}`);
  }

  deleteHall(hallId: number) {
    return this.http.delete(this.host + `/deleteHall?hallId=${hallId}`);
  }

  addHall(hallName: string, cinemaId: number, seatsNum: number | null) {
    return this.http.get(this.host + `/addHall?hallName=${hallName}&cinemaId=${cinemaId}&seatsNum=${seatsNum}`);
  }

}
