import { Component, OnInit } from '@angular/core';
import * as bootstrap from 'bootstrap';
import { OwlOptions } from 'ngx-owl-carousel-o';
import { Router } from '@angular/router';
import { MovieService } from '../_services/movie.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public topRatedMovies: any;
  public newMovies: any;

  constructor(
    private movieService: MovieService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.init_carousel();
    this.getTopRatedMovies();
    this.getNewMovies();
  }

  init_carousel() {
    let myCarousel = document.querySelector('#' + 'carouselExample')
    let carousel = new bootstrap.Carousel(myCarousel || '')
    carousel.cycle()
  }

  getTopRatedMovies() {
    this.movieService.getTopRatedMovies().subscribe(resp => {
      this.topRatedMovies = resp;
    });
  }

  getNewMovies() {
    this.movieService.getNewMovies().subscribe(resp => {
      this.newMovies = resp;
    });
  }

  gotoDetails(movie: any) {
    localStorage.setItem("currentMovieDetails", JSON.stringify(movie));
    this.router.navigate(["/movie-details"]);
  }

  customOptions: OwlOptions = {
    //margin: 10,
    center: false,
    loop: true,
    mouseDrag: true,
    touchDrag: false,
    pullDrag: false,
    dots: false,
    autoplay: true,
    autoplayHoverPause: true,
    autoplayMouseleaveTimeout: 1100,
    autoplaySpeed: 900,
    navSpeed: 700,
    navText: ['<-', '->'],
    responsive: {
      0: {
        items: 2
      },
      400: {
        items: 3
      },
      740: {
        items: 4
      },
      940: {
        items: 5
      }
    },
    nav: true
  }

}
