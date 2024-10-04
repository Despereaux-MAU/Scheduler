package com.despereaus.scheduler.repository;

import com.despereaus.scheduler.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 일정 생성
    public int save(Schedule schedule) {
        String sql = "INSERT INTO schedule (username, title, contents, password, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, schedule.getUsername(), schedule.getTitle(), schedule.getContents(),
                schedule.getPassword(), schedule.getCreatedAt(), schedule.getUpdatedAt());
    }

    // 모든 일정 조회
    public List<Schedule> findAll() {
        String sql = "SELECT * FROM schedule";
        return jdbcTemplate.query(sql, new ScheduleRowMapper());
    }

    // 특정 일정 조회
    public Schedule findById(Long id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ScheduleRowMapper(), id);
    }

    // 일정 수정
    public int update(Schedule schedule) {
        String sql = "UPDATE schedule SET title = ?, contents = ?, updated_at = ? WHERE id = ? AND password = ?";
        return jdbcTemplate.update(sql, schedule.getTitle(), schedule.getContents(), schedule.getUpdatedAt(),
                schedule.getId(), schedule.getPassword());
    }

    // 일정 삭제
    public int deleteById(Long id, String password) {
        String sql = "DELETE FROM schedule WHERE id = ? AND password = ?";
        return jdbcTemplate.update(sql, id, password);
    }

    // RowMapper
    private static class ScheduleRowMapper implements RowMapper<Schedule> {
        @Override
        public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
            Schedule schedule = new Schedule();
            schedule.setId(rs.getLong("id"));
            schedule.setUsername(rs.getString("username"));
            schedule.setTitle(rs.getString("title"));
            schedule.setContents(rs.getString("contents"));
            schedule.setPassword(rs.getString("password"));
            schedule.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            schedule.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return schedule;
        }
    }
}