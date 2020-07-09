package com.cogent.insurance.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/branch-managers")
public class BranchManagerController {

    @GetMapping
    public String getBranchManager(){
        return "GET";
    }

    @PostMapping
    public String createBranchManager(){
        return "POST";
    }

    @PutMapping
    public String updateBranchManager(){
        return "PUT";
    }

    @DeleteMapping
    public String deleteBranchManager(){
        return "DELETE";
    }
}
