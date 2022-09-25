package fi.haagahelia.bookstore.web;

import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository crepository;

    @RequestMapping(value = "/categorylist")
    public String categoryList(Model model) {
        model.addAttribute("categories", crepository.findAll());
        return "categoryList";
    }

    @RequestMapping(value = "/addcategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    @RequestMapping(value = "/savecategory", method = RequestMethod.POST)
    public String save(Category category) {
        crepository.save(category);
        return "redirect:categorylist";
    }

    @RequestMapping(value = "/deletecategory/{id}", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable("id") Long categoryId, Model model) {
        crepository.deleteById(categoryId);
        return "redirect:../categorylist";
    }

    @RequestMapping(value = "/editcategory", method = RequestMethod.GET)
    public String editCategory(@RequestParam(name="id", required = false) Long id, Model model) {
        if (crepository.findById(id).isPresent()) {
            model.addAttribute("category", crepository.findById(id));
            return "editcategory";
        }
        return "redirect:../categorylist";
    }
}
