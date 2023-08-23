import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  host = 'http://localhost:8080';
  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  constructor(private http: HttpClient) {
  }

  getMovies(currentPage: number, sort: string, titleFilter: string, selectedCategoryId: number = 0, year: any) {
    if (year === null) {
      year = "";
    }
    return this.http.get(this.host + 
      `/movies?page=${currentPage}&sortReleaseDate=${sort}&title=${titleFilter}&categoryId=${selectedCategoryId}&year=${year}`
      , {headers: this.requestHeader,});
  }

  getAllMovies() {
    return this.http.get(this.host + `/allMovies`);
  }

  deleteMovie(movieId: number) {
    return this.http.delete(this.host + `/deleteMovie?movieId=${movieId}`);
  }

  updateMovie(movie: any) {
    return this.http.put(this.host + `/updateMovie?movieId=${movie.id}`, movie);
  }

  addMovie(movie: any) {
    return this.http.post(this.host + `/addMovie`, movie);
  }

  getTopRatedMovies() {
    return this.http.get(this.host + `/topRatedMovies`, {headers: this.requestHeader,});
  }

  getNewMovies() {
    return this.http.get(this.host + `/newMovies`, {headers: this.requestHeader,});
  }

  resetAverageMovieRate(movieId: number) {
    return this.http.get(this.host + `/resetAverageRate?movieId=${movieId}`);
  }

}
