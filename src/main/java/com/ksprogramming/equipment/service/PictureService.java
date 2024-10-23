package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.PictureData;
import com.ksprogramming.equipment.entities.Picture;
import com.ksprogramming.equipment.mapper.PictureMapper;
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
        pictureData.setCreateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return PictureMapper.entityToData(pictureRepository.save(PictureMapper.dataToEntity(pictureData)));
    }
    public PictureData updatePicture(PictureData pictureData) {
        pictureData.setUpdateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return PictureMapper.entityToData(pictureRepository.save(PictureMapper.dataToEntity(pictureData)));
    }
}
