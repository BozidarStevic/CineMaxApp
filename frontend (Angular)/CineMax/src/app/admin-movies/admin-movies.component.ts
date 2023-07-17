import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryService } from '../_services/category.service';
import { MovieService } from '../_services/movie.service';

@Component({
  selector: 'app-admin-movies',
  templateUrl: './admin-movies.component.html',
  styleUrls: ['./admin-movies.component.css']
})
export class AdminMoviesComponent implements OnInit {

  public sort: string;
  public currentPage: number;
  public titleFilter: string;
  public selectedCategory: number = 0;
  public year: number | null = null;
  public response: any;
  public movies: any;
  public pageCount: number;
  public categories: any;
  public showAddModal: boolean = false;
  public movieForUpdate: any;
  public moviesForSelect: any;
  public showUpdateCategoryFlag: any;
  public categoryForUpdate: any;
  public newCategory: string = "";

  constructor(
    public movieService: MovieService,
    public categoryService: CategoryService,
    private router: Router
    ) {
    this.sort = "desc"
    this.currentPage = 0;
    this.titleFilter = "";
    this.pageCount = 0;
    this.movieForUpdate = {
      title: "",
      photo: "",
      description: "",
      duration: 0,
      director: "",
      releaseDate: "",
      category: {
        id: 0
      }
    }
   }

  ngOnInit(): void {
    this.getMovies();
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

  getMovies() {
    this.movieService
      .getMovies(this.currentPage, this.sort, this.titleFilter, this.selectedCategory, this.year)
      .subscribe(resp => {
        this.response = resp;
        this.movies = this.response.content;
        this.pageCount = this.response.totalPages;
      });
  }

  loadCategoriesForSelect() {
    this.categoryService.getCategories().subscribe(resp => {
       this.categories = resp;
    });
  }

  showAddMovie() {
    this.loadCategoriesForSelect();
    this.showAddModal = true;
    this.movieForUpdate = { category: { id: 0 } };
  }

  showUpdateMovie(movie: any) {
    this.showAddModal = false;
    this.movieForUpdate = JSON.parse(JSON.stringify(movie));
    console.log(this.movieForUpdate.category.id);
    this.loadCategoriesForSelect();
  }

  resetMovieForUpdate() {
    this.movieForUpdate = {
      title: "",
      photo: "",
      description: "",
      duration: 0,
      director: "",
      releaseDate: "",
      category: {
        id: 0
      }
    }
    console.log(this.movieForUpdate.category.id);
  }

  resetAverageRate(movieId: number) {
    this.movieService.resetAverageMovieRate(movieId).subscribe(resp => {
      this.getMovies();
    });
  }

  loadMoviesForSelect() {
    this.movieService.getAllMovies().subscribe(resp => {
      this.moviesForSelect = resp;
    });
  }

  deleteMovie(movieId: number) {
    this.movieService.deleteMovie(movieId).subscribe(resp => {
      this.getMovies();
    });
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

  createRange(num: number) {
    return new Array(num);
  }

  goToPage(pageNum: number) {
    this.currentPage = pageNum;
    this.getMovies();
  }

  addMovieComplete(): boolean {
    if (this.movieForUpdate.title != "" &&
      this.movieForUpdate.photo != "" &&
      this.movieForUpdate.description != "" &&
      this.movieForUpdate.duration > 0 &&
      this.movieForUpdate.director != "" &&
      this.movieForUpdate.releaseDate != "" &&
      this.movieForUpdate.category.id > 0) {
      return true;
    } else {
      return false;
    }
  }

  updateMovie() {
    this.movieService.updateMovie(this.movieForUpdate).subscribe(resp => {
      this.getMovies();
    });
  }

  addMovie() {
    this.movieService.addMovie(this.movieForUpdate).subscribe(resp => {
      this.getMovies();
    });
  }

  filterMovies() {
    this.currentPage = 0;
    this.getMovies();
  }

  resetFilters() {
    this.currentPage = 0;
    this.titleFilter = "";
    this.selectedCategory  = 0;
    this.year  = null;
    this.sort = "desc";

    this.getMovies();
  }

  showUpdateCategory(category: any) {
    this.showUpdateCategoryFlag = category.id;
    this.categoryForUpdate = JSON.parse(JSON.stringify(category));
  }

  deleteCategory(categoryId: number) {
    this.categoryService.deleteCategory(categoryId).subscribe(resp => {
      this.loadCategoriesForSelect();
    });
  }

  closeUpdateCategory() {
    this.showUpdateCategoryFlag = 0;
  }

  updateCategory() {
    this.categoryService.updateCategory(this.categoryForUpdate).subscribe(resp => {
      this.showUpdateCategoryFlag = 0;
      this.loadCategoriesForSelect();
    });
  }

  addCategory() {
    this.categoryService.addCategory(this.newCategory).subscribe(resp => {
      this.loadCategoriesForSelect();
    });
  }

  gotoDetails(movie: any) {
    localStorage.setItem("currentMovieDetails", JSON.stringify(movie));
    this.router.navigate(["/movie-details"]);
  }

}
