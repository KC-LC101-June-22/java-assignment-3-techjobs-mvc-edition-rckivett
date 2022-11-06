package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


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
    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<Job> jobs;
        if (searchTerm.equals("") || searchTerm.toLowerCase().equals("all")) {
            jobs = JobData.findAll();
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("search", searchType);
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);
        return "search";
//        if user enters all or if the box is empty, call the findAll() method from JobData
//    otherwise, send the search information to findByColumnAndValue
//    in either case, store the results in a jobs ArrayList
//    pass jobs into the search.html view via the model parameter
//    pass ListController.columnChoices into the view, as the existing search handler does
    }
}
