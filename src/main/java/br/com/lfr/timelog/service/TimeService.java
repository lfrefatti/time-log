package br.com.lfr.timelog.service;

import br.com.lfr.timelog.domain.Time;

import java.util.List;

public interface TimeService {

    Time insertTime(Time time, String userId);

    List<Time> findProjectTimes(String projectId);

    void updateTime(Time time, String userId, String timeId);

    Time findTimeById(String id);

}
