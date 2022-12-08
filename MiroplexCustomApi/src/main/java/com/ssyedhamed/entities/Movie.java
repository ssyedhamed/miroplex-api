package com.ssyedhamed.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long _id;
	private String title;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "genre")
	private Genre genre;
	private int numberInStock;
	private double dailyRentalRate;
	  @JsonFormat(pattern="dd-MM-yyyy HH:mm")
	  @Column(name = "publish_date", nullable = false, updatable = false)
	  @CreationTimestamp
	private LocalDateTime publishDate;
	private boolean liked;
	public long get_id() {
		return _id;
	}
	public void set_id(long id) {
		this._id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public int getNumberInStock() {
		return numberInStock;
	}
	public void setNumberInStock(int numberInStock) {
		this.numberInStock = numberInStock;
	}
	public double getDailyRentalRate() {
		return dailyRentalRate;
	}
	public void setDailyRentalRate(double dailyRentalRate) {
		this.dailyRentalRate = dailyRentalRate;
	}
	public LocalDateTime getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(LocalDateTime date) {
		this.publishDate = date;
	}
	public boolean isLiked() {
		return liked;
	}
	public void setLiked(boolean liked) {
		this.liked = liked;
	}
	@Override
	public String toString() {
		return "Movies [id=" + _id + ", title=" + title + ", genre=" + genre + ", numberInStock=" + numberInStock
				+ ", dailyRentalRate=" + dailyRentalRate + ", date=" + publishDate + ", liked=" + liked + "]";
	}
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Movie(long id,String title, Genre genre, int numberInStock, double dailyRentalRate) {
		super();
		this._id=id;
		this.title = title;
		this.genre = genre;
		this.publishDate=LocalDateTime.now();
		this.numberInStock = numberInStock;
		this.dailyRentalRate = dailyRentalRate;
		
		this.liked = false;
	}
	
}
