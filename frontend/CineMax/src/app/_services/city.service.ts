import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CityService {

  host = 'http://localhost:8080';
  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  constructor(private http: HttpClient) {
  }

  getCities() {
    return this.http.get(this.host + '/cities', {headers: this.requestHeader,});
  }

  updateCity(city: any) {
    return this.http.get(this.host + `/updateCity?cityId=${city.id}&cityName=${city.name}`);
  }

  deleteCity(cityId: number) {
    return this.http.delete(this.host + `/deleteCity?cityId=${cityId}`);
  }

  addCity(cityName: string) {
    return this.http.get(this.host + `/addCity?cityName=${cityName}`);
  }

}
