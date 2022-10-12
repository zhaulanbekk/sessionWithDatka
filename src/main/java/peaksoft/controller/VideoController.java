package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Video;
import peaksoft.service.impl.VideoServiceImpl;

@Controller
@RequestMapping("/videos")
public class VideoController {


    private final VideoServiceImpl videoService;

    @Autowired
    public VideoController(VideoServiceImpl videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/{id}")
    public String allVideo(Model model, @PathVariable Long id){
        model.addAttribute("videos", videoService.getAllVideos(id));
        model.addAttribute("lessonId", id);
        return "/video/allVideo";
    }
    @GetMapping("/newVideo/{id}")
    public String addVideo(Model model, @PathVariable Long id){
        model.addAttribute("video", new Video());
        model.addAttribute("lessonId",id);
        return "/video/newVideo";
    }
    @PostMapping("/{id}/saveVideo")
    public String saveVideo(@ModelAttribute("video") Video video,
                           @PathVariable("id") Long id){
       videoService.addVideo(id,video);
        return "redirect:/videos/"+id;
    }

    @GetMapping("/updateVideo/{id}/{lessonId}")
    public String updateVideo(Model model,
                             @PathVariable Long id,
                             @PathVariable("lessonId") Long lessonId) {
        model.addAttribute("video",videoService.getVideoById(id));
        return "/video/update";
    }

    @PostMapping("/{id}/{lessonId}/editVideo")
    public String save(@PathVariable("id") Long id,
                       @PathVariable("lessonId") Long lessonId,
                       @ModelAttribute Video video) {
        videoService.addVideo(id, video);
        return "redirect:/videos/"+lessonId;
    }

    @PostMapping("{id}/{lessonId}/deleteVideo")
    public String deleteLesson(@PathVariable Long id,
                               @PathVariable("lessonId") Long id2) {
       videoService.deleteVideo(id);
        return "redirect:/videos/"+id2;
    }
}
