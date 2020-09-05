package com.jeff.movieviewer.database.room.converter;

import androidx.room.TypeConverter;

import com.jeff.movieviewer.database.local.Photo;
import com.jeff.movieviewer.utilities.ConverterUtil;

public class PhotoConverter {
    private PhotoConverter() { }

    @TypeConverter
    public static String fromPhoto(Photo photo) {
        return ConverterUtil.serialise(photo);
    }

    @TypeConverter
    public static Photo toPhoto(String serialised) {
        return ConverterUtil.deserialise(serialised, Photo.class);
    }
}
