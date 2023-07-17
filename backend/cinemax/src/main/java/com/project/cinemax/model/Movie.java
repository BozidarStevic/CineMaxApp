package com.project.cinemax.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	private String photo;
	
	private String director;
	
	@Temporal(TemporalType.DATE)
	private Date releaseDate;
	
	private int duration;
	
	private double averageRate;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonIgnore
	private Collection<Projection> projections;
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonIgnore
	private Collection<Rate> rates;
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonIgnore
	private Collection<Comment> comments;
	
	@ManyToOne
	private Category category;

	public Movie() {
		super();
	}

	public Movie(String title, String description, String photo, String director, Date releaseDate, int duration, double averageRate) {
		super();
		this.title = title;
		this.description = description;
		this.photo = photo;
		this.director = director;
		this.releaseDate = releaseDate;
		this.duration = duration;
		this.averageRate = averageRate;
	}
	
	public Movie(String description, String director, int duration, String photo,  Date releaseDate, String title, Category category, double averageRate) {
		super();
		this.title = title;
		this.description = description;
		this.photo = photo;
		this.director = director;
		this.releaseDate = releaseDate;
		this.duration = duration;
		this.averageRate = averageRate;
		this.category = category;
	}

	public Movie(String title, String description, String photo, String director, Date releaseDate, int duration,
			Collection<Projection> projections, Collection<Rate> rates, Collection<Comment> comments, Category category, double averageRate) {
		super();
		this.title = title;
		this.description = description;
		this.photo = photo;
		this.director = director;
		this.releaseDate = releaseDate;
		this.duration = duration;
		this.projections = projections;
		this.category = category;
		this.averageRate = averageRate;
		this.rates = rates;
		this.comments = comments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Collection<Projection> getProjections() {
		return projections;
	}

	public void setProjections(Collection<Projection> projections) {
		this.projections = projections;
	}

	public Collection<Rate> getRates() {
		return rates;
	}

	public void setRates(Collection<Rate> rates) {
		this.rates = rates;
	}

	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(double averageRate) {
		this.averageRate = averageRate;
	}


}
