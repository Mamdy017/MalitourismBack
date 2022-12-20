package com.malitourism.api.img;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class image {
    public static void saveimg(String uploDirPays, String NomDrapeau, MultipartFile multipartFile) throws IOException {

        Path UploadPath = Paths.get(uploDirPays);

        if(!Files.exists(UploadPath)) {
            Files.createDirectories(UploadPath);
        }
        try(InputStream inputStream = multipartFile.getInputStream()){
            Path fichierPath = UploadPath.resolve(NomDrapeau);

            Files.copy(inputStream, fichierPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe){
            throw new IOException("Impossible d'enregistrer le fichier image:" + NomDrapeau, ioe);
        }
    }
    public static void saveimgR(String uploRegion, String PhotoRegion, MultipartFile multipartFile) throws IOException {

        Path UploadPath = Paths.get(uploRegion);

        if(!Files.exists(UploadPath)) {
            Files.createDirectories(UploadPath);
        }
        try(InputStream inputStream = multipartFile.getInputStream()){
            Path fichierPath = UploadPath.resolve(PhotoRegion);

            Files.copy(inputStream, fichierPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe){
            throw new IOException("Impossible d'enregistrer le fichier image:" + PhotoRegion, ioe);
        }
    }


}