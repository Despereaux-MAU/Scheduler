package com.despereaus.scheduler.service;

import com.despereaus.scheduler.dto.ScheduleRequestDto;
import com.despereaus.scheduler.dto.ScheduleResponseDto;
import com.despereaus.scheduler.entity.Schedule;
import com.despereaus.scheduler.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule(scheduleRequestDto.getUsername(), scheduleRequestDto.getTitle(),
                scheduleRequestDto.getContents(), scheduleRequestDto.getPassword(), LocalDateTime.now(), LocalDateTime.now());
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule);
    }

    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = scheduleRepository.findById(id);
        if (!schedule.getPassword().equals(scheduleRequestDto.getPassword())) {
            throw new IllegalArgumentException("Invalid password.");
        }
        schedule.setTitle(scheduleRequestDto.getTitle());
        schedule.setContents(scheduleRequestDto.getContents());
        schedule.setUpdatedAt(LocalDateTime.now());
        scheduleRepository.update(schedule);
        return new ScheduleResponseDto(schedule);
    }

    public void deleteSchedule(Long id, String password) {
        int result = scheduleRepository.deleteById(id, password);
        if (result == 0) {
            throw new IllegalArgumentException("Invalid id or password.");
        }
    }
}