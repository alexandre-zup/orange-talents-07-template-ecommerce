package br.com.zupacademy.mercadolivre.api.dto.request;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class NovasImagensRequest {

    private @NotEmpty List<MultipartFile> imagens;

    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public @NotEmpty List<MultipartFile> getImagens() {
        return imagens;
    }
}
