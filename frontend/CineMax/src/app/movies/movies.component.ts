import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MovieService } from '../_services/movie.service';
import { CategoryService } from '../_services/category.service';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {

  public movies: any;
  public categories: any;
  public response: any;

  public currentPage: number;
  public pageCount: number;

  public titleFilter: string;
  public selectedCategory: number = 0;
  public year: number | null = null;
  public sort: string;


  constructor(
    private categoryService: CategoryService,
    private movieService: MovieService,
    private router: Router
    ) 
  {
    this.currentPage = 0;
    this.pageCount = 0;
    this.titleFilter = "";
    this.sort = "desc";
  }

  ngOnInit() {
    if (localStorage.getItem("sort") !== null) {
      this.currentPage = JSON.parse(localStorage.getItem("currentPage") || '{}');
      this.titleFilter = localStorage.getItem("titleFilter") || '';
      this.selectedCategory = JSON.parse(localStorage.getItem("selectedCategory")!);
      this.year = JSON.parse(localStorage.getItem("year") || '{}');
      this.sort = localStorage.getItem("sort") || '';
    }
    this.getMovies();
    this.loadCategories();
  }

  ngOnDestroy() {
    localStorage.setItem("currentPage", JSON.stringify(this.currentPage));
    localStorage.setItem("titleFilter", this.titleFilter);
    localStorage.setItem("selectedCategory", JSON.stringify(this.selectedCategory));
    localStorage.setItem("year", JSON.stringify(this.year));
    localStorage.setItem("sort", this.sort);
  }


  changeSort() {
    switch (this.sort) {
      case "asc": {
        this.sort = "desc";
        break;
      }
      case "desc": {
        this.sort = "asc";
        break;
      }
    }
    this.getMovies();
  }

  loadCategories() {
    this.categoryService.getCategories().subscribe(resp => { this.categories = resp; });
  }

  getMovies() {
    this.movieService
      .getMovies(this.currentPage, this.sort, this.titleFilter, this.selectedCategory, this.year)
      .subscribe(resp => {
        this.response = resp;
        this.movies = this.response.content;
        this.pageCount = this.response.totalPages;
      });
  }

  filterMovies() {
    this.currentPage = 0;
    this.getMovies();
  }

  decrementPage() {
    if (this.currentPage > 0) {
      this.currentPage = this.currentPage - 1;
      this.getMovies();
    }
  }

  incrementPage() {
    if (this.currentPage < this.pageCount - 1) {
      this.currentPage = this.currentPage + 1;
      this.getMovies();
    }
  }

  goToPage(pageNum: number) {
    this.currentPage = pageNum;
    this.getMovies();
  }

  goToDetails(movie: any) {
    localStorage.setItem("currentMovieDetails", JSON.stringify(movie));
    this.router.navigate(["/movie-details"]);
  }

  createRange(num: number) {
    return new Array(num);
  }

  resetFilters() {
    this.currentPage = 0;
    this.titleFilter = "";
    this.selectedCategory  = 0;
    this.year  = null;
    this.sort = "desc";

    this.getMovies();
  }

  gotoTickets(movie: any) {
    localStorage.setItem("currentMovieDetails", JSON.stringify(movie));
    this.router.navigate(["/tickets"]);
  }

}