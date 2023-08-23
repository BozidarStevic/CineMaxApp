import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RateService {

  host = 'http://localhost:8080';
  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  constructor(private http: HttpClient) {
  }

  rateMovie(rate: number, movieId: number, userName: string) {
    return this.http.get(this.host + `/rateMovie?rate=${rate}&movieId=${movieId}&userName=${userName}`);
  }

  isRated(movieId: number, userName: string) {
    return this.http.get(this.host + `/isRated?movieId=${movieId}&userName=${userName}`);
  }

}
