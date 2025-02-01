package com.gardenia.viveroapp.Converters;

import org.springframework.stereotype.Component;

import com.gardenia.viveroapp.DTO.ImageDTO;
import com.gardenia.viveroapp.Model.Image;

@Component
public class ImageFactory implements AbstractFactory<Image, ImageDTO> {

    @Override
    public ImageDTO toDTO(Image entity) {
        if (entity == null) {
            return null;
        } else {
            ImageDTO dto = ImageDTO.builder()
                    .idimage(entity.getIdimage())
                    .image_url(entity.getImage_url())
                    .build();
            return dto;
        }
    }

    @Override
    public Image DTOToEntity(ImageDTO dto) {
        if (dto == null) {
            return null;
        } else {
            Image entity = new Image();
            entity.setIdimage(dto.getIdimage());
            entity.setImage_url(dto.getImage_url());
            return entity;
        }
    }

}
