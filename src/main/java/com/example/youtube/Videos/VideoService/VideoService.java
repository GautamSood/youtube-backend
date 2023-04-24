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
import org.springframework.http.HttpStatus;
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
                    .video_url(TEMP_URL)
                    .ageRestrict(false)
                    .Baned(false)
                    .build();
            List<Videos> lt = user.getVideos();
            lt.add(vid);
            user.setVideos(lt);
            userRepository.save(user);
        }catch (Exception e){
            throw new Exception("not able to make video object");
        }
        return ResponseEntity.ok("Successfully Uploaded !"+ TEMP_URL);

    }

//    vid upload service
//    *******************************************************************************************************************


    @Transactional
    public ResponseEntity<String> deletevid(long vidId) throws Exception {
        if(videosRepository.findById(vidId).isPresent()){
            var vid = videosRepository.findById(vidId).orElseThrow(() -> new UsernameNotFoundException("not found"));
            String fileUrl = vid.getVideo_url();
            String bucketName = "metube-2df26.appspot.com";
            String fileNameWithQuery = fileUrl.substring(fileUrl.lastIndexOf("/") + 1); // 2dc79b56-70b3-456a-9ffe-cd2abcc242cd.mp4?alt=media
            String fileName = fileNameWithQuery.substring(0, fileNameWithQuery.lastIndexOf("?")); // 2dc79b56-70b3-456a-9ffe-cd2abcc242cd.mp4
            BlobId blobId = BlobId.of(bucketName, fileName);
            Storage storage = StorageOptions.newBuilder().setCredentials(GoogleCredentials.fromStream(new FileInputStream("C:/Users/soodg/Downloads/metube-2df26-firebase-adminsdk-1fx5p-2d969a9004.json"))).build().getService();
            boolean deleted = storage.delete(blobId);
            if (deleted) {
                System.out.println("File deleted successfully");
                videosRepository.deleteById(vidId);
                return ResponseEntity.ok("vid deleted");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("problem in deleting the vid");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("vid already deleted");
    }

    public String agerestrict(long vidId) {
        var vid = videosRepository.findById(vidId).orElseThrow(() -> new UsernameNotFoundException("vid not found"));
        vid.setAgeRestrict(true);
        videosRepository.save(vid);
        return "done";
    }


    public String banned(long vidId) {
        var vid = videosRepository.findById(vidId).orElseThrow(() -> new UsernameNotFoundException("vid not found"));
        vid.setBaned(true);
        videosRepository.save(vid);
        return "done";
    }

    public String removeageristrict(long vidId) {
        var vid = videosRepository.findById(vidId).orElseThrow(() -> new UsernameNotFoundException("vid not found"));
        vid.setBaned(false);
        videosRepository.save(vid);
        return "done";
    }

    public String removedBanned(long vidId) {
        var vid = videosRepository.findById(vidId).orElseThrow(() -> new UsernameNotFoundException("vid not found"));
        vid.setAgeRestrict(false);
        videosRepository.save(vid);
        return "done";
    }
}
