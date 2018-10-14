package br.com.lfr.timelog.interfaces;

import br.com.lfr.timelog.domain.User;
import br.com.lfr.timelog.domain.UserWrapper;
import br.com.lfr.timelog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService service;

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public @ResponseBody UserWrapper post(@RequestBody User userToCreate){
        return new UserWrapper(service.insertUser(userToCreate));
    }

    @RequestMapping(path = "/{id}", method = GET)
    @ResponseStatus(OK)
    public @ResponseBody UserWrapper get(@PathVariable("id") String id){
        return new UserWrapper(service.findUserById(id));
    }

    @RequestMapping(path = "/{id}", method = PUT)
    @ResponseStatus(NO_CONTENT)
    public @ResponseBody UserWrapper put(@PathVariable("id") String id, @RequestBody User userToUpdate){
        return new UserWrapper(service.updateUser(id, userToUpdate));
    }

}
