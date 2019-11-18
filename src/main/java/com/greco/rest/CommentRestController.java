package com.greco.rest;

import com.greco.exception.BadRequestException;
import com.greco.messages.GenericCheckingMessage;
import com.greco.model.Comment;
import com.greco.model.Users;
import com.greco.model.projection.IProjectable;
import com.greco.model.projection.Projection;
import com.greco.service.AuthenticationService;
import com.greco.service.CommentService;
import com.greco.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment")
public class CommentRestController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public IProjectable insert(@RequestBody Comment comment) {
       Users loggedInUser = authenticationService.getLoggedUser();
       comment.setOwner(loggedInUser);
       comment.setCreationDate(Utils.getTimestamp());
       if(Utils.isEmpty(comment.getText().trim()))
           throw new BadRequestException(GenericCheckingMessage.COMMENT_EMPTY_TEXT.toString());
       return Projection.convertSingle(commentService.insert(comment), "comment");
    }

    @GetMapping("/{id}")
    public IProjectable byId(@PathVariable("id") Long id) {
       return Projection.convertSingle(commentService.findById(id), "comment");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        commentService.deleteById(id);
    }

}
