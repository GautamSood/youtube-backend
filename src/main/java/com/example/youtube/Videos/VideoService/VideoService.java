package com.example.youtube.Videos.VideoService;

import com.example.youtube.Videos.LikeDislike.LikeDislikeRepository;
import com.example.youtube.User.UserRepository;
import com.example.youtube.Videos.Videos;
import com.example.youtube.Videos.VideosRepository;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final UserRepository userRepository;
    private final VideosRepository videosRepository;
    private final LikeDislikeRepository likeDislikeRepository;



    //    vid upload service
//    ***********************************************************************************************************
    private String uploadFile(File file, String fileName) throws IOException {

        BlobId blobId = BlobId.of("metube-2df26.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:/Users/soodg/Downloads/metube-2df26-firebase-adminsdk-1fx5p-2d969a9004.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format("https://firebasestorage.googleapis.com/v0/b/metube-2df26.appspot.com/o/%s?alt=media", URLEncoder.encode(fileName, StandardCharsets.UTF_8));

    }
    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }
    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
    public ResponseEntity<String> upload(MultipartFile multipartFile, Long userId, String title , String des) throws Exception {

        var user = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("not Found"));

        String fileName = multipartFile.getOriginalFilename();
        fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));
        File file = this.convertToFile(multipartFile, fileName);
        String TEMP_URL = this.uploadFile(file, fileName);
        file.delete();
        try{
            var vid = Videos.builder().title(title)
                    .description(des)
                    .video_user_id(user)
                    .video_url(TEMP_URL).build();
            List<Videos> lt = user.getVideos();
            lt.add(vid);
            user.setVideos(lt);
            userRepository.save(user);
        }catch (Exception e){
            throw new Exception("not able to make video object");
        }
        return ResponseEntity.ok("Successfully Uploaded !"+ TEMP_URL);

    }

    @Transactional
    public String deletevid(long vidId) {
        if(videosRepository.findById(vidId).isPresent()){
            videosRepository.deleteById(vidId);
            return "vid deleted";
        }
        return "vid already deleted";
    }

//    vid upload service
//    *******************************************************************************************************************


}
