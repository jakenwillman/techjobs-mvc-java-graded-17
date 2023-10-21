package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    // use @PostMapping annotation to "Post" the requests for specified search "results" onto the server
    // @PostMapping is also specified in the test for this task to pass
    @PostMapping(value = "results")
    // the displaySearchResults method should take in a Model parameter
    // the method should also take in two parameters specifying the searchType and searchTerm names that were used in the form field of search.html
    // use @RequestParam annotations for Spring Boot to pass in both search parameters
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        // created a jobs ArrayList to store the users search results
        ArrayList<Job> jobs;
        // if user enters "all" in the search box or leaves it empty, call findAll() from JobData
        if (searchTerm.toLowerCase().equals("all") || searchTerm.isEmpty()) {
            // store all job results in the jobs ArrayList using findAll()
            jobs = JobData.findAll();
            // otherwise, send the search info to findByColumnAndValue
        } else {
            // store specified results in the jobs ArrayList using findByColumnAndValue()
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        // pass jobs into the search.html view via the model parameter
        model.addAttribute("jobs", jobs);
        // pass ListController.columnChoices into the view via the model add
        model.addAttribute("columns", columnChoices);

        return "search";
    }
}


