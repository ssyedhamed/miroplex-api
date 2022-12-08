package com.ssyedhamed.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "genres")
public class Genre {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long _id;
	private String name;
//	@ManyToOne
//	private Movie movie;
	public Genre() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Genre(long id, String name) {
		super();
		this._id = id;
		this.name = name;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long id) {
		this._id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Genre [id=" + _id + ", name=" + name + "]";
	}
	
}
