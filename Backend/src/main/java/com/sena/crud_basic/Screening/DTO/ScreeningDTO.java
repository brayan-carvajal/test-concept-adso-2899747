package com.sena.crud_basic.Screening.DTO;

import java.time.LocalDate;
import com.sena.crud_basic.Movie.Entity.Movie;
import com.sena.crud_basic.Room.Entity.Room;

public class ScreeningDTO {
    private int idScreening;
    private Movie movie;
    private Room room;
    private LocalDate dateTime;

    public ScreeningDTO() {}

    public ScreeningDTO(int idScreening, Movie movie, Room room, LocalDate dateTime) {
        this.idScreening = idScreening;
        this.movie = movie;
        this.room = room;
        this.dateTime = dateTime;
    }

    public int getIdScreening() { return idScreening; }
    public void setIdScreening(int idScreening) { this.idScreening = idScreening; }
    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
    public LocalDate getDateTime() { return dateTime; }
    public void setDateTime(LocalDate dateTime) { this.dateTime = dateTime; }
}