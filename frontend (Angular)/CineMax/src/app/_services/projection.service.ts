import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProjectionService {

  host = 'http://localhost:8080';
  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  constructor(private http: HttpClient) {
  }

  getAllProjections() {
    return this.http.get(this.host + `/allProjections`);
  }

  getProjections(cinemaId: number, date: string) {
    return this.http.get(this.host + `/getProjections?cinemaId=${cinemaId}&date=${date}`, {headers: this.requestHeader,});
  }

  getProjectionsByMovieDate(movieId: number, date: string) {
    return this.http.get(this.host + `/getProjectionsByMovieDate?movieId=${movieId}&date=${date}`, {headers: this.requestHeader,});
  }

  deleteProjection(projectionId: number) {
    return this.http.delete(this.host + `/deleteProjection?projectionId=${projectionId}`);
  }

  updateProjection(projection: any) {
    if(projection.startTime.length === 5) {
      projection.startTime = projection.startTime + ":00";
    }
    return this.http.put(this.host + `/updateProjection?projectionId=${projection.id}`, projection);
  }

  addProjection(projection: any) {
    projection.startTime = projection.startTime + ":00";
    return this.http.post(this.host + `/addProjection`, projection);
  }

}
