package com.springboot.hotelmanagement.service;


import com.springboot.hotelmanagement.dto.RoomDto;

import com.springboot.hotelmanagement.entity.Room;
import com.springboot.hotelmanagement.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class RoomServiceImpl implements RoomService{

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    @Transactional
    public Room findById(int theId) {
        Optional<Room> result= roomRepository.findById(theId);
        Room theRoom= null;
        if(result.isPresent()){
            theRoom=result.get();
        }else{
            throw new RuntimeException("The room with given id"+ theId+ "is not present in the database");
        }
        return theRoom;
    }

    @Override
    @Transactional
    public void save(Room theRoom) {
        roomRepository.save(theRoom);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        roomRepository.deleteById(theId);
    }

    @Override
    @Transactional
    public List<Room> findRooms(int theId) {
        return roomRepository.findRooms(theId);
    }

    private RoomDto convertEntityToDto(Room room){
        RoomDto roomDto=new RoomDto();
        roomDto=modelMapper.map(room, RoomDto.class);
        return roomDto;
    }
}