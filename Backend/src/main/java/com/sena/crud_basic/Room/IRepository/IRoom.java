package com.sena.crud_basic.Room.IRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.sena.crud_basic.Room.Entity.Room;

public interface IRoom extends JpaRepository<Room, Integer> {
    @Query("SELECT u FROM room u WHERE u.status != false")
    List<Room> getActive();

    @Query("SELECT f FROM room f WHERE f.status != false AND (CAST(f.roomNumber AS string) LIKE %?1% OR CAST(f.capacity AS string) LIKE %?1%)")
    List<Room> searchByRoomNumberOrCapacity(String filter);
}