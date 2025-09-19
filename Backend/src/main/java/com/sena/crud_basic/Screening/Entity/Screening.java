package com.sena.crud_basic.Screening.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.sena.crud_basic.Movie.Entity.Movie;
import com.sena.crud_basic.Room.Entity.Room;

@Entity(name = "screening")
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idScreening")
    private int idScreening;

    @ManyToOne
    @JoinColumn(name = "idMovie", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "idRoom", nullable = false)
    private Room room;

    @Column(name = "dateTime", nullable = false)
    private LocalDate dateTime;

    @Column(name = "status", nullable = false, columnDefinition = "boolean default true ")
    private boolean status;

    public Screening() {}

    public Screening(int idScreening, Movie movie, Room room, LocalDate dateTime, boolean status) {
        this.idScreening = idScreening;
        this.movie = movie;
        this.room = room;
        this.dateTime = dateTime;
        this.status = status;
    }

    public int getIdScreening() { return idScreening; }
    public void setIdScreening(int idScreening) { this.idScreening = idScreening; }
    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
    public LocalDate getDateTime() { return dateTime; }
    public void setDateTime(LocalDate dateTime) { this.dateTime = dateTime; }
    public boolean getStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
}