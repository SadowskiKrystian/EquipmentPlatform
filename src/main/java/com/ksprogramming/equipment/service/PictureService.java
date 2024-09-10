package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.PictureData;
import com.ksprogramming.equipment.entities.Picture;
import com.ksprogramming.equipment.repository.PictureRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class PictureService implements PictureServiceInterface {
    private PictureRepository pictureRepository;

    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public PictureData createPicture(PictureData pictureData) {
        return pictureEntityToData(pictureRepository.save(pictureDataToEntity(pictureData)));
    }

    private PictureData pictureEntityToData(Picture picture) {
        return new PictureData(picture.getId(), picture.getName(), picture.getCreateDate());
    }

    private Picture pictureDataToEntity(PictureData pictureData) {
        return new Picture(pictureData.getName(), LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    }
}
