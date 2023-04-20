package com.example.youtube.Videos.VideoController;

import com.example.youtube.Subscriber.subscriberRepository;
import com.example.youtube.Videos.VideoService.VideoService;
import com.example.youtube.Videos.Videos;
import com.example.youtube.Videos.VideosRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

@RestController
@RequestMapping("/api/v1/vids")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;
    private final VideosRepository videosRepository;
//    @PostMapping("/uploadvid/{userId}")
//    public ResponseEntity<String> upload(
////            @RequestParam(value = "image" , required=true) MultipartFile multipartFile,
//            @PathVariable(value = "userId") long id,
//            @RequestParam(value = "title" , required=true) String title,
//            @RequestParam(value = "des" , required=true) String des
//    ) throws IOException {
//        System.out.println("vid uploaded");
//        return ResponseEntity.ok(videoService.upload(id,title,des));
////        return vidService.upload(multipartFile,id,title,des);
//    }

    @PostMapping("/uploadvid/{userId}")
    public ResponseEntity<String> Upload(
            @RequestParam(value = "image" , required=true) MultipartFile multipartFile,
            @PathVariable(value = "userId") long id,
            @RequestParam(value = "title" , required=true) String title,
            @RequestParam(value = "des" , required=true) String des
    ) throws Exception {
        System.out.println("vid uploaded");
        return videoService.upload(multipartFile,id,title,des);
    }

    @DeleteMapping("/deletevid/{vidId}")
    public ResponseEntity<String> deleteVid(
            @PathVariable(value = "vidId") long vidId
    ){
        return ResponseEntity.ok(videoService.deletevid(vidId));
    }

    @GetMapping(value = "/stream/{videoId}")
    public void streamVideo(@PathVariable("videoId") Long videoId, HttpServletResponse response) throws IOException {

        try {

            var video = videosRepository.findById(videoId);
            String vidUrl = video.get().getVideo_url();

            URL url = new URL(vidUrl);
            URLConnection connection = url.openConnection();
            InputStream stream = connection.getInputStream();


            // set the response headers
            response.setContentType("video/mp4");
            response.setHeader("Content-Disposition", "inline; filename=" +video.get().getTitle() +".mp4");


            OutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[256];
            int bytesRead;
            int count = 0;
            while ((bytesRead = stream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                count++;
                System.out.println("Sent chunk of size " + bytesRead + " bytes " + count);
            }
            outputStream.flush();
            stream.close();
        } catch (Exception e) {
            throw new IOException("could not stream the vid due to internal server error");
        }
    }

}
