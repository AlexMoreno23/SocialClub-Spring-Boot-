package by.morunov.controller;

import by.morunov.domain.dto.NewsDto;
import by.morunov.domain.entity.User;
import by.morunov.service.impl.NewsServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alex Morunov
 */

@RestController
@RequestMapping("/api/news")
@AllArgsConstructor
public class NewsController {

    private final NewsServiceImpl newsService;
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsController.class);

    @GetMapping
    public ResponseEntity<List<NewsDto>> getAllNews() {
        List<NewsDto> allNews = newsService.getAll();
        return new ResponseEntity<>(allNews, HttpStatus.OK);
    }


    @GetMapping("/search")
    @ResponseBody
    public List<NewsDto> getAllNewsByAuthorOrTitle(@RequestParam (value = "author", required = false) String authorFirstName,
                                                   @RequestParam (value = "title",  required = false) String title){
        return newsService.getByAuthorOrTitle(authorFirstName, title);
    }


    @PostMapping
    public ResponseEntity<NewsDto> addNews(@AuthenticationPrincipal User user,
                                           @RequestBody NewsDto newsDto) {
        User author = new User();
        author.setId(user.getId());
        author.setFirstName(user.getFirstName());
        author.setLastName(user.getLastName());
        author.setRoles(user.getRoles());
        newsDto.setAuthor(author);
        newsService.addNews(newsDto);
        LOGGER.info("Add news");
        return new ResponseEntity<>(newsDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NewsDto> deleteNews(@PathVariable("id") Long id) {
        newsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
