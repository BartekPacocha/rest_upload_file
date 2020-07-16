package pl.bpacocha.rest.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        fileService.uploadFile(file);

        redirectAttributes.addFlashAttribute("message",
            "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getById(@PathVariable String id) {
        return fileService.getById(id).toString();
    }

    @GetMapping("/all")
    @ResponseBody
    public List<DataModel> showAll(Model model) {
        return fileService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteById(@PathVariable String id) {
        fileService.deleteDataModelById(id);
    }

    @RequestMapping("/date")
    @ResponseBody
    public List<DataModel> showByDate(@RequestParam("from") String from, @RequestParam("to") String to) {
        return fileService.findBetweenDates(from, to);
    }

}
