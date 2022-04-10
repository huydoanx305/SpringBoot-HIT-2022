package com.hit.buoi6;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@RestController
@RequestMapping("/hit")
public class LinkController {
    @Autowired
    private LinkRepository linkRepository;

    @GetMapping
    private ResponseEntity<?> findAllLink() {
        return ResponseEntity.ok().body(linkRepository.findAll());
    }

    @GetMapping({"/{shortedLink}"})
    private ModelAndView getByUrl(@PathVariable String shortedLink) {
        return new ModelAndView("redirect:" +  linkRepository.findByShortedLink(shortedLink).getOriginalLink());
    }

    @PostMapping
    private ResponseEntity<?> createLink(@RequestBody LinkDTO linkDTO) {
        Link link = new Link();
        link.setOriginalLink(linkDTO.getOriginalLink());
        link.setShortedLink(NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, NanoIdUtils.DEFAULT_ALPHABET, 9));
        return ResponseEntity.ok().body(linkRepository.save(link));
    }
}
