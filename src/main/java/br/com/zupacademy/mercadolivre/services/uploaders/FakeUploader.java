package br.com.zupacademy.mercadolivre.services.uploaders;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FakeUploader implements Uploader {
    @Override
    public List<String> envia(List<MultipartFile> imagens) {
        return imagens.stream().map(
                x -> "https://my-storage.com/alexandre-zup/"+ x.getOriginalFilename()
        ).collect(Collectors.toList());
    }
}
