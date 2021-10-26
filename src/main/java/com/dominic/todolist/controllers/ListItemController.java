package com.dominic.todolist.controllers;

import com.dominic.todolist.models.ListItem;
import com.dominic.todolist.repositories.ListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ListItemController {
    private final ListItemRepository listItemRepository;

    @Autowired
    public ListItemController(ListItemRepository listItemRepository) {
        this.listItemRepository = listItemRepository;
    }

    @GetMapping({"/", "/todo"})
    public String getListView(Model model) {
        model.addAttribute("listItems", listItemRepository.retrieveAllByDateCreated());
        return "todo_list";
    }

    @GetMapping("/todo/create")
    public String getListCreationView(Model model) {
        model.addAttribute("listItem", new ListItem());
        System.out.println("Before: " + model.getAttribute("listItem"));
        return "todo_create";
    }

    @GetMapping("/todo/update/{id}")
    public String getListUpdateView(
            Model model,
            @PathVariable Long id
    ) {
        System.out.println(listItemRepository.retrieveById(id));
        model.addAttribute("listItem", listItemRepository.retrieveById(id));
        return "todo_update";
    }

    @PostMapping("/todo/create")
    public String insertListItem(
            ListItem listItem,
            Model model
    ) {
        System.out.println("After: " + model.getAttribute("listItem"));
        listItemRepository.createListItem(listItem);
        return getListView(model);
    }

    @PostMapping("/todo/update")
    public String updateListItem(
            Model model,
            ListItem item
    ) {
        listItemRepository.updateListItem(item);
        return getListView(model);
    }

    @GetMapping("/todo/delete/{id}")
    public String deleteListItem(
            Model model,
            @PathVariable Long id
    ) {
        listItemRepository.deleteListItem(id);
        return getListView(model);
    }
}













































