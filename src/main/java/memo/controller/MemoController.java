package memo.controller;

import memo.domain.Memo;
import memo.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemoController {

    MemoService memoService;

    @Autowired
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping("/")
    public String startPage(Model model){
        model.addAttribute("memos",memoService.findMemos());
        return "home";
    }

    @GetMapping("/new")
    public String newMemo(){
        return "newMemo";
    }

    @PostMapping("/new")
    public String insertMemo(MemoForm memoForm){
        Memo memo = new Memo();
        memo.setTitle(memoForm.getTitle());
        memo.setContents(memoForm.getContents());
        memoService.insert(memo);
        return "redirect:/";
    }

    @GetMapping("/view")
    public String viewMemo(@RequestParam("id") Long id, Model model){
        model.addAttribute("memo",memoService.findMemo(id).get());
        return "view";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id){
        memoService.delete(id);
        return "redirect:/";
    }
}
