package com.ksprogramming.equipment.service;

import com.ksprogramming.equipment.data.PictureData;
import com.ksprogramming.equipment.entities.Picture;

public interface PictureServiceInterface {
    PictureData createPicture(PictureData picture);
    PictureData updatePicture(PictureData picture);
}
