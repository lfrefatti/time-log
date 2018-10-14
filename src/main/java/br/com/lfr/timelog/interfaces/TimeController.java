package br.com.lfr.timelog.interfaces;

import br.com.lfr.timelog.domain.Time;
import br.com.lfr.timelog.domain.TimeWrapper;
import br.com.lfr.timelog.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/time")
public class TimeController {

    @Autowired
    TimeService timeService;

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public @ResponseBody TimeWrapper post(@RequestBody Time time, HttpServletRequest request){
        return new TimeWrapper(timeService.insertTime(time, request.getUserPrincipal().getName()));
    }

    @RequestMapping(path = "/{id}", method = GET)
    @ResponseStatus(OK)
    public @ResponseBody TimeWrapper get(@PathVariable String id){
        return new TimeWrapper(timeService.findTimeById(id));
    }

    @RequestMapping(path = "/{id}", method = PUT)
    @ResponseStatus(NO_CONTENT)
    public void put(@RequestBody Time time, @PathVariable String id, HttpServletRequest request){
        timeService.updateTime(time, request.getUserPrincipal().getName(), id);
    }

}
